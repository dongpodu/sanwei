package com.sanwei.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanwei.base.BaseTest;
import com.well.sanwei.dao.OrderItemDao;
import com.well.sanwei.dao.OrderItemResourseDao;
import com.well.sanwei.dao.ResourceDao;
import com.well.sanwei.dao.UserDao;
import com.well.sanwei.model.Resource;
import com.well.sanwei.model.User;
import com.well.sanwei.view.OrderItemResourse;

public class TestResourceDao extends BaseTest {
	@Autowired
	OrderItemDao orderItemDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ResourceDao resourceDao;
	@Autowired
	OrderItemResourseDao orderItemResourseDao;
	
	@Test
	public void testInsert(){
//		System.out.println(orderItemRecourseDao.queryAll());
		User user = new User();
		user.setCity("合肥");
		user.setEmail("elisonwell@163.com");
		user.setName("杜移送");
		user.setPassword("11111111");
		user.setProfileUrl("eeeeeee");
		userDao.insert(user);
	}
	
	@Test
	public void testInsertResource(){
		Resource r = new Resource();
		/*r.setCoverUrl("CoverUrl");
		r.setCreatName("CreatName");
		r.setCreatorId(111);
		r.setFileType("jpg");
		r.setCourseName("CourseName");
		r.setResourceType("1");
		r.setPrimaryCategory("初中");
		r.setSencondCategory("初一");
		r.setThirdCategory("语文");
		r.setPage(1);*/
//		resourceDao.insert(r);
		System.out.println(resourceDao.queryList(r));
	}
	
	@Test
	public void testInsertOrderItem(){
		List<OrderItemResourse> list =  orderItemResourseDao.queryByUserId(8);
		for(OrderItemResourse item:list){
			System.out.println(item.getBuyUser().getCity());
		}
	}
}
