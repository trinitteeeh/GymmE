package controllers;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import models.Supplement;
import models.User;
import repositories.UserDAO;

public class UserController {
	private volatile static UserController instance;
	private UserController() {}
	private UserDAO dao = new UserDAO();

	public static UserController getUserController() {
		if(instance == null) {
			synchronized (UserController.class) {
				if(instance == null) instance = new UserController();
			}
		}
		return instance;
	}

	public List<User> findAll(){
		return dao.findAll();
	}

	public User find(String id) {
		return dao.findById(id);
	}

	public void save(String userName, String userEmail, String userPassword, RadioButton userGender,
			LocalDate userDOB, String userRole) {	
		String userID =dao.createID("UserID", "US", "MsUser");
		Alert prompt = validate(userName, userEmail, userPassword, userGender, userDOB, userRole);
		if(prompt.getAlertType().equals(AlertType.INFORMATION)) {
			User user =  new User(userID, userName, userEmail, userPassword, userGender.getText(), userDOB, userRole);
			dao.save(user);			
		}
		prompt.showAndWait();
	}

	public void update(String userID, String userName, String userEmail, String userPassword, RadioButton userGender,
			LocalDate userDOB, String userRole) {
		Alert prompt = validate(userID, userName, userEmail, userPassword, userGender, userDOB, userRole);
		if(prompt.getAlertType().equals(AlertType.INFORMATION)) {
			User user = find(userID);
			user.setUserName(userName);
			user.setUserEmail(userEmail);
			user.setUserPassword(userPassword);
			user.setUserGender(userGender.getText());
			user.setUserDOB(userDOB);
			user.setUserRole(userRole);
			dao.update(user);	
		}
		prompt.showAndWait();
	}

	public void delete(String userID) {
		Alert prompt = validate(userID);
		if(prompt.getAlertType().equals(AlertType.INFORMATION)) {
			User user = find(userID);
			dao.delete(user);
		}
		prompt.showAndWait();
		
	}
	
	private Alert validate(String name, String email, String password, RadioButton gender, LocalDate dob, String role) {
		Alert prompt;
		if (name.isEmpty() || name.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User name is empty!", ButtonType.OK);
		}
		else if (email.isEmpty() || email.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User email is empty!", ButtonType.OK);
		}
		else if (password.isEmpty() || password.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User password is empty!", ButtonType.OK);
		}
		else if(gender == null) {
			prompt = new Alert(AlertType.ERROR, "User gender is empty!", ButtonType.OK);
		}
		else if(dob == null) {
			prompt = new Alert(AlertType.ERROR, "User date of birth is empty!", ButtonType.OK);
		}
		else if (role.isEmpty() || role.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User role is empty!", ButtonType.OK);
		}
		else {
			prompt = new Alert(AlertType.INFORMATION, "User Added", ButtonType.OK);
		}
		return prompt;
	}
	
	private Alert validate(String id, String name, String email, String password, RadioButton gender, LocalDate dob, String role) {
		Alert prompt;
		if (id.isEmpty() || id.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User id is empty!", ButtonType.OK);
		}
		else if (name.isEmpty() || name.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User name is empty!", ButtonType.OK);
		}
		else if (email.isEmpty() || email.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User email is empty!", ButtonType.OK);
		}
		else if (password.isEmpty() || password.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User password is empty!", ButtonType.OK);
		}
		else if(gender == null) {
			prompt = new Alert(AlertType.ERROR, "User gender is empty!", ButtonType.OK);
		}
		else if(dob == null) {
			prompt = new Alert(AlertType.ERROR, "User date of birth is empty!", ButtonType.OK);
		}
		else if (role.isEmpty() || role.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User role is empty!", ButtonType.OK);
		}
		else {
			prompt = new Alert(AlertType.INFORMATION, "User Updated", ButtonType.OK);
		}
		return prompt;
	}
	
	private Alert validate(String id) {
		Alert prompt;
		if (id.isEmpty() || id.equals("")) {
			prompt = new Alert(AlertType.ERROR, "User id is empty!", ButtonType.OK);
		}
		else {
			prompt = new Alert(AlertType.INFORMATION, "User Deleted", ButtonType.OK);
		}
		return prompt;
	}
}
