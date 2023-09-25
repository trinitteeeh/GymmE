package models;

public class TransactionDetail {

	private String transactionID, supplementID;
	private int quantity;
	public TransactionDetail(String transactionID, String supplementID, int quantity) {
		super();
		this.transactionID = transactionID;
		this.supplementID = supplementID;
		this.quantity = quantity;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
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
