package application;

public class Day {

	private String date;
	private String user;
	private int caloriesConsumed;
	private int caloriesBurned;

	public Day(String date, String user, int caloriesConsumed, int caloriesBurned) {
		this.date = date;
		this.user = user;
		this.caloriesBurned = caloriesBurned;
		this.caloriesConsumed = caloriesConsumed;

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

	public int getEnergyBalance() {
		double ebDouble = (((double) caloriesConsumed / caloriesBurned) - 1) * 100;
		return (int) ebDouble;
	}

	@Override
	public String toString() {
		return date + "," + user + "," + caloriesConsumed + "," + caloriesBurned;
	}

}
