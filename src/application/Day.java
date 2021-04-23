package application;

import java.util.ArrayList;

public class Day {
	
	private String date;
	private String user;
	private int caloriesConsumed;
	private int caloriesBurned;
	private ArrayList<String> foodList;
	private ArrayList<String> workoutList;
	
	public Day(String date, String user, int caloriesConsumed, int caloriesBurned, ArrayList<String> foodList, ArrayList<String> workoutList) {
		this.date = date;
		this.user = user;
		this.caloriesBurned = caloriesBurned;
		this.caloriesConsumed = caloriesConsumed;
		this.foodList = foodList;
		this.workoutList = workoutList;
	}
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getCaloriesConsumed() {
		return caloriesConsumed;
	}

	public void setCaloriesConsumed(int caloriesConsumed) {
		this.caloriesConsumed = caloriesConsumed;
	}

	public int getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(int caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	public ArrayList<String> getFoodList() {
		return foodList;
	}

	public void setFoodList(ArrayList<String> foodList) {
		this.foodList = foodList;
	}

	public ArrayList<String> getWorkoutList() {
		return workoutList;
	}

	public void setWorkoutList(ArrayList<String> workoutList) {
		this.workoutList = workoutList;
	}

	public int getEnergyBalance() {
		return (int)((double)caloriesConsumed/caloriesBurned - 1) * 100;
	}
	
	public String getFoodListString() {
		String output = "";
		for (int i = 0; i < foodList.size(); i++) {
			output += foodList.get(i) + "-";
		}
		return output;
	}
	
	public String getWorkoutListString() {
		String output = "";
		for (int i = 0; i < workoutList.size(); i++) {
			output += workoutList.get(i) + "-";
		}
		return output;
	}

	@Override
	public String toString() {
		return date + "," + user + "," + caloriesConsumed + "," + caloriesBurned + "," + 
				getFoodListString() + "," + getWorkoutListString();
	}
	
	

}
