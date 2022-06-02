package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class ReleasedateBean {
	private int releasedate_id;
	private int releasedate;
	
	public int getReleasedate_id() {
		return releasedate_id;
	}
	public void setReleasedate_id(int releasedate_id) {
		this.releasedate_id = releasedate_id;
	}
	public int getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(int releasedate) {
		this.releasedate = releasedate;
	}
	
	public String toString() {
		String pattern = "Release Date = %d"; 
		String text = String.format(pattern,  this.releasedate);
		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Release Year", Integer.toString(this.releasedate)));

		return jsonHelper.toJsonObject(dataList);
	}
}
