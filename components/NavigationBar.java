package components;

import controllers.AuthController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public abstract class NavigationBar extends HBox implements ComponentMaker{

	protected Menu logoMenu, logOutMenu;
	protected MenuBar leftBar, rightBar;
	protected Image logo;
	protected ImageView logoView;
	protected Label logoLbl, logOutLbl;
	protected HBox logoPane, logOutPane;
	
	protected void createLogoMenu() {
		logoLbl = createLabel(WHITE, "SupleGain", "Arial", true, 15);
		logo = new Image("file:assets/logo.png");
		logoView = new ImageView(logo);
		logoView.setFitHeight(20);
		logoView.setFitWidth(20);
		
		logoPane = new HBox(5);
		logoPane.getChildren().addAll(logoView, logoLbl);
		logoPane.setAlignment(Pos.CENTER);
		
		logoMenu = new Menu("", logoPane);
		
	}
	
	protected void createLogOutMenu() {
		logOutLbl = createLabel(WHITE, "Log Out", "Arial", true, 15);
		logOutPane = new HBox(5);
		logOutPane.getChildren().add(logOutLbl);
		logOutMenu = new Menu("", logOutPane);
		
		logOutLbl.setOnMouseClicked(e ->{
			AuthController.getAuthController().signOut();
		});
	}

	protected void createMenuBar() {
		createLogoMenu();
		createLogOutMenu();
		
		leftBar = new MenuBar(logoMenu);
		rightBar = new MenuBar(logOutMenu);
		Region spacer = new Region();
		
		HBox.setHgrow(spacer, Priority.SOMETIMES);
		this.getChildren().addAll(leftBar, spacer, rightBar);
		
		this.setBackground(PRIMARY_BACKGROUND);
		leftBar.setBackground(PRIMARY_BACKGROUND);
		rightBar.setBackground(PRIMARY_BACKGROUND);
	}

	public NavigationBar() {
		createMenuBar();
	}

}
