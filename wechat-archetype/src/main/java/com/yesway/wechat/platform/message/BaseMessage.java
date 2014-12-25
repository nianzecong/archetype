package com.yesway.wechat.platform.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class BaseMessage {
	// 开发者微信号
	protected String toUserName;
	// 发送方帐号（一个OpenID）
	protected String fromUserName;
	// 消息创建时间 （整型）
	protected String createTime;
	// 消息类型 text
	protected String msgType;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public static BaseMessage parseMessage(String xml) throws Exception {
		BaseMessage baseMessage = null;
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		if (rootElt != null) {
			String toUserName = rootElt.elementTextTrim("ToUserName");
			String fromUserName = rootElt.elementTextTrim("FromUserName");
			String createTime = rootElt.elementTextTrim("CreateTime");
			String msgType = rootElt.elementTextTrim("MsgType");

			baseMessage = new BaseMessage();
			baseMessage.setToUserName(toUserName);
			baseMessage.setFromUserName(fromUserName);
			baseMessage.setCreateTime(createTime);
			baseMessage.setMsgType(msgType);
		}
		return baseMessage;
	}
}
