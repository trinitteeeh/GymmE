package controllers;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import models.Cart;
import models.Supplement;
import repositories.CartDAO;

public class CartController {
	private volatile static CartController instance;
	private CartController() {}
	private CartDAO dao = new CartDAO();

	public static CartController getCartController() {
		if(instance == null) {
			synchronized (CartController.class) {
				if(instance == null) instance = new CartController();
			}
		}
		return instance;
	}
	
	public List<Cart> findAll(String userID){
		return dao.findAll(userID);
	}
	
	public Cart find(String userID, String supplementID) {
		return dao.findById(userID, supplementID);
	}
	
	public void save(String userID, String supplementID, int quantity) {
		Cart c = find(userID, supplementID);
		Alert prompt = validate(supplementID, quantity, c);
		
		prompt.showAndWait();
		if (prompt.getAlertType().equals(AlertType.INFORMATION)) {
			if(c != null) {
				c.setQuantity(c.getQuantity() + quantity);
				dao.update(c);
			}
			else {				
				Cart cart = new Cart(userID, supplementID, quantity);
				dao.save(cart);
			}
		}
		
		
	}
	
	public void update(String userID, String supplementID, int quantity) {
		Cart cart = find(userID, supplementID);
		cart.setQuantity(quantity);
		dao.update(cart);
	}
	
	public void delete(String userID, String supplementID) {
		Cart cart = find(userID, supplementID);
		dao.delete(cart);
	}
	
	public void delete(String userID) {
		dao.delete(userID);
	}
	
	private Alert validate(String supplementID, int quantity, Cart c) {
		Alert prompt;
		if(quantity <= 0) {
			prompt = new Alert(AlertType.ERROR, "Quantity cannot be empty", ButtonType.OK);
		}
		else if(c != null) {
			Supplement sp = SupplementController.getSupplementController().find(c.getSupplementID());
			if(sp.getSupplementStock() < quantity + c.getQuantity()) {
				prompt = new Alert(AlertType.ERROR, "Stock is not Enough", ButtonType.OK);
			}
			else {
				prompt = new Alert(AlertType.INFORMATION, "Added to Cart");
			}
		}
		else {
			prompt = new Alert(AlertType.INFORMATION, "Added to Cart");
		}
		return prompt;
	}
}
