package com.well.sanwei.view;

import com.well.sanwei.model.OrderItem;
import com.well.sanwei.model.Resource;
import com.well.sanwei.model.User;

public class OrderItemResourse extends OrderItem{
	private Resource resource;	//课程实体类
	private User buyUser;		//课程购买者用户实体类

	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public User getBuyUser() {
		return buyUser;
	}
	public void setBuyUser(User buyUser) {
		this.buyUser = buyUser;
	}
}
