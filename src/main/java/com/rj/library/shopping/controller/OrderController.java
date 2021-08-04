package com.rj.library.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rj.library.shopping.models.OrderModel;
import com.rj.library.shopping.service.IOrderService;

@RestController
@CrossOrigin
@RequestMapping(value="/books")
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	
	@PutMapping(value="/order")
	public ResponseEntity<String> placeOrder(@RequestBody List<OrderModel> orders) {
		return ResponseEntity.ok().body(orderService.placeOrder(orders));
	}

}
