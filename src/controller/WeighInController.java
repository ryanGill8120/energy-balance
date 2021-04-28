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
import model.Model;

public class WeighInController implements Initializable{
	
	@FXML
    private TextField weightTF;

    @FXML
    private Label promptLbl;

    @FXML
    void process(ActionEvent event) throws IOException {
    	
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	String weightString = weightTF.getText();
    	boolean validWeight = Model.validateInt(weightString);
    	if (validWeight) {
    		int weight = (int)Integer.parseInt(weightString);
    		Model.currentUser.setWeight((double)weight);
    		Model.currentUser.setLastWeighIn(Model.today);
    		String pw = Model.auth.get(Model.currentUser.getUserName());
    		Model.addUser(Model.currentUser, pw);
    		a.setHeaderText("Weigh In successful");
    		a.setContentText("You may return to the previous screen");
    		weightTF.clear();
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Input");
    		a.setContentText("Weight must be an integer");
    	}
    	a.show();
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String formattedDate = Model.today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
		promptLbl.setText("Enter your weight as of " + formattedDate);
		
	}

}
