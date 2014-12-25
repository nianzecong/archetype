package com.yesway.quickstart.service.impl;

import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import com.yesway.quickstart.dao.UserDao;
import com.yesway.quickstart.entity.User;
import com.yesway.quickstart.service.IHelloWorld;

@WebService(serviceName = "HelloWorldService")
public class HelloWorldImpl implements IHelloWorld {
	@Autowired
	private UserDao userDao;

	@Override
	public String sayHi(String text) {
		System.out.println("sayHi called");
		return "Hello " + text;
	}

	public String sayHiToUser(User user) {
		return user.getName() + ",Hi~";
	}

	public User getUser(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

}
