package com.yesway.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yesway.wechat.cfg.AppConfig;
import com.yesway.wechat.cfg.Environment;
import com.yesway.wechat.platform.util.WechatUtil;
import com.yesway.wechat.service.MessageService;

@Controller
@RequestMapping("/dispatcher")
public class WechatController {
	private static final Logger log = LoggerFactory.getLogger(WechatController.class);

	@Autowired
	private MessageService messageService;

	public String getAppToken() {
		return AppConfig.getParameter(Environment.WEHCAT_APPTOKEN);
	}

	@RequestMapping(method = RequestMethod.GET)
	public void doGet(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request, HttpServletResponse response) {
		log.debug("doGet {signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr + "}");
		// 校验是否是来自微信的请求
		if (WechatUtil.checkSignature(getAppToken(), signature, timestamp, nonce)) {
			// 验证成功，原样返回echostr内容
			try {
				response.getWriter().write(echostr);
			} catch (IOException e) {
				log.error("doGet error:", e);
			}
		} else {
			log.error("微信接入请求失败！消息来源IP：" + request.getRemoteAddr() + "，来源端口：" + request.getRemotePort());
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public void doPost(String signature, String timestamp, String nonce, @RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		log.debug("doPost {signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",body:" + body + "}");
		if (WechatUtil.checkSignature(getAppToken(), signature, timestamp, nonce)) {
			try {
				// 消息验证通过，解析消息后进行处理，返回对应消息
				String res = messageService.processMessage(body);
				response.setContentType("text/xml;charset=utf-8");
				response.getWriter().write(res);
			} catch (IOException e) {
				log.error("doPost:", e);
			}
		} else {
			log.error("接收到非微信请求！消息来源IP：" + request.getRemoteAddr() + "，来源端口：" + request.getRemotePort());
		}
	}
}
