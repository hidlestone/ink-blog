package com.payn.ink.service.impl;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.User;
import com.payn.ink.mapper.UserMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.UserService;
import com.payn.ink.util.MD5Util;
import com.payn.ink.vo.invo.InstallInVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: payn
 * @date: 2020/8/18 20:21
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int createUser(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public ResponseResult login(User user, HttpServletRequest request) {
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			return ResponseResult.fail("用户名和密码不能为空");
		}
		user.setPassword(MD5Util.MD5encode(user.getPassword()));
		User loginUser = userMapper.userLogin(user);
		if (null == loginUser) {
			return new ResponseResult(false, -2, "用户名或密码错误");
		}
		//将登陆的用户对象存储到session中
		request.getSession().setAttribute(InkConstants.LOGIN_SESSION_KEY, loginUser);
		return ResponseResult.SUCCESS();//登陆操作成功
	}

	@Override
	public int countByUserName(String author) {
		return userMapper.countByUserName(author);
	}

	@Override
	public User findAdminUser() {
		return userMapper.getAdminUser();
	}

	@Override
	public int deleteUserByUsername(String username) {
		return userMapper.deleteUserByUsername(username);
	}

}
