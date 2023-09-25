package components;

import controllers.PageController;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

public class CustomerNavbar extends NavigationBar{
	private Menu supplementMenu, transactionMenu;
	private Label supplementLbl, transactionLbl;
	
	private void createCustomerNavbar() {
		transactionLbl = createLabel(WHITE, "Transaction", "Arial", true, 15);
		supplementLbl = createLabel(WHITE, "Supplement", "Arial", true, 15);
		
		transactionMenu = new Menu("", transactionLbl);
		supplementMenu = new Menu("", supplementLbl);
		
		super.rightBar.getMenus().clear();
		super.rightBar.getMenus().addAll(supplementMenu, transactionMenu, logOutMenu);
		
		transactionLbl.setOnMouseClicked(e -> PageController.getPageController().showTransactionView());
		supplementLbl.setOnMouseClicked(e -> PageController.getPageController().showHomeView());
	}

	public CustomerNavbar() {
		super();
		createCustomerNavbar();
	}
}

