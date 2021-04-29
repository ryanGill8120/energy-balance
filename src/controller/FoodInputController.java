package controller;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import application.Food;
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
import javafx.stage.FileChooser;
import model.Model;
import javafx.fxml.*;
import java.awt.*;
import java.awt.image.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
import java.net.*;
import java.util.*;

public class FoodInputController implements Initializable{
	
	private Desktop desktop = Desktop.getDesktop();
	private BufferedImage buffImage;
	
    @FXML
    private TextField calTF;

    @FXML
    private TextField nameTF;
    
    @FXML
    private TextField servingSizeTF;

    @FXML
    private Button submitBtn;
    
    @FXML
    private ImageView foodImg;
    
    @FXML
    void chooseFoodPic(ActionEvent event) throws IOException {
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choose Food Picture");
    	fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    	File foodFile = fileChooser.showOpenDialog(Model.foodStage);
    	if(foodFile != null) {
    		buffImage = ImageIO.read(foodFile);
    		Image img = SwingFXUtils.toFXImage(buffImage, null);
    		foodImg.setImage(img);
    	}
    	
    	

    }
    
    @FXML
    void process(ActionEvent event) throws IOException {
    	
    	String name = nameTF.getText();
    	String servingSize = servingSizeTF.getText();
    	String calories = calTF.getText();
    	
    	boolean validName = Model.validateString(name);
    	boolean validServingSize = Model.validateString(servingSize);
    	boolean validCalories = Model.validateInt(calories);
    	
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	if (validName && validServingSize && validCalories) {
    		
    		String pic = nameTF.getText() + ".png";
    		Food food = new Food(name, pic, servingSize, (int)Integer.parseInt(calories));
    		Model.addFood(food);
    		calTF.clear();
    		nameTF.clear();
    		servingSizeTF.clear();
    		String path = "./src/staticFiles/" + pic;
    		
    		ImageIO.write(buffImage, "jpg", new File(path));
    		a.setHeaderText("Food added succesfully");
    		a.setContentText("You have added " + name + " to your list of available foods.");
    		try {
    			buffImage = ImageIO.read(new File("./src/staticFiles/NoFood.png"));
    			Image img = SwingFXUtils.toFXImage(buffImage, null);
    			foodImg.setImage(img);
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		a.show();
    		
    		//ImageIO.write(img, "jpg", new File("./src/staticFiles/test.png"));
    		
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Input");
    		String output = "The following problems exist:\n\n";
    		if (!validName) {
    			nameTF.setStyle("-fx-text-inner-color: red;");
    			output += "Name must be under 30 characters and cannot contain ','\n\n";
    		}
    		if (!validServingSize) {
    			servingSizeTF.setStyle("-fx-text-inner-color: red;");
    			output += "Serving Size must be under 30 characters and cannot contain ','\n\n";
    		}
    		if (!validCalories) {
    			calTF.setStyle("-fx-text-inner-color: red;");
    			output += "Calories should be an integer less than or equal to 999,999,999\n\n";
    		}
    		a.setContentText(output);
    		a.show();
    	}
    	
    	

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			buffImage = ImageIO.read(new File("./src/staticFiles/NoFood.png"));
			Image img = SwingFXUtils.toFXImage(buffImage, null);
			foodImg.setImage(img);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} 

}
