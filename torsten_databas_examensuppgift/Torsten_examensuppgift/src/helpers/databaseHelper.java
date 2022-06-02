package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseHelper {
	public static String driver = "jdbc";
	public static String dbType = "mysql";
	public static String server = "localhost";
	public static int portNum = 3306;
	
	//Connect to database with given database name
	public static Connection DbConnect(String database) {
		String constr = driver + ":" + dbType + "://" + server + ":" + portNum + "/" + database;
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(constr, "root", "swag");
		} catch (SQLException e) {
			System.out.println("Failed to connect to database");
			e.printStackTrace();
		}

		return conn;
	}
}