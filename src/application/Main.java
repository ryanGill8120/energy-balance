package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import model.Model;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			User testUser = new User("testUser", "John", 90.0, 2.0, 25, "Male");
			Food testFood = new Food("Potato", "Potato.jpg", 163);
			Workout testWorkout = new Workout("Run", "Run.jpg", 100, 1, "Miles");
			Day testDay = new Day(Model.today.toString(), testUser.getUserName(), 2100, 2000, "Potato (x1)", "Run (x1)");
			
			Model.addUser(testUser, "test123");
			Model.addFood(testFood);
			Model.addWorkout(testWorkout);
			Model.addDay(testDay);
			*/
			
			Model.loadFiles();
			System.out.println(Model.userMap);
			/*
			User testUser = new User("testUser", "John", 90.0, 2.0, 25, "Male");
			Day testDay = new Day("2020-04-24", "testUser", 2100, 2000, "Potato (x9)", "Run (x5)");
			Model.addDay(testDay);
			testUser.populateUserHistory();
			System.out.println(testUser.getUserHistory());
			*/
			
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
