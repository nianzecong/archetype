package com.yesway.webnotify.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cometd.bayeux.server.ServerChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yesway.webnotify.cometd.CometdService;
import com.yesway.webnotify.service.NotifyService;

@Service
public class NotifyServiceImpl implements NotifyService {

	@Autowired
	private CometdService cometdService;

	@Override
	public boolean notify(String channel, String message) {
		return cometdService.broadcast(channel, message);
	}

	@Override
	public List<String> getChannels(String _channel) {
		// 取所有频道
		List<ServerChannel> allChannel = cometdService.getChannels();
		List<String> channels = new ArrayList<String>();
		// 进行正则匹配
		Pattern p = Pattern.compile(_channel);
		for (ServerChannel channel : allChannel) {
			String channelId = channel.getChannelId().toString();
			Matcher m = p.matcher(channelId);
			if (m.find()) {
				channels.add(channelId);
			}
		}
		return channels;
	}
}
