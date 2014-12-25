package com.yesway.wechat.platform.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class VoiceMessage extends BaseMessage {
	// 消息id，64位整型，仅用于接受消息中
	private String msgId;
	// 通过上传多媒体文件，得到的id
	private String mediaId;
	// 语音格式，如amr，speex等，仅用于接受用户消息
	private String format;
	// 开通语音识别功能，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段
	// 语音识别结果，UTF8编码
	private String recognition;

	public VoiceMessage() {
		msgType = "voice";
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	public String buildXmlMessage() {
		StringBuilder result = new StringBuilder();
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
		result.append("<Voice><MediaId><![CDATA[" + mediaId + "]]></MediaId></Voice>");
		result.append("</xml>");
		return result.toString();
	}

	public String buildJsonMessage() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		result.append("\"touser\":\"" + toUserName + "\",");
		result.append("\"msgtype\":\"" + msgType + "\",");
		result.append("\"voice\":{\"media_id\":\"" + mediaId + "\"}");
		result.append("}");
		return result.toString();
	}

	public static VoiceMessage parseMessage(String xml) throws Exception {
		VoiceMessage voiceMessage = null;
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		if (rootElt != null) {
			String toUserName = rootElt.elementTextTrim("ToUserName");
			String fromUserName = rootElt.elementTextTrim("FromUserName");
			String createTime = rootElt.elementTextTrim("CreateTime");
			String msgType = rootElt.elementTextTrim("MsgType");
			String msgId = rootElt.elementTextTrim("MsgId");

			String mediaId = rootElt.elementTextTrim("MediaId");
			String format = rootElt.elementTextTrim("Format");
			String recognition = rootElt.elementTextTrim("Recognition");

			voiceMessage = new VoiceMessage();
			voiceMessage.setToUserName(toUserName);
			voiceMessage.setFromUserName(fromUserName);
			voiceMessage.setCreateTime(createTime);
			voiceMessage.setMsgType(msgType);
			voiceMessage.setMsgId(msgId);

			voiceMessage.setMediaId(mediaId);
			voiceMessage.setFormat(format);
			voiceMessage.setRecognition(recognition);
		}
		return voiceMessage;
	}

	public static void main(String[] args) throws Exception {
		VoiceMessage voiceMessage = new VoiceMessage();
		voiceMessage.setToUserName("toUser");
		voiceMessage.setFromUserName("fromUser");
		voiceMessage.setCreateTime("12345678");
		voiceMessage.setMsgType("voice");
		voiceMessage.setMediaId("media_id");
		// 被动响应消息
		System.out.println(voiceMessage.buildXmlMessage());
		// 发送客服消息
		System.out.println(voiceMessage.buildJsonMessage());
		// 接收普通消息
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1357290913</CreateTime><MsgType><![CDATA[voice]]></MsgType><MediaId><![CDATA[media_id]]></MediaId><Format><![CDATA[Format]]></Format><Recognition><![CDATA[腾讯微信团队]]></Recognition><MsgId>1234567890123456</MsgId></xml>";
		System.out.println(VoiceMessage.parseMessage(xml).buildXmlMessage());
	}
}
