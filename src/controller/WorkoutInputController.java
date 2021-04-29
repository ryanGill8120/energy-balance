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
import javafx.stage.Stage;
import model.Model;

public class WorkoutInputController implements Initializable{
	
//	private Desktop desktop = Desktop.getDesktop();
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
    
    @FXML
    void chooseWorkoutPic(ActionEvent event) throws IOException {
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choose Workout Picture");
    	fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    	File foodFile = fileChooser.showOpenDialog(Model.workoutStage);
    	if(foodFile != null) {
    		buffImage = ImageIO.read(foodFile);
    		Image img = SwingFXUtils.toFXImage(buffImage, null);
    		workoutImageView.setImage(img);
    	}

    }
    
    @FXML
    void process(ActionEvent event) throws IOException {
    	
    	
    	String name = nameTF.getText();
    	String repName = repNameTF.getText();
    	String calories = calTF.getText();
    	String reps = repTF.getText();
    	
    	boolean validName = Model.validateString(name);
    	boolean validRepName = Model.validateString(repName);
    	boolean validCalories = Model.validateInt(calories);
    	boolean validReps = Model.validateInt(reps);
    	
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	if (validName && validRepName && validCalories && validReps) {
    		
    		String pic = name + ".png";
    		Workout workout = new Workout(name, pic, (int)Integer.parseInt(calories), (int)Integer.parseInt(reps), repName);
    		Model.addWorkout(workout);
    		
    		nameTF.clear();
    		repNameTF.clear();
    		calTF.clear();
    		repTF.clear();
    		String path = "./src/staticFiles/" + pic;
    		ImageIO.write(buffImage, "jpg", new File(path));
    		a.setHeaderText("Workout added successfuly");
    		a.setContentText("You added " + name + " to your list of available Workouts.");
    		
    		try {
    			buffImage = ImageIO.read(new File("./src/staticFiles/NoWorkout.jpeg"));
    			Image img = SwingFXUtils.toFXImage(buffImage, null);
    			workoutImageView.setImage(img);
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		a.show();
    		Stage stage = (Stage) repTF.getScene().getWindow();
    	    stage.close();
    	}else {
    		
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Input");
    		String output = "The following problems exist:\n\n";
    		if(!validName) {
    			nameTF.setStyle("-fx-text-inner-color: red;");
    			output += "Name must be under 30 characters and cannot contain ','\n\n";
    		}if(!validRepName) {
    			repNameTF.setStyle("-fx-text-inner-color: red;");
    			output += "Rep Name must be under 30 characters and cannot contain ','\n\n";
    		}if(!validCalories) {
    			calTF.setStyle("-fx-text-inner-color: red;");
    			output += "Calories should be an integer less than or equal to 999,999,999\n\n";
    		}if(!validReps) {
    			repTF.setStyle("-fx-text-inner-color: red;");
    			output += "Reps should be an integer less than or equal to 999,999,999\n\n";
    		}
    		a.setContentText(output);
    		a.show();
    	}
    	
    	

    }

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
