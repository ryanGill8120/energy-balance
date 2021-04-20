package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class LoginController {
	
	@FXML
    private Button loginBtn;
	
	@FXML
    private Button signUpBtn;

    @FXML
    private AnchorPane root;

    @FXML
    void toDash(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Dash.fxml"));
    	root.getChildren().setAll(pane);

    }
    
    @FXML
    void toNewUser(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/NewUser.fxml"));
    	root.getChildren().setAll(pane);

    }

}
