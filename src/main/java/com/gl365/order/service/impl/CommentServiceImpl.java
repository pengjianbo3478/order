package com.gl365.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gl365.order.common.DBConstants;
import com.gl365.order.common.DBConstants.GRADE_LEAVE;
import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.common.build.CommentBuild;
import com.gl365.order.common.build.CommentPersonalBuild;
import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.PageResultDto;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.comment.CommentDto;
import com.gl365.order.dto.comment.CommentPersonalDto;
import com.gl365.order.dto.comment.PersonalCommentStatus;
import com.gl365.order.dto.comment.UserCommentDto;
import com.gl365.order.dto.comment.command.GetCommentTempleteCommand;
import com.gl365.order.dto.comment.command.GetMerchantCommentsCommand;
import com.gl365.order.dto.comment.command.GetOperatorScoreCommand;
import com.gl365.order.dto.comment.command.GetOperatorScoreDetailed4MerchantCommand;
import com.gl365.order.dto.comment.command.GetScoreDetailed4MerchantCommand;
import com.gl365.order.dto.comment.command.SaveComment4MemberCommand;
import com.gl365.order.dto.comment.command.SaveCommentPersonal4MemberCommand;
import com.gl365.order.dto.comment.command.UpdateComment4MemberCommand;
import com.gl365.order.dto.req.merchant.GetMerchantDetail4MerchantReq;
import com.gl365.order.dto.req.merchant.UpdateMerchantCommentCountReq;
import com.gl365.order.dto.rsp.merchant.MerchantInfoDtoResult;
import com.gl365.order.dto.rsp.operator.MerchantOperatorDtoResult;
import com.gl365.order.dto.user.User;
import com.gl365.order.dto.user.UserInfoDto;
import com.gl365.order.dto.user.command.GetUserInfoByUserIdCommand;
import com.gl365.order.mapper.CommentLabelsMapper;
import com.gl365.order.mapper.CommentLabelsTemplateMapper;
import com.gl365.order.mapper.CommentMapper;
import com.gl365.order.mapper.CommentPersonalMapper;
import com.gl365.order.mapper.OrderMapper;
import com.gl365.order.model.comment.Comment;
import com.gl365.order.model.comment.CommentGradeLeave;
import com.gl365.order.model.comment.CommentLabels;
import com.gl365.order.model.comment.CommentLabelsTemplate;
import com.gl365.order.model.comment.CommentPersonal;
import com.gl365.order.mq.producer.MerchantCommentGradeProducer;
import com.gl365.order.remote.PayQueryClient;
import com.gl365.order.remote.dto.PayQueryReq;
import com.gl365.order.remote.dto.PayQueryResp;
import com.gl365.order.service.CommentService;
import com.gl365.order.service.repo.MerchantInfoServiceRepo;
import com.gl365.order.service.repo.OperatorInfoServiceRepo;
import com.gl365.order.service.repo.UserServiceRepo;
import com.gl365.order.service.repo.impl.UserServiceRepoImpl;

/**
 * < 商家评论Service >
 * 
 * @author hui.li 2017年4月20日 - 下午3:00:42
 * @Since 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

	private static final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);

	@Autowired
	private UserServiceRepoImpl userServiceRepoImpl;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private CommentLabelsMapper commentLabelsMapper;
	@Autowired
	private CommentPersonalMapper commentPersonalMapper;
	@Autowired
	private MerchantInfoServiceRepo merchantInfoServiceRepo;
	@Autowired
	private OperatorInfoServiceRepo operatorInfoServiceRepo;
	@Autowired
	private CommentLabelsTemplateMapper commentLabelsTemplateMapper;
	@Autowired
	private MerchantCommentGradeProducer merchantCommentGradeProducer;
	@Autowired
	private UserServiceRepo UserServiceRepo;
	@Autowired
	private PayQueryClient payQueryClient;

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public ResultDto<List<CommentLabelsTemplate>> getTemplete(GetCommentTempleteCommand command) {
		LOG.info("获取商家评论模板 > > > 行业ID：{}", command.getIndustry());
		try {
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS, commentLabelsTemplateMapper.getTempleteByIndustry(command.getIndustry()));
		} catch (Exception e) {
			LOG.error("获取商家评论模板 > > > 失败, 错误：{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	@Override
	public PageResultDto<CommentDto> getCommentList(GetMerchantCommentsCommand command) {
		LOG.info("获取商家评论列表 > > > 入参：{}", JsonUtils.toJsonString(command));
		try {
			// 查询评论列表
			PageDto<Comment> page = getCommentBeanPage(command);
			// 构建列表Dto 【已评论】绑定会员信息【未评论】绑定账户信息
			PageDto<CommentDto> result = command.isComment() ? getUserCommentDto(page) : getPaymentCommentDto(page);
			LOG.debug("获取商家评论列表结果 > > > 出参：{}", JsonUtils.toJsonString(result));
			return new PageResultDto<>(ResultCodeEnum.System.SUCCESS, result);
		} catch (Exception e) {
			LOG.error("获取商家评论列表失败 > > > 异常：{}", e);
			return new PageResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	@Override
	public ResultDto<CommentDto> getCommentListForGroupPay(GetMerchantCommentsCommand command) {
		LOG.info("获取商家群买单评论 > > > 入参：{}", JsonUtils.toJsonString(command));
		try {
			String userId = command.getUserId();
			String groupId = command.getGroupId();
			PayQueryReq req = new PayQueryReq();
			req.setGroupOrderId(groupId);
			req.setUserId(userId);
			// 根据userId和群号查交易获取支付订单号
			PayQueryResp payQueryRlt = payQueryClient.getPayInfoByIdAndGroupId(req);
			LOG.info("根据id获取交易详情param={},rlt={}",JsonUtils.toJsonString(req),JsonUtils.toJsonString(payQueryRlt));
			String paymentNo = payQueryRlt.getPayId();
			CommentDto rlt = null;
			if (StringUtils.isNotBlank(paymentNo)) {
				// 查询评论列表
				Comment comment = commentMapper.selectByUidMnoPno(userId, command.getMerchantNo(), paymentNo);
				if (comment != null) {
					List<Comment> data = new ArrayList<>();
					data.add(comment);
					PageDto<Comment> page = new PageDto<>(1, 1, 1, data);
					// 构建列表Dto 【已评论】绑定会员信息【未评论】绑定账户信息
					PageDto<CommentDto> result = command.isComment() ? getUserCommentDto(page) : getPaymentCommentDto(page);
					LOG.debug("获取商家群买单评论结果 > > > 出参：{}", JsonUtils.toJsonString(result));
					rlt = result.getList().get(0);
				}
			}			
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS, rlt);
		} catch (Exception e) {
			LOG.error("获取商家群买单评论失败 > > > 异常：{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	@Override
	public ResultDto<Integer> saveUpdateCommentPersonal(SaveCommentPersonal4MemberCommand command) {
		LOG.info("保存/更新打赏评论》》》入参：{}", JsonUtils.toJsonString(command));
		try {
			
			if(null==command || null==command.getPaymentNo()){
				LOG.warn("保存/更新打赏评论》》》command.getPaymentNo() 为空：{}",JsonUtils.toJsonString(command));
				return new ResultDto<>(ResultCodeEnum.System.PARAM_NULL);
			}
			
			// 原支付订单号查询已存在的订单
			List<CommentPersonal> result = commentPersonalMapper.selectByUidMnoPno(command.getUserId(), command.getPaymentNo());
			if (CollectionUtils.isEmpty(result))
				commentPersonalMapper.insertSelective(CommentPersonalBuild.buildCreateCommentPersonal(command));
			else
				commentPersonalMapper.updateByUidMnoPno(CommentPersonalBuild.buildUpdateCommentPersonal(command));
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		} catch (Exception e) {
			LOG.error("保存/更新打赏评论》》》错误：{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}

	}

	@Override
	public ResultDto<?> saveDefaultComment(SaveComment4MemberCommand command) {
		LOG.info("保存商家初始评论 > > > 入参：{}", JsonUtils.toJsonString(command));
		try {
			// 保存初始评论
			commentMapper.insertSelective(CommentBuild.buildCreatComment(command));
			// 同步商家评论统计信息
			merchantCommentGradeProducer.send(new UpdateMerchantCommentCountReq(command.getMerchantNo(), DBConstants.MAX_GRADE, DBConstants.AUTO_INCREASE_1));
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		} catch (Exception e) {
			LOG.error("保存商家初始评论失败 > > > 异常：{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class }, readOnly = false)
	public ResultDto<?> updateComment(UpdateComment4MemberCommand command) {
		LOG.info("更新商家评论 > > > 入参：{}", JsonUtils.toJsonString(command));
		try {
			// 获取初始评论
			Comment comment = commentMapper.selectByUidMnoPno(command.getUserId(), command.getMerchantNo(), command.getPaymentNo());
			if (null == comment) {
				LOG.error("更新商家评论 > > > 未查询到对应评论!");
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
			}
			// 判断重复评论
			if (DBConstants.REAL_COMMENT.equals(comment.getStatus()))
				return new ResultDto<>(ResultCodeEnum.Merchant.REAL_COMMENT.getCode(),ResultCodeEnum.Merchant.REAL_COMMENT.getMsg(),null);
			// 更新评论信息
			commentMapper.updateByPrimaryKeySelective(CommentBuild.buildUpdateComment(command, comment.getId()));
			// 保存评论标签
			saveCommentLabels(command, comment.getId());
			// 同步商家评论统计信息
			merchantCommentGradeProducer.send(new UpdateMerchantCommentCountReq(command.getMerchantNo(), command.getGrade() - DBConstants.MAX_GRADE, DBConstants.AUTO_INCREASE_0));
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		} catch (Exception e) {
			LOG.error("更新商家评论失败 > > > 错误：{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	@Override
	public ResultDto<?> backRecoverComment(UpdateComment4MemberCommand command) {
		// 获取评论ID
		LOG.info("更新商家评论 > > > 入参：{}", JsonUtils.toJsonString(command));
		try {
			Comment comment = commentMapper.selectByUidMnoPno(command.getUserId(), command.getMerchantNo(), command.getPaymentNo());
			if (null == comment) {
				LOG.info("更新商家评论 > > > 未查询到对应评论!");
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
			}
			Integer addGrade = command.isDelete() ? -comment.getGrade() : comment.getGrade();
			Integer addAccount = command.isDelete() ? -DBConstants.AUTO_INCREASE_1 : DBConstants.AUTO_INCREASE_1;
			// 更新评论信息
			Comment updateBean = new Comment();
			updateBean.setId(comment.getId());
			updateBean.setUserId(command.getUserId());
			updateBean.setMerchantNo(command.getMerchantNo());
			updateBean.setPaymentNo(command.getPaymentNo());
			updateBean.setDelFlag(command.isDelete());
			commentMapper.updateByPrimaryKeySelective(updateBean);
			// 同步商家评论统计信息
			merchantCommentGradeProducer.send(new UpdateMerchantCommentCountReq(command.getMerchantNo(), addGrade, addAccount));
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		} catch (Exception e) {
			LOG.error("更新商家评论 > > > 错误：{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	@Override
	public ResultDto<List<CommentPersonalDto>> getOperatorScore(GetOperatorScoreCommand command) {
		LOG.info("获取用户对员工评价分数》》》入参：{}", JsonUtils.toJsonString(command));
		try {
			List<CommentPersonal> data = commentPersonalMapper.selectOperatorScoreByMerchantNoOperatorId(command.getMerchantNo(), command.getOperatorId());
			if (CollectionUtils.isEmpty(data))
				return new ResultDto<>(ResultCodeEnum.System.SUCCESS, null);
			List<CommentPersonalDto> result = new ArrayList<>();
			for (CommentPersonal bean : data) {
				MerchantOperatorDtoResult operator = null;
//				if (StringUtils.isEmpty(bean.getOperatorId()) || null == (operator = operatorInfoServiceRepo.queryOperator(bean.getOperatorId()).getData()) || OperatorQuitType.LEAVE.getValue().equals(operator.getQuit()))
				if (StringUtils.isEmpty(bean.getOperatorId()) || null == (operator = operatorInfoServiceRepo.queryOperator(bean.getOperatorId()).getData()))
					continue;

				// 员工不存在或离职时,不展示评价分数     modify by xty 20170901  离职的也可看评分
				CommentPersonalDto dto = new CommentPersonalDto();
				BeanUtils.copyProperties(bean, dto);
				dto.setOperatorName(operator.getOperatorName());
				dto.setRoleId(operator.getRoleId());

				// 操作员的会员基本信息
				User user = userServiceRepoImpl.getUserInfoByUserId(new GetUserInfoByUserIdCommand(operator.getUserId()));
				if (null != user) {
					dto.setOperatorImage(user.getPhoto());
				}
				result.add(dto);
			}
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS, result);
		} catch (Exception e) {
			LOG.error("获取用户对员工评价分数》》》错误：{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	@Override
	public ResultDto<Integer> getAllOperatorComment(GetOperatorScoreCommand command) {
		LOG.info("获取所有员工总评价条数》》》入参：{}", JsonUtils.toJsonString(command));
		try {
			Integer data = commentPersonalMapper.selectOperatorCommentCountByMerchantNo(command.getMerchantNo());
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS,data == null ? 0 : data);
		} catch (Exception e) {
			LOG.error("获取所有员工总评价条数》》》错误：{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}
	
	@Override
	public PageResultDto<CommentDto> getScoreDetailed(GetScoreDetailed4MerchantCommand command) {
		LOG.info("获取本店评价明细》》》入参：{}", JsonUtils.toJsonString(command));
		try {
			// 查询评论列表
			PageDto<Comment> page = getCommentBeanPage(command);
			// 构建评论Dto,绑定用户信息
			PageDto<CommentDto> result = getUserCommentDto(page);
			LOG.debug("获取本店评价明细》》》出参：{}", JsonUtils.toJsonString(result));
			return new PageResultDto<>(ResultCodeEnum.System.SUCCESS, result);
		} catch (Exception e) {
			LOG.error("获取本店评价明细 > > > 异常：{}", e);
			return new PageResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	@Override
	public PageResultDto<CommentPersonalDto> getOperatorScoreDetailed(GetOperatorScoreDetailed4MerchantCommand command) {
		LOG.info("获取用户对员工评价明细》》》入参：{}", JsonUtils.toJsonString(command));
		try {
			// 查询评论列表
			PageDto<CommentPersonalDto> page = getCommentPersonalBeanPage(command);
			return new PageResultDto<>(ResultCodeEnum.System.SUCCESS, page);
		} catch (Exception e) {
			LOG.error("获取商家评论列表失败 > > > 异常：{}", e);
			return new PageResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	@Override
	public ResultDto<PageDto<UserCommentDto>> getUserComments(String userId, String curP, String pageS) {
		// 获取评论Page
		int curPage = Integer.parseInt(curP);
		int pageSize = Integer.parseInt(pageS);
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("beginNum", pageSize * (curPage - 1));
		map.put("endNum", pageSize);
		int totalCount = commentMapper.selectByUserIdCount(map);
		List<Comment> list = totalCount > 0 ? commentMapper.selectByUserId(map) : null;
		// 构建评论DTO
		if (CollectionUtils.isEmpty(list))
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS, new PageDto<UserCommentDto>(totalCount, curPage, pageSize, new ArrayList<>())); // 不确定H5非空判断逻辑
		List<UserCommentDto> resultList = new ArrayList<>();
		UserCommentDto commentDto = null;
		User user = userServiceRepoImpl.getUserInfoByUserId(new GetUserInfoByUserIdCommand(userId));
		String nickName = null == user ? null : user.getNickName();
		for (Comment comment : list) {
			resultList.add(commentDto = new UserCommentDto());
			commentDto.setPaymentNo(comment.getPaymentNo());
			commentDto.setMerchantNo(comment.getMerchantNo());
			commentDto.setUserId(userId);
			commentDto.setNickName(nickName);
			commentDto.setCreateTime(comment.getCreateTime());
			commentDto.setGrade(comment.getGrade());
			commentDto.setContent(comment.getContent());
			// 构建商家信息
			ResultDto<List<MerchantInfoDtoResult>> mercahntInfo = merchantInfoServiceRepo.getMerchantDetail(new GetMerchantDetail4MerchantReq(comment.getMerchantNo()));
			if (null == mercahntInfo || null == mercahntInfo.getData() || CollectionUtils.isEmpty(mercahntInfo.getData()))
				continue;
			MerchantInfoDtoResult merchant = mercahntInfo.getData().get(0);
			commentDto.setMerchantAreaName(merchant.getBusAreaName());
			commentDto.setMerchantcategoryName(merchant.getCategoryName());
			commentDto.setMerchantImage(merchant.getMainImage());
			commentDto.setShortName(merchant.getMerchantShortname());
			// 构建评论标签
			List<CommentLabels> labels = commentLabelsMapper.selectByCommentId(comment.getId());
			if (CollectionUtils.isEmpty(labels))
				continue;
			String[] labelNames = new String[labels.size()];
			for (int i = 0; i < labels.size(); i++) {
				labelNames[i] = labels.get(i).getCommentLabelName();
			}
			commentDto.setLabels(labelNames);
		}
		return new ResultDto<>(ResultCodeEnum.System.SUCCESS, new PageDto<UserCommentDto>(totalCount, curPage, pageSize, resultList));
	}

	@Override
	public Comment getCommentInfo(String userId, String merchantNo, String paymentNo) {
		try {
			if (StringUtils.isEmpty(userId) && StringUtils.isEmpty(merchantNo) && StringUtils.isEmpty(paymentNo)) {
				LOG.error("reqParams are null,return null");
				return null;
			}
			//return commentMapper.selectByUidMnoPno(userId, merchantNo, paymentNo);
			return commentMapper.getCommentStatus(userId, merchantNo, paymentNo);
		} catch (Exception e) {
			LOG.error("reqParams are null,return null：exception :{}", e);
			return null;
		}
	}

	@Override
	public List<PersonalCommentStatus> getCommentPersonalStatus(List<String> paymentNos) {
		LOG.info("getCommentStatus begin,paymentNos={}", JsonUtils.toJsonString(paymentNos));
		List<PersonalCommentStatus> rlt = new ArrayList<>();
		if (null == paymentNos) {
			return rlt;
		}
		try {
			paymentNos = orderMapper.queryPayCompleteOrder(paymentNos);
			LOG.info("queryPayCompleteOrder rlt paymentNos={}", JsonUtils.toJsonString(paymentNos));
			if(CollectionUtils.isNotEmpty(paymentNos)){
				rlt = commentPersonalMapper.queryCommentStatus(paymentNos);
			}
		} catch (Exception e) {
			LOG.error("getCommentStatus ===> commentPersonalMapper.queryCommentStatus exception:", e);
		}
		LOG.info("getCommentStatus end,rlt:{}", rlt);
		return rlt;
	}

	private PageDto<CommentPersonalDto> getOperatorCommentDto(PageDto<CommentPersonal> page) {
		if (CollectionUtils.isEmpty(page.getList())) {
			return new PageDto<>(page.getTotalCount(), page.getCurPage(), page.getPageSize(), page.getDataMap(), null);
		}
		List<CommentPersonalDto> result = new ArrayList<>();
		for (CommentPersonal comment : page.getList()) {
			CommentPersonalDto dto = new CommentPersonalDto();
			BeanUtils.copyProperties(comment, dto);
			// 构建操作员信息
			// MerchantOperatorDtoResult operator =
			// operatorInfoServiceRepo.queryOperator(dto.getOperatorId()).getData();
			// if (null != operator) {
			// dto.setOperatorName(operator.getOperatorName());
			// dto.setRoleId(operator.getRoleId());
			// }
			// 构建评分信息-用户名称
			User user = UserServiceRepo.getUserInfoByUserId(new GetUserInfoByUserIdCommand(comment.getUserId()));
			if (null != user)
				dto.setOperatorName(user.getNickName());
			result.add(dto);
		}
		return new PageDto<>(page.getTotalCount(), page.getCurPage(), page.getPageSize(), page.getDataMap(), result);
	}

	private PageDto<CommentDto> getPaymentCommentDto(PageDto<Comment> page) {
		if (CollectionUtils.isEmpty(page.getList())) {
			return new PageDto<>(page.getTotalCount(), page.getCurPage(), page.getPageSize(), null);
		}
		List<CommentDto> resultList = new ArrayList<>();
		for (Comment comment : page.getList()) {
			CommentDto dto = CommentBuild.CommentDtoBuild(comment);
			resultList.add(dto);
			// TODO 为了两个字段不需要取带关联信息的商家详情
			ResultDto<List<MerchantInfoDtoResult>> merchantInfo = merchantInfoServiceRepo.getMerchantDetail(new GetMerchantDetail4MerchantReq(dto.getMerchantNo()));
			if (null != merchantInfo && CollectionUtils.isNotEmpty(merchantInfo.getData()) && null != merchantInfo.getData().get(0)) {
				dto.setUserImage(merchantInfo.getData().get(0).getMainImage());
				dto.setRealName(merchantInfo.getData().get(0).getMerchantShortname());
			}

		}
		return new PageDto<>(page.getTotalCount(), page.getCurPage(), page.getPageSize(), resultList);
	}

	private PageDto<CommentDto> getUserCommentDto(PageDto<Comment> page) {
		if (CollectionUtils.isEmpty(page.getList())) {
			return new PageDto<>(page.getTotalCount(), page.getCurPage(), page.getPageSize(), page.getDataMap(), null);
		}
		List<CommentDto> resultList = new ArrayList<>();
		for (Comment comment : page.getList()) {
			CommentDto dto = CommentBuild.CommentDtoBuild(comment);
			// 用户头像 昵称
			User user = userServiceRepoImpl.getUserInfoByUserId(new GetUserInfoByUserIdCommand(comment.getUserId()));
			if (null != user) {
				dto.setRealName(user.getNickName());
				dto.setUserImage(user.getPhoto());
			}
			// 评论标签
			List<CommentLabels> labels = commentLabelsMapper.selectByCommentId(comment.getId());
			if (CollectionUtils.isNotEmpty(labels)) {
				List<String> labelsId = new ArrayList<>();
				for (CommentLabels bean : labels) {
					labelsId.add(bean.getCommentLabelName());
				}
				dto.setLabels(labelsId);
			}
			resultList.add(dto);
		}
		return new PageDto<>(page.getTotalCount(), page.getCurPage(), page.getPageSize(), page.getDataMap(), resultList);
	}

	private PageDto<Comment> getCommentBeanPage(GetMerchantCommentsCommand command) {
		int curPage = command.getCurPage();
		int pageSize = command.getPageSize();
		Map<String, Object> param = new HashMap<>();
		param.put("merchantNo", command.getMerchantNo());
		param.put("status", command.isComment());
		param.put("userId", command.getUserId());
		param.put("begin", (curPage - 1) * pageSize);
		param.put("end", pageSize);
		int total = commentMapper.selectCountByMerchantNo(param);
		List<Comment> data = commentMapper.selectByMerchantNo(param);
		return new PageDto<>(total, curPage, pageSize, data);
	}

	private PageDto<Comment> getCommentBeanPage(GetScoreDetailed4MerchantCommand command) {
		int curPage = command.getCurPage();
		int pageSize = command.getPageSize();
		Map<String, Object> param = new HashMap<>();
		param.put("merchantNo", command.getMerchantNo());
		param.put("status", null); // 是否为真实评论,默认全部带出
		param.put("type", DBConstants.GRADE_MAP.get(command.getCommentType()));
		param.put("begin", (curPage - 1) * pageSize);
		param.put("end", pageSize);
		int total = commentMapper.selectCountByMerchantNo(param);
		List<Comment> data = commentMapper.selectByMerchantNo(param);
		// 评分等级统计
		param.put("good", DBConstants.GRADE_MAP.get(GRADE_LEAVE.GOOD.getValue()));
		param.put("medium", DBConstants.GRADE_MAP.get(GRADE_LEAVE.MEDIUM.getValue()));
		param.put("bad", DBConstants.GRADE_MAP.get(GRADE_LEAVE.BAD.getValue()));
		List<CommentGradeLeave> leaves = commentMapper.selectGradeLeaveByMerchantNo(param);
		Map<String, Object> dataMap = new HashMap<>();
		if (CollectionUtils.isNotEmpty(leaves)) {
			for (CommentGradeLeave leave : leaves) {
				dataMap.put("goodCount", leave.getGood());
				dataMap.put("differenceCount", leave.getBad());
				dataMap.put("mediumCount", leave.getMedium());
			}
		}
		return new PageDto<>(total, curPage, pageSize, dataMap, data);
	}

	private PageDto<CommentPersonalDto> getCommentPersonalBeanPage(GetOperatorScoreDetailed4MerchantCommand command) {
		int curPage = command.getCurPage();
		int pageSize = command.getPageSize();
		Map<String, Object> param = new HashMap<>();
		param.put("merchantNo", command.getMerchantNo());
		param.put("operatorId", command.getOperatorId());
		param.put("type", DBConstants.GRADE_MAP.get(command.getCommentType()));
		param.put("begin", (curPage - 1) * pageSize);
		param.put("end", pageSize);
		int total = commentPersonalMapper.selectCommentPersonalCount(param);
		List<CommentPersonal> data = commentPersonalMapper.selectCommentPersonalPage(param);
		// 评分等级统计
		param.put("good", DBConstants.GRADE_MAP.get(GRADE_LEAVE.GOOD.getValue()));
		param.put("medium", DBConstants.GRADE_MAP.get(GRADE_LEAVE.MEDIUM.getValue()));
		param.put("bad", DBConstants.GRADE_MAP.get(GRADE_LEAVE.BAD.getValue()));
		List<CommentGradeLeave> leaves = commentPersonalMapper.selectGradeLeaveByMerchantNo(param);
		Map<String, Object> dataMap = new HashMap<>();
		if (CollectionUtils.isNotEmpty(leaves)) {
			for (CommentGradeLeave leave : leaves) {
				dataMap.put("goodCount", leave.getGood());
				dataMap.put("differenceCount", leave.getBad());
				dataMap.put("mediumCount", leave.getMedium());
			}
		}
		
		List<CommentPersonalDto> result = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(data)) {
			List<UserInfoDto> userInfoDtos = UserServiceRepo
					.getUserInfoList(data.stream().map(CommentPersonal::getUserId).collect(Collectors.joining(",")));
			for (CommentPersonal comment : data) {
				CommentPersonalDto dto = new CommentPersonalDto();
				BeanUtils.copyProperties(comment, dto);
				for (UserInfoDto userInfoDto : userInfoDtos) {
					if (userInfoDto.getUserId().equals(comment.getUserId())) {
						dto.setOperatorName(userInfoDto.getUserName());
						dto.setOperatorImage(userInfoDto.getImgUrl());
					}
				}
				result.add(dto);
			}
		}
		
		return new PageDto<>(total, curPage, pageSize, dataMap, result);
	}

	private void saveCommentLabels(UpdateComment4MemberCommand command, String id) {
		Integer[] labels = command.getLabels();
		if (null != labels && labels.length > 0) {
			// 保存商家评论标签
			for (Integer label : labels) {
				CommentLabels insertLabel = new CommentLabels();
				insertLabel.setCommentLabelId(label);
				insertLabel.setCommentId(id);
				commentLabelsMapper.insertSelective(insertLabel);
			}
		}
	}

	@Override
	public ResultDto<?> updateCommentPersonalStatus(CommentPersonal commentPersonal) {
		LOG.info("updateCommentPersonalStatus begin,reqParam:{}",JsonUtils.toJsonString(commentPersonal));
		try{
			int rlt = commentPersonalMapper.updatePersonalCommentStatus(commentPersonal);
			LOG.info("updateCommentPersonalStatus rlt:{}",rlt);
		}catch(Exception e){
			LOG.error("updateCommentPersonalStatus exception,e:",e);
		}
		return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
	}

}
