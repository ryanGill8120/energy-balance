package application;
	
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import model.Model;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			

			Model.currentDate = LocalDate.now();
			Model.loadFiles();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene scene = new Scene(root,1200,900);
			scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
			primaryStage.setTitle("Energy Balance");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
