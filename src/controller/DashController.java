package controller;

import java.io.IOException;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Day;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Model;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import javafx.event.*;
import java.net.*;
import java.util.*;

public class DashController implements Initializable{
	
	//FXML elements
	@FXML
    private Button logoutBtn;
	
	@FXML
    private Button addMealBtn;
	
	@FXML
    private Button newWorkoutBtn;
	
	@FXML
    private Button newFoodBtn;
	
	@FXML
    private LineChart<String, Number> graph;

    @FXML
    private AnchorPane dash;

    /**
     * @param event
     * @throws IOException
     * 
     * takes user to the Login screen (Back button)
     */
    @FXML
    void toLogin(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
    	dash.getChildren().setAll(pane);

    }
    
    /**
     * @param event
     * @throws IOException
     * 
     * Takes user to the Nutrition Manager screen
     */
    @FXML
    void toMeal(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DayInput.fxml"));
    	dash.getChildren().setAll(pane);

    }
    
    /**
     * @param event
     * @throws IOException
     * 
     * Opens Add new food window
     */
    @FXML
	void openNewFood(ActionEvent event) throws IOException {

		Stage stage = new Stage();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FoodInput.fxml"));
		Scene scene = new Scene(root, 800, 800);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		stage.setTitle("Add New Food");
		stage.setScene(scene);
		stage.show();

	}

    /**
     * @param event
     * @throws IOException
     * 
     * Opens Add Workout Window
     */
    @FXML
    void openNewWorkout(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/WorkoutInput.fxml"));
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		stage.setTitle("Add New Workout");
		stage.setScene(scene);
		stage.show();

    }
    
    //Line Chart functions
    
    /**
     * @param event
     * 
     * Displays the last week's calories consumed and burned on the Line chart
     */
    @FXML
    void getWeekCal(ActionEvent event) {
    	
    	//sets up objects needed for the chart
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Calories");
		graph.setTitle("Past week's nutrition");
		
		//populates an array list with day objects from the user's history
		ArrayList<Day> weekData = new ArrayList<Day>();
		
		//iteration grabs the last 7 objects in the userHistory ArrayList
		for (int i = Model.currentUser.getUserHistory().size() - 8; i < Model.currentUser.getUserHistory().size()-1;i++) {
			weekData.add(Model.currentUser.getUserHistory().get(i));
		}
		
		//prepares more of the needed LineChart objects, then iterates over the weekData ArrayList and adds the date and the
		//calories burned/consumed to the chart objects
		XYChart.Series<String, Number> consumed = new XYChart.Series<String, Number>();
        consumed.setName("Calories Consumed");
        XYChart.Series<String, Number> burned = new XYChart.Series<String, Number>();
        burned.setName("Calories Burned");
        for(Day day: weekData) {
        	consumed.getData().add(new XYChart.Data<String, Number>(day.getDate().toString(), day.getCaloriesConsumed()));
        	burned.getData().add(new XYChart.Data<String, Number>(day.getDate().toString(), day.getCaloriesBurned()));
        }
        
        //makes the LineChart with the data added
        graph.getData().setAll(consumed, burned);
    	

    }
    
    //identical functionality to the getWeekCal(), but does so for past month

    @FXML
    void getMonthCal(ActionEvent event) {
    	
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Calories");
		graph.setTitle("Past Month's nutrition");
		
		ArrayList<Day> monthData = new ArrayList<Day>();
		
		//iteration grabs last 31 days of user's history
		for (int i = Model.currentUser.getUserHistory().size() - 32; i < Model.currentUser.getUserHistory().size()-1;i++) {
			monthData.add(Model.currentUser.getUserHistory().get(i));
		}
		XYChart.Series<String, Number> consumed = new XYChart.Series<String, Number>();
        consumed.setName("Calories Consumed");
        XYChart.Series<String, Number> burned = new XYChart.Series<String, Number>();
        burned.setName("Calories Burned");
        for(Day day: monthData) {
        	consumed.getData().add(new XYChart.Data<String, Number>(day.getDate().toString(), day.getCaloriesConsumed()));
        	burned.getData().add(new XYChart.Data<String, Number>(day.getDate().toString(), day.getCaloriesBurned()));
        }
        graph.getData().setAll(consumed, burned);

    }
    
    //Similar functionality to the getWeekCal and getMonthCal, but trying to poll every day for 3 months would crowd the 
    //graph, instead, the function creates rolling averages for 1 week period and displays that data to the user

    @FXML
    void getQuarterCal(ActionEvent event) {
    	
    	//set up
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Calories");
		graph.setTitle("Past 3 Month's nutrition");
		
		//gets past 91 days of history (~ 3 months, but exactly 13 weeks)
		ArrayList<Day> monthData = new ArrayList<Day>();
		for (int i = Model.currentUser.getUserHistory().size() - 92; i < Model.currentUser.getUserHistory().size()-1;i++) {
			monthData.add(Model.currentUser.getUserHistory().get(i));
		}
		
		XYChart.Series<String, Number> consumed = new XYChart.Series<String, Number>();
        consumed.setName("Calories Consumed");
        XYChart.Series<String, Number> burned = new XYChart.Series<String, Number>();
        burned.setName("Calories Burned");
        
        //computes sums for 1 week period
        int consumedSum = 0, burnedSum = 0;
        for(int i=1;i<=monthData.size();i++) {
        	consumedSum += monthData.get(i-1).getCaloriesConsumed();
        	burnedSum += monthData.get(i-1).getCaloriesBurned();
        	
        	//after one week of data, adds the average to the the chart
        	if(i%7 == 0) {
        		consumed.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), consumedSum/7));
        		burned.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), burnedSum/7));
        		consumedSum = 0;
        		burnedSum = 0;
        	}
        }
        graph.getData().setAll(consumed, burned);

    }

    //Identical to getQuarterCal, but calculates data for entire year (almost). In the effort to provide accurate data, the rolling
    //average will calculate after every 30 days. The graph will display 12 30 day periods, not necessarily in the same month, but in
    // a way that makes visualizing the data
    @FXML
    void getYearCal(ActionEvent event) {
    	
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Calories");
		graph.setTitle("Past Year's nutrition");
		
		//gets last 360 days of data, for exactly 12 30 day periods
		ArrayList<Day> monthData = new ArrayList<Day>();
		for (int i = Model.currentUser.getUserHistory().size() - 361; i < Model.currentUser.getUserHistory().size()-1;i++) {
			monthData.add(Model.currentUser.getUserHistory().get(i));
		}
		XYChart.Series<String, Number> consumed = new XYChart.Series<String, Number>();
        consumed.setName("Calories Consumed");
        XYChart.Series<String, Number> burned = new XYChart.Series<String, Number>();
        burned.setName("Calories Burned");
        
        int consumedSum = 0, burnedSum = 0;
        for(int i=1;i<=monthData.size();i++) {
        	consumedSum += monthData.get(i-1).getCaloriesConsumed();
        	burnedSum += monthData.get(i-1).getCaloriesBurned();
        	
        	//computes average and adds data to chart every 30 days
        	if(i%30 == 0) {
        		consumed.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), consumedSum/30));
        		burned.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), burnedSum/30));
        		consumedSum = 0;
        		burnedSum = 0;
        	}
        }
        graph.getData().setAll(consumed, burned);

		
		

    }

    //Identical in functionality to getWeekCal(), but adds the user's energy balance data to the graph
    @FXML
    void getWeekEB(ActionEvent event) {
    	
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Balance");
		graph.setTitle("Past week's Energy Balance");
		
		ArrayList<Day> weekData = new ArrayList<Day>();
		for (int i = Model.currentUser.getUserHistory().size() - 8; i < Model.currentUser.getUserHistory().size()-1;i++) {
			weekData.add(Model.currentUser.getUserHistory().get(i));
		}
		XYChart.Series<String, Number> energy = new XYChart.Series<String, Number>();
        energy.setName("Energy Balance");
       
        
        for(Day day: weekData) {
        	energy.getData().add(new XYChart.Data<String, Number>(day.getDate().toString(), day.getEnergyBalance()));
        	
        }
        graph.getData().setAll(energy);


    }

    //Identical in functionality to getMonthCal(), but adds the user's energy balance data to the graph
    @FXML
    void getMonthEB(ActionEvent event) {
    	
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Balance");
		graph.setTitle("Past Month's Energy Balance");
		
		ArrayList<Day> monthData = new ArrayList<Day>();
		for (int i = Model.currentUser.getUserHistory().size() - 32; i < Model.currentUser.getUserHistory().size()-1;i++) {
			monthData.add(Model.currentUser.getUserHistory().get(i));
		}
		XYChart.Series<String, Number> energy = new XYChart.Series<String, Number>();
        energy.setName("Energy Balance");
       
        
        for(Day day: monthData) {
        	energy.getData().add(new XYChart.Data<String, Number>(day.getDate().toString(), day.getEnergyBalance()));
        	
        }
        graph.getData().setAll(energy);

    }

    //Identical in functionality to getQuarterCal(), but adds the user's energy balance data to the graph
    @FXML
    void getQuarterEB(ActionEvent event) {
    	
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Balance");
		graph.setTitle("Past 3 Month's Energy Balance");
		
		ArrayList<Day> monthData = new ArrayList<Day>();
		for (int i = Model.currentUser.getUserHistory().size() - 92; i < Model.currentUser.getUserHistory().size()-1;i++) {
			monthData.add(Model.currentUser.getUserHistory().get(i));
		}
		XYChart.Series<String, Number> energy = new XYChart.Series<String, Number>();
        energy.setName("Energy Balance");
       
        
        int energySum = 0;
        for(int i=1;i<=monthData.size();i++) {
        	energySum += monthData.get(i-1).getEnergyBalance();
        	
        	if(i%7 == 0) {
        		energy.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), energySum/7));
        		energySum = 0;
        	}
        }
        graph.getData().setAll(energy);

    }

    //Identical in functionality to getYearCal(), but adds the user's energy balance data to the graph
    @FXML
    void getYearEB(ActionEvent event) {
    	
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Balance");
		graph.setTitle("Past 3 Month's Energy Balance");
		
		ArrayList<Day> monthData = new ArrayList<Day>();
		for (int i = Model.currentUser.getUserHistory().size() - 361; i < Model.currentUser.getUserHistory().size()-1;i++) {
			monthData.add(Model.currentUser.getUserHistory().get(i));
		}
		XYChart.Series<String, Number> energy = new XYChart.Series<String, Number>();
        energy.setName("Energy Balance");
       
        
        int energySum = 0;
        for(int i=1;i<=monthData.size();i++) {
        	energySum += monthData.get(i-1).getEnergyBalance();
        	
        	if(i%30 == 0) {
        		energy.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), energySum/30));
        		energySum = 0;
        	}
        }
        graph.getData().setAll(energy);

    }
    
    //opens the Weigh-In window
    @FXML
    void openWeighIn(ActionEvent event) throws IOException {
    	
    	//fresh stage for new window
    	Stage stage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/WeighIn.fxml"));
		Scene scene = new Scene(root,600,600);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		stage.setTitle("Weigh-In");
		stage.setScene(scene);
		stage.show();

    }

    //code launched as Dash Window loads
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//gets current and accurate user history, even after they have made changes in Nutrition Manager
		Model.currentUser.populateUserHistory();
		
		//below code is identical to the getWeekCal, basically, the last week nutrition graph is what the user will see
		//as they open the dash window
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Calories");
		graph.setTitle("Past week's nutrition");
		
		ArrayList<Day> weekData = new ArrayList<Day>();
		for (int i = Model.currentUser.getUserHistory().size() - 8; i < Model.currentUser.getUserHistory().size()-1;i++) {
			weekData.add(Model.currentUser.getUserHistory().get(i));
		}
		XYChart.Series<String, Number> consumed = new XYChart.Series<String, Number>();
        consumed.setName("Calories Consumed");
        XYChart.Series<String, Number> burned = new XYChart.Series<String, Number>();
        burned.setName("Calories Burned");
        
        for(Day day: weekData) {
        	
        	consumed.getData().add(new XYChart.Data<String, Number>(day.getDate().toString(), day.getCaloriesConsumed()));
        	burned.getData().add(new XYChart.Data<String, Number>(day.getDate().toString(), day.getCaloriesBurned()));
        }
        graph.getData().setAll(consumed, burned);
        
        //Determines if it has been more than a week since the user's last weigh in and pops up the Weigh in window if that is the case
        //This way, the app reminds the user to keep a current weight history
        if(ChronoUnit.DAYS.between(Model.currentUser.getLastWeighIn(), Model.today) >= 7) {
        	try {
        	Stage stage = new Stage();
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/WeighIn.fxml"));
    		Scene scene = new Scene(root,600,600);
    		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
    		stage.setTitle("Weigh-In");
    		stage.setScene(scene);
    		stage.show();
        	}catch(IOException e) {
        		e.printStackTrace();
        	}
        }
        
        
		
	}


}
