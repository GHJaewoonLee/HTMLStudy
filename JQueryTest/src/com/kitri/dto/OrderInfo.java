package com.kitri.dto;

import java.util.Date;
import java.util.List;


public class OrderInfo {

	private int order_no;
	private Customer customer;
	private Date order_time;
	private List<OrderInfoDetail> details;

	
	public OrderInfo() {
	
	}

	public OrderInfo(int order_no, Customer customer, Date order_time, List<OrderInfoDetail> details) {
		this.order_no = order_no;
		this.customer = customer;
		this.order_time = order_time;
		this.details = details;
	}

	
	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public List<OrderInfoDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderInfoDetail> details) {
		this.details = details;
	}
}
