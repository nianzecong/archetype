package com.yesway.wechat.platform.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class VideoMessage extends BaseMessage {
	// 消息id，64位整型，仅用于接受消息中
	protected String msgId;
	// 通过上传多媒体文件，得到的id
	private String mediaId;
	// 视频消息的标题
	private String title;
	// 视频消息的描述
	private String description;
	// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。仅用于接受消息
	private String thumbMediaId;

	public VideoMessage() {
		msgType = "video";
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

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String buildXmlMessage() {
		StringBuilder result = new StringBuilder();
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
		result.append("<Video>");
		result.append("<MediaId><![CDATA[" + mediaId + "]]></MediaId>");
		result.append("<Title><![CDATA[" + title + "]]></Title>");
		result.append("<Description><![CDATA[" + description + "]]></Description>");
		result.append("</Video>");
		result.append("</xml>");
		return result.toString();
	}

	public String buildJsonMessage() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		result.append("\"touser\":\"" + toUserName + "\",");
		result.append("\"msgtype\":\"" + msgType + "\",");
		result.append("\"video\":{");
		result.append("\"media_id\":\"" + mediaId + "\",");
		result.append("\"title\":\"" + title + "\",");
		result.append("\"description\":\"" + description + "\"}");
		result.append("}");
		return result.toString();
	}

	public static VideoMessage parseMessage(String xml) throws Exception {
		VideoMessage videoMessage = null;
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		if (rootElt != null) {
			String toUserName = rootElt.elementTextTrim("ToUserName");
			String fromUserName = rootElt.elementTextTrim("FromUserName");
			String createTime = rootElt.elementTextTrim("CreateTime");
			String msgType = rootElt.elementTextTrim("MsgType");
			String msgId = rootElt.elementTextTrim("MsgId");

			String mediaId = rootElt.elementTextTrim("MediaId");
			String thumbMediaId = rootElt.elementTextTrim("ThumbMediaId");

			videoMessage = new VideoMessage();
			videoMessage.setToUserName(toUserName);
			videoMessage.setFromUserName(fromUserName);
			videoMessage.setCreateTime(createTime);
			videoMessage.setMsgType(msgType);
			videoMessage.setMsgId(msgId);

			videoMessage.setMediaId(mediaId);
			videoMessage.setThumbMediaId(thumbMediaId);
		}
		return videoMessage;
	}

	public static void main(String[] args) throws Exception {
		VideoMessage videoMessage = new VideoMessage();
		videoMessage.setToUserName("toUser");
		videoMessage.setFromUserName("fromUser");
		videoMessage.setCreateTime("12345678");
		videoMessage.setMsgType("video");
		videoMessage.setMediaId("media_id");
		videoMessage.setTitle("title");
		videoMessage.setDescription("description");
		// 被动响应消息
		System.out.println(videoMessage.buildXmlMessage());
		// 发送客服消息
		System.out.println(videoMessage.buildJsonMessage());
		// 接收普通消息
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1357290913</CreateTime><MsgType><![CDATA[video]]></MsgType><MediaId><![CDATA[media_id]]></MediaId><ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId><MsgId>1234567890123456</MsgId></xml>";
		System.out.println(VideoMessage.parseMessage(xml).buildXmlMessage());
	}
}
