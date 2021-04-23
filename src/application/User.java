package application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.Model;

public class User {
	
	private String userName;
	private String name;
	private double weight;
	private double height;
	private LocalDate birthday;
	private String sex;
	private ArrayList<Day> userHistory = new ArrayList<Day>();
	
	public User(String userName, String name, double weight, double height, LocalDate birthday, String sex) {
		
		this.userName = userName;
		this.name = name;
		this.weight = weight;
		this.height = height;
		this.birthday = birthday;
		this.sex = sex;
		
	}
	
	public void populateUserHistory() {
		
		LocalDate yearAgo = LocalDate.of(Model.today.getYear()-1, Model.today.getMonthValue(), Model.today.getDayOfMonth());
		long numOfDaysBetween = ChronoUnit.DAYS.between(yearAgo, Model.today);
		List<LocalDate> dateList = new ArrayList<LocalDate>();
		dateList = IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> yearAgo.plusDays(i)).collect(Collectors.toList());
		for (LocalDate date: dateList) {
			if (Model.historyMap.containsKey(userName + "," + date.toString())){
				userHistory.add(Model.historyMap.get(userName + "," + date.toString()));
			}
		}
	}
	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getWeight() {
		return weight;
	}



	public void setWeight(double weight) {
		this.weight = weight;
	}



	public double getHeight() {
		return height;
	}



	public void setHeight(double height) {
		this.height = height;
	}



	public LocalDate getBirthday() {
		return birthday;
	}



	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public ArrayList<Day> getUserHistory() {
		return userHistory;
	}



	@Override
	public String toString() {
		return userName + "," + name + "," + weight + "," + height + "," + birthday.toString() + "," + sex;
	}
	
	
}
