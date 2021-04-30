package application;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.Model;

/**
 * Data structure for the user's information. Note that the username is saved but the password is not.
 *
 */
public class User {

	// String for user's username.
	private String userName;
	// String for user's name.
	private String name;
	// Double for user's weight.
	private double weight;
	// Double for user's height.
	private double height;
	// LocalDate for user's birthday.
	private LocalDate birthday;
	// LocalDate for user's last weigh-in.
	private LocalDate lastWeighIn;
	// String for user's sex.
	private String sex;
	// ArrayList of Days used to store the user's previous history.
	private ArrayList<Day> userHistory = new ArrayList<Day>();

	/**
	 * User constructor for instanciation.
	 * 
	 * @param String    userName: Username of User
	 * @param String    name: Name of User
	 * @param double    weight: Weight of User
	 * @param double    height: Height of User
	 * @param LocalDate birthday: Birth date of User
	 * @param LocalDate lastWeighIn: Last time User weighed-in
	 * @param String    sex: Sex of User
	 **/
	public User(String userName, String name, double weight, double height, LocalDate birthday, LocalDate lastWeighIn,
			String sex) {
		this.userName = userName;
		this.name = name;
		this.weight = weight;
		this.height = height;
		this.birthday = birthday;
		this.lastWeighIn = lastWeighIn;
		this.sex = sex;
	}
	
	/**
	 * Populates the user's history for the last year. Note, if a corresponding day is not present in the properties file, 
	 * the function will assign a 'blank day' where it is assumed the user burned their basal metabolism and consumed just
	 * as much. This way, the graph will be more readable when it uses this information. The user can update past days anytime
	 * in the Nutrition Manager 
	 */
	public void populateUserHistory() {
		
		userHistory.clear(); //resets the arrayList
		
		//creates a LocalDate object equal to one year ago
		LocalDate yearAgo = LocalDate.of(Model.today.getYear()-1, Model.today.getMonthValue(), Model.today.getDayOfMonth());
		long numOfDaysBetween = ChronoUnit.DAYS.between(yearAgo, Model.today);
		
		//populates an ArrayList with all of the days between today and a year ago
		List<LocalDate> dateList = new ArrayList<LocalDate>();
		dateList = IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> yearAgo.plusDays(i)).collect(Collectors.toList());
		dateList.add(Model.today); //adds the current day too
		
		//iterates over entire list to create objects if they are found in the user's history
		for (LocalDate date: dateList) {
			
			//the day is present in history, get object from hashmap and add it to ArrayList
			if (Model.historyMap.containsKey(userName + "," + date.toString())){
				userHistory.add(Model.historyMap.get(userName + "," + date.toString()));
				
			//day is not present, make blank Day and add it to the ArrayList
			}else {
				Day day = new Day(date.toString(), userName, getBasalMetabolism(), getBasalMetabolism());
				userHistory.add(day);
			}
		}

	}
	
	//getters and setters

	public String getUserName() {
		return userName;
	}

	/**
	 * Setter for username.
	 * 
	 * @param String userName: Username to set.
	 **/
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Getter for name.
	 * 
	 * @return String name: User's name.
	 **/
	public String getName() {
		return name;
	}

	/**
	 * Setter for name.
	 * 
	 * @param String name: Name to set.
	 **/
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for weight.
	 * 
	 * @return double weight: User's weight.
	 **/
	public double getWeight() {
		return weight;
	}

	/**
	 * Setter for weight.
	 * 
	 * @param double weight: Weight to set.
	 **/
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Getter for height.
	 * 
	 * @return double height: User's height.
	 **/
	public double getHeight() {
		return height;
	}

	/**
	 * Setter for height.
	 * 
	 * @param double height: Height to set.
	 **/
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Getter for birthday.
	 * 
	 * @return LocalDate birthday: User's birthday.
	 **/
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * Setter for birthday.
	 * 
	 * @param LocalDate birthday: Birthday to set.
	 **/
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * Getter for sex.
	 * 
	 * @return String sex: User's sex.
	 **/
	public String getSex() {
		return sex;
	}

	/**
	 * Setter for sex.
	 * 
	 * @param LocalDate birthday: Birthday to set.
	 **/
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Getter for User's history.
	 * 
	 * @return ArrayList<Day> userHistory: User's history.
	 **/
	public ArrayList<Day> getUserHistory() {
		return userHistory;
	}

	/**
	 * Getter for User's last weigh-in.
	 * 
	 * @return LocalDate lastWeighIn: User's last weigh-in.
	 **/
	public LocalDate getLastWeighIn() {
		return lastWeighIn;
	}

	/**
	 * Setter for User's last weigh-in.
	 * 
	 * @param LocalDate lastWeighIn: Weigh-in date to set.
	 **/
	public void setLastWeighIn(LocalDate lastWeighIn) {
		this.lastWeighIn = lastWeighIn;
	}
	
	/**
	 * @return basalMetabolism - int
	 * 
	 * Calculates the user's basal metabolism based on the nutrition equation. This number is the calories
	 * one burns even at rest, and will change based on the person's age, sex, height and weight
	 */

	public int getBasalMetabolism() {

		// Get time between user's birthday and today
		Period period = Period.between(birthday, Model.today);
		int age = period.getYears();
		int basalMetabolism = 0;
		// Calculate basal metabolism based off gender
		if (sex.equals("Male")) {
			
			//formula for males
			basalMetabolism = (int)(66.47 + (6.24 * weight) + (12.7 * height) - (6.755 * age));
		}else {
			
			//formula for females
			basalMetabolism = (int)(655.1 + (4.35 * weight) + (4.7 * height) - (4.7 * age));
		}
		return basalMetabolism;
	}
	
	//Returns a specified day from the user's history based on the LocalDate object passed

	public Day getDay(LocalDate date) {
		for (Day day : userHistory) {
			if (day.getDate().equals(date.toString()))
				return day;
		}
		return null;
	}

	/**
	 * toString for the User.
	 * 
	 * @return String string: String format for user.
	 **/
	@Override
	public String toString() {
		return userName + "," + name + "," + weight + "," + height + "," + birthday.toString() + ","
				+ lastWeighIn.toString() + "," + sex;
	}

}
