package com.well.sanwei.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.well.sanwei.dao.OrderItemDao;
import com.well.sanwei.dao.OrderItemResourseDao;
import com.well.sanwei.dao.ResourceDao;
import com.well.sanwei.model.OrderItem;
import com.well.sanwei.model.Resource;
import com.well.sanwei.model.User;
import com.well.sanwei.view.OrderItemResourse;

@Service
public class OrderItemService {
	
	@Autowired  
    private OrderItemResourseDao orderItemResourseDao;
	@Autowired
	private OrderItemDao orderItemDAO;
	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 查询用户所有的订单
	 * @param userId
	 * @return
	 */
    public List<OrderItemResourse> queryByUserId(int userId) {
		return orderItemResourseDao.queryByUserId(userId);  
    }
    
    /**
     * 查询未付款的订单
     * @param userId
     * @return
     */
    public List<OrderItemResourse> queryUncountedByUserId(int userId) {
		List<OrderItemResourse> list = orderItemResourseDao.queryByUserId(userId);
		List<OrderItemResourse> uncountedItem = new ArrayList<OrderItemResourse>();
		if(list==null || list.isEmpty()){
			return uncountedItem;
		}
		for(OrderItemResourse item:list){
			if(OrderItem.Status.未付款.getStatus().equals(item.getStatus())){
				uncountedItem.add(item);
			}
		}
		return uncountedItem;  
    }
    
	@Transactional
    public void deleteOrderItemById(int id) {  
		orderItemDAO.deleteOrderItemById(id);  
    }
    
    /**
     * 添加商品到购物车
     */
	@Transactional
    public boolean addOrderItem(int resourceId,User user){
		if(resourceId==0 || user==null){
			throw new IllegalArgumentException("参数resourceId不能为0，且user不能为空！");
		}
		Resource resource = resourceDao.queryById(resourceId);
		if(resource==null){
			return false;
		}
		OrderItem item = new OrderItem();
		item.setCourseId(resource.getId());
		item.setCreateTime(new Date());
		item.setNumber(1);
		item.setPrice(resource.getPrice());
		item.setStatus(OrderItem.Status.未付款.getStatus());
		item.setUpdateTime(new Date());
		item.setUserId(user.getId());
		item.setUserName(user.getName());
    	orderItemDAO.insert(item);
    	return true;
    }
    
    /**
     * 订单状态改变
     * @param otr
     */
    public void orderSubmit(int id,int status) {
    	OrderItemResourse otr = new OrderItemResourse();
		otr.setId(id);
		otr.setStatus(status);
		orderItemDAO.updateStausById(otr);  
    }
    
    /**
     * 订单结算
     */
    public BigDecimal orderSettleAccounts(List<OrderItemResourse> ords,HttpServletRequest request){
    	BigDecimal totalprice = new BigDecimal(0);
    	if(ords != null && ords.size()>0){
    		HttpSession session = request.getSession(false);
        	if(session!=null){
    			session.setAttribute("orderitems",ords);//把结算购物商品放入session以便后续会话取值
    		}
        	for(OrderItemResourse ord:ords){
//        		totalprice = totalprice.add(ord.getRecourse().getPrice());
        	}
    	}
    	//返回结算总价格
    	return totalprice;
    }
}
