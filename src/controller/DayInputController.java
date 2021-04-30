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
	
	//instance variables used
	private Food emptyFood = new Food("No Food Selected", "NoFood.png", "Serving Size:", 0);
	private Workout emptyWorkout = new Workout("No Workout Selected", "NoWorkout.jpeg", 0, 0, "No rep name");
	private int servingSize = 1, repSize = 1;
	private BufferedImage buffFoodImage;
	private BufferedImage buffWorkoutImage;
	
	//Live variables
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
    
    
    /**
     * @param event
     * @throws IOException
     * 
     * Allows the user to switch the date using the DatePicker control
     */
    @FXML
    void setDate(ActionEvent event) throws IOException {
    	
    	//determines the date from a year ago, anything before this would be unreachable since userHistory only contains the 
    	//last year's worth of data
    	String input;
    	LocalDate yearAgo = LocalDate.of(Model.today.getYear()-1, Model.today.getMonthValue(), Model.today.getDayOfMonth());
    	String formattedDate = Model.today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	String yearAgoFmt = yearAgo.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	
    	//if date chosen is not in the future, but not more than a year ago
    	if (Model.today.compareTo(datePicker.getValue()) >= 0 && datePicker.getValue().compareTo(yearAgo) >= 0) {
    		
    		//repopulates history so that changes made are saved
    		Model.currentUser.populateUserHistory();
	    	Model.currentDate = datePicker.getValue();  //sets currentDate to the datePicker value
	    	
	    	//scene is reloaded, but initialize() will load data based on the currentDate LocalDate value, thus switching the day
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DayInput.fxml"));
	    	meal.getChildren().setAll(pane);
	  
	    //date is out of bounds, error pop-ups shown to user
    	}else {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setHeaderText("No time travelling!");
    		a.setContentText("Please select a date after " + yearAgoFmt + " and no later than " + formattedDate);
    		a.show();
    	}

    }

    /**
     * @param event
     * 
     * Allows user to select a food from the comboBox
     */
    @FXML
    void chooseFood(ActionEvent event) {
    	
    	//sets objects and view labels to the chosen value
    	currentFood = Model.foodMap.get(chooseFoodCB.getValue());
		foodNameLbl.setText(currentFood.getName());
    	calPerServingAmount.setText(currentFood.getCalories() + "");
    	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
    	
    	//loads the picture from file and displays it to the user
    	try {
    		String path = "./src/staticFiles/" + currentFood.getPicture();
			buffFoodImage = ImageIO.read(new File(path));
			Image img = SwingFXUtils.toFXImage(buffFoodImage, null);
			foodImage.setImage(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    /**
     * @param event
     * 
     * Identical functionality to chooseFood(), but does so for workout ComboBox
     */
    @FXML
    void chooseWorkout(ActionEvent event) {
    	
    	//sets object and labels
    	currentWorkout = Model.workoutMap.get(chooseWorkoutCB.getValue());
    	workoutNameLbl.setText(currentWorkout.getName());
    	calBurnedPerRepAmount.setText(currentWorkout.getCalories() + "");
    	calBurnedAmount.setText((repSize * currentWorkout.getCalories()) + "");
    	
    	//loads picture
    	try {
    		String path = "./src/staticFiles/" + currentWorkout.getPicture();
			buffWorkoutImage = ImageIO.read(new File(path));
			Image img = SwingFXUtils.toFXImage(buffWorkoutImage, null);
			workoutImage.setImage(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	

    }

    //dead function?
    @FXML
    void  Servings(ActionEvent event) {

    }

    //increments the serving size and displays it
    @FXML
    void servingPlus(ActionEvent event) {
    	servingSize++;
    	servingNumberLbl.setText(servingSize + "");
    	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
    }

    //decrements the serving size and displays it
    @FXML
    void servingMinus(ActionEvent event) {
    	servingSize--;
    	if (servingSize < 1)
    		servingSize=1;
    	servingNumberLbl.setText(servingSize + "");
    	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");

    }

    //Takes user to the Add New Food Window
    @FXML
    void openFood(ActionEvent event) throws IOException {
    	
    	
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FoodInput.fxml"));
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		Model.foodStage.setTitle("Add New Food");
		Model.foodStage.setScene(scene);
		Model.foodStage.show();

    }

    //Takes the user to the add New Workout Window
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

    //increments the rep size and displays it
    @FXML
    void repPlus(ActionEvent event) {
    	
    	repSize++;
    	repNumberLbl.setText(repSize + "");
    	calBurnedAmount.setText((repSize * currentWorkout.getCalories())+"");

    }

    //decrements the rep size and displays it
    @FXML
    void repMinus(ActionEvent event) {
    	
    	repSize--;
    	if (repSize < 1) {
    		repSize = 1;
    	}
    	repNumberLbl.setText(repSize + "");
    	calBurnedAmount.setText((repSize * currentWorkout.getCalories())+"");
    	

    }

    //Once user has selected their food and number of servings, this function handles the addition of this food to the user's history as well
    //as displaying the updated information to them on the current screen
    @FXML
    void addFoodToDay(ActionEvent event) throws IOException {
    	
    	int servingCal = currentFood.getCalories() * servingSize; //calculates calories for this serving
    	//output string for list view assembled
    	String output = currentFood.getName() + " : " + currentFood.getServingSize() + " (x" + servingSize + ") : " + servingCal + " calories";
    	
    	//Handles the fact that populateUserHistory() in user assumes that the user at least ate their basal metabolism on a day that has no data.
    	//If they are adding food to a blank, the string below will be present. and if it is, the user's calories will be set to zero since actual 
    	//food data is being added
     	if (foodObs.get(0).equals("*** No Food Eaten Today ***")) {
    		currentDay.setCaloriesConsumed(0);
    		foodObs.clear();
    	}
     	
     	//now adds relevant data to controls and shows the food in the ListView
    	currentDay.setCaloriesConsumed(currentDay.getCaloriesConsumed() + servingCal);
    	totalCalConsumed.setText(currentDay.getCaloriesConsumed()+ "");
    	foodObs.add(output);
    	foodEaten.setItems(foodObs);
    	String foodHistoryString = "";
    	
    	//Constructs the string that will be used in the foodHistory properties file
    	for (int i = 0; i < foodObs.size(); i++) {
    		if (i != foodObs.size()-1) {
    			foodHistoryString += foodObs.get(i) + ",";
    		}else {
    			foodHistoryString += foodObs.get(i);
    		}
    	}
    	
    	//stores the data
    	Model.addDay(currentDay);
    	Model.addFoodHistory(currentDay, foodHistoryString);
    	
    	//resets labels following the changes
    	servingSize = 1;
    	servingNumberLbl.setText("1");
    	energyLbl.setText(currentDay.getEnergyBalance()+"%");
    	

    }
    
    //Identical in functionality to addFoodToDay, but now for workouts. This time, basal metabolism is always added to the calories burned for
    //the day, per actual nutrition formulae

    @FXML
    void addWorkoutToDay(ActionEvent event) throws IOException {
    	//setup
    	int repCal = currentWorkout.getCalories() * repSize;
    	String output = currentWorkout.getName() + " : " + currentWorkout.getRepSize();
    	output += " " + currentWorkout.getRepName() + "(x" + repSize + ") : " + repCal + " calories";
    	
    	//checks if the workout is first added for the day, updates listView accordingly
    	if (workoutObs.get(0).equals("*** No Workouts Done Today ***")) {
    		currentDay.setCaloriesBurned(Model.currentUser.getBasalMetabolism());
    		workoutObs.clear();
    	}
    	
    	//updates controls
    	currentDay.setCaloriesBurned(currentDay.getCaloriesBurned() + repCal);
    	totalCalBurned.setText(currentDay.getCaloriesBurned()+ "");
    	workoutObs.add(output);
    	workoutsDone.setItems(workoutObs);
    	
    	//prepares a string for writing to file
    	String workoutHistoryString = "";
    	for (int i = 0; i < workoutObs.size(); i++) {
    		if (i != workoutObs.size()-1) {
    			workoutHistoryString += workoutObs.get(i) + ",";
    		}else {
    			workoutHistoryString += workoutObs.get(i);
    		}
    	}
    	
    	//adds data to file
    	Model.addDay(currentDay);
    	Model.addWorkoutHistory(currentDay, workoutHistoryString);
    	
    	//resets controls after addition
    	repSize = 1;
    	repNumberLbl.setText("1");
    	energyLbl.setText(currentDay.getEnergyBalance()+"%");
    	
    	
    	

    }

    //allows the user to search for a food in the search bar
    @FXML
    void searchFood(ActionEvent event) {
    	
    	//if the food is found, update object and labels
    	if (Model.queryFood(searchFoodTF.getText())) {
    		currentFood = Model.foodMap.get(searchFoodTF.getText());
    		foodNameLbl.setText(currentFood.getName());
        	calPerServingAmount.setText(currentFood.getCalories() + "");
        	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
        	
        //food is not found, default object is set and labels too
    	}else {
    		currentFood = emptyFood;
    		foodNameLbl.setText(currentFood.getName());
        	calPerServingAmount.setText(currentFood.getCalories() + "");
        	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
    	}
    	
    	//loads the appropriate picture in either case
    	try {
    		String path = "./src/staticFiles/" + currentFood.getPicture();
			buffFoodImage = ImageIO.read(new File(path));
			Image img = SwingFXUtils.toFXImage(buffFoodImage, null);
			foodImage.setImage(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    
    //Identical functionality to searchFood, but for workouts
    @FXML
    void searchWorkout(ActionEvent event) {
    	
    	//workout is found, update object and controls
    	if (Model.queryWorkout(searchWorkoutTF.getText())) {
    		currentWorkout = Model.workoutMap.get(searchWorkoutTF.getText());
    		workoutNameLbl.setText(currentWorkout.getName());
    		calBurnedPerRepAmount.setText(currentWorkout.getCalories() + "");
    		calBurnedAmount.setText((repSize * currentWorkout.getCalories()) + "");
    		
    	//workout not found, default object used
    	}else {
    		currentWorkout = emptyWorkout;
    		workoutNameLbl.setText(currentWorkout.getName());
        	calBurnedPerRepAmount.setText(currentWorkout.getCalories() + "");
        	calBurnedAmount.setText((repSize * currentWorkout.getCalories())+"");
    	}
    	
    	//loads appropriate picture
    	try {
    		String path = "./src/staticFiles/" + currentWorkout.getPicture();
			buffWorkoutImage = ImageIO.read(new File(path));
			Image img = SwingFXUtils.toFXImage(buffWorkoutImage, null);
			workoutImage.setImage(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    //displays an alert explaining basal metabolism
    @FXML
    void getBaslAlert(ActionEvent event) {
    	
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setHeaderText("Basal Metabolism");
    	a.setContentText("Basal metabolism is the calories your body burns even at rest, for all daily functions including pumping your heart and breathing");
    	a.show();

    }
	
	
	
	@FXML
    private Button backBtn;

	//takes the user to the Dash Window (back button)
    @FXML
    void toDash(ActionEvent event) throws IOException {
    	
    	Model.currentDate = LocalDate.now();
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Dash.fxml"));
    	meal.getChildren().setAll(pane);

    }
    
    //refreshes the combobox by reloading scene, same execution that reloads the screen when switching dates
    @FXML
    void refreshFood(ActionEvent event) throws IOException {
    	
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

  //refreshes the combobox by reloading scene, same execution that reloads the screen when switching dates
    @FXML
    void refreshWorkout(ActionEvent event) throws IOException {
    	
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
    
    
    
    //Sets the data seen at the opening of the Nutrition Manager
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//local variables
		String input;
		datePicker.setValue(Model.today);
		
		//gets current User's info
		Model.currentUser.populateUserHistory();
		String formattedDate = Model.currentDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    	currentDateLbl.setText(formattedDate);
    	
    	//loads the day object from the user's history
    	Day day = Model.currentUser.getDay(Model.currentDate);
    	if (day != null) {
    		currentDay = day;
    	}else {
    		//error
    		System.out.println("day not found");
    	}
    	
    	//loads food info
    	
    	//finds the foodEaten history from the hashmap
    	if (Model.foodHistoryMap.containsKey(currentDay.getUser() + "," + currentDay.getDate())) {
    		
    		//Each food item is separated by a comma in the string (CSV), splits each item into a string array
    		String[] foodHistoryArr = Model.foodHistoryMap.get(currentDay.getUser() + "," + currentDay.getDate()).split(",");
    		
    		//iterates over array and adds each item to the foodEaten ListView
    		for(String f: foodHistoryArr) {
    			foodObs.add(f);
    		}
    		totalCalConsumed.setText(currentDay.getCaloriesConsumed()+"");
    	//No food eaten for the day in the history file
    	}else {
    		foodObs.add("*** No Food Eaten Today ***");
    		totalCalConsumed.setText(0+"");
    	}
    	
    	//actual ListView setup
    	foodEaten.getItems().setAll(foodObs);
    	
    	//Loads food combo box with food from file
    	for(Entry<String, Food> entry: Model.foodMap.entrySet()) {
    		input = entry.getValue().getName();
    		chooseFoodCB.getItems().add(input);
        }
    	
    	//more control setup
    	chooseFoodCB.setValue("-- Choose Food --");
    	foodNameLbl.setText(currentFood.getName());
    	calPerServingAmount.setText(currentFood.getCalories() + "");
    	servingNumberLbl.setText(servingSize + "");
    	calConsumedAmount.setText((servingSize * currentFood.getCalories())+"");
    	
    	//loads the food image
    	try {
			buffFoodImage = ImageIO.read(new File("./src/staticFiles/" + currentFood.getPicture()));
			Image img = SwingFXUtils.toFXImage(buffFoodImage, null);
			foodImage.setImage(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//loads workout info, similar to code above, but for workouts
    	
    	//loads ListView and control data
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
    	
    	//sets up comboBox
    	for(Entry<String, Workout> entry: Model.workoutMap.entrySet()) {
    		input = entry.getValue().getName();
    		chooseWorkoutCB.getItems().add(input);
        }
    	
    	//more control setup
    	chooseWorkoutCB.setValue("-- Choose Workout --");
    	workoutNameLbl.setText(currentWorkout.getName());
    	calBurnedPerRepAmount.setText(currentWorkout.getCalories() + "");
    	repNumberLbl.setText(repSize + "");
    	calBurnedAmount.setText((repSize * currentWorkout.getCalories())+"");
    	basalLbl.setText(Model.currentUser.getBasalMetabolism() + "");
    	energyLbl.setText(currentDay.getEnergyBalance() + "%");
    	
    	//loads the appropriate picture
    	try {
			buffWorkoutImage = ImageIO.read(new File("./src/staticFiles/" + currentWorkout.getPicture()));
			Image img = SwingFXUtils.toFXImage(buffWorkoutImage, null);
			workoutImage.setImage(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		
	}

}
