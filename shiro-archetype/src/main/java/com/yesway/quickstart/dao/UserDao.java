package com.yesway.quickstart.dao;

import com.yesway.quickstart.entity.User;

public interface UserDao {
	public abstract User getUserByLoginName(String loginName);
}
