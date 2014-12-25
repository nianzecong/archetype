package com.yesway.quickstart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesway.quickstart.dao.UserDao;
import com.yesway.quickstart.entity.User;
import com.yesway.quickstart.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public int addUser(User user) {
		return userDao.addUser(user);
	}

	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

	public User getUserByUserId(long userId) {
		return userDao.getUserByUserId(userId);
	}

	public List<User> getUserListAll() {
		return userDao.getUserListAll();
	}

	public int getUserCount(Map<String, Object> map) {
		return userDao.getUserCount(map);
	}

	public List<User> getUserList(Map<String, Object> map) {
		return userDao.getUserList(map);
	}

	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	public int deleteUser(long userId) {
		return userDao.deleteUser(userId);
	}
}
