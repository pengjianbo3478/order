package com.gl365.order.service.repo;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.order.dto.ResultDto;
import com.gl365.order.dto.user.User;
import com.gl365.order.dto.user.UserInfoDto;
import com.gl365.order.dto.user.command.GetUserInfoByUserIdCommand;

/**
 * < 会员ServiceRepo >
 * 
 * @since hui.li 2017年6月23日 上午10:44:50
 */
@FeignClient(name="member",url="${${env:}.url.member:}")
public interface UserServiceRepo {

	/**
	 * 获取会员信息
	 * 
	 * @param userDto
	 * @return
	 */
	@RequestMapping(value = "/member/user/info/userId", method = RequestMethod.POST)
	public User getUserInfoByUserId(@RequestBody GetUserInfoByUserIdCommand command);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/member/user/getUserByuserIdList", method = RequestMethod.POST)
	public List<User> getUserByuserIdList(@RequestBody List<String> userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/member/user/userInfoList", method = RequestMethod.POST)
	public List<UserInfoDto> getUserInfoList(@RequestBody String req);
	
	/**
	 * 根据用户userId查询会员关联的其它userId
	 */
	@RequestMapping(value = "/member/userRelation/queryUserRelationByUserId/{userId}", method = RequestMethod.POST)
	public ResultDto<Set<String>> queryUserRelationByUserId(@PathVariable("userId") String userId);

}
