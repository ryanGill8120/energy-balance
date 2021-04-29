package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import application.Workout;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Model;
import javafx.fxml.*;
import java.awt.image.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.event.*;
import java.net.*;
import java.util.*;

/**
 * A window to allow the user to create a new workout type and add it to file
 *
 */
public class WorkoutInputController implements Initializable{

	//instance variable to store a buffered image
	private BufferedImage buffImage;
	
	@FXML
    private TextField calTF;

    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    @FXML
    private TextField repTF;
    
    @FXML
    private TextField repNameTF;

    @FXML
    private AnchorPane workoutInput;
    
    @FXML
    private ImageView workoutImageView;
    
    /**
     * @param event
     * @throws IOException
     * 
     * lets the user choose a picture using their OS's file chooser
     */
    @FXML
    void chooseWorkoutPic(ActionEvent event) throws IOException {
    	
    	//fileChoser set up to only view images
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choose Workout Picture");
    	fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    	
    	//launches from static stage
    	File foodFile = fileChooser.showOpenDialog(Model.workoutStage);
    	if(foodFile != null) {
    		
    		//sets instance variable and updates the ImageView
    		buffImage = ImageIO.read(foodFile);
    		Image img = SwingFXUtils.toFXImage(buffImage, null);
    		workoutImageView.setImage(img);
    	}

    }
    
    /**
     * @param event
     * @throws IOException
     * 
     * Handles error checking and adds a new Workout to file if no errors exist
     */
    @FXML
    void process(ActionEvent event) throws IOException {
    	
    	//gets data from view
    	String name = nameTF.getText();
    	String repName = repNameTF.getText();
    	String calories = calTF.getText();
    	String reps = repTF.getText();
    	
    	//validity flags with associated functions called
    	boolean validName = Model.validateString(name);
    	boolean validRepName = Model.validateString(repName);
    	boolean validCalories = Model.validateInt(calories);
    	boolean validReps = Model.validateInt(reps);
    	
    	//if all input is valid
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	if (validName && validRepName && validCalories && validReps) {
    		
    		//constructs Workout object and adds it to file
    		String pic = name + ".png";
    		Workout workout = new Workout(name, pic, (int)Integer.parseInt(calories), (int)Integer.parseInt(reps), repName);
    		Model.addWorkout(workout);
    		
    		//cleans up view
    		nameTF.clear();
    		repNameTF.clear();
    		calTF.clear();
    		repTF.clear();
    		
    		//writes the image to file and preps alert
    		String path = "./src/staticFiles/" + pic;
    		ImageIO.write(buffImage, "jpg", new File(path));
    		a.setHeaderText("Workout added successfuly");
    		a.setContentText("You added " + name + " to your list of available Workouts.");
    		
    		//resets the Image View
    		try {
    			buffImage = ImageIO.read(new File("./src/staticFiles/NoWorkout.jpeg"));
    			Image img = SwingFXUtils.toFXImage(buffImage, null);
    			workoutImageView.setImage(img);
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    		//shows alert
    		a.show();
    		
    	//At least one error exists
    	}else {
    		
    		//preps alert
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Input");
    		String output = "The following problems exist:\n\n";
    		
    		//Invalid Name
    		if(!validName) {
    			nameTF.setStyle("-fx-text-inner-color: red;");
    			output += "Name must be under 30 characters and cannot contain ','\n\n";
    			
    		//Invalid Rep Name
    		}if(!validRepName) {
    			repNameTF.setStyle("-fx-text-inner-color: red;");
    			output += "Rep Name must be under 30 characters and cannot contain ','\n\n";
    			
    		//Invalid Calories
    		}if(!validCalories) {
    			calTF.setStyle("-fx-text-inner-color: red;");
    			output += "Calories should be an integer less than or equal to 999,999,999\n\n";
    			
    		//Invalid Reps
    		}if(!validReps) {
    			repTF.setStyle("-fx-text-inner-color: red;");
    			output += "Reps should be an integer less than or equal to 999,999,999\n\n";
    		}
    		a.setContentText(output);
    		a.show();
    	}
    	
    	

    }

	/**
	 * sets initial image to default image
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			buffImage = ImageIO.read(new File("./src/staticFiles/NoWorkout.jpeg"));
			Image img = SwingFXUtils.toFXImage(buffImage, null);
			workoutImageView.setImage(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
