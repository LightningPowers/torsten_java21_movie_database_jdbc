package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ActorViewBean;
import helpers.jsonHelper;

public class ActorView {

	private Connection connection;
	private ArrayList<ActorViewBean> actorviewList;
	
	//Constructor
	public ActorView(Connection cn) {
		this.connection = cn;
		this.actorviewList = new ArrayList<ActorViewBean>();
		getActorviewList();
	}
	
	//Loops through and returns an arrraylist of bean from specified query
	public ArrayList<ActorViewBean> getActorviewList() {
		String qry = "select * from actorview";

		if (this.actorviewList.size() > 0) 
			return this.actorviewList;
			
		this.actorviewList = new ArrayList<ActorViewBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return this.actorviewList;
	}
	
	public String toJson() {
		String beansContent = "";
		for (ActorViewBean ab : this.actorviewList) {
			beansContent += ab.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Actorview", beansContent);
	}

	//Fetches data from database and assigns it to bean
 	private ActorViewBean buildActorview(ResultSet rs) {
		ActorViewBean avb = new ActorViewBean();

		try {
			avb.setName(rs.getString("Name"));
			avb.setAge(rs.getInt("Age"));
			avb.setHometown(rs.getString("Hometown"));
			avb.setAddress(rs.getString("Address"));
			avb.setPostalcode(rs.getInt("Postal Code"));
			avb.setState(rs.getString("State"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return avb;
	}
	
 	private void buildActorviewList(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this.actorviewList.add(buildActorview(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildActorviewList(rs);
		} catch (SQLException e) {
			System.out.println("getActors exception for result set");
			e.printStackTrace();
		}

 	}
	
}
