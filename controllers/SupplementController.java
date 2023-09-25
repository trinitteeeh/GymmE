package controllers;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import models.Supplement;
import repositories.SupplementDAO;

public class SupplementController {
	private volatile static SupplementController instance;
	private SupplementController() {}
	private SupplementDAO dao = new SupplementDAO();

	public static SupplementController getSupplementController() {
		if(instance == null) {
			synchronized (SupplementController.class) {
				if(instance == null) instance = new SupplementController();
			}
		}
		return instance;
	}
	
	public List<Supplement> findAll(){
		return dao.findAll();
	}
	
	public Supplement find(String id) {
		return dao.findById(id);
	}
	
	public void save(String supplementName, LocalDate supplementExpiryDate, int supplementPrice, int supplementStock, String supplementTypeID) {
		String supplementID =dao.createID("SupplementID", "SU", "MsSupplement");
		Alert prompt = validate(supplementName, supplementExpiryDate, supplementPrice, supplementStock, supplementTypeID);
		if(prompt.getAlertType().equals(AlertType.INFORMATION)){
			Supplement supplement = new Supplement(supplementID, supplementName, supplementExpiryDate, supplementPrice, supplementStock, supplementTypeID);
			dao.save(supplement);
		}
		prompt.showAndWait();
	}
	
	public void update(String supplementID, String supplementName, LocalDate supplementExpiryDate, int supplementPrice, int supplementStock, String supplementTypeID) {
		Alert prompt = validate(supplementID, supplementName, supplementExpiryDate, supplementPrice, supplementStock, supplementTypeID);
		if(prompt.getAlertType().equals(AlertType.INFORMATION)){
			Supplement supplement = find(supplementID);
			supplement.setSupplementName(supplementName);
			supplement.setSupplementExpiryDate(supplementExpiryDate);
			supplement.setSupplementPrice(supplementPrice);
			supplement.setSupplementStock(supplementStock);
			supplement.setSupplementTypeID(supplementTypeID);
			dao.update(supplement);
		}
		prompt.showAndWait();
	}
	
	public void delete(String supplementID) {
		Alert prompt = validate(supplementID);
		if(prompt.getAlertType().equals(AlertType.INFORMATION)){
			Supplement supplement = find(supplementID);
			dao.delete(supplement);
		}
		prompt.showAndWait();
	}
	
	private Alert validate(String supplementName, LocalDate supplementExpiryDate, int supplementPrice, int supplementStock, String supplementTypeID) {
		Alert prompt;
		if (supplementName.isEmpty() || supplementName.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Supplement name is empty!", ButtonType.OK);
		}
		else if (supplementExpiryDate == null) {
			prompt = new Alert(AlertType.ERROR, "Supplement expiry date is empty!", ButtonType.OK);
		}
		else if (supplementPrice <= 0 ) {
			prompt = new Alert(AlertType.ERROR, "Supplement price is empty!", ButtonType.OK);
		}
		else if (supplementStock <= 0) {
			prompt = new Alert(AlertType.ERROR, "Supplement stock is empty!", ButtonType.OK);
		}
		else if (supplementTypeID.isEmpty() || supplementTypeID.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Supplement type id is empty!", ButtonType.OK);
		}
		else {
			prompt = new Alert(AlertType.INFORMATION, "Product Added!", ButtonType.OK);
		}
		
		return prompt;
	}
	
	private Alert validate(String supplementID, String supplementName, LocalDate supplementExpiryDate, int supplementPrice, int supplementStock, String supplementTypeID) {
		Alert prompt;
		if (supplementID.isEmpty() || supplementID.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Supplement id is empty!", ButtonType.OK);
		}
		else if (supplementName.isEmpty() || supplementName.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Supplement name is empty!", ButtonType.OK);
		}
		else if (supplementExpiryDate == null) {
			prompt = new Alert(AlertType.ERROR, "Supplement expiry date is empty!", ButtonType.OK);
		}
		else if (supplementPrice <= 0 ) {
			prompt = new Alert(AlertType.ERROR, "Supplement price is empty!", ButtonType.OK);
		}
		else if (supplementStock <= 0) {
			prompt = new Alert(AlertType.ERROR, "Supplement stock is empty!", ButtonType.OK);
		}
		else if (supplementTypeID.isEmpty() || supplementTypeID.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Supplement type id is empty!", ButtonType.OK);
		}
		else {
			prompt = new Alert(AlertType.INFORMATION, "Product Updated!", ButtonType.OK);
		}
		
		return prompt;
	}
	
	private Alert validate(String supplementID) {
		Alert prompt;
		if (supplementID.isEmpty() || supplementID.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Supplement id is empty!", ButtonType.OK);
		}
		else {
			prompt = new Alert(AlertType.INFORMATION, "Product Deleted!", ButtonType.OK);
		}
		
		return prompt;
	}
}
