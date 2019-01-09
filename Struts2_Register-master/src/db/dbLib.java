package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbLib {
	public static Connection getConn() {
		 Connection connection=null;
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/test?user=root&password=420519";
			connection = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return connection;
	}
}
