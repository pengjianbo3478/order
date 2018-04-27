package com.gl365.order.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.common.enums.CashStatusEnum;
import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.req.gateway.NotifyCashResultsReq;
import com.gl365.order.dto.req.gateway.NotifyCashWithdrawalReq;
import com.gl365.order.event.AuditCashEvent;
import com.gl365.order.mapper.OrderCashMapper;
import com.gl365.order.model.OrderCash;
import com.gl365.order.service.CashOrderService;
import com.gl365.order.util.GsonUtils;
import com.gl365.order.util.ReflectUtils;

@Service
public class CashOrderServiceImpl implements CashOrderService{

	private static final Logger LOG = LoggerFactory.getLogger(CashOrderServiceImpl.class);
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	

	@Autowired
	private OrderCashMapper orderCashMapper;
	
	public ResultDto<?> saveNotifyCash(NotifyCashWithdrawalReq req){
		LOG.info("订单系统取现通知入参-----------》》{}",req);
		
		try {
			checkStrLength(req, "puserNo", true, 35,null);
			checkStrLength(req, "applyNo", true, 50,null);
			checkStrLength(req, "amount", true, 10,null);
			checkStrLength(req, "accountNo", true, 25,null);
			checkStrLength(req, "accountName", true, 30,null);
		} catch (Exception e1) {
			LOG.info("订单系统取现通知入参：saveNotifyCash:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
		}
		try{
			//构建数据库存储对象
			OrderCash oc=new OrderCash();
			BeanUtils.copyProperties(req, oc);
			oc.setStatus(CashStatusEnum.Apply.getValue());
			LOG.info("订单系统取现通知准备保存-----------》》{}",oc);
			orderCashMapper.insertSelective(oc);
			LOG.info("订单系统取现通知保存完毕-----------》》{}",oc);
			eventPublisher.publishEvent(new AuditCashEvent(this,GsonUtils.toJson(req)));
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		}catch(Exception e){
			String errMsg = e.getMessage();
			if (StringUtils.isNotBlank(errMsg) && errMsg.indexOf("Duplicate entry") != -1) {
				LOG.error("接收提现通知写入失败:{}", "order_sn违反唯一约束");
				//return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, "apply_no已经存在");
				ResultDto<?> result= new ResultDto<>(ResultCodeEnum.System.SUCCESS);
				result.setDescription("apply_no已经存在");
				return result;
			}
			LOG.error("订单系统取现通知保存异常-----------》》{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
		
	}
	
	
	public ResultDto<?> updateNotifyCash(NotifyCashResultsReq req){
		LOG.info("订单系统取现结果通知入参-----------》》{}",req);
		
		try {
			checkStrLength(req, "puserNo", true, 35,null);
			checkStrLength(req, "applyNo", true, 50,null);
			checkStrLength(req, "result", true, 2,null);
		} catch (Exception e1) {
			LOG.info("订单系统取现结果知入参：updateNotifyCash:{}", e1);
			return new ResultDto<>(ResultCodeEnum.System.PARAM_ERROR, e1.getMessage());
		}
		try{
			//构建数据库存储对象
			OrderCash oc=new OrderCash();
			BeanUtils.copyProperties(req, oc);
			//1:-提现成功，2-提现失败
			if(req.getResult().equals("1")){
				oc.setStatus(CashStatusEnum.Succeed.getValue());
			}else{
				oc.setStatus(CashStatusEnum.Fail.getValue());
			}
			LOG.info("订单系统取现结果通知准备修改-----------》》{}",oc);
			
			int update=orderCashMapper.updateByApplyNo(oc);
			LOG.info("提现申结果请消息修改状态完成：{} 影响结果{}", oc,update);
			
			LOG.info("订单系统取现结果通知修改完毕-----------》》{}",oc);
			return new ResultDto<>(ResultCodeEnum.System.SUCCESS);
		}catch(Exception e){
			LOG.error("订单系统取现结果通知修改异常-----------》》{}", e);
			return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
		
	}
	
	
	/**
	 * 检查对象字符属性不为空且未超长
	 * 
	 * @param obj
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @param mustNeed
	 *            是否必须 true必须 false非必须
	 * @param maxLength
	 *            最大字符长度
	 * @return 返回null表示检查通过，否则返回检查失败的明细描述
	 * @throws Exception
	 */
	private void checkStrLength(Object obj, String fieldName, boolean mustNeed, int maxLength,Integer minLength) throws Exception {
		Object val = ReflectUtils.getValueByFieldName(obj, fieldName);
		if (val == null && mustNeed) {
			throw new Exception("参数" + fieldName + "值为空");
		}
		
		if (val == null && !mustNeed) {
			return;
		}
		
		String str = String.valueOf(val);
		if (!str.trim().equals(str)) {
			str = str.trim();
			ReflectUtils.setValueByFieldName(obj, fieldName, str);
		}
		if (mustNeed && str.length() == 0) {
			throw new Exception("参数" + fieldName + "值为空");
		}
		if (mustNeed && str.length() > maxLength) {
			throw new Exception("参数" + fieldName + "值不允许大于" + maxLength + "个字符");
		}
		if(null!=minLength){
			if (mustNeed && str.length() < minLength.intValue()) {
				throw new Exception("参数" + fieldName + "值不允许小于" + minLength + "个字符");
			}
		}
	}

}
