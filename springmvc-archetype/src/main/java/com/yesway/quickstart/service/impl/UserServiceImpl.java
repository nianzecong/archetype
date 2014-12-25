package com.yesway.quickstart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yesway.quickstart.dao.UserDao;
import com.yesway.quickstart.entity.User;
import com.yesway.quickstart.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}
}
