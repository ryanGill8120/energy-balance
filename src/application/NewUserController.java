package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class NewUserController {
	
	@FXML
    private Button submitBtn;

    @FXML
    private AnchorPane newUser;

    @FXML
    void toLogin(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	newUser.getChildren().setAll(pane);

    }

}
