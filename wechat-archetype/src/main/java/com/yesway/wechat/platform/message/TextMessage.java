package com.yesway.wechat.platform.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TextMessage extends BaseMessage {
	// 消息id，64位整型，仅用于接受消息中
	protected String msgId;
	// 文本消息内容
	private String content;

	public TextMessage() {
		msgType = "text";
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String buildXmlMessage() {
		StringBuilder result = new StringBuilder();
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
		result.append("<Content><![CDATA[" + content + "]]></Content>");
		result.append("</xml>");
		return result.toString();
	}

	public String buildJsonMessage() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		result.append("\"touser\":\"" + toUserName + "\",");
		result.append("\"msgtype\":\"" + msgType + "\",");
		result.append("\"text\":{\"content\":\"" + content + "\"}");
		result.append("}");
		return result.toString();
	}

	public static TextMessage parseMessage(String xml) throws Exception {
		TextMessage textMessage = null;
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		if (rootElt != null) {
			String toUserName = rootElt.elementTextTrim("ToUserName");
			String fromUserName = rootElt.elementTextTrim("FromUserName");
			String createTime = rootElt.elementTextTrim("CreateTime");
			String msgType = rootElt.elementTextTrim("MsgType");
			String content = rootElt.elementTextTrim("Content");
			String msgId = rootElt.elementTextTrim("MsgId");

			textMessage = new TextMessage();
			textMessage.setToUserName(toUserName);
			textMessage.setFromUserName(fromUserName);
			textMessage.setCreateTime(createTime);
			textMessage.setMsgType(msgType);
			textMessage.setMsgId(msgId);

			textMessage.setContent(content);
		}
		return textMessage;
	}

	public static void main(String[] args) throws Exception {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName("toUser");
		textMessage.setFromUserName("fromUser");
		textMessage.setCreateTime("12345678");
		textMessage.setMsgType("text");
		textMessage.setContent("你好");
		// 被动响应消息
		System.out.println(textMessage.buildXmlMessage());
		// 发送客服消息
		System.out.println(textMessage.buildJsonMessage());
		// 接收普通消息
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
		System.out.println(TextMessage.parseMessage(xml).buildXmlMessage());
	}
}
