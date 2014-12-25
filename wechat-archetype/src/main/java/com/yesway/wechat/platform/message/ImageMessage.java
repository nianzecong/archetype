package com.yesway.wechat.platform.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ImageMessage extends BaseMessage {
	// 消息id，64位整型，仅用于接受消息中
	protected String msgId;
	// 图片链接，仅用于接收普通消息的图片消息
	private String picUrl;
	// 通过上传多媒体文件，得到的id
	private String mediaId;

	public ImageMessage() {
		msgType = "image";
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String buildXmlMessage() {
		StringBuilder result = new StringBuilder();
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
		result.append("<Image><MediaId><![CDATA[" + mediaId + "]]></MediaId></Image>");
		result.append("</xml>");
		return result.toString();
	}

	public String buildJsonMessage() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		result.append("\"touser\":\"" + toUserName + "\",");
		result.append("\"msgtype\":\"" + msgType + "\",");
		result.append("\"image\":{\"media_id\":\"" + mediaId + "\"}");
		result.append("}");
		return result.toString();
	}

	public static ImageMessage parseMessage(String xml) throws Exception {
		ImageMessage imageMessage = null;
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		if (rootElt != null) {
			String toUserName = rootElt.elementTextTrim("ToUserName");
			String fromUserName = rootElt.elementTextTrim("FromUserName");
			String createTime = rootElt.elementTextTrim("CreateTime");
			String msgType = rootElt.elementTextTrim("MsgType");
			String msgId = rootElt.elementTextTrim("MsgId");

			String picUrl = rootElt.elementTextTrim("PicUrl");
			String mediaId = rootElt.elementTextTrim("MediaId");

			imageMessage = new ImageMessage();
			imageMessage.setToUserName(toUserName);
			imageMessage.setFromUserName(fromUserName);
			imageMessage.setCreateTime(createTime);
			imageMessage.setMsgType(msgType);
			imageMessage.setMsgId(msgId);

			imageMessage.setPicUrl(picUrl);
			imageMessage.setMediaId(mediaId);
		}
		return imageMessage;
	}

	public static void main(String[] args) throws Exception {
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setToUserName("toUser");
		imageMessage.setFromUserName("fromUser");
		imageMessage.setCreateTime("12345678");
		imageMessage.setMsgType("image");
		imageMessage.setMediaId("media_id");
		// 被动响应消息
		System.out.println(imageMessage.buildXmlMessage());
		// 发送客服消息
		System.out.println(imageMessage.buildJsonMessage());
		// 接收普通消息
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[this is a url]]></PicUrl><MediaId><![CDATA[media_id]]></MediaId><MsgId>1234567890123456</MsgId></xml>";
		System.out.println(ImageMessage.parseMessage(xml).buildXmlMessage());
	}
}
