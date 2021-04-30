package application;

import java.util.ArrayList;

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
	
	//Getters and Setters

	/**
	 * @return date
	 * 
	 */

	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return
	 */
	public int getCaloriesConsumed() {
		return caloriesConsumed;
	}

	/**
	 * @param caloriesConsumed
	 */
	public void setCaloriesConsumed(int caloriesConsumed) {
		this.caloriesConsumed = caloriesConsumed;
	}

	/**
	 * @return
	 */
	public int getCaloriesBurned() {
		return caloriesBurned;
	}

	/**
	 * @param caloriesBurned
	 */
	public void setCaloriesBurned(int caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	/**
	 * @return int
	 * 
	 * Calculates the user's energy balance. Comapares calories consumed to calories burned
	 * and determines a positive percentage (int) if the user is gaining weight, more calories consumed
	 * or loosing weight, more calories burned
	 */

	public int getEnergyBalance() {
		double ebDouble = (((double) caloriesConsumed / caloriesBurned) - 1) * 100;
		return (int) ebDouble;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return date + "," + user + "," + caloriesConsumed + "," + caloriesBurned;
	}

}
