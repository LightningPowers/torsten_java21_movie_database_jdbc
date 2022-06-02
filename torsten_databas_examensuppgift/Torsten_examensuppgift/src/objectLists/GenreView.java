package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.GenreViewBean;
import helpers.jsonHelper;

public class GenreView {

	private Connection connection;
	private ArrayList<GenreViewBean> genreViewList;
	
	//Constructor
	public GenreView(Connection cn) {
		this.connection = cn;
		this.genreViewList = new ArrayList<GenreViewBean>();
		getGenreViewList();
	}
	
	//Loops through and returns an arrraylist of bean from specified query
	public ArrayList<GenreViewBean> getGenreViewList() {
		String qry = "select * from genreview";

		if (this.genreViewList.size() > 0) 
			return this.genreViewList;
			
		this.genreViewList = new ArrayList<GenreViewBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getGenreViewList exception for statement");
			e.printStackTrace();
		}
		
		return this.genreViewList;
	}
	
	public String toJson() {
		String beansContent = "";
		for (GenreViewBean item : this.genreViewList) {
			beansContent += item.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Genre View", beansContent);
	}

	//Fetches data from database and assigns it to bean
 	private GenreViewBean buildGenreView(ResultSet rs) {
		GenreViewBean gvb = new GenreViewBean();

		try {
			gvb.setGenre(rs.getString("Genre"));
			gvb.setTitle(rs.getString("Title"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return gvb;
	}
	
 	private void buildGenreViewList(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this.genreViewList.add(buildGenreView(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildGenreViewList(rs);
		} catch (SQLException e) {
			System.out.println("getGenreViewList exception for result set");
			e.printStackTrace();
		}

 	}
	
}
