package com.java.jdbc.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Order {
	private int orderId;
	private String orderName;
	private Date orderDate;
	
	
	public Order() {
		super();
	}
	public Order(int orderId, String orderName, Date orderDate) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderName=" + orderName + ", orderDate=" + orderDate + "]";
	}
	
	
}