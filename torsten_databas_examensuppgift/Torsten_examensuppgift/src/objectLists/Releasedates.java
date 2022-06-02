package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ReleasedateBean;
import helpers.jsonHelper;

public class Releasedates {

	private Connection connection;
	private ArrayList<ReleasedateBean> releasedates;
	
	private String selectQuery = "select * from releasedate";
	private String insertQuery = "insert into releasedate (releasedate) values (?)";
	private String deleteQuery = "delete from releasedate where releasedate = ?";
	
	//Constructor
	public Releasedates(Connection cn) {
		this.connection = cn;
		this.releasedates = new ArrayList<ReleasedateBean>();
		getReleasedates();
	}
	
	//Loops through and returns an arrraylist of bean from specified query
	public ArrayList<ReleasedateBean> getReleasedates() {
		if (this.releasedates.size() > 0) 
			return this.releasedates;
			
		this.releasedates = new ArrayList<ReleasedateBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(selectQuery)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getReleasedates exception for statement");
			e.printStackTrace();
		}
		
		return this.releasedates;
	}
	
	public String createReleasedate(int year) {

		String returnString = "";
		String query = "";
		if (year <= 1900 || year > 2100) {
			returnString = "Failed to add movie, release year too low/high!";
		} else {
			query = insertQuery;
			returnString = "Successfully added releasedate!";
		}

		if (query.equals(insertQuery)) {
			try (PreparedStatement myQry = this.connection.prepareStatement(query)) {
				myQry.setInt(1, year);
	
				myQry.execute();
			} catch (SQLException e) {
				returnString = "createReleasedate exception for statement";
				e.printStackTrace();
			}
		}
		return returnString;
	}
	
	public String deleteReleasedate(int year) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(deleteQuery)) {
			myQry.setInt(1, year);

			myQry.execute();
			returnString = "Successfully deleted releasedate";
		} catch (SQLException e) {
			returnString = "deleteMovie exception for statement. "
					+ "Make sure that no movies are using this releasedate!";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String toJson() {
		String beansContent = "";
		for (ReleasedateBean item : this.releasedates) {
			beansContent += item.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Release Years", beansContent);
	}

	//Fetches data from database and assigns it to bean
 	private ReleasedateBean buildReleasedate(ResultSet rs) {
		ReleasedateBean rdb = new ReleasedateBean();

		try {
			rdb.setReleasedate(rs.getInt("releasedate"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rdb;
	}
	
 	private void buildReleasedates(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this.releasedates.add(buildReleasedate(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildReleasedates(rs);
		} catch (SQLException e) {
			System.out.println("getReleasedates exception for result set");
			e.printStackTrace();
		}

 	}
	
}
