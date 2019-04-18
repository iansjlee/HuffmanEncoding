package Trees;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	// creates graphic window 
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new GraphicLauncher(primaryStage);
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}




	public static void main(String[] args) {
		launch (args);
	}


}
