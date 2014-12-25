package com.yesway.quickstart.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.yesway.quickstart.entity.User;

@WebService
public interface IHelloWorld {
	//加入WebParam注解，以保证xml文件中参数名字的正确性 所以如果没有加注解，参数将被命名为arg0
	String sayHi(@WebParam(name="text") String text);
	String sayHiToUser(@WebParam(name="user") User user);
	User getUser(@WebParam(name="loginName") String loginName);
}
