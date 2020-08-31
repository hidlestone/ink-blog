package com.payn.ink.mapper;

import com.payn.ink.domain.User;

public interface UserMapper {
	
	int insertSelective(User user);

	User userLogin(User user);

	int countByUserName(String author);

	User getAdminUser();

	int deleteUserByUsername(String username);
}