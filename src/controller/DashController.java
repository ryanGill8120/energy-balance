package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashController {
	
	@FXML
    private Button logoutBtn;
	
	@FXML
    private Button addMealBtn;
	
	@FXML
    private Button newWorkoutBtn;
	
	@FXML
    private Button newFoodBtn;

    @FXML
    private AnchorPane dash;

    @FXML
    void toLogin(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
    	dash.getChildren().setAll(pane);

    }
    
    @FXML
    void toMeal(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DayInput.fxml"));
    	dash.getChildren().setAll(pane);

    }
    
    @FXML
    void openNewFood(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FoodInput.fxml"));
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		stage.setTitle("Add New Food");
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void openNewWorkout(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/WorkoutInput.fxml"));
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		stage.setTitle("Add New Food");
		stage.setScene(scene);
		stage.show();

    }


}
