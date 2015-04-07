package com.well.sanwei.dao;

import com.well.sanwei.model.User;

public interface UserDao {
	
	public User queryById(int id);
	
	public User queryByEmail(String email);
	
	public int insert(User user);
	
}
