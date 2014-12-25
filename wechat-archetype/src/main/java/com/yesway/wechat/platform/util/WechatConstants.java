package com.yesway.wechat.platform.util;

public class WechatConstants {

	// 消息类型：文本
	public static final String MESSAGE_TYPE_TEXT = "text";
	// 消息类型：图片
	public static final String MESSAGE_TYPE_IMAGE = "image";
	// 消息类型：音频
	public static final String MESSAGE_TYPE_VOICE = "voice";
	// 消息类型：视频
	public static final String MESSAGE_TYPE_VIDEO = "video";
	// 消息类型：音乐
	public static final String MESSAGE_TYPE_MUSIC = "music";
	// 消息类型：图文
	public static final String MESSAGE_TYPE_NEWS = "news";
	// 消息类型：链接
	public static final String MESSAGE_TYPE_LINK = "link";
	// 消息类型：地理位置
	public static final String MESSAGE_TYPE_LOCATION = "location";
	// 消息类型：事件推送
	public static final String MESSAGE_TYPE_EVENT = "event";

	// 事件类型：subscribe(订阅)
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// 事件类型：unsubscribe(取消订阅)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// 事件类型：SCAN(用户已关注时的事件推送)
	public static final String EVENT_TYPE_SCAN = "SCAN";
	// 事件类型：LOCATION(上报地理位置事件)
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	// 事件类型：CLICK(自定义菜单点击事件)
	public static final String EVENT_TYPE_CLICK = "CLICK";
	// 事件类型：VIEW(点击菜单跳转链接时的事件推送)
	public static final String EVENT_TYPE_VIEW = "VIEW";

	// 获取access_token接口地址
	public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	// 发送客服消息接口地址
	public static final String MESSAGE_CUSTOM_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	// 获取用户基本信息接口地址
	public static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";
	// 获取关注者列表接口地址
	public static final String USER_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get";

	// 自定义菜单创建接口地址
	public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";
	// 自定义菜单查询接口地址
	public static final String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";
	// 自定义菜单删除接口地址
	public static final String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";

	// 创建二维码ticket接口地址
	public static final String QRCODE_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
	// 通过ticket换取二维码接口地址
	public static final String QRCODE_SHOW_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";

	// 创建分组接口地址
	public static final String GROUPS_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/groups/create";
	// 查询所有分组接口地址
	public static final String GROUPS_GET_URL = "https://api.weixin.qq.com/cgi-bin/groups/get";
	// 查询用户所在分组接口地址
	public static final String GROUPS_GETID_URL = "https://api.weixin.qq.com/cgi-bin/groups/getid";
	// 修改分组名接口地址
	public static final String GROUPS_UPDATE_URL = "https://api.weixin.qq.com/cgi-bin/groups/update";
	// 移动用户分组接口地址
	public static final String GROUPS_MEMBERS_UPDATE_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update";
	// 发货通知接口地址
	public static final String DELIVERNOTIFY_URL = "https://api.weixin.qq.com/pay/delivernotify";

	// 上传多媒体文件
	public static final String MEDIA_UPLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload";
	// 下载多媒体文件
	public static final String MEDIA_GET_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	
	//编码格式
	public static final String CHARSET = "utf-8";
}
