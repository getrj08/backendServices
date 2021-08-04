package com.rj.library.shopping.service;

import java.util.List;

import com.rj.library.shopping.models.OrderModel;

public interface IOrderService {
	
	public String placeOrder(List<OrderModel> orders);

}
