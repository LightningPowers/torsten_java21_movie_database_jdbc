package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class ActorViewBean {
	private String name;
	private int age;
	private String hometown;
	private String address;
	private int postalcode;
	private String state;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String toString() {
		String pattern = "Name = %s, Age = %d, Hometown = %s, Address = %s, Postal Code = %d, State = %s"; 
		String text = String.format(pattern, this.name, this.age, this.hometown,
				this.address, this.postalcode, this.state);
		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Name", this.name));
		dataList.add(new keyvaluepair("Age", Integer.toString(this.age)));
		dataList.add(new keyvaluepair("Hometown", this.hometown));	
		dataList.add(new keyvaluepair("Address", this.address));
		dataList.add(new keyvaluepair("Postal Code", Integer.toString(this.postalcode)));
		dataList.add(new keyvaluepair("State", this.state));	

		return jsonHelper.toJsonObject(dataList);
	}
}
