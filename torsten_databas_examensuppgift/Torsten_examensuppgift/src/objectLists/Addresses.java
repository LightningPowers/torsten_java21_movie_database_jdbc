package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.AddressBean;
import helpers.jsonHelper;

public class Addresses {
	private Connection connection;
	private ArrayList<AddressBean> addresses;
	
	private String selectQuery = "select * from address";
	private String insertQuery = "insert into address (address, postalcode, state) values (?, ?, ?)";
	private String updateQuery = "update address set address = ?, postalcode  = ?, state = ? where address = ?";
	private String deleteQuery = "call sp_deleteaddress(?, ?)";
	
	//Constructor
	public Addresses(Connection cn) {
		this.connection = cn;
		this.addresses = new ArrayList<AddressBean>();
		getAddresses();
	}
	
	//Loops through and returns an arrraylist of bean from specified query
	public ArrayList<AddressBean> getAddresses() {
		if (this.addresses.size() > 0) 
			return this.addresses;
			
		this.addresses = new ArrayList<AddressBean>();
		try (PreparedStatement myQry = this.connection.prepareStatement(selectQuery)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return this.addresses;
	}
	
	public String createAddress(String address, int postalcode, String state) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(insertQuery)) {
			myQry.setString(1, address);
			myQry.setInt(2, postalcode);
			myQry.setString(3, state);

			myQry.execute();
			returnString = "Successfully created address";
		} catch (SQLException e) {
			returnString = "createAddress exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String updateAddress(String newaddress, int newpostalcode, String newstate, String oldaddress) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(updateQuery)) {
			myQry.setString(1, newaddress);
			myQry.setInt(2, newpostalcode);
			myQry.setString(3, newstate);
			myQry.setString(4, oldaddress);

			myQry.executeUpdate();
			returnString = "Successfully updated address";
		} catch (SQLException e) {
			returnString = "updateAddress exception for statement";
			e.printStackTrace();
		}
		return returnString;
	}
	
	//Moves any actors that are connected to address to another before deleting it
	public String deleteAddress(String deletionaddress, String movingaddress) {

		String returnString = "";

		try (PreparedStatement myQry = this.connection.prepareStatement(deleteQuery)) {
			myQry.setString(1, deletionaddress);
			myQry.setString(2, movingaddress);

			myQry.execute();
			returnString = "Successfully deleted address";
		} catch (SQLException e) {
			returnString = "deleteActor exception for statement, maybe moving address does not exist?";
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String toJson() {
		String beansContent = "";
		for (AddressBean ab : this.addresses) {
			beansContent += ab.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Addresses", beansContent);
	}
	
	//Fetches data from database and assigns it to bean
 	private AddressBean buildAddress(ResultSet rs) {
		AddressBean ab = new AddressBean();

		try {
			ab.setAddress(rs.getString("address"));
			ab.setPostalcode(rs.getInt("postalcode"));
			ab.setState(rs.getString("state"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}
	
 	private void buildAddresses(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this.addresses.add(buildAddress(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildAddresses(rs);
		} catch (SQLException e) {
			System.out.println("getAddresses exception for result set");
			e.printStackTrace();
		}
 	}
}
