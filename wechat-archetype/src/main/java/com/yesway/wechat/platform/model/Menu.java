package com.yesway.wechat.platform.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	// 菜单项列表
	// 目前自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”
	private List<Button> button = new ArrayList<Button>();

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}

	public String buildJsonMenu() {
		StringBuilder result = new StringBuilder();
		result.append("{\"button\":[");
		if (button != null) {
			for (int i = 0; i < button.size(); i++) {
				Button btn = button.get(i);
				result.append("{");
				if (btn.getSub_button().size() == 0) {
					result.append("\"type\":\"" + btn.getType() + "\",");
					result.append("\"name\":\"" + btn.getName() + "\",");
					if ("click".equals(btn.getType())) {
						result.append("\"key\":\"" + btn.getKey() + "\"");
					} else {
						result.append("\"url\":\"" + btn.getUrl() + "\"");
					}
				} else {
					result.append("\"name\":\"" + btn.getName() + "\",");
					result.append("\"sub_button\":[");
					for (int j = 0; j < btn.getSub_button().size(); j++) {
						Button subButton = btn.getSub_button().get(j);
						result.append("{");
						result.append("\"type\":\"" + subButton.getType() + "\",");
						result.append("\"name\":\"" + subButton.getName() + "\",");
						if ("click".equals(subButton.getType())) {
							result.append("\"key\":\"" + subButton.getKey() + "\"");
						} else {
							result.append("\"url\":\"" + subButton.getUrl() + "\"");
						}
						result.append("}");
						if (j < btn.getSub_button().size() - 1) {
							result.append(",");
						}
					}
					result.append("]");
				}
				result.append("}");
				if (i < button.size() - 1) {
					result.append(",");
				}
			}
		}
		result.append("]}");
		return result.toString();
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		Button button1 = new Button();
		button1.setName("今日歌曲");
		button1.setType("click");
		button1.setKey("V1001_TODAY_MUSIC");

		Button button2 = new Button();
		button2.setName("歌手简介");
		button2.setType("click");
		button2.setKey("V1001_TODAY_SINGER");

		Button button3 = new Button();
		button3.setName("菜单");

		Button suButton1 = new Button();
		suButton1.setName("搜索");
		suButton1.setType("view");
		suButton1.setUrl("http://www.soso.com/");
		button3.getSub_button().add(suButton1);

		Button suButton2 = new Button();
		suButton2.setName("视频");
		suButton2.setType("view");
		suButton2.setUrl("http://v.qq.com/");
		button3.getSub_button().add(suButton2);

		Button suButton3 = new Button();
		suButton3.setName("赞一下我们");
		suButton3.setType("click");
		suButton3.setKey("V1001_GOOD");
		button3.getSub_button().add(suButton3);

		menu.getButton().add(button1);
		menu.getButton().add(button2);
		menu.getButton().add(button3);

		System.out.println(menu.buildJsonMenu());
	}
}
