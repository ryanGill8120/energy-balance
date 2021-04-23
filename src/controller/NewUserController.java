package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Model;

public class NewUserController implements Initializable{
	
	@FXML
    private ChoiceBox<Integer> feetCB;
	Integer[] feet = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @FXML
    private ChoiceBox<String> sexCB;
    String[] sexes = {"Female", "Male"};

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
    Integer[] inches = {0,1,2,3,4,5,6,7,8,9,10,11};

    @FXML
    private AnchorPane newUser;

    @FXML
    private PasswordField secondPW;

    @FXML
    private ChoiceBox<String> monthCB;
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @FXML
    private ChoiceBox<Integer> yearCB;
	

    @FXML
    void toLogin(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
    	newUser.getChildren().setAll(pane);

    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		feetCB.getItems().setAll(feet);
		sexCB.getItems().setAll(sexes);
		dayCB.getItems().setAll(populateDays());
		inchesCB.getItems().setAll(inches);
		monthCB.getItems().setAll(months);
		yearCB.getItems().setAll(populateYears());
		
	}
	
	public Integer[] populateDays() {
		Integer[] days = new Integer[31];
		for (int i =0; i<31; i++) {
			days[i] = (Integer) i + 1;
		}
		return days;
		
	}
	
	public Integer[] populateYears() {
		Integer[] years = new Integer[100];
		Integer thisYear = (Integer)Model.today.getYear(); 
		for (int i = 0; i < 100; i++) {
			years[i] = thisYear - (Integer)i;
		}
		return years;
	}
	
	@FXML
	public void processUser() throws IOException {
		
		String name = nameTF.getText();
		String userName = usernameTF.getText();
		String password = firstPW.getText();
		double weight = (double)Integer.parseInt(weightTF.getText());
		double height = (double) (12*feetCB.getValue() + inchesCB.getValue());
		int month =1;
		String monthString = monthCB.getValue();
		
		switch(monthString) {
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
		//Error checking will happen here, and only create a user object if everything is good
		//actually, local date will break if it cannot parse correct date, user entered in feb 31 or something
		
		
		LocalDate birthday = LocalDate.of((int)yearCB.getValue(), month, (int)dayCB.getValue());
		String sex = sexCB.getValue();
		User user = new User(userName, name, weight, height, birthday, sex);
		Model.addUser(user, password);
		statusLabel.setText("User: " + userName + " has been added!");
		
		
	}
    
    

}
