package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ActorBean;
import helpers.jsonHelper;

public class Actors {
	private Connection connection;
	private ArrayList<ActorBean> actors;

	private String selectQuery = "select * from actor";
	private String insertQuery = "call sp_addactor(?, ?, ?, ?)";
	private String updateQueryShort = "update actor set actor_age = ?, hometown  = ? where actor_name = ?";
	private String updateQueryLong = "update actor set actor_name = ?, actor_age  = ?, hometown = ? where actor_name = ?";
	private String addActorQuery = "call sp_addactorrelations(?, ?)";
	private String removeActorQuery = "call sp_unassignactor(?, ?)";
	private String deleteQuery = "call sp_deleteactor(?)";

	// Constructor
	public Actors(Connection cn) {
		this.connection = cn;
		this.actors = new ArrayList<ActorBean>();
		getActors();
	}

	// Loops through and returns an arrraylist of bean from specified query
	public ArrayList<ActorBean> getActors() {
		if (this.actors.size() > 0)
			return this.actors;

		this.actors = new ArrayList<ActorBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(selectQuery)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}

		return this.actors;
	}

	public String createActor(String name, String hometown, int newAge, int postalcode) {

		String returnString = "";
		String query = "";
		if (newAge <= 0 || newAge > 125) {
			returnString = "Failed to add actor, age too low/high!";
		} else {
			query = insertQuery;
			returnString = "Successfully added actor!";
		}

		if (query.equals(insertQuery)) {
			try (PreparedStatement myQry = this.connection.prepareStatement(query)) {
				myQry.setString(1, name);
				myQry.setString(2, hometown);
				myQry.setInt(3, newAge);
				myQry.setInt(4, postalcode);
	
				myQry.execute();
			} catch (SQLException e) {
				returnString = "createActor exception for statement";
				e.printStackTrace();
			}
		}
		return returnString;
	}

	public String updateActorInfo(int newAge, String newhometown, String name) {
				
		String returnString = "";
		String query = "";
		if (newAge <= 0 || newAge > 125) {
			returnString = "Failed to update actor, age too low/high!";
		} else {
			query = updateQueryShort;
			returnString = "Successfully updated actor!";
		}
		
			try (PreparedStatement myQry = this.connection.prepareStatement(query)) {
				myQry.setInt(1, newAge);
				myQry.setString(2, newhometown);
				myQry.setString(3, name);
	
				myQry.executeUpdate();
				returnString = "Successfully updated actor";
			} catch (SQLException e) {
				returnString = "updateActorInfo exception for statement";
				e.printStackTrace();
			}

		return returnString;
	}
	
	public String updateActor(String newName, int newAge, String newhometown, String name) {
		
		String returnString = "";
		String query = "";
		if (newAge <= 0 || newAge > 125) {
			returnString = "Failed to update actor, age too low/high!";
		} else {
			query = updateQueryLong;
			returnString = "Successfully updated actor!";
		}
		
			try (PreparedStatement myQry = this.connection.prepareStatement(query)) {
				myQry.setString(1, newName);
				myQry.setInt(2, newAge);
				myQry.setString(3, newhometown);
				myQry.setString(4, name);
	
				myQry.executeUpdate();
				returnString = "Successfully updated actor";
			} catch (SQLException e) {
				returnString = "updateActor exception for statement";
				e.printStackTrace();
			}
		return returnString;
	}
	
	public String assignActor(String movietitle, String actorname) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(addActorQuery)) {
			myQry.setString(1, movietitle);
			myQry.setString(2, actorname);

			myQry.execute();
			returnString = "Successfully assigned actor";
		} catch (SQLException e) {
			returnString = "assignActor exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String removeActor(String movietitle, String actorname) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(removeActorQuery)) {
			myQry.setString(1, movietitle);
			myQry.setString(2, actorname);

			myQry.execute();
			returnString = "Successfully removed actor";
		} catch (SQLException e) {
			returnString = "removeActor exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String deleteActor(String name) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(deleteQuery)) {
			myQry.setString(1, name);

			myQry.execute();
			returnString = "Successfully deleted actor";
		} catch (SQLException e) {
			returnString = "deleteActor exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String toJson() {
		String beansContent = "";
		for (ActorBean item : this.actors) {
			beansContent += item.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Actors", beansContent);
	}

	// Fetches data from database and assigns it to bean
	private ActorBean buildActor(ResultSet rs) {
		ActorBean ab = new ActorBean();

		try {
			ab.setId(rs.getInt("actor_id"));
			ab.setAge(rs.getInt("actor_age"));
			ab.setName(rs.getString("actor_name"));
			ab.setHometown(rs.getString("hometown"));
			ab.setAddressId(rs.getInt("address_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}

	private void buildActors(ResultSet rs) throws SQLException {
		while (rs.next()) { // rows
			this.actors.add(buildActor(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildActors(rs);
		} catch (SQLException e) {
			System.out.println("getActors exception for result set");
			e.printStackTrace();
		}

	}
}
