package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.MovieBean;
import helpers.jsonHelper;

public class Movies {
	
	private Connection connection;
	private ArrayList<MovieBean> movies;
	
	private String selectQuery = "select * from movies";
	private String insertQuery = "insert into movies (title, director_id, releasedate_id)"
			+ "select ?, director_id, releasedate_id "
			+ "from director, releasedate "
			+ "where director_name = ? "
			+ "and releasedate = ?";
	private String updateQuery = "call sp_updatemovie(?, ?, ?)";
	private String deleteQuery = "call sp_deletemovie(?)";
	
	//Constructor
	public Movies(Connection cn) {
		this.connection = cn;
		this.movies = new ArrayList<MovieBean>();
		getMovies();
	}
	
	//Loops through and returns an arrraylist of bean from specified query
	public ArrayList<MovieBean> getMovies() {
		if (this.movies.size() > 0) 
			return this.movies;
			
		this.movies = new ArrayList<MovieBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(selectQuery)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getMovies exception for statement");
			e.printStackTrace();
		}
		return this.movies;
	}
	
	public String createMovie(String title, String director, int year) {

		String returnString = "";
		String query = "";
		if (year <= 1900 || year > 2100) {
			returnString = "Failed to add movie, release year too low/high!";
		} else {
			query = insertQuery;
			returnString = "Successfully added movie!";
		}

		if (query.equals(insertQuery)) {
			try (PreparedStatement myQry = this.connection.prepareStatement(query)) {
				myQry.setString(1, title);
				myQry.setString(2, director);
				myQry.setInt(3, year);
	
				myQry.execute();
			} catch (SQLException e) {
				returnString = "createMovie exception for statement";
				e.printStackTrace();
			}
		}
		return returnString;
	}
	
	public String updateMovie(String movietitle, String newtitle, String newdirector) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(updateQuery)) {
			myQry.setString(1, movietitle);
			myQry.setString(2, newtitle);
			myQry.setString(3, newdirector);

			myQry.executeUpdate();
			returnString = "Successfully updated movie";
		} catch (SQLException e) {
			returnString = "updateMovie exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String deleteMovie(String movietitle) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(deleteQuery)) {
			myQry.setString(1, movietitle);

			myQry.execute();
			returnString = "Successfully deleted movie";
		} catch (SQLException e) {
			returnString = "deleteMovie exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String toJson() {
		String beansContent = "";
		for (MovieBean item : this.movies) {
			beansContent += item.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Movies", beansContent);
	}

	//Fetches data from database and assigns it to bean
 	private MovieBean buildMovie(ResultSet rs) {
		MovieBean mb = new MovieBean();

		try {
			mb.setTitle(rs.getString("title"));
			mb.setMovie_id(rs.getInt("movie_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mb;
	}
	
 	private void buildMovies(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this.movies.add(buildMovie(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovies(rs);
		} catch (SQLException e) {
			System.out.println("getMovies exception for result set");
			e.printStackTrace();
		}

 	}

}
