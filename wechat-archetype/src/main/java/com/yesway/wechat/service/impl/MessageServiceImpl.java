package com.yesway.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yesway.wechat.platform.message.BaseMessage;
import com.yesway.wechat.platform.message.EventMessage;
import com.yesway.wechat.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

	// 接收到微信平台发过来的消息进行处理
	@Override
	public String processMessage(String xml) {
		String result = "";
		try {
			BaseMessage baseMessage = BaseMessage.parseMessage(xml);
			String msgType = baseMessage.getMsgType();
			if ("text".equals(msgType)) {
				// 文本消息
				log.debug("处理text消息");
			} else if ("image".equalsIgnoreCase(msgType)) {
				// 图片消息
				log.debug("处理image消息");
			} else if ("voice".equalsIgnoreCase(msgType)) {
				// 语音消息
				log.debug("处理voice消息");
			} else if ("video".equalsIgnoreCase(msgType)) {
				// 视频消息
				log.debug("处理video消息");
			} else if ("location".equalsIgnoreCase(msgType)) {
				// 地理位置消息
				log.debug("处理location消息");
			} else if ("link".equalsIgnoreCase(msgType)) {
				// 链接消息
				log.debug("处理link消息");
			} else if ("event".equalsIgnoreCase(msgType)) {
				// 事件推送
				EventMessage eventMessage = EventMessage.parseMessage(xml);
				String event = eventMessage.getEvent();
				if ("subscribe".equalsIgnoreCase(event)) {
					// 订阅
					log.debug("处理subscribe事件");
				} else if ("unsubscribe".equalsIgnoreCase(event)) {
					// 取消订阅
					log.debug("处理unsubscribe事件");
				} else if ("SCAN".equalsIgnoreCase(event)) {
					// 用户已关注时的事件推送
					log.debug("处理SCAN事件");
				} else if ("LOCATION".equalsIgnoreCase(event)) {
					// 上报地理位置事件
					log.debug("处理LOCATION事件");
				} else if ("CLICK".equalsIgnoreCase(event)) {
					// 自定义菜单事件
					log.debug("处理CLICK事件");
				} else if ("VIEW".equalsIgnoreCase(event)) {
					// 点击菜单跳转链接时的事件推送
					log.debug("处理VIEW事件");
				} else {
					log.debug("处理未知事件");
				}
			} else {
				// 未知消息
				log.debug("处理未知消息");
			}
		} catch (Exception e) {
			log.error("processMessage error:", e);
		}
		return result;
	}

}
