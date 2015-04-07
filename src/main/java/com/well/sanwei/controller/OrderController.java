package com.well.sanwei.controller;


import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.well.sanwei.dao.ResourceDao;
import com.well.sanwei.service.OrderItemService;
import com.well.sanwei.view.OrderItemResourse;

@Controller
@RequestMapping("/*")
public class OrderController extends BaseController{
	
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 查询当前登录人购物车里未支付的订单
	 * @return
	 */
	@RequestMapping("/orderview")
	public ModelAndView orderview(HttpServletRequest request){
		int userId = 0;
		if(getLoginUser() != null){
			userId = getLoginUser().getId();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("orderView");
		mv.addObject("orderItems", orderItemService.queryUncountedByUserId(userId));
		return mv;
	}
	
	/**
	 * 删除购物车里的订单
	 */
	@RequestMapping("/orderdelete")
	public String deleteOrder(int id){
		orderItemService.deleteOrderItemById(id);
		return "redirect:/orderview";
	}
	
	/**
	 * 订单结算
	 */
	public String orderSettleAccounts(List<OrderItemResourse> ords,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(ords != null && ords.size()>0){
			BigDecimal totalprice = orderItemService.orderSettleAccounts(ords, request);
			mv.addObject("OrderItemRecourses", totalprice);
		}
		return "orderview";
	}
	
	/**
	 * 订单提交
	 */
	@RequestMapping("/ordersubmit")
	public String orderSubmit(int id){
		orderItemService.orderSubmit(id,2);
		return "orderview";
	}
	
	/**
	 * 订单支付
	 */
	@RequestMapping("/orderpay")
	public String orderPay(int id){
		orderItemService.orderSubmit(id,3);
		return "orderview";
	}
	
	/**
	 * 订单取消
	 */
	@RequestMapping("/ordercancle")
	public String orderCancle(int id){
		orderItemService.orderSubmit(id,1);
		return "orderview";
	}
	
	/**
	 * 添加商品到购物车
	 */
	@RequestMapping("/orderadd")
	public String addOrderItem(int resourceId){
		orderItemService.addOrderItem(resourceId,getLoginUser());
		return "redirect:/orderview";
	}
}
