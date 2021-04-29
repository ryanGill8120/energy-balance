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

public class NewUserController implements Initializable {

	// FXML ChoiceBox for feet input
	@FXML
	private ChoiceBox<Integer> feetCB;
	// A list of options for the feet choice box
	Integer[] feet = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	// FXML ChoiceBox for sex input
	@FXML
	private ChoiceBox<String> sexCB;
	// Sexes for sex choice box
	String[] sexes = { "Female", "Male" };

	// FXML TextField for name input
	@FXML
	private TextField nameTF;

	// FXML Label for status
	@FXML
	private Label statusLabel;

	// FXML TextField for username input
	@FXML
	private TextField usernameTF;

	// FXML Button for submitting
	@FXML
	private Button submitBtn;

	// FXML Button for returning to login
	@FXML
	private Button backBtn;

	// FXML PasswordField for password input
	@FXML
	private PasswordField firstPW;

	// FXML ChoiceBox for day input
	@FXML
	private ChoiceBox<Integer> dayCB;

	// FXML TextField for weight input
	@FXML
	private TextField weightTF;

	// FXML ChoiceBox for inches input
	@FXML
	private ChoiceBox<Integer> inchesCB;
	// A list of options for the inches choice box
	Integer[] inches = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

	// FXML AnchorPane for the scene
	@FXML
	private AnchorPane newUser;

	// FXML PasswordField for confirming password
	@FXML
	private PasswordField secondPW;

	// FXML ChoiceBox for month input
	@FXML
	private ChoiceBox<String> monthCB;
	// A list of months for the month choice box
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	// FXML ChoiceBox for year input
	@FXML
	private ChoiceBox<Integer> yearCB;

	/**
	 * FXML onAction for the back button. Returns to login.
	 * 
	 * @param ActionEvent event
	 * @throws IOException
	 */
	@FXML
	void toLogin(ActionEvent event) throws IOException {

		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		newUser.getChildren().setAll(pane);

	}

	/**
	 * Initialize function for the controller. Is called by javafx when the scene is
	 * set. Populates the choice boxes.
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

	/**
	 * Populates an array of days of the month and returns it.
	 * 
	 * @return Integer[] days: Integer array full of days of the month.
	 */
	public Integer[] populateDays() {
		Integer[] days = new Integer[31];
		for (int i = 0; i < 31; i++) {
			days[i] = (Integer) i + 1;
		}
		return days;

	}

	/**
	 * Populates an array of years and returns it.
	 * 
	 * @return Integer[] years: Integer array full of years.
	 */
	public Integer[] populateYears() {
		Integer[] years = new Integer[100];
		Integer thisYear = (Integer) Model.today.getYear();
		for (int i = 0; i < 100; i++) {
			years[i] = thisYear - (Integer) i;
		}
		return years;
	}

	/**
	 * FXML Method that processes user creation button.
	 * 
	 * @throws IOException
	 */
	@FXML
	public void processUser() throws IOException {

		// Create alert object and get values from all input components
		Alert a = new Alert(AlertType.CONFIRMATION);
		String name = nameTF.getText();
		String username = usernameTF.getText();
		String firstPWString = firstPW.getText();
		String secondPWString = secondPW.getText();
		String weightString = weightTF.getText();
		int month = 1;

		// Check whether each mode of input is valid
		boolean validName = Model.validateName(name);
		boolean validUserName = Model.validateUsername(username);
		boolean novelUserName = Model.queryUser(username);
		boolean validPassword = Model.validatePassword(firstPWString);
		boolean validPasswordMatch = Model.validatePasswordMatch(firstPWString, secondPWString);
		boolean validHeight = feetCB.getValue() != null && inchesCB.getValue() != null;
		boolean validWeight = Model.validateWeight(weightString);
		boolean validSex = sexCB.getValue() != null;
		boolean validDate = yearCB.getValue() != null && monthCB.getValue() != null && dayCB.getValue() != null;

		// Create string for error message
		String output = "The following problems exist:\n\n";

		// Create variable for checking birthday format
		LocalDate birthday = LocalDate.now();

		// If the date is valid, convert month string to integer
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

			// Check whether date exists
			try {
				birthday = LocalDate.of((int) yearCB.getValue(), month, (int) dayCB.getValue());
			} catch (DateTimeException e) {
				// If date does not exist, add error to output
				validDate = false;
				output += e.getMessage() + "\n\n";
			}
		}

		// If all conditions passed check
		if (validName && validUserName && validPassword && validPasswordMatch && validHeight && validWeight && validSex
				&& validDate && novelUserName) {
			// Get double values of weight and height
			double weight = (double) Integer.parseInt(weightString);
			double height = (double) (12 * feetCB.getValue() + inchesCB.getValue());
			// Get value of sex
			String sex = sexCB.getValue();
			// Create User using values
			User user = new User(username, name, weight, height, birthday, Model.today, sex);
			// Add the new user
			Model.addUser(user, firstPWString);

			// Clear all fields
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

			// Send message indicating success
			a.setHeaderText("User Account Created");
			a.setContentText("Thank you " + name + " for signing up! Please Log In.");
			a.show();
			// Set scene to login
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			newUser.getChildren().setAll(pane);

			// If any checks failed
		} else {
			// Add appropriate message for each error
			if (!validName) {
				nameTF.setStyle("-fx-text-inner-color: red;");
				output += "Name must be under 30 characters and contain only letters.\n";
			}
			if (!validUserName) {
				usernameTF.setStyle("-fx-text-inner-color: red;");
				output += "Username must be under 30 characters and contain only letters and numbers.\n";
			}
			if (!validPassword) {
				output += "Password must be 6-20 characters long.\n";
			}
			if (!validPasswordMatch) {
				output += "Passwords entered do not match.\n";
			}
			if (!validDate) {
				output += "Please enter a valid date.\n";
			}
			if (!validHeight) {
				output += "Please enter your height.\n";
			}
			if (!validSex) {
				output += "Please enter your sex.\n";
			}
			if (!validWeight) {
				weightTF.setStyle("-fx-text-inner-color: red;");
				output += "Weight must be an integer less than or equal to 1,000.\n";
			}
			if (!novelUserName) {
				usernameTF.setStyle("-fx-text-inner-color: red;");
				output += "Username \"" + username + "\"already exists.\n";
			}
			// Send alert with full error message
			a.setAlertType(AlertType.ERROR);
			a.setHeaderText("Invalid Input");
			a.setContentText(output);
			a.show();
		}

	}

}
