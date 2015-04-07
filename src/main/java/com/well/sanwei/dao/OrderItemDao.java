package com.well.sanwei.dao;

import com.well.sanwei.model.OrderItem;

public interface OrderItemDao {
	
	public OrderItem queryById(int id);
	
	public void insert(OrderItem item);
	
	public int updateStausById(OrderItem item);
	
	public int deleteOrderItemById(int id);
}
