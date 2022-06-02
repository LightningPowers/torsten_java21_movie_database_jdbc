package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.DirectorBean;
import helpers.jsonHelper;

public class Directors {

	private Connection connection;
	private ArrayList<DirectorBean> directors;
	
	private String selectQuery = "select * from director";
	private String insertQuery = "insert into director (director_name, director_age, city_name) values (?, ?, ?)";
	private String updateQuery = "update director set director_name = ?, director_age  = ?, city_name = ? where director_name = ?";
	private String deleteQuery = "call sp_deletedirector(?)";
	
	//Constructor
	public Directors(Connection cn) {
		this.connection = cn;
		this.directors = new ArrayList<DirectorBean>();
		getDirectors();
	}
	
	//Loops through and returns an arrraylist of bean from specified query
	public ArrayList<DirectorBean> getDirectors() {

		if (this.directors.size() > 0) 
			return this.directors;
			
		this.directors = new ArrayList<DirectorBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(selectQuery)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getDirectors exception for statement");
			e.printStackTrace();
		}
		
		return this.directors;
	}
	
	public String createDirector(String name, int newAge, String city){

		String returnString = "";
		String query = "";
		if (newAge <= 17 || newAge > 125) {
			returnString = "Failed to add director, age too low/high!";
		} else {
			query = insertQuery;
			returnString = "Successfully added director!";
		}

		if (query.equals(insertQuery)) {
			try (PreparedStatement myQry = this.connection.prepareStatement(query)) {
				myQry.setString(1, name);
				myQry.setInt(2, newAge);
				myQry.setString(3, city);
	
				myQry.execute();
			} catch (SQLException e) {
				returnString = "createDirector exception for statement";
				e.printStackTrace();
			}
		}
		return returnString;
	}
	
	public String updateDirector(String newName, int newAge, String newhometown, String name) {

		String returnString = "";
		String query = "";
		if (newAge <= 17 || newAge > 125) {
			returnString = "Failed to update director, age too low/high!";
		} else {
			query = updateQuery;
			returnString = "Successfully updated director!";
		}

		try (PreparedStatement myQry = this.connection.prepareStatement(query)) {
			myQry.setString(1, newName);
			myQry.setInt(2, newAge);
			myQry.setString(3, newhometown);
			myQry.setString(4, name);

			myQry.executeUpdate();
			returnString = "Successfully updated director";
		} catch (SQLException e) {
			returnString = "updateDirector exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String deleteDirector(String name) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(deleteQuery)) {
			myQry.setString(1, name);

			myQry.execute();
			returnString = "Successfully deleted director";
		} catch (SQLException e) {
			returnString = "deleteDirector exception for statement";
			e.printStackTrace();
		}

		return returnString;
	}
	
	public String toJson() {
		String beansContent = "";
		for (DirectorBean item : this.directors) {
			beansContent += item.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Directors", beansContent);
	}

	//Fetches data from database and assigns it to bean
 	private DirectorBean buildDirector(ResultSet rs) {
		DirectorBean db = new DirectorBean();

		try {
			db.setId(rs.getInt("director_id"));
			db.setName(rs.getString("director_name"));
			db.setAge(rs.getInt("director_age"));
			db.setHometown(rs.getString("city_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return db;
	}
	
 	private void buildDirectors(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this.directors.add(buildDirector(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildDirectors(rs);
		} catch (SQLException e) {
			System.out.println("getDirectors exception for result set");
			e.printStackTrace();
		}

 	}
	
}
