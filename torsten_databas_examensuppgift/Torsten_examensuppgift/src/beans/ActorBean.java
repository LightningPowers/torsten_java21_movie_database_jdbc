package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class ActorBean {
	private int id;
	private String name;
	private int age;
	private String hometown;
	private int addressId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

		return jsonHelper.toJsonObject(dataList);
	}
	
}
