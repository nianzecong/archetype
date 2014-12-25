package com.yesway.wechat.service;

public interface MessageService {
	// 接收到微信平台发过来的消息进行处理
	public abstract String processMessage(String xml);
}
