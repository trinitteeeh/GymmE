package controllers;

import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import models.User;
import repositories.UserDAO;

public class AuthController {
	private volatile static AuthController instance;
	public User currentUser = new User("US002", "qwe", "qwe", "qwe", "Female", null, "Customer");
	private UserDAO userDAO = new UserDAO();

	private AuthController() {}

	public static AuthController getAuthController() {
		if(instance == null) {
			synchronized (AuthController.class) {
				if(instance == null) instance = new AuthController();
			}
		}
		return instance;
	}
	

	public void signUp(String userName, String userEmail, String userPassword, RadioButton userGender, LocalDate userDOB, String userRole) {
		Alert prompt = validateRegister(userName, userEmail, userPassword, userGender, userDOB);
		prompt.showAndWait();
		if (prompt.getAlertType().equals(AlertType.INFORMATION)) {
			String userID =userDAO.createID("UserID", "US", "MsUser");
			User user = new User(userID, userName, userEmail, userPassword, userGender.getText(), userDOB, "Customer"); 
			userDAO.save(user);
			PageController.getPageController().showSignInView();
		}
	}

	public void signIn(String email, String password) {
		Alert prompt = validateLogin(email, password);
		
		if (prompt.getAlertType().equals(AlertType.ERROR)) {
			prompt.showAndWait();
			return;
		}

		if (prompt.getAlertType().equals(AlertType.INFORMATION)) {
			if(currentUser.getUserRole().equals("Customer")) {
				PageController.getPageController().showHomeView();
			}
			else if(currentUser.getUserRole().equals("Admin")) {
				PageController.getPageController().showSupplementPage();
			}			
		}
	}

	public void signOut() {
		currentUser = null;
		PageController.getPageController().showSignInView();
	}

	private Alert validateRegister(String name, String email, String password, RadioButton gender, LocalDate userDOB) {
		Alert prompt;
		if (name.isEmpty() || name.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Name is empty!", ButtonType.OK);
		}
		else if (email.isEmpty() || email.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Email is empty!", ButtonType.OK);
		} 
		else if (password.isEmpty() || password.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Password is empty!", ButtonType.OK);
		}
		else if(gender == null) {
			prompt = new Alert(AlertType.ERROR, "Gender is empty!", ButtonType.OK);
		}
		else if(userDOB == null) {
			prompt = new Alert(AlertType.ERROR, "Date of Birth is empty!", ButtonType.OK);
		}
		else {
			User user = (User) userDAO.select(email);
			if (user != null) {
				prompt = new Alert(AlertType.ERROR, "Email has already been used", ButtonType.OK);
			}  else {
				prompt = new Alert(AlertType.INFORMATION, "Sucess");
			}
		}	
		return prompt;
	}

	private Alert validateLogin(String email, String password) {
		Alert prompt;
		if (email.isEmpty() || email.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Email is empty!", ButtonType.OK);
		} 
		else if (password.isEmpty() || password.equals("")) {
			prompt = new Alert(AlertType.ERROR, "Password is empty!", ButtonType.OK);
		} 
		else {
			User user = (User) userDAO.select(email, password);
			if (user == null) {
				prompt = new Alert(AlertType.ERROR, "User not Found", ButtonType.OK);
			}  else {
				prompt = new Alert(AlertType.INFORMATION, "Success");
				currentUser = user;
			}
		}	
		return prompt;
	}

	public User getCurrentUser() {
		return this.currentUser;
	}
}
