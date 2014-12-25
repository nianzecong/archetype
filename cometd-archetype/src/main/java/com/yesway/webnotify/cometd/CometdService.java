package com.yesway.webnotify.cometd;

import java.util.List;
import java.util.Map;
import org.cometd.annotation.Service;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.AbstractService;
import org.cometd.server.DefaultSecurityPolicy;

@Service("cometd")
public class CometdService extends AbstractService {

	public CometdService(BayeuxServer bayeuxServer) {
		// 通过BayeuxServer对象和服务通道名称调用父类构造
		super(bayeuxServer, "cometd");
		// 订阅通道， 并指定消息到达通道时的回调函数
		addService("/notify/*", "processCometd");
		// 鉴权
		this.getBayeux().setSecurityPolicy(new DefaultSecurityPolicy() {
			// 握手时进行企业鉴权，根据实际情况进行权限配置
			@Override
			public boolean canHandshake(BayeuxServer server, ServerSession session, ServerMessage message) {
				boolean canHandshake = "1".equals((String) message.get("name")) && "123".equals((String) message.get("pwd"));
				return canHandshake;
			}
		});
	}

	public void processCometd(ServerSession remote, Map<String, Object> data) {
		remote.deliver(getServerSession(), "/cometd", data, null);
	}

	public boolean broadcast(String _channel, Object data) {
		boolean result = false;
		ServerChannel channel = getBayeux().getChannel(_channel);
		if (channel != null) {
			channel.publish(getServerSession(), data, null);
			result = true;
		}
		return result;
	}

	public List<ServerChannel> getChannels() {
		return getBayeux().getChannels();
	}

}
