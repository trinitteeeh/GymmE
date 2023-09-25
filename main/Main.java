package main;

import components.Attribute;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import views.HomeView;
import views.ManageSupplementView;
import views.ManageUserView;
import views.SignUpView;

public class Main extends Application implements Attribute{
	
	private Scene scene;
	public static StackPane root;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {		
		root = new StackPane();
//		root.getChildren().add(new HomeView());
		root.getChildren().add(new SignUpView());
//		root.getChildren().add(new ManageSupplementView());
//		root.getChildren().add(new ManageUserView());
		
		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.setTitle("SupleGain");
		primaryStage.show();
		
	}

}
