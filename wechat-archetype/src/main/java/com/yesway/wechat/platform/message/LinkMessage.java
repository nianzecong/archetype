package com.yesway.wechat.platform.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class LinkMessage extends BaseMessage {
	// 消息id，64位整型，仅用于接受消息中
	protected String msgId;
	// 消息标题
	private String title;
	// 消息描述
	private String description;
	// 消息链接
	private String url;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static LinkMessage parseMessage(String xml) throws Exception {
		LinkMessage linkMessage = null;
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		if (rootElt != null) {
			String toUserName = rootElt.elementTextTrim("ToUserName");
			String fromUserName = rootElt.elementTextTrim("FromUserName");
			String createTime = rootElt.elementTextTrim("CreateTime");
			String msgType = rootElt.elementTextTrim("MsgType");
			String msgId = rootElt.elementTextTrim("MsgId");

			String title = rootElt.elementTextTrim("Title");
			String description = rootElt.elementTextTrim("Description");
			String url = rootElt.elementTextTrim("Url");

			linkMessage = new LinkMessage();
			linkMessage.setToUserName(toUserName);
			linkMessage.setFromUserName(fromUserName);
			linkMessage.setCreateTime(createTime);
			linkMessage.setMsgType(msgType);
			linkMessage.setMsgId(msgId);

			linkMessage.setTitle(title);
			linkMessage.setDescription(description);
			linkMessage.setUrl(url);
		}
		return linkMessage;
	}

	public static void main(String[] args) throws Exception {
		// 接收普通消息
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1351776360</CreateTime><MsgType><![CDATA[link]]></MsgType><Title><![CDATA[公众平台官网链接]]></Title><Description><![CDATA[公众平台官网链接]]></Description><Url><![CDATA[url]]></Url><MsgId>1234567890123456</MsgId></xml>";
		System.out.println(LinkMessage.parseMessage(xml));
	}
}
