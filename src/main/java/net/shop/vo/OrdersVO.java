package net.shop.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersVO {
	
	private int number;
	private Date orderDate;
	private String status;
	private String userEmail;
	private String userName;
	private String receiver;
	private String address;
	private int postcode;
	private int goodNumber;
	private int amount;
	private int price;
	private int totalPrice;

	public OrdersVO() {
	}

	public OrdersVO(int goodNumber, int amount, int price, String userEmail, String userName, String address, int postcode, String receiver) {
		this();
		this.status = "ok";
		this.goodNumber = goodNumber;
		this.amount = amount;
		this.price = price;
		this.userEmail = userEmail;
		this.userName = userName;
		this.address = address;
		this.postcode = postcode;
		this.receiver = receiver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(int goodNumber) {
		this.goodNumber = goodNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
}
