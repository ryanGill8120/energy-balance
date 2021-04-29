package controller;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.event.*;
import java.net.*;
import java.util.*;

/**
 * 
 * Window to allow user to update their weight on file
 */
public class WeighInController implements Initializable{
	
	@FXML
    private TextField weightTF;

    @FXML
    private Label promptLbl;

    /**
     * @param event
     * @throws IOException
     * 
     * handles error checking and updating of the user's weight on file if no errors exist
     */
    @FXML
    void process(ActionEvent event) throws IOException {
    	
    	//preps data
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	String weightString = weightTF.getText();
    	boolean validWeight = Model.validateInt(weightString);
    	
    	//if weight is valid
    	if (validWeight) {
    		int weight = (int)Integer.parseInt(weightString);
    		Model.currentUser.setWeight((double)weight);
    		Model.currentUser.setLastWeighIn(Model.today);
    		String pw = Model.auth.get(Model.currentUser.getUserName());
    		
    		//adds updated info
    		Model.addUser(Model.currentUser, pw);
    		
    		//preps the alert
    		a.setHeaderText("Weigh-In Successful");
    		a.setContentText("Your current weight of "+weight+" lbs was added!");
    		Stage stage = (Stage) weightTF.getScene().getWindow();
    	    stage.close();
    	    
    	//invalid weight, error shown
    	} else {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Input");
    		a.setContentText("Weight must be an integer!");
    	}
    	a.show();
    	
    }

    //sets the date on the prompt to today
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		String formattedDate = Model.today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
		promptLbl.setText("Enter your weight as of " + formattedDate + ":");
		
	}

}
