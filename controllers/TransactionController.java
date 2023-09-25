package controllers;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import models.Cart;
import models.Transaction;
import models.TransactionDetail;
import repositories.TransactionDAO;
import repositories.TransactionDetailDAO;

public class TransactionController {
	private volatile static TransactionController instance;
	private TransactionController() {}
	TransactionDAO dao = new TransactionDAO();
	TransactionDetailDAO daoDetail = new TransactionDetailDAO();

	public static TransactionController getTransactionController() {
		if(instance == null) {
			synchronized (TransactionController.class) {
				if(instance == null) instance = new TransactionController();
			}
		}
		return instance;
	}
	
	public List<Transaction> findAll(){
		return dao.findAll();
	}

	public List<Transaction> findByUserID(String userID) {
		return dao.findByUserID(userID);
	}
	
	public Transaction findByTransactionID(String userID) {
		return dao.findById(userID);
	}

	public void save(String userID, List<Cart> cartList) {
		Alert prompt;
		if(cartList.size() <=0) {
			prompt  = new Alert(AlertType.ERROR, "Cart is Empty", ButtonType.OK);
		}
		else {
			LocalDate transactionDate = LocalDate.now(); 
			String transactionID =dao.createID("TransactionID", "TR", "TransactionHeader");
			Transaction transaction =  new Transaction(transactionID, userID, transactionDate);
			dao.save(transaction);
			
			for(Cart c : cartList) {
				save(transactionID, c.getSupplementID(), c.getQuantity());
			}
			CartController.getCartController().delete(userID);
			prompt = new Alert(AlertType.INFORMATION, "Transaction Sucess", ButtonType.OK);
		}
		prompt.showAndWait();
		PageController.getPageController().showTransactionView();
	}

	public void update(String transactionID, String userID, LocalDate transactionDate) {
		Transaction transaction = findByTransactionID(transactionID);
		transaction.setUserID(userID);
		transaction.setTransactionDate(transactionDate);
		dao.update(transaction);
	}

	public void delete(String transactionID) {
		Transaction transaction = findByTransactionID(transactionID);
		dao.delete(transaction);
	}

	public List<TransactionDetail> findAll(String transactionID){
		return daoDetail.findAll(transactionID);
	}
	
	public TransactionDetail find(String transactionID, String supplementID) {
		return daoDetail.findById(transactionID, supplementID);
	}
	
	public void save(String transactionID, String supplementID, int quantity) {
		TransactionDetail transactionDetail = new TransactionDetail(transactionID, supplementID, quantity);
		daoDetail.save(transactionDetail);
	}
	
	public void update(String transactionID, String supplementID, int quantity) {
		TransactionDetail transactionDetail = find(transactionID, supplementID);
		transactionDetail.setQuantity(quantity);
		daoDetail.update(transactionDetail);
	}
	
	public void delete(String transactionID, String supplementID) {
		TransactionDetail transactionDetail = find(transactionID, supplementID);
		daoDetail.delete(transactionDetail);
	}
	
}
