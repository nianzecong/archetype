package com.yesway.wechat.platform.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

//仅用于接受的消息
public class LocationMessage extends BaseMessage {
	// 消息id，64位整型，仅用于接受消息中
	protected String msgId;
	// 地理位置维度
	private String location_X;
	// 地理位置经度
	private String location_Y;
	// 地图缩放大小
	private String scale;
	// 地理位置信息
	private String label;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getLocation_X() {
		return location_X;
	}

	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}

	public String getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static LocationMessage parseMessage(String xml) throws Exception {
		LocationMessage locationMessage = null;
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		if (rootElt != null) {
			String toUserName = rootElt.elementTextTrim("ToUserName");
			String fromUserName = rootElt.elementTextTrim("FromUserName");
			String createTime = rootElt.elementTextTrim("CreateTime");
			String msgType = rootElt.elementTextTrim("MsgType");
			String msgId = rootElt.elementTextTrim("MsgId");

			String location_X = rootElt.elementTextTrim("Location_X");
			String location_Y = rootElt.elementTextTrim("Location_Y");
			String scale = rootElt.elementTextTrim("Scale");
			String label = rootElt.elementTextTrim("Label");

			locationMessage = new LocationMessage();
			locationMessage.setToUserName(toUserName);
			locationMessage.setFromUserName(fromUserName);
			locationMessage.setCreateTime(createTime);
			locationMessage.setMsgType(msgType);
			locationMessage.setMsgId(msgId);

			locationMessage.setLocation_X(location_X);
			locationMessage.setLocation_Y(location_Y);
			locationMessage.setScale(scale);
			locationMessage.setLabel(label);
		}
		return locationMessage;
	}

	public static void main(String[] args) throws Exception {
		// 接收普通消息
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1351776360</CreateTime><MsgType><![CDATA[location]]></MsgType><Location_X>23.134521</Location_X><Location_Y>113.358803</Location_Y><Scale>20</Scale><Label><![CDATA[位置信息]]></Label><MsgId>1234567890123456</MsgId></xml> ";
		System.out.println(LocationMessage.parseMessage(xml));
	}
}
