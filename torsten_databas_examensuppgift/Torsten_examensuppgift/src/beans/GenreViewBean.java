package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class GenreViewBean {
	private String genre;
	private String title;
	
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString() {
		String pattern = "Genre = %s, Title = %s"; 
		String text = String.format(pattern, this.genre, this.title);
		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Genre", this.genre));
		dataList.add(new keyvaluepair("Title", this.title));	

		return jsonHelper.toJsonObject(dataList);
	}
}
