import java.sql.*;
import java.util.*;

//this class is what's used in every other class, it's the class for connecting to MYSQL database
public class MySqlConnect {
	
	//variables declared for my database (fooddata) username(root) and custom password (1234abcd)
	String url = "jdbc:mysql://localhost:3306/fooddata";
	String username = "root";
	String password = "1234abcd";
	public Connection connection = null;

	
	//method to connect to my database using my url, username, and password
	public Connection connect() {
		if (connection == null) {

			try {
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("Database connected.");
				//this exception is thrown if a connection cannot be made
			} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect to the database.");
			}
		}

		return connection;
	}
	
	//method to disconnect from database
	public void disconnect() {
		
		//if connection exists
		if (connection != null) {
			
			//close connection
			try {
				connection.close();
				connection = null;
				System.out.println("Database disconnected.");
			//exception is thrown if the disconnect were to fail
			} catch (SQLException e) {
				throw new IllegalStateException("Disconnect failed.");
			}

		}

	}

}
