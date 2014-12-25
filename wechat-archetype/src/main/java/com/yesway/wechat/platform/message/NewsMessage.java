package com.yesway.wechat.platform.message;

import java.util.ArrayList;
import java.util.List;

public class NewsMessage extends BaseMessage {
	// 图文消息列表
	private List<Article> articles = new ArrayList<Article>();

	public NewsMessage() {
		msgType = "news";
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public int getArticleCount() {
		return articles.size();
	}

	public String buildXmlMessage() {
		StringBuilder result = new StringBuilder();
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
		result.append("<ArticleCount>" + getArticleCount() + "</ArticleCount>");
		result.append("<Articles>");
		for (Article article : articles) {
			result.append("<item>");
			result.append("<Title><![CDATA[" + article.getTitle() + "]]></Title>");
			result.append("<Description><![CDATA[" + article.getDescription() + "]]></Description>");
			result.append("<PicUrl><![CDATA[" + article.getPicUrl() + "]]></PicUrl>");
			result.append("<Url><![CDATA[" + article.getUrl() + "]]></Url>");
			result.append("</item>");
		}
		result.append("</Articles>");
		result.append("</xml>");
		return result.toString();
	}

	public String buildJsonMessage() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		result.append("\"touser\":\"" + toUserName + "\",");
		result.append("\"msgtype\":\"" + msgType + "\",");
		result.append("\"news\":{\"articles\":[");
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			result.append("{");
			result.append("\"title\":\"" + article.getTitle() + "\",");
			result.append("\"description\":\"" + article.getDescription() + "\",");
			result.append("\"url\":\"" + article.getUrl() + "\",");
			result.append("\"picurl\":\"" + article.getPicUrl() + "\"");
			result.append("}");
			if (i < articles.size() - 1) {
				result.append(",");
			}
		}
		result.append("]}");
		result.append("}");
		return result.toString();
	}

	public static void main(String[] args) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName("toUser");
		newsMessage.setFromUserName("fromUser");
		newsMessage.setCreateTime("12345678");
		newsMessage.setMsgType("news");

		Article article1 = new Article();
		article1.setTitle("title1");
		article1.setDescription("description1");
		article1.setPicUrl("picurl");
		article1.setUrl("url");

		newsMessage.getArticles().add(article1);

		Article article2 = new Article();
		article2.setTitle("title");
		article2.setDescription("description");
		article2.setPicUrl("picurl");
		article2.setUrl("url");

		newsMessage.getArticles().add(article2);

		// 被动响应消息
		System.out.println(newsMessage.buildXmlMessage());
		// 发送客服消息
		System.out.println(newsMessage.buildJsonMessage());
	}
}
