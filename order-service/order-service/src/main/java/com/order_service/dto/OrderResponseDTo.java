package com.order_service.dto;


public class OrderResponseDTo {
	
	private Long orderid;
	private Long productid;
	private int quantity;
	private double totalPrice;
	
	private String productname;
	private double price;
	
	public OrderResponseDTo() {}

	public OrderResponseDTo(Long orderid, Long productid, int quantity, double totalPrice, String productname,
			double price) {
		super();
		this.orderid = orderid;
		this.productid = productid;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.productname = productname;
		this.price = price;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
