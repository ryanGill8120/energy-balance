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

public class DashController implements Initializable{
	
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

    @FXML
    void toLogin(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
    	dash.getChildren().setAll(pane);

    }
    
    @FXML
    void toMeal(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DayInput.fxml"));
    	dash.getChildren().setAll(pane);

    }
    
    @FXML
    void openNewFood(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FoodInput.fxml"));
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		stage.setTitle("Add New Food");
		stage.setScene(scene);
		stage.show();

    }

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
    
    @FXML
    void getWeekCal(ActionEvent event) {
    	
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
    	

    }

    @FXML
    void getMonthCal(ActionEvent event) {
    	
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Calories");
		graph.setTitle("Past Month's nutrition");
		
		ArrayList<Day> monthData = new ArrayList<Day>();
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

    @FXML
    void getQuarterCal(ActionEvent event) {
    	
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Calories");
		graph.setTitle("Past 3 Month's nutrition");
		
		ArrayList<Day> monthData = new ArrayList<Day>();
		for (int i = Model.currentUser.getUserHistory().size() - 92; i < Model.currentUser.getUserHistory().size()-1;i++) {
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
        	if(i%7 == 0) {
        		consumed.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), consumedSum/7));
        		burned.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), burnedSum/7));
        		consumedSum = 0;
        		burnedSum = 0;
        	}
        }
        graph.getData().setAll(consumed, burned);

    }

    @FXML
    void getYearCal(ActionEvent event) {
    	
    	CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Day");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Calories");
		graph.setTitle("Past Year's nutrition");
		
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
        	if(i%30 == 0) {
        		consumed.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), consumedSum/30));
        		burned.getData().add(new XYChart.Data<String, Number>(monthData.get(i-1).getDate().toString(), burnedSum/30));
        		consumedSum = 0;
        		burnedSum = 0;
        	}
        }
        graph.getData().setAll(consumed, burned);

		
		

    }

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
    
    @FXML
    void openWeighIn(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/WeighIn.fxml"));
		Scene scene = new Scene(root,600,600);
		scene.getStylesheets().add(getClass().getResource("/staticFiles/application.css").toExternalForm());
		stage.setTitle("Weigh-In");
		stage.setScene(scene);
		stage.show();

    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Model.currentUser.populateUserHistory();
		
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
