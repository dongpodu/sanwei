package com.well.sanwei.dao;

import java.util.List;

import com.well.sanwei.view.OrderItemResourse;

public interface OrderItemResourseDao {
	
	public List<OrderItemResourse> queryByUserId(int userId);
	
}
