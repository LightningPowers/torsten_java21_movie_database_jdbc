package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.DirectorViewBean;
import helpers.jsonHelper;

public class DirectorView {

	private Connection connection;
	private ArrayList<DirectorViewBean> directorViewList;
	
	//Constructor
	public DirectorView(Connection cn) {
		this.connection = cn;
		this.directorViewList = new ArrayList<DirectorViewBean>();
		getDirectorViewList();
	}
	
	//Loops through and returns an arrraylist of bean from specified query
	public ArrayList<DirectorViewBean> getDirectorViewList() {
		String qry = "select * from directorview";

		if (this.directorViewList.size() > 0) 
			return this.directorViewList;
			
		this.directorViewList = new ArrayList<DirectorViewBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getDirectorViewList exception for statement");
			e.printStackTrace();
		}
		
		return this.directorViewList;
	}
	
	public String toJson() {
		String beansContent = "";
		for (DirectorViewBean item : this.directorViewList) {
			beansContent += item.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Director View", beansContent);
	}

	//Fetches data from database and assigns it to bean
 	private DirectorViewBean buildDirectorView(ResultSet rs) {
		DirectorViewBean dvb = new DirectorViewBean();

		try {
			dvb.setName(rs.getString("Name"));
			dvb.setAge(rs.getInt("Age"));
			dvb.setHometown(rs.getString("Hometown"));
			dvb.setFilms(rs.getString("Films"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dvb;
	}
	
 	private void buildDirectorViewList(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this.directorViewList.add(buildDirectorView(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildDirectorViewList(rs);
		} catch (SQLException e) {
			System.out.println("getDirectorViewList exception for result set");
			e.printStackTrace();
		}

 	}
	
}
