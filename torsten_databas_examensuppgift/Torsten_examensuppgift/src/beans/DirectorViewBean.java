package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class DirectorViewBean {
	private String name;
	private int age;
	private String hometown;
	private String films;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	
	public String getFilms() {
		return films;
	}
	public void setFilms(String films) {
		this.films = films;
	}
	
	public String toString() {
		String pattern = "Name = %s, Age = %d, Hometown = %s"; 
		String text = String.format(pattern, this.name, this.age, this.hometown);
		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Name", this.name));
		dataList.add(new keyvaluepair("Age", Integer.toString(this.age)));
		dataList.add(new keyvaluepair("Hometown", this.hometown));	
		dataList.add(new keyvaluepair("Films", this.films));	

		return jsonHelper.toJsonObject(dataList);
	}
	
}
