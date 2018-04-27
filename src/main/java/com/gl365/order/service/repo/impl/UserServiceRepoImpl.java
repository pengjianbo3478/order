package com.gl365.order.service.repo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl365.order.common.utils.JsonUtils;
import com.gl365.order.dto.user.User;
import com.gl365.order.dto.user.command.GetUserInfoByUserIdCommand;
import com.gl365.order.service.repo.UserServiceRepo;

@Component
public class UserServiceRepoImpl {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceRepoImpl.class);

	private static final String MICRO_SERVICE = "member";

	@Autowired
	private UserServiceRepo userServiceRepo;

	public User getUserInfoByUserId(GetUserInfoByUserIdCommand command) {
		LOG.info("Micro Service[{}] method action begin , command : [{}]", MICRO_SERVICE, JsonUtils.toJsonString(command));
		User result = userServiceRepo.getUserInfoByUserId(command);
		if (null == result) {
			LOG.warn("Micro Service[{}] method warn result data empty ! message : [{}]", MICRO_SERVICE, result);
			return null;
		}
		return result;
	}

}
