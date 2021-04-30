package application;

/**
 * Data structure to hold workout information
 *
 */
public class Workout {

	private String name;
	private String picture;
	private int calories; // burned per rep
	private int repSize;
	private String repName;

	public Workout(String name, String picture, int calories, int repSize, String repName) {
		this.name = name;
		this.picture = picture;
		this.calories = calories;
		this.repSize = repSize;
		this.repName = repName;
	}
	
	//getters and setters

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

	public int getRepSize() {
		return repSize;
	}

	public void setRepSize(int repSize) {
		this.repSize = repSize;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	@Override
	public String toString() {
		return name + "," + picture + "," + calories + "," + repSize + "," + repName;
	}
}
