package com.yesway.webnotify.service;

import java.util.List;

public interface NotifyService {

	public abstract boolean notify(String channel, String message);

	public abstract List<String> getChannels(String channel);

}
