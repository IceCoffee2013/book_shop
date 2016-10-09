package net.shop.vo;


public class WishlistVO {
	private int number;
	private String userEmail;
	private int boardNumber;
	private int price;
	private String imagePath;
	private String title;
	
	public WishlistVO(){
		super();
	}
	public WishlistVO(String userEmail, int boardNumber) {
		super();
		this.userEmail = userEmail;
		this.boardNumber = boardNumber;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
