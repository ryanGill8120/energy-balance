package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MealInputController {
	
	@FXML
	private AnchorPane meal;
	
	@FXML
    private Button backBtn;

    @FXML
    void toDash(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("Dash.fxml"));
    	meal.getChildren().setAll(pane);

    }

}
