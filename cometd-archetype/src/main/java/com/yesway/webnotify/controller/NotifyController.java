package com.yesway.webnotify.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.yesway.webnotify.service.NotifyService;

@Controller
@RequestMapping("/notify/*")
public class NotifyController {

	@Autowired
	private NotifyService notifyService;

	// 接收通行信息jsonp
	@RequestMapping("/send.jsonp")
	public @ResponseBody
	JSONPObject sendJsonp(String channel, String message, String callback) {
		boolean result = notifyService.notify(channel, message);
		return new JSONPObject(callback, result);
	}

	// 接收通行信息json
	@RequestMapping("/send.json")
	public @ResponseBody
	Map<String, Object> sendJson(String channel, String message) {
		boolean result = notifyService.notify(channel, message);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("result", result);
		return map;
	}

	// 接收通行信息
	@RequestMapping("/send")
	public @ResponseBody
	boolean send(String channel, String message) {
		return notifyService.notify(channel, message);
	}

	// 获取匹配的通道列表
	@RequestMapping("/getchannels.jsonp")
	public @ResponseBody
	JSONPObject getChannelsJsonp(String channel, String callback) {
		List<String> channels = notifyService.getChannels(channel);
		return new JSONPObject(callback, channels);
	}

	// 获取匹配的通道列表
	@RequestMapping("/gethannels")
	public @ResponseBody
	List<String> getChannels(String channel) {
		return notifyService.getChannels(channel);
	}

}
