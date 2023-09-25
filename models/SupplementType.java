package models;

public class SupplementType {
	
	private String supplementTypeID, supplementTypeName;

	public SupplementType(String supplementTypeID, String supplementTypeName) {
		super();
		this.supplementTypeID = supplementTypeID;
		this.supplementTypeName = supplementTypeName;
	}

	public String getSupplementTypeID() {
		return supplementTypeID;
	}

	public void setSupplementTypeID(String supplementTypeID) {
		this.supplementTypeID = supplementTypeID;
	}

	public String getSupplementTypeName() {
		return supplementTypeName;
	}

	public void setSupplementTypeName(String supplementTypeName) {
		this.supplementTypeName = supplementTypeName;
	}
	
	

}
