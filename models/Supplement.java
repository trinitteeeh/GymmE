package models;

import java.time.LocalDate;

public class Supplement {
	private String supplementID, supplementName;
	private LocalDate supplementExpiryDate;
	private int supplementPrice, supplementStock;
	private String supplementTypeID;
	
	public Supplement(String supplementID, String supplementName, LocalDate supplementExpiryDate, int supplementPrice,
			int supplementStock, String supplementTypeID) {
		super();
		this.supplementID = supplementID;
		this.supplementName = supplementName;
		this.supplementExpiryDate = supplementExpiryDate;
		this.supplementPrice = supplementPrice;
		this.supplementStock = supplementStock;
		this.supplementTypeID = supplementTypeID;
	}

	public String getSupplementID() {
		return supplementID;
	}

	public void setSupplementID(String supplementID) {
		this.supplementID = supplementID;
	}

	public String getSupplementName() {
		return supplementName;
	}

	public void setSupplementName(String supplementName) {
		this.supplementName = supplementName;
	}

	public LocalDate getSupplementExpiryDate() {
		return supplementExpiryDate;
	}

	public void setSupplementExpiryDate(LocalDate supplementExpiryDate) {
		this.supplementExpiryDate = supplementExpiryDate;
	}

	public int getSupplementPrice() {
		return supplementPrice;
	}

	public void setSupplementPrice(int supplementPrice) {
		this.supplementPrice = supplementPrice;
	}

	public int getSupplementStock() {
		return supplementStock;
	}

	public void setSupplementStock(int supplementStock) {
		this.supplementStock = supplementStock;
	}

	public String getSupplementTypeID() {
		return supplementTypeID;
	}

	public void setSupplementTypeID(String supplementTypeID) {
		this.supplementTypeID = supplementTypeID;
	}
}
