package Controller;

import Model.Manager;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFX extends Application {
	
	public static void main(String[] args) {
		launch();
		System.out.println("End");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Manager theModel = new Manager();
		View theView = new View(primaryStage);
		Controller controller = new Controller(theModel,theView);
	}

}
