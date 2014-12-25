package com.yesway.quickstart.service;

import com.yesway.quickstart.entity.User;

public interface UserService {
	public abstract User getUserByLoginName(String loginName);
}
