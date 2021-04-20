package application;

public class Food {
	
	private String name;
	private String picture;
	private int calories; //per serving
	
	public Food(String name, String picture, int calories) {
		this.name = name;
		this.picture = picture;
		this.calories = calories;
	}
	
	@Override
	public String toString() {
		return name + "," + picture + "," + calories;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}

}
