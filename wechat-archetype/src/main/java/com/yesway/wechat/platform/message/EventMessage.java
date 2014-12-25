package com.yesway.wechat.platform.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class EventMessage extends BaseMessage {
	// 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	private String event;
	// subscribe事件KEY值，qrscene_为前缀，后面为二维码的参数值
	// CLICK事件KEY值，与自定义菜单接口中KEY值对应
	// VIEW事件KEY值，设置的跳转URL
	// SCAN事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
	private String eventKey;
	// 二维码的ticket，可用来换取二维码图片
	private String ticket;
	// 地理位置纬度
	private String latitude;
	// 地理位置经度
	private String longitude;
	// 地理位置精度
	private String precision;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public static EventMessage parseMessage(String xml) throws Exception {
		EventMessage eventMessage = null;
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement();
		if (rootElt != null) {
			String toUserName = rootElt.elementTextTrim("ToUserName");
			String fromUserName = rootElt.elementTextTrim("FromUserName");
			String createTime = rootElt.elementTextTrim("CreateTime");
			String msgType = rootElt.elementTextTrim("MsgType");
			String event = rootElt.elementTextTrim("Event");

			String eventKey = rootElt.elementTextTrim("EventKey");
			String ticket = rootElt.elementTextTrim("Ticket");

			String latitude = rootElt.elementTextTrim("Latitude");
			String longitude = rootElt.elementTextTrim("Longitude");
			String precision = rootElt.elementTextTrim("Precision");

			eventMessage = new EventMessage();
			eventMessage.setToUserName(toUserName);
			eventMessage.setFromUserName(fromUserName);
			eventMessage.setCreateTime(createTime);
			eventMessage.setMsgType(msgType);
			eventMessage.setEvent(event);

			eventMessage.setEventKey(eventKey);
			eventMessage.setTicket(ticket);

			eventMessage.setLatitude(latitude);
			eventMessage.setLongitude(longitude);
			eventMessage.setPrecision(precision);
		}
		return eventMessage;
	}

	public static void main(String[] args) throws Exception {
		// 关注/取消关注事件 subscribe unsubscribe
		// String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event></xml>";
		// 扫描带参数二维码事件 用户未关注时，进行关注后的事件推送 subscribe
		// String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[qrscene_123123]]></EventKey><Ticket><![CDATA[TICKET]]></Ticket></xml>";
		// 用户已关注时的事件推送 SCAN
		// String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[SCAN]]></Event><EventKey><![CDATA[SCENE_VALUE]]></EventKey><Ticket><![CDATA[TICKET]]></Ticket></xml>";
		// 上报地理位置事件 LOCATION
		// String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[LOCATION]]></Event><Latitude>23.137466</Latitude><Longitude>113.352425</Longitude><Precision>119.385040</Precision></xml>";
		// 自定义菜单事件 CLICK
		// String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[EVENTKEY]]></EventKey></xml>";
		// 点击菜单跳转链接时的事件推送 VIEW
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[VIEW]]></Event><EventKey><![CDATA[www.qq.com]]></EventKey></xml>";
		System.out.println(EventMessage.parseMessage(xml));

	}
}
