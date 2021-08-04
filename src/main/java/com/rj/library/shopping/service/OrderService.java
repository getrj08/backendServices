package com.rj.library.shopping.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.library.shopping.models.Book;
import com.rj.library.shopping.models.OrderEntity;
import com.rj.library.shopping.models.OrderModel;
import com.rj.library.shopping.repository.IBookRepository;
import com.rj.library.shopping.repository.IOrderRepository;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	IOrderRepository orderRepository;
	
	@Autowired
	IBookRepository bookRepository;

	@Override
	public String placeOrder(List<OrderModel> orders) {
		List<String> bookIdList = orders.stream().map(OrderModel::getId).collect(Collectors.toList());
		Iterable<Book> mongoBooks = bookRepository.findAllById(bookIdList);
		Map<String,Book> orderedBooks = new HashMap<>();
		mongoBooks.forEach(book -> {
			orderedBooks.put(book.getIsbn(),book);
		});
		
		int totalOrderPrice = 0;
		
		for(OrderModel order : orders) {
			int count = order.getCount();
			Book b = orderedBooks.get(order.getId());
			int totalBookPrice = count*b.getPrice();
			order.setTotalBookPrice(totalBookPrice);
			totalOrderPrice += totalBookPrice;
		}
		
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrder(orders);
		orderEntity.setOrderId(UUID.randomUUID().toString());
		orderEntity.setOrderDate(new Date());
		orderEntity.setTotal(totalOrderPrice);
		orderEntity.setPaymentId(UUID.randomUUID().toString());
		orderEntity.setUserId(getRandomUserName());
		
		OrderEntity confirmedOrder = orderRepository.save(orderEntity);
		
		
		return confirmedOrder.getOrderId();
	}
	
	private String getRandomUserName() {
		Random r = new Random();
		char c = (char)(new Random().nextInt(26) + 'a');
		return String.valueOf(c);
	}

	
	
}
