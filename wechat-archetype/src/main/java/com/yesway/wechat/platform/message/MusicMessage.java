package com.yesway.wechat.platform.message;

public class MusicMessage extends BaseMessage {
	// 音乐标题
	private String title;
	// 音乐描述
	private String description;
	// 音乐链接
	private String musicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String hQMusicUrl;
	// 缩略图的媒体id，通过上传多媒体文件，得到的id
	private String thumbMediaId;

	public MusicMessage() {
		msgType = "music";
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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String gethQMusicUrl() {
		return hQMusicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String buildXmlMessage() {
		StringBuilder result = new StringBuilder();
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
		result.append("<Music>");
		result.append("<Title><![CDATA[" + title + "]]></Title>");
		result.append("<Description><![CDATA[" + description + "]]></Description>");
		result.append("<MusicUrl><![CDATA[" + musicUrl + "]]></MusicUrl>");
		result.append("<HQMusicUrl><![CDATA[" + hQMusicUrl + "]]></HQMusicUrl>");
		result.append("<ThumbMediaId><![CDATA[" + thumbMediaId + "]]></ThumbMediaId>");
		result.append("</Music>");
		result.append("</xml>");
		return result.toString();
	}

	public String buildJsonMessage() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		result.append("\"touser\":\"" + toUserName + "\",");
		result.append("\"msgtype\":\"" + msgType + "\",");
		result.append("\"music\":{");
		result.append("\"title\":\"" + title + "\",");
		result.append("\"description\":\"" + description + "\",");
		result.append("\"musicurl\":\"" + musicUrl + "\",");
		result.append("\"hqmusicurl\":\"" + hQMusicUrl + "\",");
		result.append("\"thumb_media_id\":\"" + thumbMediaId + "\"}");
		result.append("}");
		return result.toString();
	}

	public static void main(String[] args) {
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setToUserName("toUser");
		musicMessage.setFromUserName("fromUser");
		musicMessage.setCreateTime("12345678");
		musicMessage.setMsgType("music");
		musicMessage.setTitle("TITLE");
		musicMessage.setDescription("DESCRIPTION");
		musicMessage.setMusicUrl("MUSIC_Url");
		musicMessage.sethQMusicUrl("HQ_MUSIC_Url");
		musicMessage.setThumbMediaId("media_id");
		// 被动响应消息
		System.out.println(musicMessage.buildXmlMessage());
		// 发送客服消息
		System.out.println(musicMessage.buildJsonMessage());
	}
}
