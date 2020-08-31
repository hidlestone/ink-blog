package com.payn.ink.service;

import com.payn.ink.domain.User;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.invo.InstallInVo;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	
	int createUser(User user);

	ResponseResult login(User user, HttpServletRequest request);

	int countByUserName(String author);

	User findAdminUser();

	int deleteUserByUsername(String userName);
}
