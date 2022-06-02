package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class MovieViewBean {
	private String title;
	private String genre;
	private String director;
	private int releasedate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(int releasedate) {
		this.releasedate = releasedate;
	}
	
	public String toString() {
		String pattern = "title = %s, genre = %s, director = %s, releasedate = %d"; 
		String text = String.format(pattern, this.title, this.genre, this.director, this.releasedate);
		return text;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Title", this.title));	
		dataList.add(new keyvaluepair("Genre", this.genre));
		dataList.add(new keyvaluepair("Director", this.director));	
		dataList.add(new keyvaluepair("Release Year", Integer.toString(this.releasedate)));	

		return jsonHelper.toJsonObject(dataList);
	}
}
