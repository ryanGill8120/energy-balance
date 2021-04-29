package controller;

import java.io.IOException;
import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Model;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import java.net.*;
import java.util.*;

public class NewUserController implements Initializable {

	//FXML variables, some have attached base values (like inches 0 - 11) will be added,
	//usually on a combo box
	@FXML
	private ChoiceBox<Integer> feetCB;
	Integer[] feet = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	@FXML
	private ChoiceBox<String> sexCB;
	String[] sexes = { "Female", "Male" };

	@FXML
	private TextField nameTF;

	@FXML
	private Label statusLabel;

	@FXML
	private TextField usernameTF;

	@FXML
	private Button submitBtn;

	@FXML
	private Button backBtn;

	@FXML
	private PasswordField firstPW;

	@FXML
	private ChoiceBox<Integer> dayCB;

	@FXML
	private TextField weightTF;

	@FXML
	private ChoiceBox<Integer> inchesCB;
	Integer[] inches = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

	@FXML
	private AnchorPane newUser;

	@FXML
	private PasswordField secondPW;

	@FXML
	private ChoiceBox<String> monthCB;
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	@FXML
	private ChoiceBox<Integer> yearCB;

	
	
	/**
	 * @param event
	 * @throws IOException
	 * 
	 * takes the user to the Login screen (back button)
	 */
	@FXML
	void toLogin(ActionEvent event) throws IOException {

		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		newUser.getChildren().setAll(pane);

	}

	
	
	/**
	 *
	 *Sets CHoiceBoxes to their pre-assigned values
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		feetCB.getItems().setAll(feet);
		sexCB.getItems().setAll(sexes);
		dayCB.getItems().setAll(populateDays());
		inchesCB.getItems().setAll(inches);
		monthCB.getItems().setAll(months);
		yearCB.getItems().setAll(populateYears());

	}

	//helper function to create list of available values for days 1 - 31
	public Integer[] populateDays() {
		Integer[] days = new Integer[31];
		for (int i = 0; i < 31; i++) {
			days[i] = (Integer) i + 1;
		}
		return days;

	}

	//helper function to create a list of valid years, goes back 100 years, used for choosing age.
	public Integer[] populateYears() {
		Integer[] years = new Integer[100];
		Integer thisYear = (Integer) Model.today.getYear();
		for (int i = 0; i < 100; i++) {
			years[i] = thisYear - (Integer) i;
		}
		return years;
	}

	/**
	 * @throws IOException
	 * 
	 * Handles error checking of input values and then adds a user to file if all input is valid
	 */
	@FXML
	public void processUser() throws IOException {

		
		//variables initialized
		Alert a = new Alert(AlertType.CONFIRMATION);
		String name = nameTF.getText();
		String username = usernameTF.getText();
		String firstPWString = firstPW.getText();
		String secondPWString = secondPW.getText();
		String weightString = weightTF.getText();
		int month = 1;

		//validity flags, each has an associated validation method unique to the needed bounds
		boolean validName = Model.validateName(name);
		boolean validUserName = Model.validateUsername(username);
		boolean novelUserName = Model.queryUser(username);
		boolean validPassword = Model.validatePassword(firstPWString);
		boolean validPasswordMatch = Model.validatePasswordMatch(firstPWString, secondPWString);
		boolean validHeight = feetCB.getValue() != null && inchesCB.getValue() != null;
		boolean validWeight = Model.validateWeight(weightString);
		boolean validSex = sexCB.getValue() != null;
		boolean validDate = yearCB.getValue() != null && monthCB.getValue() != null && dayCB.getValue() != null;

		//error output string created
		String output = "The following problems exist:\n\n";

		LocalDate birthday = LocalDate.now();
		
		//switch for the choosing a month from choice box
		if (validDate) {
			switch (monthCB.getValue()) {
			case "January":
				month = 1;
				break;
			case "February":
				month = 2;
				break;
			case "March":
				month = 3;
				break;
			case "April":
				month = 4;
				break;
			case "May":
				month = 5;
				break;
			case "June":
				month = 6;
				break;
			case "July":
				month = 7;
				break;
			case "August":
				month = 8;
				break;
			case "September":
				month = 9;
				break;
			case "October":
				month = 10;
				break;
			case "November":
				month = 11;
				break;
			case "December":
				month = 12;
				break;
			default:
				break;
			}
			
			//checks for a valid birthday date
			try {
				birthday = LocalDate.of((int) yearCB.getValue(), month, (int) dayCB.getValue());
			} catch (DateTimeException e) {
				validDate = false;
				output += e.getMessage() + "\n\n";
			}
		}
		
		//Main Condition, all must be true
		if (validName && validUserName && validPassword && validPasswordMatch && validHeight && validWeight && validSex
				&& validDate && novelUserName) {

			//loads data and then constructs a User object
			double weight = (double) Integer.parseInt(weightString);
			double height = (double) (12 * feetCB.getValue() + inchesCB.getValue());
			String sex = sexCB.getValue();
			User user = new User(username, name, weight, height, birthday, Model.today, sex);
			
			//adds the new User to file
			Model.addUser(user, firstPWString);

			//cleans up screen, and preps alert
			nameTF.clear();
			usernameTF.clear();
			firstPW.clear();
			secondPW.clear();
			weightTF.clear();
			yearCB.setValue(null);
			monthCB.setValue(null);
			dayCB.setValue(null);
			feetCB.setValue(null);
			inchesCB.setValue(null);
			a.setHeaderText("User Account Created");
			a.setContentText("Thank you " + name + " for signing up! Please Log In.");
			a.show();
			
			//takes the user to the Dash Screen
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			newUser.getChildren().setAll(pane);

		//at least one invalid input occured, each error will add a problem to the output alert string
		} else {
			
			//invalid Name
			if (!validName) {
				nameTF.setStyle("-fx-text-inner-color: red;");
				output += "Name must be under 30 characters and contain only letters.\n";
			}
			
			//invalid username
			if (!validUserName) {
				usernameTF.setStyle("-fx-text-inner-color: red;");
				output += "Username must be under 30 characters and contain only letters and numbers.\n";
			}
			
			//invalid password
			if (!validPassword) {
				output += "Password must be 6-20 characters long.\n";
			}
			
			//Passwords do not match
			if (!validPasswordMatch) {
				output += "Passwords entered do not match.\n";
			}
			
			//An invalid date was chosen for users birthday. IE, 4/31/1990
			if (!validDate) {
				output += "Please enter a valid date.\n";
			}
			
			//User did not choose height
			if (!validHeight) {
				output += "Please enter your height.\n";
			}
			
			//User did not choose sex
			if (!validSex) {
				output += "Please enter your sex.\n";
			}
			
			//invalid weight
			if (!validWeight) {
				weightTF.setStyle("-fx-text-inner-color: red;");
				output += "Weight must be an integer less than or equal to 1,000.\n";
			}
			
			//username chosen already exists
			if (!novelUserName) {
				usernameTF.setStyle("-fx-text-inner-color: red;");
				output += "Username \"" + username + "\"already exists.\n";
			}
			
			//shows error alert
			a.setAlertType(AlertType.ERROR);
			a.setHeaderText("Invalid Input");
			a.setContentText(output);
			a.show();
		}

	}

}
