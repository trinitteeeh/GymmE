package views;

import components.ComponentMaker;
import controllers.AuthController;
import controllers.PageController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SignInView extends VBox implements ComponentMaker{
	
	private Label titleLbl, nameLbl1, nameLbl2;
	private Button signInBtn;
	private GridPane formPane;
	private FlowPane signupPane;
	private TextField emailTxt;
	private PasswordField passwordTxt;
	private VBox bannerPane;
	
	
	private void createTitle() {
		titleLbl = createLabel(PRIMARY, "Sign In", "Arial", true, 30);
	}
	
	private void createBanner() {
		bannerPane = new VBox(5);
		bannerPane.setPrefWidth(SCENE_WIDTH/2);
		bannerPane.setBackground(PRIMARY_BACKGROUND);
		
		nameLbl1 = createLabel(SECONDARY, "SUPLE", "Arial", true, 70);
		nameLbl2 = createLabel(BASE, "GAIN", "Arial", true, 70);
		
		bannerPane.getChildren().addAll(nameLbl1, nameLbl2);
		bannerPane.setAlignment(Pos.CENTER_LEFT);
		bannerPane.setPadding(new Insets(0, 0, 0, SCENE_WIDTH/10));
		
	}

	private void createForm() {	
		Label emailLbl = createLabel(PRIMARY, "Email", "Arial", true, 15);
		Label passwordLbl = createLabel(PRIMARY, "Password", "Arial", true, 15);
				
		emailTxt = createTextField(SCENE_WIDTH*0.35, 20, 10, WHITE, PRIMARY, "Arial", 10, false);
		passwordTxt = createPasswordField(SCENE_WIDTH*0.35, 20, 10, WHITE, PRIMARY);
		

		VBox emailPane = new VBox(5);
		VBox passwordPane = new VBox(5);
		formPane = new GridPane();

		emailPane.getChildren().addAll(emailLbl, emailTxt);
		passwordPane.getChildren().addAll(passwordLbl, passwordTxt);

		formPane.add(emailPane, 0, 0);
		formPane.add(passwordPane, 0, 1);

		GridPane.setMargin(emailPane, new Insets(0, 0, 7, 0));
		GridPane.setMargin(passwordPane, new Insets(0, 0, 7, 0));

		formPane.setAlignment(Pos.CENTER);
	}

	private void createSignInPane() {
		Label signupLbl = new Label("to Resgister");
		Hyperlink signupLink = new Hyperlink("Click Here");
		signupPane = new FlowPane();
		signupPane.getChildren().addAll(signupLink, signupLbl);
		signupPane.setAlignment(Pos.CENTER);

		signupLink.setOnAction(e -> PageController.getPageController().showSignUpView());
	}

	private void createSignUpButton() {
		signInBtn = createButton("Sign In", 167, 43, 10, 0, 0, PRIMARY, WHITE, WHITE, "Arial", 20, true);

		signInBtn.setOnAction(e -> {
			AuthController.getAuthController().signIn(emailTxt.getText(), passwordTxt.getText());
		});
		signInBtn.setCursor(Cursor.HAND);
	}


	private void createSignInView() {
		createBanner();
		createTitle();
		createForm();
		createSignInPane();
		createSignUpButton();
		
		VBox signInPane = new VBox(9);
		signInPane.getChildren().addAll(titleLbl, formPane, signupPane, signInBtn);
		signInPane.setAlignment(Pos.CENTER);
		signInPane.setPrefWidth(SCENE_WIDTH/2);
		signInPane.setPrefHeight(SCENE_HEIGHT);
		
		HBox root = new HBox();
		root.getChildren().addAll(bannerPane, signInPane);
		this.getChildren().add(root);
		this.setAlignment(Pos.CENTER);
		this.setBackground(WHITE_BACKGROUND);
	}

	
	public SignInView() {
		createSignInView();
	}
	
}
