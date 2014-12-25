package com.yesway.quickstart.dao;

import java.util.List;
import java.util.Map;

import com.yesway.quickstart.entity.User;

public interface UserDao {
	public abstract int addUser(User user);

	public abstract User getUserByLoginName(String loginName);

	public abstract User getUserByUserId(long userId);

	public abstract List<User> getUserListAll();

	public abstract int getUserCount(Map<String, Object> map);

	public abstract List<User> getUserList(Map<String, Object> map);

	public abstract int updateUser(User user);
	
	public abstract int deleteUser(long userId);
}
