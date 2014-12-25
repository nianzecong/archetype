package com.yesway.wechat.platform.model;

public class Media {
	// 错误代码
	private String errcode;
	// 错误描述
	private String errmsg;
	// 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	private String type;
	// 媒体文件上传后，获取时的唯一标识
	private String media_id;
	// 媒体文件上传时间戳
	private long created_at;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

}
