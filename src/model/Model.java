package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map.Entry;

import application.Day;
import application.Food;
import application.User;
import application.Workout;

public class Model {
	
	
	
	public static LocalDate today = LocalDate.now();
	
	//data structures for Users, Food, Workouts, User History by day (for calendar and graph)
	
	//a temporary storage hashMap, used for writing to properties file
	public static HashMap<String, String> temp = new HashMap<String, String>();
	
	//live data structures
	public static HashMap<String, String> auth = new HashMap<String, String>();
	public static HashMap<String, Food> foodMap = new HashMap<String, Food>();
	public static HashMap<String, Workout> workoutMap = new HashMap<String, Workout>();
	public static HashMap<String, User> userMap = new HashMap<String, User>();
	public static HashMap<String, Day> historyMap = new HashMap<String, Day>();
	public static User currentUser;
	
	//File reading/writing objects
	public static Properties authProp = new Properties();
	public static Properties foodProp = new Properties();
	public static Properties workoutProp = new Properties();
	public static Properties userProp = new Properties();
	public static Properties historyProp = new Properties();
	public static File authFile = new File("auth.properties");
	public static File foodFile = new File("food.properties");
	public static File workoutFile = new File("workout.properties");
	public static File userFile = new File("users.properties");
	public static File historyFile = new File("history.properties");
	
	
	//start-up file loader function()
	public static void loadFiles() throws IOException{
		
		//loads the file from application directory
		FileInputStream authReader=new FileInputStream(authFile);
		FileInputStream userReader = new FileInputStream(userFile);
		FileInputStream workoutReader = new FileInputStream(workoutFile);
		FileInputStream foodReader = new FileInputStream(foodFile);
		FileInputStream historyReader = new FileInputStream(historyFile);
		
		authProp.load(authReader);
		userProp.load(userReader);
		workoutProp.load(workoutReader);
		foodProp.load(foodReader);
		historyProp.load(historyReader);
		
		authReader.close();
		userReader.close();
		workoutReader.close();
		foodReader.close();
		historyReader.close();
		
		//iterates through the file and adds values to HashMap
		for(Object key: authProp.stringPropertyNames()){
        	auth.put(key.toString(), authProp.get(key).toString());
        }
		
		//loads all users
		for(Object key: userProp.stringPropertyNames()) {
			String[] userArr = userProp.get(key).toString().split(",");
			String userName = userArr[0];
			String name = userArr[1];
			double weight = (double)Double.parseDouble(userArr[2]);
			double height = (double)Double.parseDouble(userArr[3]);
			int age = (int)Integer.parseInt(userArr[4]);
			String sex = userArr[5];
			User user = new User(userName, name, weight, height, age, sex);
			userMap.put(userName, user);
		}
		
		//loads all food data
		for(Object key: foodProp.stringPropertyNames()) {
			String[] foodArr = foodProp.get(key).toString().split(",");
			String name = foodArr[0];
			String picture = foodArr[1];
			int calories = (int)Integer.parseInt(foodArr[2]);
			Food food = new Food(name, picture, calories);
			foodMap.put(name, food);
		}
		
		//loads workouts
		for(Object key: workoutProp.stringPropertyNames()) {
			String[] workoutArr = workoutProp.get(key).toString().split(",");
			String name = workoutArr[0];
			String picture = workoutArr[1];
			int calories = (int)Integer.parseInt(workoutArr[2]);
			int repSize = (int)Integer.parseInt(workoutArr[3]);
			String repName = workoutArr[4];
			Workout workout = new Workout(name, picture, calories, repSize, repName);
			workoutMap.put(name, workout);
		}
		
		//loads history (NOT SCALABLE) right now the program will load all data from all users into memory 
		//at launch, we can implement a saving/loading mechanism so that not all of this would be in memory 
		//for too long. Also, we only need the last year of data for one user, which can be attained from this
		//file, but will take up way less space in memory. Realistically, this would be handled by a database
		//framework like SQL
		
		for (Object key: historyProp.stringPropertyNames()) {
			String[] historyArr = historyProp.get(key).toString().split(",");
			String date = historyArr[0];
			String user = historyArr[1];
			int caloriesConsumed = (int)Integer.parseInt(historyArr[2]);
			int caloriesBurned = (int)Integer.parseInt(historyArr[3]);
			String foodList = historyArr[4];
			String workoutList = historyArr[5];
			Day day = new Day(date, user, caloriesConsumed, caloriesBurned, foodList, workoutList);
			historyMap.put(key.toString(), day);
			
		}
	}
	
	//adder functions for all data structures
	
	//add user
	public static void addUser(User user, String password) throws IOException {
		
		FileOutputStream userWriter = new FileOutputStream(userFile,true);
		FileOutputStream authWriter = new FileOutputStream(authFile,true);
		auth.put(user.getUserName(), password);
		authProp.putAll(auth);
		authProp.store(authWriter, null);
		
		userMap.put(user.getUserName(), user);
		for (Entry<String, User> entry: userMap.entrySet()) {
    		temp.put(entry.getKey(), entry.getValue().toString());
		}
		userProp.putAll(temp);
		temp.clear();
		userProp.store(userWriter, null);
		
		
	}
	
	//add food
	public static void addFood(Food food) throws IOException {
		
		FileOutputStream writer = new FileOutputStream(foodFile,true);
		foodMap.put(food.getName(), food);
		for (Entry<String, Food> entry: foodMap.entrySet()) {
    		temp.put(entry.getKey(), entry.getValue().toString());
		}
		foodProp.putAll(temp);
		temp.clear();
		foodProp.store(writer, null);
		
	}
	
	//add workout
	public static void addWorkout(Workout workout) throws IOException {
		
		FileOutputStream writer = new FileOutputStream(workoutFile,true);
		workoutMap.put(workout.getName(), workout);
		for (Entry<String, Workout> entry: workoutMap.entrySet()) {
    		temp.put(entry.getKey(), entry.getValue().toString());
		}
		workoutProp.putAll(temp);
		temp.clear();
		workoutProp.store(writer, null);
		
	}
	
	public static void addDay(Day day) throws IOException {
		
		FileOutputStream writer = new FileOutputStream(historyFile, true);
		historyMap.put((day.getUser() + "," + day.getDate()), day);
		for (Entry<String, Day> entry: historyMap.entrySet()) {
			temp.put(entry.getKey(), entry.getValue().toString());
		}
		historyProp.putAll(temp);
		temp.clear();
		historyProp.store(writer, null);
		
	}
	
	//search methods
	
	public boolean authenticate(String username, String password) {
		if (auth.containsKey(username)) {
			if (password.equals(auth.get(username))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean queryFood(String name) {
		if (foodMap.containsKey(name))
			return true;
		return false;
	}
	
	public boolean queryWorkout(String name) {
		if (workoutMap.containsKey(name))
			return true;
		return false;
	}
	
	
	
	//nutrition calculation functions. (BMI, avg. calories/day, projected weight gain/loss, etc.)
	
	

}
