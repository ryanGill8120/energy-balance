package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Model;

/**
 * Allows the user to Login, handles the authentication
 *
 */
public class LoginController {
	
	@FXML
    private Button loginBtn;
	
	@FXML
    private Button signUpBtn;

    @FXML
    private AnchorPane root;
    
    @FXML
    private PasswordField pwField;

    @FXML
    private TextField userTF;

    //takes user to the dash window if the authentication is successful
    @FXML
    void toDash(ActionEvent event) throws IOException {
    	
    	//if user authentication is valid
    	if (Model.authenticate(userTF.getText(), pwField.getText())) {
    		Model.currentUser = Model.userMap.get(userTF.getText());
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Dash.fxml"));
    		root.getChildren().setAll(pane);
    		
    	//displays error alert otherwise
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText("Invalid Username or Password");
    		alert.setContentText("The username or password provided were not found.\nPlease sign up if you are a new user.");
    		alert.show();
    		pwField.clear();
    		userTF.clear();
    	}
    }
    
    //takes user to the new User sign-up window
    @FXML
    void toNewUser(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/NewUser.fxml"));
    	root.getChildren().setAll(pane);

    }

}
