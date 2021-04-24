package controller;

import java.io.IOException;

import application.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Model;

public class FoodInputController {
	

    @FXML
    private TextField calTF;

    @FXML
    private TextField nameTF;
    
    @FXML
    private TextField servingSizeTF;

    @FXML
    private Button submitBtn;
    
    @FXML
    void process(ActionEvent event) throws IOException {
    	
    	String name = nameTF.getText();
    	String pic = nameTF.getText() + ".jpg";
    	String servingSize = servingSizeTF.getText();
    	int calories = Integer.parseInt(calTF.getText());
    	Food food = new Food(name, pic, servingSize, calories);
    	Model.addFood(food);

    } 

}
