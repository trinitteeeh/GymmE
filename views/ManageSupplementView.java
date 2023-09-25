package views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import components.AdminNavbar;
import components.ComponentMaker;
import controllers.SupplementController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Supplement;

public class ManageSupplementView extends VBox implements ComponentMaker, EventHandler<ActionEvent>{
	private TableView<Supplement> SupplementTable;
	private VBox controlPane, SupplementPane;
	private Button addBtn, removeBtn, updateBtn;
	private ObservableList<Supplement> SupplementData;
	private HBox root;
	private TextField nameTxt, priceTxt, stockTxt, typeTxt;
	private DatePicker expiryDateTxt;

	@SuppressWarnings("unchecked")
	private void createSupplementTable() {
		List<Supplement> SupplementList = SupplementController.getSupplementController().findAll();
		SupplementData = FXCollections.observableArrayList(SupplementList);

		SupplementTable = new TableView<Supplement>();
		SupplementTable.setItems(SupplementData);
		SupplementTable.setMinHeight(SCENE_HEIGHT * 0.85);
		SupplementTable.setPrefHeight(SCENE_HEIGHT * 0.85);

		TableColumn<Supplement, String>  c1 = new TableColumn<>("ID");
		c1.setCellValueFactory(new PropertyValueFactory<>("supplementID"));
		TableColumn<Supplement, String>  c2 = new TableColumn<>("Name");
		c2.setCellValueFactory(new PropertyValueFactory<>("supplementName"));
		TableColumn<Supplement, String>  c3 = new TableColumn<>("Expiry Date");
		c3.setCellValueFactory(new PropertyValueFactory<>("supplementExpiryDate"));
		TableColumn<Supplement, Integer>  c4 = new TableColumn<>("Price");
		c4.setCellValueFactory(new PropertyValueFactory<>("supplementPrice"));
		TableColumn<Supplement, Integer>  c5 = new TableColumn<>("Stock");
		c5.setCellValueFactory(new PropertyValueFactory<>("supplementStock"));
		TableColumn<Supplement, Integer>  c6 = new TableColumn<>("Type");
		c6.setCellValueFactory(new PropertyValueFactory<>("supplementTypeID"));

		SupplementTable.getColumns().setAll(c1, c2, c3, c4, c5, c6);

		SupplementPane = new VBox();
		SupplementPane.setPrefSize(SCENE_WIDTH*0.6, SCENE_HEIGHT);
		SupplementPane.setPadding(new Insets(20));
		SupplementPane.getChildren().add(SupplementTable);

		addTableListener();

	}

	private void addTableListener() {
		SupplementTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				nameTxt.setText(newValue.getSupplementName());
				expiryDateTxt.setValue(newValue.getSupplementExpiryDate());
				priceTxt.setText(Integer.toString(newValue.getSupplementPrice()));
				stockTxt.setText(Integer.toString(newValue.getSupplementStock()));
				typeTxt.setText(newValue.getSupplementTypeID());
			}
		});
	}



	private void createControlPane() {
		Label titleLbl = createLabel(PRIMARY, "Supplements", "Arial", true, 20);
		Label nameLbl = createLabel(PRIMARY, "Supplement Name", "Arial", true, 15);
		Label expiryDateLbl = createLabel(PRIMARY, "Supplement Expiry Date", "Arial", true, 15);
		Label priceLbl = createLabel(PRIMARY, "Supplement Price", "Arial", true, 15);
		Label stockLbl = createLabel(PRIMARY, "Supplement Stock", "Arial", true, 15);
		Label typeLbl = createLabel(PRIMARY, "Supplement Type", "Arial", true, 15);

		nameTxt= createTextField(445, 15, 10, WHITE, PRIMARY, "Arial", 15, false);
		priceTxt= createTextField(445, 15, 10, WHITE, PRIMARY, "Arial", 15, false);
		stockTxt = createTextField(445, 15, 10, WHITE, PRIMARY, "Arial", 15, false);
		expiryDateTxt = new DatePicker();
		typeTxt = createTextField(445, 15, 10, WHITE, PRIMARY, "Arial", 15, false);


		VBox formPane = new VBox();
		VBox namePane = new VBox();
		VBox expiryDatePane = new VBox();
		VBox pricePane = new VBox();
		VBox stockPane = new VBox();
		VBox typePane = new VBox();

		namePane.getChildren().addAll(nameLbl, nameTxt);
		pricePane.getChildren().addAll(priceLbl, priceTxt);
		stockPane.getChildren().addAll(stockLbl, stockTxt);
		expiryDatePane.getChildren().addAll(expiryDateLbl, expiryDateTxt);
		typePane.getChildren().addAll(typeLbl, typeTxt);
		formPane.getChildren().addAll(namePane, expiryDatePane, pricePane, stockPane, typePane);
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
		String supplementName = nameTxt.getText();
		LocalDate supplementExpiryDate = expiryDateTxt.getValue();
		int supplementPrice = Integer.parseInt(priceTxt.getText());
		int supplementStock = Integer.parseInt(stockTxt.getText());
		String supplementTypeID = typeTxt.getText();

		if(e.getSource() == addBtn) {
			SupplementController.getSupplementController().save(supplementName, supplementExpiryDate, supplementPrice, supplementStock, supplementTypeID);
			refresh();
		}
		else if(e.getSource() == removeBtn) {
			String supplementID = SupplementTable.getSelectionModel().getSelectedItem().getSupplementID();
			SupplementController.getSupplementController().delete(supplementID);
			refresh();
		}
		else if(e.getSource() == updateBtn) {
			String supplementID = SupplementTable.getSelectionModel().getSelectedItem().getSupplementID();
			SupplementController.getSupplementController().update(supplementID, supplementName, supplementExpiryDate, supplementPrice, supplementStock, supplementTypeID);
			refresh();
		}

	}

	private void refresh() {
		List<Supplement> SupplementList = SupplementController.getSupplementController().findAll();
		SupplementData = FXCollections.observableArrayList(SupplementList);
		SupplementTable.setItems(SupplementData);
	}

	private void createManageSupplementView() {
		createSupplementTable();
		createControlPane();

		root = new HBox();
		root.getChildren().addAll(SupplementPane, controlPane);
		this.setBackground(WHITE_BACKGROUND);
		this.getChildren().addAll(new AdminNavbar(), root);
	}


	public ManageSupplementView() {
		createManageSupplementView();
	}
}
