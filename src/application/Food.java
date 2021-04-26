package application;

public class Food {
	
	private String name;
	private String picture;
	private String servingSize;
	private int calories; //per serving
	
	public Food(String name, String picture, String servingSize, int calories) {
		this.name = name;
		this.picture = picture;
		this.servingSize = servingSize;
		this.calories = calories;
	}
	
	@Override
	public String toString() {
		return name + "," + picture + "," + servingSize + "," + calories;
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
	
	
	
	public String getServingSize() {
		return servingSize;
	}

	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

}
