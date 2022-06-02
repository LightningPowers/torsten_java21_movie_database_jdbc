package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.MovieViewBean;
import helpers.jsonHelper;

public class MovieView {

	private Connection connection;
	private ArrayList<MovieViewBean> movieViewList;
	
	//Constructor
	public MovieView(Connection cn) {
		this.connection = cn;
		this.movieViewList = new ArrayList<MovieViewBean>();
		getMovieViewList();
	}
	
	//Loops through and returns an arrraylist of bean from specified query
	public ArrayList<MovieViewBean> getMovieViewList() {
		String qry = "select * from movieview";

		if (this.movieViewList.size() > 0) 
			return this.movieViewList;
			
		this.movieViewList = new ArrayList<MovieViewBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getMovieViewList exception for statement");
			e.printStackTrace();
		}
		
		return this.movieViewList;
	}
	
	public String toJson() {
		String beansContent = "";
		for (MovieViewBean item : this.movieViewList) {
			beansContent += item.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Movie View", beansContent);
	}

	//Fetches data from database and assigns it to bean
 	private MovieViewBean buildMovieView(ResultSet rs) {
		MovieViewBean mvb = new MovieViewBean();

		try {
			mvb.setTitle(rs.getString("Title"));
			mvb.setGenre(rs.getString("Genre"));
			mvb.setDirector(rs.getString("Director"));
			mvb.setReleasedate(rs.getInt("Release Year"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mvb;
	}
	
 	private void buildMovieViewList(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this.movieViewList.add(buildMovieView(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovieViewList(rs);
		} catch (SQLException e) {
			System.out.println("getMovieViewList exception for result set");
			e.printStackTrace();
		}

 	}
	
}
