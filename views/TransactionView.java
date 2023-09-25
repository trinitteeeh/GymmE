package views;


import java.time.LocalDate;
import java.util.List;

import components.AdminNavbar;
import components.ComponentMaker;
import components.CustomerNavbar;
import controllers.AuthController;
import controllers.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;
import models.Transaction;
import models.TransactionDetail;
import models.User;

public class TransactionView extends VBox implements ComponentMaker{

	private ObservableList<Transaction> transactionData;
	private ObservableList<TransactionDetail> trDetailData;
	private TableView<Transaction> transactionTable;
	private TableView<TransactionDetail> trDetailTable;

	private Pane internalPane;

	private Window window;
	private VBox root;

	private void createTransactionTable() {
		User currentUser = AuthController.getAuthController().getCurrentUser();
		
		String userID = currentUser.getUserID();
		List<Transaction> data;
		if(currentUser.getUserRole().equals("Customer")) {
			data = TransactionController.getTransactionController().findByUserID(userID);	
			root.getChildren().add(new CustomerNavbar());
		}
		else {
			data = TransactionController.getTransactionController().findAll();	
			root.getChildren().add(new AdminNavbar());
		}
		transactionData = FXCollections.observableList(data);
		transactionTable = new TableView<Transaction>();
		transactionTable.setItems(transactionData);
		transactionTable.setPrefWidth(SCENE_WIDTH*0.5);
		transactionTable.setMaxWidth(SCENE_WIDTH*0.5);
		
		internalPane = new Pane();

		TableColumn<Transaction, String>  c1 = new TableColumn<>("TransactionID");
		c1.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		TableColumn<Transaction, LocalDate>  c2 = new TableColumn<>("TransactionDate");
		c2.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
		transactionTable.getColumns().setAll(c1, c2);
		transactionTable.setPrefHeight(340);
		transactionTable.setMaxHeight(340);
		
		transactionTable.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !transactionTable.getSelectionModel().isEmpty()) {
		    	Transaction t = transactionTable.getSelectionModel().getSelectedItem();
		    	createDetailPane(t.getTransactionID());
		    }
		});

	}

	private void createDetailPane(String transactionID) {
		window = new Window();
		
		List<TransactionDetail> data = TransactionController.getTransactionController().findAll(transactionID);
		trDetailData = FXCollections.observableList(data);

		trDetailTable = new TableView<TransactionDetail>();
		trDetailTable.setItems(trDetailData);
		
		TableColumn<TransactionDetail, String>  c1 = new TableColumn<>("TransactionID");
		c1.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		TableColumn<TransactionDetail, String>  c2 = new TableColumn<>("SupplementID");
		c2.setCellValueFactory(new PropertyValueFactory<>("supplementID"));
		TableColumn<TransactionDetail, String>  c3 = new TableColumn<>("Quantity");
		c3.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		trDetailTable.getColumns().setAll(c1, c2, c3);
		trDetailTable.setPrefHeight(100);
		trDetailTable.setMaxHeight(100);
		
		window.getContentPane().getChildren().add(trDetailTable);
		window.setPrefHeight(250);
		window.getRightIcons().add(new CloseIcon(window));
		window.getRightIcons().add(new MinimizeIcon(window));
		window.setTitle("Transaction Detail");


        // Set the internal pane position to the center of the screen
        internalPane.setLayoutX(SCENE_WIDTH/2);
        internalPane.setLayoutY(SCENE_HEIGHT/2);
		
		internalPane.getChildren().add(window);
	}

	private void crateTransactionView() {
		root = new VBox(10);
		createTransactionTable();
		Label titleLbl = createLabel(PRIMARY, "Supplements", "Arial", true, 20);
		this.setBackground(WHITE_BACKGROUND);
		root.getChildren().addAll(titleLbl, transactionTable, internalPane);
		root.setAlignment(Pos.CENTER);
		this.getChildren().add(root);
	}

	public TransactionView() {
		crateTransactionView();
	}
}
