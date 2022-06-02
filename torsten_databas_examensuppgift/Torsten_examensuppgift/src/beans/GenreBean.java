package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class GenreBean {
	private int genre_id;
	private String genre;
	
	public int getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String toString() {
		String pattern = "Genre = %s"; 
		String text = String.format(pattern, this.genre);
		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Genre", this.genre));

		return jsonHelper.toJsonObject(dataList);
	}
}
