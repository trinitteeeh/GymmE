package controllers;

import java.time.LocalDate;
import java.util.List;

import models.SupplementType;
import repositories.SupplementTypeDAO;

public class SupplementTypeController {
	private volatile static SupplementTypeController instance;
	private SupplementTypeController() {}
	private SupplementTypeDAO dao = new SupplementTypeDAO();

	public static SupplementTypeController getSupplementTypeController() {
		if(instance == null) {
			synchronized (SupplementTypeController.class) {
				if(instance == null) instance = new SupplementTypeController();
			}
		}
		return instance;
	}
	
	public List<SupplementType> findAll(){
		return dao.findAll();
	}
	
	public SupplementType find(String id) {
		return dao.findById(id);
	}
	
	public void save(String supplementTypeName) {
		String supplementTypeID =dao.createID("SupplementTypeID", "SP", "MsSupplementType");
		SupplementType supplementType = new SupplementType(supplementTypeID, supplementTypeName);
		dao.save(supplementType);
	}
	
	public void update(String supplementTypeID, String supplementTypeName) {
		SupplementType supplementType = find(supplementTypeID);
		supplementType.setSupplementTypeName(supplementTypeName);
		dao.update(supplementType);
	}
	
	public void delete(String supplementTypeID) {
		SupplementType supplementType = find(supplementTypeID);
		dao.delete(supplementType);
	}
}
