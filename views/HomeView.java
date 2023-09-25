package views;

import java.util.ArrayList;
import java.util.List;

import components.ComponentMaker;
import components.CustomerNavbar;
import controllers.AuthController;
import controllers.CartController;
import controllers.PageController;
import controllers.SupplementController;
import controllers.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import models.Cart;
import models.Supplement;

public class HomeView extends VBox implements ComponentMaker{
	private FlowPane supplementPane, buttonPane;
	private ScrollPane scrollPane;
	private VBox cartPane;
	private ObservableList<Cart> cartData;
	private TableView<Cart> cartTable;
	private HBox root;
	private Button removeBtn, orderBtn;

	private void createSupplementDisplay() {
		supplementPane = new FlowPane();
		supplementPane.setHgap(15);
		supplementPane.setVgap(30);
		supplementPane.setPrefWidth(SCENE_WIDTH*0.6);
		supplementPane.setPadding(new Insets(40));

		List<Supplement>list = SupplementController.getSupplementController().findAll();

		for(Supplement supplement : list) {
			HBox card = new HBox();
			card = createSupplementCard(supplement.getSupplementID(), supplement.getSupplementName(), supplement.getSupplementPrice(), supplement.getSupplementStock());
			supplementPane.getChildren().add(card);
			supplementPane.setBackground(WHITE_BACKGROUND);
		}
		//		
		scrollPane = new ScrollPane(supplementPane);
		scrollPane.setFitToHeight(true);
		scrollPane.getStyleClass().add("edge-to-edge");
	}

	private HBox createSupplementCard(String supplementID, String supplementName, int supplementPrice, int supplementStock) {
		HBox card = new HBox(10);
		card.setPrefSize(SCENE_WIDTH*0.20, SCENE_HEIGHT*0.20);
		card.setMaxSize(SCENE_WIDTH*0.20, SCENE_HEIGHT*0.20);

		TextFlow idTxt = createTextFlow(supplementID, "Arial", 14, true);
		TextFlow nameTxt = createTextFlow(supplementName, "Arial", 12, false);
		TextFlow priceTxt = createTextFlow("Rp "+supplementPrice, "Arial", 10, false);
		TextFlow stockTxt = createTextFlow("Stock: "+supplementStock, "Arial", 10, false);
		VBox leftCard = new VBox(5);
		
		leftCard.getChildren().addAll(idTxt, nameTxt, priceTxt, stockTxt);
		leftCard.setPadding(new Insets(0,5,0,10));
		leftCard.setAlignment(Pos.CENTER_LEFT);
		leftCard.setPrefWidth(SCENE_WIDTH*0.20);
		leftCard.setMaxWidth(SCENE_WIDTH*0.20);

		Spinner<Integer>qtySpinner = new Spinner<>(0, supplementStock, 0);
		Button addBtn = createButton("Add", 100, 30, 5, 2, 2, BASE, PRIMARY, BLACK, "Arial", 10, true);
		addBtn.setCursor(Cursor.HAND);
		addBtn.setOnAction(e ->{
			int quantity = qtySpinner.getValue();
			String userID = AuthController.getAuthController().currentUser.getUserID();
			CartController.getCartController().save(userID, supplementID, quantity);
			cartData = FXCollections.observableList(CartController.getCartController().findAll(userID));
			cartTable.refresh();		
			cartTable.setItems(cartData);
		});

		VBox rightCard = new VBox(5);
		rightCard.getChildren().addAll(qtySpinner, addBtn);
		rightCard.setAlignment(Pos.CENTER);
		rightCard.setPadding(new Insets(0,10,0,0));

		card.getChildren().addAll(leftCard, rightCard);
		card.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, BLACK, 0.0, 25.0, 0.0,  3.0));
		card.setBackground(new Background(new BackgroundFill(SECONDARY, new CornerRadii(10), null)));

		return card;
	}

	private void createButtonPane() {
		removeBtn = createButton("Remove", 100, 50, 10, 5, 5, SECONDARY, BLACK, WHITE, "Arial", 14, true);
		orderBtn = createButton("Order", 100, 50, 10, 5, 5, PRIMARY, BLACK, WHITE, "Arial", 14, true);
		removeBtn.setCursor(Cursor.HAND);
		orderBtn.setCursor(Cursor.HAND);
		
		String userID = AuthController.getAuthController().currentUser.getUserID();

		removeBtn.setOnAction(e ->{
			String supplementID = cartTable.getSelectionModel().getSelectedItem().getSupplementID();
			CartController.getCartController().delete(userID, supplementID);
			cartData.remove(cartTable.getSelectionModel().getSelectedIndex());
		});

		orderBtn.setOnAction(e->{
			TransactionController.getTransactionController().save(userID, cartData);

		});

		buttonPane = new FlowPane();
		buttonPane.setHgap(10);
		buttonPane.getChildren().addAll(removeBtn, orderBtn);
		buttonPane.setAlignment(Pos.CENTER_LEFT);
		buttonPane.setPrefWidth(SCENE_WIDTH*0.3);
		buttonPane.setMaxWidth(SCENE_WIDTH*0.3);
	}

	@SuppressWarnings("unchecked")
	private void createCartDisplay() {
		cartPane = new VBox(10);
		Label cartLbl = createLabel(PRIMARY, "CART", "Arial", true, 40);

		String userID = AuthController.getAuthController().getCurrentUser().getUserID();
		List<Cart> data = CartController.getCartController().findAll(userID);
		cartData = FXCollections.observableList(data);

		cartTable = new TableView<Cart>();
		cartTable.setItems(cartData);
		cartTable.setPrefWidth(SCENE_WIDTH*0.3);
		cartTable.setMaxWidth(SCENE_WIDTH*0.3);

		TableColumn<Cart, String>  c1 = new TableColumn<>("SupplementID");
		c1.setCellValueFactory(new PropertyValueFactory<>("SupplementID"));
		TableColumn<Cart, Integer>  c2 = new TableColumn<>("Quantity");
		c2.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		cartTable.getColumns().setAll(c1, c2);
		cartTable.setPrefHeight(340);
		cartTable.setMaxHeight(340);

		createButtonPane();
		cartPane.getChildren().addAll(cartLbl, cartTable, buttonPane);
		cartPane.setAlignment(Pos.CENTER);
		cartPane.setBackground(WHITE_BACKGROUND);
		cartPane.setPrefWidth(SCENE_WIDTH*0.4);
	}

	private void crateHomeView() {
		createSupplementDisplay();
		createCartDisplay();

		root = new HBox();
		root.getChildren().addAll(scrollPane, cartPane);
		this.getChildren().addAll(new CustomerNavbar(), root);
	}

	public HomeView() {
		crateHomeView();
	}
	
}
