package views;

import java.time.LocalDate;
import java.util.List;

import components.AdminNavbar;
import components.ComponentMaker;
import controllers.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.User;

public class ManageUserView extends VBox implements ComponentMaker, EventHandler<ActionEvent>{
	private TableView<User> UserTable;
	private VBox controlPane, UserPane;
	private Button addBtn, removeBtn, updateBtn;
	private ObservableList<User> UserData;
	private HBox root;
	private TextField nameTxt, emailTxt, passwordTxt, roleTxt;
	private DatePicker dobTxt;
	private ToggleGroup genderGroup;
	private HBox genderBox;
	private RadioButton maleBtn, femaleBtn;

	@SuppressWarnings("unchecked")
	private void createUserTable() {
		List<User> UserList = UserController.getUserController().findAll();
		UserData = FXCollections.observableArrayList(UserList);

		UserTable = new TableView<User>();
		UserTable.setItems(UserData);
		UserTable.setMinHeight(SCENE_HEIGHT * 0.85);
		UserTable.setPrefHeight(SCENE_HEIGHT * 0.85);

		TableColumn<User, String>  c1 = new TableColumn<>("ID");
		c1.setCellValueFactory(new PropertyValueFactory<>("userID"));
		TableColumn<User, String>  c2 = new TableColumn<>("Name");
		c2.setCellValueFactory(new PropertyValueFactory<>("userName"));
		TableColumn<User, String>  c3 = new TableColumn<>("Email");
		c3.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
		TableColumn<User, String>  c4 = new TableColumn<>("Password");
		c4.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
		TableColumn<User, String>  c5 = new TableColumn<>("Gender");
		c5.setCellValueFactory(new PropertyValueFactory<>("userGender"));
		TableColumn<User, LocalDate>  c6 = new TableColumn<>("DOB");
		c6.setCellValueFactory(new PropertyValueFactory<>("userDOB"));
		TableColumn<User, String>  c7 = new TableColumn<>("Role");
		c7.setCellValueFactory(new PropertyValueFactory<>("userRole"));

		UserTable.getColumns().setAll(c1, c2, c3, c4, c5, c6, c7);

		UserPane = new VBox();
		UserPane.setPrefSize(SCENE_WIDTH*0.6, SCENE_HEIGHT);
		UserPane.setPadding(new Insets(20));
		UserPane.getChildren().add(UserTable);

		addTableListener();

	}

	private void addTableListener() {
		UserTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				nameTxt.setText(newValue.getUserName());
				emailTxt.setText(newValue.getUserEmail());
				passwordTxt.setText(newValue.getUserPassword());
				dobTxt.setValue(newValue.getUserDOB());
				roleTxt.setText(newValue.getUserRole());
				
				if(newValue.getUserGender().equals("Female")) {
					genderGroup.selectToggle(femaleBtn);
				}else {
					genderGroup.selectToggle(maleBtn);
				}
			}
		});
	}



	private void createControlPane() {
		Label titleLbl = createLabel(PRIMARY, "Users", "Arial", true, 20);
		
		Label nameLbl = createLabel(PRIMARY, "User Name", "Arial", true, 15);
		Label emailLbl = createLabel(PRIMARY, "User Email", "Arial", true, 15);
		Label passwordLbl = createLabel(PRIMARY, "User Password", "Arial", true, 15);
		Label genderLbl = createLabel(PRIMARY, "User Gender", "Arial", true, 15);
		Label dobLbl = createLabel(PRIMARY, "User DOB", "Arial", true, 15);
		Label roleLbl = createLabel(PRIMARY, "User Role", "Arial", true, 15);

		nameTxt= createTextField(445, 15, 10, WHITE, PRIMARY, "Arial", 15, false);
		emailTxt= createTextField(445, 15, 10, WHITE, PRIMARY, "Arial", 15, false);
		passwordTxt = createTextField(445, 15, 10, WHITE, PRIMARY, "Arial", 15, false);
		dobTxt = new DatePicker();
		roleTxt = createTextField(445, 15, 10, WHITE, PRIMARY, "Arial", 15, false);
		
		maleBtn = new RadioButton("Male");
		femaleBtn = new RadioButton("Female");
		genderGroup = new ToggleGroup();
		genderGroup.getToggles().addAll(maleBtn, femaleBtn);
		genderBox = new HBox(5);
		genderBox.getChildren().addAll(maleBtn, femaleBtn);

		VBox formPane = new VBox();
		
		VBox namePane = new VBox();
		VBox emailPane = new VBox();
		VBox passwordPane = new VBox();
		VBox genderPane = new VBox();
		VBox dobPane = new VBox();
		VBox rolePane = new VBox();

		namePane.getChildren().addAll(nameLbl, nameTxt);
		emailPane.getChildren().addAll(emailLbl, emailTxt);
		passwordPane.getChildren().addAll(passwordLbl, passwordTxt);
		genderPane.getChildren().addAll(genderLbl, genderBox);
		dobPane.getChildren().addAll(dobLbl, dobTxt);
		rolePane.getChildren().addAll(roleLbl, roleTxt);
		
		formPane.getChildren().addAll(namePane, emailPane, passwordPane, genderPane, dobPane, rolePane);
		formPane.setSpacing(10);

		addBtn =  createButton("Add", 445, 30, 10, 0, 0, PRIMARY, BLACK, WHITE, "Arial", 15, true);
		removeBtn = createButton("Remove", 445, 30, 10, 0, 0, SECONDARY, DARK_PURPLE, BLACK, "Arial", 15, true);
		updateBtn= createButton("Update", 445, 30, 10, 0, 0, SECONDARY, DARK_PURPLE, BLACK, "Arial", 15, true);

		addBtn.setOnAction(this);
		removeBtn.setOnAction(this);
		updateBtn.setOnAction(this);

		addBtn.setCursor(Cursor.HAND);
		removeBtn.setCursor(Cursor.HAND);
		updateBtn.setCursor(Cursor.HAND);

		VBox buttonPane = new VBox(10);
		buttonPane.getChildren().addAll(addBtn, removeBtn, updateBtn);

		controlPane = new VBox(15);
		controlPane.setPadding(new Insets(20));
		controlPane.getChildren().addAll(titleLbl, formPane, buttonPane);
		controlPane.setAlignment(Pos.TOP_CENTER);
		controlPane.setPrefSize(SCENE_WIDTH*0.4, SCENE_HEIGHT);
	}

	@Override
	public void handle(ActionEvent e) {
		String userName = nameTxt.getText();
		String userEmail= emailTxt.getText();
		String userPassword = passwordTxt.getText();
		RadioButton userGender= ((RadioButton)genderGroup.getSelectedToggle());
		LocalDate userDOB = dobTxt.getValue();
		String userRole = roleTxt.getText();

		if(e.getSource() == addBtn) {
			UserController.getUserController().save(userName, userEmail, userPassword, userGender, userDOB, userRole);
			refresh();
		}
		else if(e.getSource() == removeBtn) {
			String userID = UserTable.getSelectionModel().getSelectedItem().getUserID();
			UserController.getUserController().delete(userID);
			refresh();
		}
		else if(e.getSource() == updateBtn) {
			String userID = UserTable.getSelectionModel().getSelectedItem().getUserID();
			UserController.getUserController().update(userID, userName, userEmail, userPassword, userGender, userDOB, userRole);
			refresh();
		}
	}

	private void refresh() {
		List<User> UserList = UserController.getUserController().findAll();
		UserData = FXCollections.observableArrayList(UserList);
		UserTable.setItems(UserData);
	}

	private void createManageUserView() {
		createUserTable();
		createControlPane();

		root = new HBox();
		root.getChildren().addAll(UserPane, controlPane);
		this.setBackground(WHITE_BACKGROUND);
		this.getChildren().addAll(new AdminNavbar(), root);
	}


	public ManageUserView() {
		createManageUserView();
	}
}
