package com.ordermanagement.dto;

public class OrderDto {
	private Long userId;
	private Long tokenNumber;
	private String orderName;
	private int quantity;
	private double price;
	public Long getTokennumber() {
		return tokenNumber;
	}
	public void setTokennumber(Long tokennumber) {
		this.tokenNumber = tokennumber;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
