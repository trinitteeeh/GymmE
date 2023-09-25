package models;

public class Cart {
	private String userID, supplementID;
	private int quantity;
	public Cart(String userID, String supplementID, int quantity) {
		super();
		this.userID = userID;
		this.supplementID = supplementID;
		this.quantity = quantity;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getSupplementID() {
		return supplementID;
	}
	public void setSupplementID(String supplementID) {
		this.supplementID = supplementID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
