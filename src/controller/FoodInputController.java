package controller;

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
import javafx.stage.Stage;
import model.Model;

/**
 * Food Input window allows user to create a new food for use in their inventory of available foods
 *
 */
public class FoodInputController implements Initializable{
	
	//objects used for file choosing
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
    
    /**
     * @param event
     * @throws IOException
     * 
     * Allows user to choose a food picture from their machine
     */
    @FXML
    void chooseFoodPic(ActionEvent event) throws IOException {
    	
    	//set up fileChooser to only look for images
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choose Food Picture");
    	fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    	
    	//displays the file chooser
    	File foodFile = fileChooser.showOpenDialog(Model.foodStage);
    	
    	//sets an instance variable to a usable form of the picture selected, and updates the imageView
    	if(foodFile != null) {
    		buffImage = ImageIO.read(foodFile);
    		Image img = SwingFXUtils.toFXImage(buffImage, null);
    		foodImg.setImage(img);
    	}
    	
    	

    }
    
    /**
     * @param event
     * @throws IOException
     * 
     * handles the addition of the food assuming all input is valid
     */
    @FXML
    void process(ActionEvent event) throws IOException {
    	
    	//gets data from controllers
    	String name = nameTF.getText();
    	String servingSize = servingSizeTF.getText();
    	String calories = calTF.getText();
    	
    	//flags to determine if input is valid
    	boolean validName = Model.validateString(name);
    	boolean validServingSize = Model.validateString(servingSize);
    	boolean validCalories = Model.validateInt(calories);
    	
    	//if all input is valid
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	if (validName && validServingSize && validCalories) {
    		
    		//constructs food object based on user input
    		String pic = nameTF.getText() + ".png";
    		Food food = new Food(name, pic, servingSize, (int)Integer.parseInt(calories));
    		Model.addFood(food);
    		
    		//clears input fields
    		calTF.clear();
    		nameTF.clear();
    		servingSizeTF.clear();
    		
    		//makes a string literal for the path, then writes the picture
    		String path = "./src/staticFiles/" + pic;
    		ImageIO.write(buffImage, "jpg", new File(path));
    		
    		//sets popup text
    		a.setHeaderText("Food added succesfully");
    		a.setContentText("You have added " + name + " to your list of available foods.");
    		
    		//resets food image
    		try {
    			buffImage = ImageIO.read(new File("./src/staticFiles/NoFood.png"));
    			Image img = SwingFXUtils.toFXImage(buffImage, null);
    			foodImg.setImage(img);
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		a.show();
    		Stage stage = (Stage) calTF.getScene().getWindow();
    	    stage.close();
    		//ImageIO.write(img, "jpg", new File("./src/staticFiles/test.png"));
    	
    	//if even one input is invalid
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Input");
    		String output = "The following problems exist:\n\n";
    		
    		//displays the errors present in a popup and changes the color of the input fields that are invalid
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
		
		//loads default food image at window open
		try {
			buffImage = ImageIO.read(new File("./src/staticFiles/NoFood.png"));
			Image img = SwingFXUtils.toFXImage(buffImage, null);
			foodImg.setImage(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	} 

}
