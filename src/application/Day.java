package application;

public class Day {
	
	private String date;
	private String user;
	private int caloriesConsumed;
	private int caloriesBurned;
	private String foodList;
	private String workoutList;
	
	public Day(String date, String user, int caloriesConsumed, int caloriesBurned, String foodList, String workoutList) {
		this.date = date;
		this.user = user;
		this.caloriesBurned = caloriesBurned;
		this.caloriesConsumed = caloriesConsumed;
		
		//these two need to change to actual ArrayLists, but must be converted to a CSV string to be stored in the properties file.
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

	public String getFoodList() {
		return foodList;
	}

	public void setFoodList(String foodList) {
		this.foodList = foodList;
	}

	public String getWorkoutList() {
		return workoutList;
	}

	public void setWorkoutList(String workoutList) {
		this.workoutList = workoutList;
	}

	public int getEnergyBalance() {
		return (int)((double)caloriesConsumed/caloriesBurned - 1) * 100;
	}

	@Override
	public String toString() {
		return date + "," + user + "," + caloriesConsumed + "," + caloriesBurned + "," + 
				foodList + "," + workoutList;
	}
	
	

}
