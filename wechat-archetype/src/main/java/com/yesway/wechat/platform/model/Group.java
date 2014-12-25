package com.yesway.wechat.platform.model;

public class Group {
	// 分组id，由微信分配
	private int id;
	// 分组名字，UTF8编码
	private String name;
	// 分组内用户数量
	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
