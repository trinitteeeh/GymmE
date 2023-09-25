package components;

import controllers.PageController;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

public class AdminNavbar extends NavigationBar{
	private Menu supplementMenu, transactionMenu, userMenu;
	private Label supplementLbl, transactionLbl, userLbl;
	
	private void createAdminNavbar() {
		transactionLbl = createLabel(WHITE, "Transaction", "Arial", true, 15);
		supplementLbl = createLabel(WHITE, "Supplement", "Arial", true, 15);
		userLbl = createLabel(WHITE, "User", "Arial", true, 15);
		
		transactionMenu = new Menu("", transactionLbl);
		supplementMenu = new Menu("", supplementLbl);
		userMenu = new Menu("", userLbl);
		
		super.rightBar.getMenus().clear();
		super.rightBar.getMenus().addAll(transactionMenu, supplementMenu, userMenu, logOutMenu);
		
		transactionLbl.setOnMouseClicked(e -> PageController.getPageController().showTransactionView());
		supplementLbl.setOnMouseClicked(e -> PageController.getPageController().showSupplementPage());
		userLbl.setOnMouseClicked(e -> PageController.getPageController().showUserPage());
	}

	public AdminNavbar() {
		super();
		createAdminNavbar();
	}
}
