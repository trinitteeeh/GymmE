package models;

import java.time.LocalDate;

public class Transaction {
	
	private String transactionID, userID;
	private LocalDate transactionDate;
	
	public Transaction(String transactionID, String userID, LocalDate transactionDate) {
		super();
		this.transactionID = transactionID;
		this.userID = userID;
		this.transactionDate = transactionDate;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
}
