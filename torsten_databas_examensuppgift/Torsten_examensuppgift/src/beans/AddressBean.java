package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class AddressBean {
	private int address_id;
	private String address;
	private int postalcode;
	private String state;
	
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
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
		String pattern = "Address = %s, Postal Code = %d, State = %s"; 
		String text = String.format(pattern, this.address, this.postalcode, this.state);
		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Address", this.address));
		dataList.add(new keyvaluepair("Postal Code", Integer.toString(this.postalcode)));
		dataList.add(new keyvaluepair("State", this.state));	

		return jsonHelper.toJsonObject(dataList);
	}
}
