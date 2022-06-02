package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.GenreBean;
import helpers.jsonHelper;

public class Genres {

	private Connection connection;
	private ArrayList<GenreBean> genres;
	
	private String selectQuery = "select * from genre";
	private String insertQuery = "insert into genre (genre) values (?)";
	private String addGenreQuery = "insert into moviegenres (movie_id, genre_id) "
			+ "select movie_id, genre_id "
			+ "from movies, genre "
			+ "where title = ? "
			+ "and genre = ?";
	private String removeGenreQuery = "call sp_unassigngenre(?, ?)";
	private String deleteQuery = "call sp_deletegenre(?);";
	
	//Constructor
	public Genres(Connection cn) {
		this.connection = cn;
		this.genres = new ArrayList<GenreBean>();
		getGenres();
	}
	
	//Loops through and returns an arrraylist of bean from specified query
	public ArrayList<GenreBean> getGenres() {
		if (this.genres.size() > 0) 
			return this.genres;
			
		this.genres = new ArrayList<GenreBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(selectQuery)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getGenres exception for statement");
			e.printStackTrace();
		}
		
		return this.genres;
	}
	
	public String createGenre(String genrename) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(insertQuery)) {
			myQry.setString(1, genrename);

			myQry.execute();
			returnString = "Successfully created genre";
		} catch (SQLException e) {
			returnString = "createGenre exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String assignGenre(String movietitle, String genrename) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(addGenreQuery)) {
			myQry.setString(1, movietitle);
			myQry.setString(2, genrename);

			myQry.execute();
			returnString = "Successfully assigned genre";
		} catch (SQLException e) {
			returnString = "assignGenre exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String removeGenre(String movietitle, String genrename) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(removeGenreQuery)) {
			myQry.setString(1, movietitle);
			myQry.setString(2, genrename);

			myQry.execute();
			returnString = "Successfully removed genre";
		} catch (SQLException e) {
			returnString = "removeGenre exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String deleteGenre(String genrename) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(deleteQuery)) {
			myQry.setString(1, genrename);

			myQry.execute();
			returnString = "Successfully deleted genre";
		} catch (SQLException e) {
			returnString = "deleteGenre exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String toJson() {
		String beansContent = "";
		for (GenreBean item : this.genres) {
			beansContent += item.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Genres", beansContent);
	}

	//Fetches data from database and assigns it to bean
 	private GenreBean buildGenre(ResultSet rs) {
		GenreBean gb = new GenreBean();

		try {
			gb.setGenre_id(rs.getInt("genre_id"));
			gb.setGenre(rs.getString("genre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return gb;
	}
	
 	private void buildGenres(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this.genres.add(buildGenre(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildGenres(rs);
		} catch (SQLException e) {
			System.out.println("getGenres exception for result set");
			e.printStackTrace();
		}

 	}
	
}
