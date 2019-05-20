package com.kitri.service;

import com.kitri.dao.OrderDAO;
import com.kitri.dto.OrderInfo;


public class OrderService {

	private OrderDAO orderDAO;
	
	public OrderService() {
		orderDAO = new OrderDAO();
	}
	
	
	public void addOrder(OrderInfo info) {
		orderDAO.insert(info);
	}
}
