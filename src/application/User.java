package application;

public class User {
	
	private String userName;
	private String name;
	private double weight;
	private double height;
	private int age;
	private String sex;
	
	public User(String userName, String name, double weight, double height, int age, String sex) {
		
		this.userName = userName;
		this.name = name;
		this.weight = weight;
		this.height = height;
		this.age = age;
		this.sex = sex;
		
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



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	@Override
	public String toString() {
		return userName + "," + name + "," + weight + "," + height + "," + age + "," + sex;
	}
	
	
}
