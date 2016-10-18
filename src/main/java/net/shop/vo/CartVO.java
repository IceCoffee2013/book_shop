package net.shop.vo;

import java.io.Serializable;

/**
 * @author Langley
 * cart item.
 */
public class CartVO implements Serializable{

	private static final long serialVersionUID = 7189614286736101498L;
	
	private int number;
	private int goodNumber;
	private String goodName;
	private int quantity;
	private int price;
	private String userEmail;
	private String imagePath;

	public CartVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartVO(int goodNumber, int quantity, String userEmail) {
		super();
		this.goodNumber = goodNumber;
		this.quantity = quantity;
		this.userEmail = userEmail;
	}

	public int getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(int goodNumber) {
		this.goodNumber = goodNumber;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
