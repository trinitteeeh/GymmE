package views;

import components.ComponentMaker;
import controllers.AuthController;
import controllers.PageController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SignUpView extends VBox implements ComponentMaker{
	
	private Label titleLbl, nameLbl1, nameLbl2;
	private Button signUpBtn;
	private GridPane formPane;
	private FlowPane signinPane;
	private TextField nameTxt, emailTxt;
	private PasswordField passwordTxt;
	private RadioButton maleBtn, femaleBtn;
	private DatePicker datePicker;
	private ToggleGroup genderGroup;
	private HBox genderBox;
	private VBox bannerPane;
	
	
	private void createTitle() {
		titleLbl = createLabel(PRIMARY, "Sign Up", "Arial", true, 30);
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
		Label nameLbl = createLabel(PRIMARY, "Name", "Arial", true, 15);
		Label emailLbl = createLabel(PRIMARY, "Email", "Arial", true, 15);
		Label passwordLbl = createLabel(PRIMARY, "Password", "Arial", true, 15);
		Label genderLbl = createLabel(PRIMARY, "Gender", "Arial", true, 15);
		Label dobLbl = createLabel(PRIMARY, "Date of Birth", "Arial", true, 15);
				
		emailTxt = createTextField(SCENE_WIDTH*0.35, 20, 10, WHITE, PRIMARY, "Arial", 10, false);
		nameTxt = createTextField(SCENE_WIDTH*0.35, 20, 10, WHITE, PRIMARY, "Arial", 10, false);
		passwordTxt = createPasswordField(SCENE_WIDTH*0.35, 20, 10, WHITE, PRIMARY);
		
		datePicker = new DatePicker();

		maleBtn = new RadioButton("Male");
		femaleBtn = new RadioButton("Female");
		genderGroup = new ToggleGroup();
		genderGroup.getToggles().addAll(maleBtn, femaleBtn);
		genderBox = new HBox(5);
		genderBox.getChildren().addAll(maleBtn, femaleBtn);

		VBox namePane = new VBox(5);
		VBox emailPane = new VBox(5);
		VBox passwordPane = new VBox(5);
		VBox genderPane = new VBox(5);
		VBox dobPane = new VBox(5);
		formPane = new GridPane();

		namePane.getChildren().addAll(nameLbl, nameTxt);
		emailPane.getChildren().addAll(emailLbl, emailTxt);
		passwordPane.getChildren().addAll(passwordLbl, passwordTxt);
		genderPane.getChildren().addAll(genderLbl, genderBox);
		dobPane.getChildren().addAll(dobLbl, datePicker);

		formPane.add(namePane, 0, 0);
		formPane.add(emailPane, 0, 1);
		formPane.add(passwordPane, 0, 2);
		formPane.add(genderPane, 0, 3);
		formPane.add(dobPane, 0, 4);

		GridPane.setMargin(namePane, new Insets(0, 0, 7, 0));
		GridPane.setMargin(emailPane, new Insets(0, 0, 7, 0));
		GridPane.setMargin(passwordPane, new Insets(0, 0, 7, 0));
		GridPane.setMargin(genderPane, new Insets(0, 0, 7, 0));
		GridPane.setMargin(dobPane, new Insets(0, 0, 7, 0));

		formPane.setAlignment(Pos.CENTER);
	}

	private void createSignInPane() {
		Label signinLbl = new Label("Already have an account?");
		Hyperlink signinLink = new Hyperlink("Log in");
		signinPane = new FlowPane();
		signinPane.getChildren().addAll(signinLbl, signinLink);
		signinPane.setAlignment(Pos.CENTER);

		signinLink.setOnAction(e -> PageController.getPageController().showSignInView());
	}

	private void createSignUpButton() {
		signUpBtn = createButton("Sign Up", 167, 43, 10, 0, 0, PRIMARY, WHITE, WHITE, "Arial", 20, true);

		signUpBtn.setOnAction(e -> {
			AuthController.getAuthController().signUp(nameTxt.getText(), emailTxt.getText(), passwordTxt.getText(), ((RadioButton)genderGroup.getSelectedToggle()), datePicker.getValue(), "Customer");
		});
		signUpBtn.setCursor(Cursor.HAND);
	}


	private void createSignUpView() {
		createBanner();
		createTitle();
		createForm();
		createSignInPane();
		createSignUpButton();
		
		VBox signUpPane = new VBox(9);
		signUpPane.getChildren().addAll(titleLbl, formPane, signinPane,signUpBtn);
		signUpPane.setAlignment(Pos.CENTER);
		signUpPane.setPrefWidth(SCENE_WIDTH/2);
		signUpPane.setPrefHeight(SCENE_HEIGHT);
		
		HBox root = new HBox();
		root.getChildren().addAll(bannerPane, signUpPane);
		this.getChildren().add(root);
		this.setAlignment(Pos.CENTER);
		this.setBackground(WHITE_BACKGROUND);
	}

	
	public SignUpView() {
		createSignUpView();
	}
	
}
