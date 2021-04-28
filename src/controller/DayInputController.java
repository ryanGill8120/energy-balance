package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import java.util.ResourceBundle;
import application.Day;
import application.Food;
import application.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Model;

public class DayInputController implements Initializable{
	
	private Food emptyFood = new Food("No Food Selected", "NoFood.png", "Serving Size:", 0);
	private Workout emptyWorkout = new Workout("No Workout Selected", "NoWorkout.jpeg", 0, 0, "No rep name");
	private int servingSize = 1, repSize = 1;
	private BufferedImage buffFoodImage;
	private BufferedImage buffWorkoutImage;
	
	//Live variables
	//private LocalDate currentDate = Model.today;
	private Day currentDay;
	private Food currentFood = emptyFood;
	private Workout currentWorkout = emptyWorkout;
	private ObservableList<String> foodObs = FXCollections.observableArrayList();
	private ObservableList<String> workoutObs = FXCollections.observableArrayList();
	
	
	//FXML variables
	@FXML
	private AnchorPane meal;
	
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
    private ListView<String> workoutsDone;

    @FXML
    private Label foodNameLbl;

    @FXML
    private ListView<String> foodEaten;

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
    private ComboBox<String> chooseFoodCB;

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
    private ComboBox<String> chooseWorkoutCB;

    

    @FXML
    private Label servingNumberLbl;

    @FXML
    private Button newFoodBtn;

    @FXML
    private Button searchFoodBtn;

    
    //FXML functions
    @FXML
    void setDate(ActionEvent event) throws IOException {
    	
    	String input;
    	LocalDate yearAgo = LocalDate.of(Model.today.getYear()-1, Model.today.getMonthValue(), Model.today.getDayOfMonth());
    	String formattedDate = Model.today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	String yearAgoFmt = yearAgo.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	
    	
    	if (Model.today.compareTo(datePicker.getValue()) >= 0 && datePicker.getValue().compareTo(yearAgo) >= 0) {
    		Model.currentUser.populateUserHistory();
	    	Model.currentDate = datePicker.getValue();
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DayInput.fxml"));
	    	meal.getChildren().setAll(pane);
	  
    	}else {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setHeaderText("No time travelling!");
    		a.setContentText("Please select a date after " + yearAgoFmt + " and no later than " + formattedDate);
    		a.show();
    	}

    }

    @FXML
    void chooseFood(ActionEvent event) {
    	
    	currentFood = Model.foodMap.get(chooseFoodCB.getValue());
		foodNameLbl.setText(currentFood.getName());
    	calPerServingAmount.setText(currentFood.getCalories() + "");
    	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
    	
    	try {
    		String path = "./src/staticFiles/" + currentFood.getPicture();
			buffFoodImage = ImageIO.read(new File(path));
			Image img = SwingFXUtils.toFXImage(buffFoodImage, null);
			foodImage.setImage(img);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void chooseWorkout(ActionEvent event) {
    	
    	currentWorkout = Model.workoutMap.get(chooseWorkoutCB.getValue());
    	workoutNameLbl.setText(currentWorkout.getName());
    	calBurnedPerRepAmount.setText(currentWorkout.getCalories() + "");
    	calBurnedAmount.setText((repSize * currentWorkout.getCalories()) + "");
    	
    	try {
    		String path = "./src/staticFiles/" + currentWorkout.getPicture();
			buffWorkoutImage = ImageIO.read(new File(path));
			Image img = SwingFXUtils.toFXImage(buffWorkoutImage, null);
			workoutImage.setImage(img);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    }

    @FXML
    void  Servings(ActionEvent event) {

    }

    @FXML
    void servingPlus(ActionEvent event) {
    	servingSize++;
    	servingNumberLbl.setText(servingSize + "");
    	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
    }

    @FXML
    void servingMinus(ActionEvent event) {
    	servingSize--;
    	if (servingSize < 1)
    		servingSize=1;
    	servingNumberLbl.setText(servingSize + "");
    	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");

    }

    @FXML
    void openFood(ActionEvent event) throws IOException {
    	
    	
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FoodInput.fxml"));
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		Model.foodStage.setTitle("Add New Food");
		Model.foodStage.setScene(scene);
		Model.foodStage.show();

    }

    @FXML
    void openWorkout(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/WorkoutInput.fxml"));
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		stage.setTitle("Add New Workout");
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void repPlus(ActionEvent event) {
    	
    	repSize++;
    	repNumberLbl.setText(repSize + "");
    	calBurnedAmount.setText((repSize * currentWorkout.getCalories())+"");

    }

    @FXML
    void repMinus(ActionEvent event) {
    	
    	repSize--;
    	if (repSize < 1) {
    		repSize = 1;
    	}
    	repNumberLbl.setText(repSize + "");
    	calBurnedAmount.setText((repSize * currentWorkout.getCalories())+"");
    	

    }

    @FXML
    void addFoodToDay(ActionEvent event) throws IOException {
    	
    	int servingCal = currentFood.getCalories() * servingSize;
    	String output = currentFood.getName() + " : " + currentFood.getServingSize() + " (x" + servingSize + ") : " + servingCal + " calories";
    	if (foodObs.get(0).equals("*** No Food Eaten Today ***")) {
    		currentDay.setCaloriesConsumed(0);
    		foodObs.clear();
    	}
    	currentDay.setCaloriesConsumed(currentDay.getCaloriesConsumed() + servingCal);
    	totalCalConsumed.setText(currentDay.getCaloriesConsumed()+ "");
    	foodObs.add(output);
    	foodEaten.setItems(foodObs);
    	String foodHistoryString = "";
    	for (int i = 0; i < foodObs.size(); i++) {
    		if (i != foodObs.size()-1) {
    			foodHistoryString += foodObs.get(i) + ",";
    		}else {
    			foodHistoryString += foodObs.get(i);
    		}
    	}
    	Model.addDay(currentDay);
    	Model.addFoodHistory(currentDay, foodHistoryString);
    	servingSize = 1;
    	servingNumberLbl.setText("1");
    	energyLbl.setText(currentDay.getEnergyBalance()+"%");
    	

    }

    @FXML
    void addWorkoutToDay(ActionEvent event) throws IOException {
    	
    	int repCal = currentWorkout.getCalories() * repSize;
    	String output = currentWorkout.getName() + " : " + currentWorkout.getRepSize();
    	output += " " + currentWorkout.getRepName() + "(x" + repSize + ") : " + repCal + " calories";
    	if (workoutObs.get(0).equals("*** No Workouts Done Today ***")) {
    		currentDay.setCaloriesBurned(Model.currentUser.getBasalMetabolism());
    		workoutObs.clear();
    	}
    	currentDay.setCaloriesBurned(currentDay.getCaloriesBurned() + repCal);
    	totalCalBurned.setText(currentDay.getCaloriesBurned()+ "");
    	workoutObs.add(output);
    	workoutsDone.setItems(workoutObs);
    	String workoutHistoryString = "";
    	for (int i = 0; i < workoutObs.size(); i++) {
    		if (i != workoutObs.size()-1) {
    			workoutHistoryString += workoutObs.get(i) + ",";
    		}else {
    			workoutHistoryString += workoutObs.get(i);
    		}
    	}
    	Model.addDay(currentDay);
    	Model.addWorkoutHistory(currentDay, workoutHistoryString);
    	repSize = 1;
    	repNumberLbl.setText("1");
    	energyLbl.setText(currentDay.getEnergyBalance()+"%");
    	
    	
    	

    }

    @FXML
    void searchFood(ActionEvent event) {
    	
    	if (Model.queryFood(searchFoodTF.getText())) {
    		currentFood = Model.foodMap.get(searchFoodTF.getText());
    		foodNameLbl.setText(currentFood.getName());
        	calPerServingAmount.setText(currentFood.getCalories() + "");
        	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
    	}else {
    		currentFood = emptyFood;
    		foodNameLbl.setText(currentFood.getName());
        	calPerServingAmount.setText(currentFood.getCalories() + "");
        	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
    	}
    	
    	try {
    		String path = "./src/staticFiles/" + currentFood.getPicture();
			buffFoodImage = ImageIO.read(new File(path));
			Image img = SwingFXUtils.toFXImage(buffFoodImage, null);
			foodImage.setImage(img);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void searchWorkout(ActionEvent event) {
    	
    	if (Model.queryWorkout(searchWorkoutTF.getText())) {
    		currentWorkout = Model.workoutMap.get(searchWorkoutTF.getText());
    		workoutNameLbl.setText(currentWorkout.getName());
    		calBurnedPerRepAmount.setText(currentWorkout.getCalories() + "");
    		calBurnedAmount.setText((repSize * currentWorkout.getCalories()) + "");
    	}else {
    		currentWorkout = emptyWorkout;
    		workoutNameLbl.setText(currentWorkout.getName());
        	calBurnedPerRepAmount.setText(currentWorkout.getCalories() + "");
        	calBurnedAmount.setText((repSize * currentWorkout.getCalories())+"");
    	}
    	
    	try {
    		String path = "./src/staticFiles/" + currentWorkout.getPicture();
			buffWorkoutImage = ImageIO.read(new File(path));
			Image img = SwingFXUtils.toFXImage(buffWorkoutImage, null);
			workoutImage.setImage(img);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void getBaslAlert(ActionEvent event) {
    	
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setHeaderText("Basal Metabolism");
    	a.setContentText("Basal metabolism is the calories your body burns even at rest, for all daily functions including pumping your heart and breathing");
    	a.show();

    }
	
	
	
	@FXML
    private Button backBtn;

    @FXML
    void toDash(ActionEvent event) throws IOException {
    	
    	Model.currentDate = LocalDate.now();
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Dash.fxml"));
    	meal.getChildren().setAll(pane);

    }
    
    @FXML
    void refreshFood(ActionEvent event) throws IOException {
    	
    	String input;
    	LocalDate yearAgo = LocalDate.of(Model.today.getYear()-1, Model.today.getMonthValue(), Model.today.getDayOfMonth());
    	String formattedDate = Model.today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	String yearAgoFmt = yearAgo.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	
    	
    	if (Model.today.compareTo(datePicker.getValue()) >= 0 && datePicker.getValue().compareTo(yearAgo) >= 0) {
    		Model.currentUser.populateUserHistory();
	    	Model.currentDate = datePicker.getValue();
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DayInput.fxml"));
	    	meal.getChildren().setAll(pane);
	  
    	}else {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setHeaderText("No time travelling!");
    		a.setContentText("Please select a date after " + yearAgoFmt + " and no later than " + formattedDate);
    		a.show();
    	}
    	
    	

    }

    @FXML
    void refreshWorkout(ActionEvent event) throws IOException {
    	
    	String input;
    	LocalDate yearAgo = LocalDate.of(Model.today.getYear()-1, Model.today.getMonthValue(), Model.today.getDayOfMonth());
    	String formattedDate = Model.today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	String yearAgoFmt = yearAgo.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	
    	
    	if (Model.today.compareTo(datePicker.getValue()) >= 0 && datePicker.getValue().compareTo(yearAgo) >= 0) {
    		Model.currentUser.populateUserHistory();
	    	Model.currentDate = datePicker.getValue();
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DayInput.fxml"));
	    	meal.getChildren().setAll(pane);
	  
    	}else {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setHeaderText("No time travelling!");
    		a.setContentText("Please select a date after " + yearAgoFmt + " and no later than " + formattedDate);
    		a.show();
    	}

    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//local variables
		String input;
		datePicker.setValue(Model.today);
		
		//gets current User's info
		
		Model.currentUser.populateUserHistory();
		String formattedDate = Model.currentDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	currentDateLbl.setText(formattedDate);
    	
    	
    	Day day = Model.currentUser.getDay(Model.currentDate);
    	if (day != null) {
    		currentDay = day;
    	}else {
    		//error
    		System.out.println("day not found");
    	}
    	
    	//loads food info
    	if (Model.foodHistoryMap.containsKey(currentDay.getUser() + "," + currentDay.getDate())) {
    		String[] foodHistoryArr = Model.foodHistoryMap.get(currentDay.getUser() + "," + currentDay.getDate()).split(",");
    		for(String f: foodHistoryArr) {
    			foodObs.add(f);
    		}
    		totalCalConsumed.setText(currentDay.getCaloriesConsumed()+"");
    	}else {
    		foodObs.add("*** No Food Eaten Today ***");
    		totalCalConsumed.setText(0+"");
    	}
    	
    	foodEaten.getItems().setAll(foodObs);
    	for(Entry<String, Food> entry: Model.foodMap.entrySet()) {
    		input = entry.getValue().getName();
    		chooseFoodCB.getItems().add(input);
        }
    	chooseFoodCB.setValue("-- Choose Food --");
    	foodNameLbl.setText(currentFood.getName());
    	calPerServingAmount.setText(currentFood.getCalories() + "");
    	servingNumberLbl.setText(servingSize + "");
    	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
    	
    	try {
			buffFoodImage = ImageIO.read(new File("./src/staticFiles/" + currentFood.getPicture()));
			Image img = SwingFXUtils.toFXImage(buffFoodImage, null);
			foodImage.setImage(img);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//loads workout info
    	if (Model.workoutHistoryMap.containsKey(currentDay.getUser() + "," + currentDay.getDate())) {
    		String[] workoutHistoryArr = Model.workoutHistoryMap.get(currentDay.getUser() + "," + currentDay.getDate()).split(",");
    		for(String w: workoutHistoryArr) {
    			workoutObs.add(w);
    		}
    		totalCalBurned.setText(currentDay.getCaloriesBurned()+"");
    	}else {
    		workoutObs.add("*** No Workouts Done Today ***");
    		totalCalBurned.setText(Model.currentUser.getBasalMetabolism()+"");
    	}
    	
    	workoutsDone.getItems().setAll(workoutObs);
    	for(Entry<String, Workout> entry: Model.workoutMap.entrySet()) {
    		input = entry.getValue().getName();
    		chooseWorkoutCB.getItems().add(input);
        }
    	chooseWorkoutCB.setValue("-- Choose Workout --");
    	workoutNameLbl.setText(currentWorkout.getName());
    	calBurnedPerRepAmount.setText(currentWorkout.getCalories() + "");
    	repNumberLbl.setText(repSize + "");
    	calBurnedAmount.setText((repSize * currentWorkout.getCalories())+"");
    	basalLbl.setText(Model.currentUser.getBasalMetabolism() + "");
    	energyLbl.setText(currentDay.getEnergyBalance() + "%");
    	
    	try {
			buffWorkoutImage = ImageIO.read(new File("./src/staticFiles/" + currentWorkout.getPicture()));
			Image img = SwingFXUtils.toFXImage(buffWorkoutImage, null);
			workoutImage.setImage(img);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	}

}
