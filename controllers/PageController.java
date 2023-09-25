package controllers;

import javafx.collections.ObservableList;
import main.Main;
import views.HomeView;
import views.ManageSupplementView;
import views.ManageUserView;
import views.SignInView;
import views.SignUpView;
import views.TransactionView;


public class PageController {
	private volatile static PageController instance;

	private PageController() {}

	public static PageController getPageController() {
		if(instance == null) {
			synchronized (PageController.class) {
				if(instance == null) instance = new PageController();
			}
		}
		return instance;
	}
	
	public void showSignUpView() {
		Main.root.getChildren().add(new SignUpView());
	}
	
	public void showSignInView() {
		Main.root.getChildren().add(new SignInView());
	}
	
	public void showHomeView() {
		Main.root.getChildren().add(new HomeView());
	}
	
	public void showTransactionView() {
		Main.root.getChildren().add(new TransactionView());
	}
	
	public void showSupplementPage() {
		Main.root.getChildren().add(new ManageSupplementView());
	}
	
	public void showUserPage() {
		Main.root.getChildren().add(new ManageUserView());
	}
	

}
