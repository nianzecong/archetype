package com.yesway.quickstart.dao.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yesway.quickstart.dao.UserDao;
import com.yesway.quickstart.entity.User;

@Service
public class UserDaoImpl implements UserDao {
	private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public User getUserByLoginName(String loginName) {
		User user = null;
		if ("admin".equalsIgnoreCase(loginName)) {
			user = new User();
			user.setUserId(1);
			user.setLoginName("admin");
			user.setFullName("管理员");
			user.setPassword("admin");
			user.setAddTime(new Date());
		} else if ("user".equalsIgnoreCase(loginName)) {
			user = new User();
			user.setUserId(1);
			user.setLoginName("user");
			user.setFullName("用户");
			user.setPassword("user");
			user.setAddTime(new Date());
		}
		return user;
	}

}
