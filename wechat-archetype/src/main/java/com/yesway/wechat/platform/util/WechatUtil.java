package com.yesway.wechat.platform.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yesway.wechat.platform.model.Group;
import com.yesway.wechat.platform.model.GroupList;
import com.yesway.wechat.platform.model.Media;
import com.yesway.wechat.platform.model.MenuList;
import com.yesway.wechat.platform.model.User;
import com.yesway.wechat.platform.model.UserList;

public class WechatUtil {
	private static final Logger log = LoggerFactory.getLogger(WechatUtil.class);

	// 获取access token方法 有效期为7200秒，返回json
	public static String getAccessTokenReturnJson(String appid, String secret) {
		String message = "";
		if (appid != null && appid.length() > 0 && secret != null && secret.length() > 0) {
			String url = WechatConstants.TOKEN_URL + "?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
			message = HttpClientUtil.doSSLGet(url, null, WechatConstants.CHARSET);
		}
		return message;
	}

	// 获取access token 有效期为7200秒，返回access_token
	public static String getAccessToken(String appid, String secret) {
		String access_token = "";
		String message = getAccessTokenReturnJson(appid, secret);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("access_token") != null && (String) map.get("access_token") != "") {
					access_token = (String) map.get("access_token");
				}
			} catch (Exception e) {
				log.error("getAccessToken error:", e);
			}
		}
		return access_token;
	}

	// 创建菜单，返回json结果
	public static String createMenuReturnJson(String access_token, String json) {
		String message = "";
		if (access_token != null && access_token.length() > 0) {
			String url = WechatConstants.MENU_CREATE_URL + "?access_token=" + access_token;
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json;charset=" + WechatConstants.CHARSET);
			message = HttpClientUtil.doSSLPost(url, null, json, WechatConstants.CHARSET);
		}
		return message;
	}

	// 创建菜单，返回boolean结果
	public static boolean createMenu(String access_token, String json) {
		boolean result = false;
		String message = createMenuReturnJson(access_token, json);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("errcode") != null && (Integer) map.get("errcode") == 0) {
					result = true;
				}
			} catch (Exception e) {
				log.error("createMenu error:", e);
			}
		}
		return result;
	}

	// 查询菜单，返回json结果
	public static String getMenuReturnJson(String access_token) {
		String message = "";
		if (access_token != null && access_token.length() > 0) {
			String url = WechatConstants.MENU_GET_URL + "?access_token=" + access_token;
			message = HttpClientUtil.doSSLGet(url, null, WechatConstants.CHARSET);
		}
		return message;
	}

	// 查询菜单，返回menulist对象
	public static MenuList getMenu(String access_token) {
		MenuList menuList = null;
		String message = getMenuReturnJson(access_token);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				menuList = mapper.readValue(message, MenuList.class);
			} catch (Exception e) {
				log.error("getMenu error:", e);
			}
		}
		return menuList;
	}

	// 删除菜单，返回json结果
	public static String deleteMenuReturnJson(String access_token) {
		String message = "";
		if (access_token != null && access_token.length() > 0) {
			String url = WechatConstants.MENU_DELETE_URL + "?access_token=" + access_token;
			message = HttpClientUtil.doSSLGet(url, null, WechatConstants.CHARSET);
		}
		return message;
	}

	// 删除菜单，返回boolean结果
	public static boolean deleteMenu(String access_token) {
		boolean result = false;
		String message = deleteMenuReturnJson(access_token);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("errcode") != null && (Integer) map.get("errcode") == 0) {
					result = true;
				}
			} catch (Exception e) {
				log.error("deleteMenu error:", e);
			}
		}
		return result;
	}

	// 发送客服消息，返回json结果
	public static String sendCustomMessageReturnJson(String access_token, String json) {
		String message = "";
		if (access_token != null && access_token.length() > 0) {
			String url = WechatConstants.MESSAGE_CUSTOM_SEND_URL + "?access_token=" + access_token;
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json;charset=" + WechatConstants.CHARSET);
			message = HttpClientUtil.doSSLPost(url, null, json, WechatConstants.CHARSET);
		}
		return message;
	}

	// 发送客服消息，返回boolean结果
	public static boolean sendCustomMessage(String access_token, String json) {
		boolean result = false;
		String message = sendCustomMessageReturnJson(access_token, json);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("errcode") != null && (Integer) map.get("errcode") == 0) {
					result = true;
				}
			} catch (Exception e) {
				log.error("sendCustomMessage error:", e);
			}
		}
		return result;
	}

	// 获取用户信息，返回json结果
	public static String getUserInfoReturnJson(String access_token, String openid) {
		String message = "";
		if (access_token != null && access_token.length() > 0 && openid != null && openid.length() > 0) {
			String url = WechatConstants.USER_INFO_URL + "?lang=zh_CN&access_token=" + access_token + "&openid=" + openid;
			message = HttpClientUtil.doSSLGet(url, null, WechatConstants.CHARSET);
		}
		return message;
	}

	// 获取用户信息，返回user对象
	public static User getUserInfo(String access_token, String openid) {
		User user = null;
		String message = getUserInfoReturnJson(access_token, openid);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				user = mapper.readValue(message, User.class);
			} catch (Exception e) {
				log.error("getUserInfo error:", e);
			}
		}
		return user;
	}

	// 获取关注者列表，一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求
	// 微信官方文档说明：关注者列表已返回完时，返回next_openid为空，与实际不符，实际始终返回，结束时与最后一个openid相同
	// 返回json结果
	public static String getUserListReturnJson(String access_token, String nextOpenid) {
		String message = "";
		if (access_token != null && access_token.length() > 0) {
			if (nextOpenid != null) {
				nextOpenid = "";
			}
			String url = WechatConstants.USER_GET_URL + "?access_token=" + access_token + "&next_openid=" + nextOpenid;
			message = HttpClientUtil.doSSLGet(url, null, WechatConstants.CHARSET);
		}
		return message;
	}

	// 获取关注者列表，返回关注者列表对象
	public static UserList getUserList(String access_token, String nextOpenid) {
		UserList userList = null;
		String message = getUserListReturnJson(access_token, nextOpenid);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				userList = mapper.readValue(message, UserList.class);
			} catch (Exception e) {
				log.error("getUserList error:", e);
			}
		}
		return userList;
	}

	// 创建二维码ticket，返回json结果
	public static String createQrcodeReturnJson(String access_token, String json) {
		String message = "";
		if (access_token != null && access_token.length() > 0 && json != null && json.length() > 0) {
			String url = WechatConstants.QRCODE_CREATE_URL + "?access_token=" + access_token;
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json;charset=" + WechatConstants.CHARSET);
			message = HttpClientUtil.doSSLPost(url, null, json, WechatConstants.CHARSET);
		}
		return message;
	}

	// 创建临时二维码ticket，返回ticket
	public static String createTempQrcode(String access_token, int expire_seconds, int scene_id) {
		String ticket = "";
		String json = "{\"expire_seconds\":" + expire_seconds + ",\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":" + scene_id + "}}}";
		String message = createQrcodeReturnJson(access_token, json);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("ticket") != null) {
					ticket = (String) map.get("ticket");
				}
			} catch (Exception e) {
				log.error("createTempQrcode error:", e);
			}
		}
		return ticket;
	}

	// 创建永久二维码ticket，返回ticket
	public static String createLimitQrcode(String access_token, int scene_id) {
		String ticket = "";
		String json = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":" + scene_id + "}}}";
		String message = createQrcodeReturnJson(access_token, json);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("ticket") != null) {
					ticket = (String) map.get("ticket");
				}
			} catch (Exception e) {
				log.error("createLimitQrcode error:", e);
			}
		}
		return ticket;
	}

	// 通过ticket获取二维码url地址
	public static String getQrcodeUrl(String ticket) {
		String url = "";
		try {
			url = WechatConstants.QRCODE_SHOW_URL + "?ticket=" + java.net.URLEncoder.encode(ticket, WechatConstants.CHARSET);
		} catch (Exception e) {
			log.error("showQrcode error:", e);
		}
		return url;
	}

	// 保存二维码到本地文件
	public static boolean saveQrcode(String ticket, String toFilePath) {
		boolean result = false;
		try {
			String url = WechatConstants.QRCODE_SHOW_URL + "?ticket=" + java.net.URLEncoder.encode(ticket, WechatConstants.CHARSET);
			result = HttpClientUtil.doDownloadFile(url, toFilePath);
		} catch (Exception e) {
			log.error("saveQrcode error:", e);
		}
		return result;
	}

	// 创建分组，返回json结果
	public static String createGroupsReturnJson(String access_token, String json) {
		String message = "";
		if (access_token != null && access_token.length() > 0 && json != null && json.length() > 0) {
			String url = WechatConstants.GROUPS_CREATE_URL + "?access_token=" + access_token;
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json;charset=" + WechatConstants.CHARSET);
			message = HttpClientUtil.doSSLPost(url, null, json, WechatConstants.CHARSET);
		}
		return message;
	}

	// 创建分组，返回分组ID
	public static Integer createGroups(String access_token, String groupName) {
		Integer groupId = null;
		String json = "{\"group\":{\"name\":\"" + groupName + "\"}}";
		String message = createGroupsReturnJson(access_token, json);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("group") != null) {
					Map<String, Object> group = (Map<String, Object>) map.get("group");
					if (group.get("id") != null) {
						groupId = (Integer) group.get("id");
					}
				}
			} catch (Exception e) {
				log.error("createGroups error:", e);
			}
		}
		return groupId;
	}

	// 查询所有分组，返回json结果
	public static String getGroupsReturnJson(String access_token) {
		String message = "";
		if (access_token != null && access_token.length() > 0) {
			String url = WechatConstants.GROUPS_GET_URL + "?access_token=" + access_token;
			message = HttpClientUtil.doSSLGet(url, null, WechatConstants.CHARSET);
		}
		return message;
	}

	// 查询所有分组，返回group对象数组
	public static List<Group> getGroups(String access_token) {
		List<Group> groups = null;
		String message = getGroupsReturnJson(access_token);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				GroupList groupList = mapper.readValue(message, GroupList.class);
				if (groupList != null) {
					groups = groupList.getGroups();
				}
			} catch (Exception e) {
				log.error("getGroups error:", e);
			}
		}
		return groups;
	}

	// 查询用户所在分组，返回json结果
	public static String getUserGroupsIdReturnJson(String access_token, String json) {
		String message = "";
		if (access_token != null && access_token.length() > 0 && json != null && json.length() > 0) {
			String url = WechatConstants.GROUPS_GETID_URL + "?access_token=" + access_token;
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json;charset=" + WechatConstants.CHARSET);
			message = HttpClientUtil.doSSLPost(url, null, json, WechatConstants.CHARSET);
		}
		return message;
	}

	// 查询用户所在分组，返回用户所在分组ID
	public static Integer getUserGroupsId(String access_token, String openid) {
		Integer groupId = null;
		String json = "{\"openid\":\"" + openid + "\"}";
		String message = getUserGroupsIdReturnJson(access_token, json);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("groupid") != null) {
					groupId = (Integer) map.get("groupid");
				}
			} catch (Exception e) {
				log.error("getUserGroupsId error:", e);
			}
		}
		return groupId;
	}

	// 修改分组名，返回json结果
	public static String updateGroupsReturnJson(String access_token, String json) {
		String message = "";
		if (access_token != null && access_token.length() > 0 && json != null && json.length() > 0) {
			String url = WechatConstants.GROUPS_UPDATE_URL + "?access_token=" + access_token;
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json;charset=" + WechatConstants.CHARSET);
			message = HttpClientUtil.doSSLPost(url, null, json, WechatConstants.CHARSET);
		}
		return message;
	}

	// 修改分组名，返回boolean结果
	public static boolean updateGroups(String access_token, int groupId, String groupName) {
		boolean result = false;
		String json = "{\"group\":{\"id\":" + groupId + ",\"name\":\"" + groupName + "\"}}";
		String message = updateGroupsReturnJson(access_token, json);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("errcode") != null && (Integer) map.get("errcode") == 0) {
					result = true;
				}
			} catch (Exception e) {
				log.error("updateGroups error:", e);
			}
		}
		return result;
	}

	// 移动用户分组，返回json结果
	public static String updateUserGroupsReturnJson(String access_token, String json) {
		String message = "";
		if (access_token != null && access_token.length() > 0 && json != null && json.length() > 0) {
			String url = WechatConstants.GROUPS_MEMBERS_UPDATE_URL + "?access_token=" + access_token;
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json;charset=" + WechatConstants.CHARSET);
			message = HttpClientUtil.doSSLPost(url, null, json, WechatConstants.CHARSET);
		}
		return message;
	}

	// 移动用户分组，返回boolean结果
	public static boolean updateMembersGroups(String access_token, String openid, int to_groupid) {
		boolean result = false;
		String json = "{\"openid\":\"" + openid + "\",\"to_groupid\":" + to_groupid + "}";
		String message = updateUserGroupsReturnJson(access_token, json);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(message, Map.class);
				if (map.get("errcode") != null && (Integer) map.get("errcode") == 0) {
					result = true;
				}
			} catch (Exception e) {
				log.error("updateGroups error:", e);
			}
		}
		return result;
	}

	// 上传多媒体文件，返回json结果
	public static String uploadMediaReturnJson(String access_token, String type, String fileName) {
		String message = "";
		if (access_token != null && access_token.length() > 0 && type != null && type.length() > 0 && fileName != null && fileName.length() > 0) {
			String url = WechatConstants.MEDIA_UPLOAD_URL + "?access_token=" + access_token + "&type=" + type;
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "multipart/form-data");
			message = HttpClientUtil.doFileUpload(url, headers, fileName, WechatConstants.CHARSET);
		}
		return message;
	}

	// 上传多媒体文件，返回media对象
	public static Media uploadMedia(String access_token, String type, String fileName) {
		Media media = null;
		String message = uploadMediaReturnJson(access_token, type, fileName);
		if (message != null && message.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				media = mapper.readValue(message, Media.class);
			} catch (Exception e) {
				log.error("uploadMedia error:", e);
			}
		}
		return media;
	}

	// 获取多媒体文件url地址
	public static String getMediaUrl(String access_token, String media_id) {
		String url = "";
		try {
			url = WechatConstants.MEDIA_GET_URL + "?access_token=" + access_token + "&media_id=" + media_id;
		} catch (Exception e) {
			log.error("getMedia error:", e);
		}
		return url;
	}

	// 保存多媒体文件到本地文件
	public static boolean saveMedia(String access_token, String media_id, String toFilePath) {
		boolean result = false;
		try {
			String url = WechatConstants.MEDIA_GET_URL + "?access_token=" + access_token + "&media_id=" + media_id;
			result = HttpClientUtil.doDownloadFile(url, toFilePath);
		} catch (Exception e) {
			log.error("saveMedia error:", e);
		}
		return result;
	}

	public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
		boolean result = false;
		try {
			if (token != null && signature != null && timestamp != null && nonce != null) {
				// 将token、timestamp、nonce三个参数进行字典序排序
				String[] arr = new String[] { token, timestamp, nonce };
				Arrays.sort(arr);
				// 将三个参数字符串拼接成一个字符串进行sha1加密
				StringBuilder content = new StringBuilder();
				for (String str : arr) {
					content.append(str);
				}
				MessageDigest md = MessageDigest.getInstance("SHA-1");
				byte[] digest = md.digest(content.toString().getBytes());
				// 获得加密后的字符串与signature对比，标识该请求来源于微信
				String tmpStr = byte2hex(digest);
				if (tmpStr.equalsIgnoreCase(signature)) {
					result = true;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			log.error("checkSignature error:", e);
		}
		return result;
	}

	// 发货通知，返回json结果
	public static String deliverNotify(String access_token, String json) {
		String message = "";
		if (access_token != null && access_token.length() > 0) {
			String url = WechatConstants.DELIVERNOTIFY_URL + "?access_token=" + access_token;
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json;charset=" + WechatConstants.CHARSET);
			message = HttpClientUtil.doSSLPost(url, null, json, WechatConstants.CHARSET);
		}
		return message;
	}
	
	// 二进制数组转成16进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0xFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs;
	}

	public static void main(String[] args) throws Exception {
		String appid = "wxad0d997ca18e246a";
		String appsecret = "e1cd1280337d7997390b85f0bdd2972f";
		String appkey = "zhZ8t4YtHAEWN0lIlKJ1SloE3OpHyG4FfO3hlKQbm4cG694grM2F0QxsfpgsbJ6YtgBuIY27YXf5Pu9hUz732Oq4rqa8cd0OuYNLULvpKpXJ5mAyAjSwG2aME7xCqS7I";
		//String apptoken = "gbookWeiXin95190";
		//String access_token = getAccessToken(appid, appsecret);
		String access_token = "MDnV1IcblxsgEmI2G8nrf2Focyr-Ht5_QLN1wX5WUd588P5pjXDaV8_gJdpuIgJJF4gPq5iq4Yo0okbKEmME6cVkaNL-USQTeiYaUb7BcyU";
		if (access_token != null && !access_token.equals("")) {
			String openid = "oMQUCt65z4SGPc-x9kpQdbaiB3WU";
			String transid = "1220652101201410153210529399";
			String out_trade_no = "20141015184955";
			String deliver_timestamp = (new Date()).getTime() / 1000 + "";
			String deliver_status = "1";
			String deliver_msg = "OK";
			String sign_method = "sha1";
			// 计算签名
			String paramStr = "appid=" + appid + "&appkey=" + appkey + "&deliver_msg=" + deliver_msg + "&deliver_status=" + deliver_status + "&deliver_timestamp=" + deliver_timestamp + "&openid=" + openid + "&out_trade_no=" + out_trade_no + "&transid=" + transid;
			MessageDigest md = MessageDigest.getInstance(sign_method);
			md.update(paramStr.getBytes(WechatConstants.CHARSET));
			String app_signature = byte2hex(md.digest());

			StringBuffer postData = new StringBuffer();

			postData.append("{");
			postData.append("\"appid\":\"" + appid + "\",");
			postData.append("\"openid\":\"" + openid + "\",");
			postData.append("\"transid\":\"" + transid + "\",");
			postData.append("\"out_trade_no\":\"" + out_trade_no + "\",");
			postData.append("\"deliver_timestamp\":\"" + deliver_timestamp + "\",");
			postData.append("\"deliver_status\":\"" + deliver_status + "\",");
			postData.append("\"deliver_msg\":\"" + deliver_msg + "\",");
			postData.append("\"app_signature\":\"" + app_signature + "\",");
			postData.append("\"sign_method\":\"" + sign_method + "\"");
			postData.append("}");
			
			System.out.println("postData:" + postData);

			deliverNotify(access_token, postData.toString());
		} else {
			System.out.print("获取access_token失败");
		}
	}
}
