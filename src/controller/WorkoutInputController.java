package controller;

import java.io.IOException;

import application.Workout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Model;

public class WorkoutInputController {
	
	@FXML
    private TextField calTF;

    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    @FXML
    private TextField repTF;

    @FXML
    private AnchorPane workoutInput;
    
    @FXML
    void process(ActionEvent event) throws IOException {
    	
    	String name = nameTF.getText();
    	String pic = name + ".jpg";
    	String repName = repTF.getText();
    	int calories = (int)Integer.parseInt(calTF.getText());
    	int reps = (int)Integer.parseInt(repTF.getText());
    	Workout workout = new Workout(name, pic, calories, reps, repName);
    	Model.addWorkout(workout);

    }

}
