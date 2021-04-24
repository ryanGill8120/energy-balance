package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class DayInputController {
	
	@FXML
    private Label calPerServingAmount;

    @FXML
    private Label basalLbl;

    @FXML
    private Label calConsumedAmount;

    @FXML
    private Label workoutCalsLbl;

    @FXML
    private Label calPerServingLbl;

    @FXML
    private ImageView foodImage;

    @FXML
    private Label energyLbl;

    @FXML
    private Button servingMinusBtn;

    @FXML
    private Button repMinusBtn;

    @FXML
    private Button searchWorkoutBtn;

    @FXML
    private Label totalCalConsumed;

    @FXML
    private Label repNumberLbl;

    @FXML
    private Label workoutNameLbl;

    @FXML
    private Label calConsumedLbl;

    @FXML
    private ListView<?> workoutsDone;

    @FXML
    private Label foodNameLbl;

    @FXML
    private ListView<?> foodEaten;

    @FXML
    private Label calBurnedPerServingLbl;

    @FXML
    private Button newWorkoutBtn;

    @FXML
    private Button repPlusBtn;

    @FXML
    private Label calBurnedPerRepAmount;

    @FXML
    private Label calBurnedLbl;

    @FXML
    private Button basalBtn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button addFoodToDayBtn;

    @FXML
    private Label calBurnedAmount;

    @FXML
    private TextField searchWorkoutTF;

    @FXML
    private ComboBox<?> chooseFoodCB;

    @FXML
    private Button servingPlusBtn;

    @FXML
    private Button addWorkoutToDayBtn;

    @FXML
    private Label totalCalBurned;

    @FXML
    private Label currentDateLbl;

    @FXML
    private TextField searchFoodTF;

    @FXML
    private ImageView workoutImage;

    

    @FXML
    private ComboBox<?> chooseWorkoutCB;

    

    @FXML
    private Label servingNumberLbl;

    @FXML
    private Button newFoodBtn;

    @FXML
    private Button searchFoodBtn;

    

    @FXML
    void setDate(ActionEvent event) {

    }

    @FXML
    void chooseFood(ActionEvent event) {

    }

    @FXML
    void chooseWorkout(ActionEvent event) {

    }

    @FXML
    void  Servings(ActionEvent event) {

    }

    @FXML
    void servingPlus(ActionEvent event) {

    }

    @FXML
    void servingMinus(ActionEvent event) {

    }

    @FXML
    void openFood(ActionEvent event) {

    }

    @FXML
    void openWorkout(ActionEvent event) {

    }

    @FXML
    void repPlus(ActionEvent event) {

    }

    @FXML
    void repMinus(ActionEvent event) {

    }

    @FXML
    void addFoodToDay(ActionEvent event) {

    }

    @FXML
    void addWorkoutToDay(ActionEvent event) {

    }

    @FXML
    void searchFood(ActionEvent event) {

    }

    @FXML
    void searchWorkout(ActionEvent event) {

    }

    @FXML
    void getBaslAlert(ActionEvent event) {

    }
	
	@FXML
	private AnchorPane meal;
	
	@FXML
    private Button backBtn;

    @FXML
    void toDash(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Dash.fxml"));
    	meal.getChildren().setAll(pane);

    }

}
