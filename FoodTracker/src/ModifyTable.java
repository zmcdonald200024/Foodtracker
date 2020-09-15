import java.sql.*;
import java.util.Scanner;

public class ModifyTable {
	
	//object instantiation and variable declaration, connecting to database with MySqlConnect
	MySqlConnect SqlConnection = new MySqlConnect();
	Scanner sc = new Scanner(System.in);
	PreparedStatement stmt = null;
	Statement deleteStmt = null;
	String choice = null;
	public String name;
	public String deleteName;
	public String delete;

	//method to create a new table
	//again, my ideology in this project was that each user would have their own table
	public void addNewUserTable() {
		
		//enter your name == "enter table name"
		System.out.println("Enter your name: ");
		name = " " + sc.next();
		
		//format for table that has columns for nutrition value information
		final String CREATE_TABLE_SQL = "CREATE TABLE" + name + "(" + "name VARCHAR(50) NOT NULL,"
				+ "calories INT NOT NULL," + "total_fat INT NOT NULL," + "cholesterol INT NOT NULL,"
				+ "sodium INT NOT NULL," + "carbohydrate INT NOT NULL," + "protein INT NOT NULL,"
				+ "submission_date DATE NOT NULL)";

		try {
			
			//preparestatement to create the table using the format that was created above
			stmt = SqlConnection.connect().prepareStatement(CREATE_TABLE_SQL);
			stmt.executeUpdate();

			System.out.println("New table created for: " + name);
			
			//prints exception, disconnects from database, closes statement
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (SqlConnection != null) {
					SqlConnection.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	 
	//method to delete an entire table from database
	public void DeleteUserTable() {
		
		//this allows the user to choose which table to delete based on the name of the table
		System.out.println("Enter the name of the table you'd like to delete: ");
		name = " " + sc.next();
		
		//this line is a confirmation message, I implemented this just in case the table had many values that were important
		System.out.println("Are you sure you want to delete this table? Type 'yes' to confirm or 'q' to return to menu.");
		choice = sc.next();
		
		//if statements based on confirmation message, just a fail-safe incase the user misclicked "delete table"
		if (choice.equalsIgnoreCase("q")) {
			return;
		}
		
		//if the user confirmed the choice, the table is deleted here
		else if (choice.equalsIgnoreCase("yes")) {

			try {
				//the "DROP TABLE" line deletes the table from the database based on the user input for the name
				deleteStmt = SqlConnection.connect().createStatement();
				delete = "DROP TABLE " + name;
				deleteStmt.executeUpdate(delete);

				SqlConnection.connect().close();

				System.out.println("Table has been deleted...");
			}
			
			//prints exception in case table doesnt exist or other exception
			catch (Exception e) {

				System.err.println(e.getMessage());

			}
			
			//this is in case the user doesnt select yes or 'q' when confirming to delete the table, auto exits back to menu
			//another failsafe in case the user accidentally tried to delete table
		} else {
			System.out.println("Invalid choice! Returning to menu...");
			return;
		}
	}
	
	//this method is to clear all values from the table, but not delete the entire thing
	public void ClearUserTable() {
		
		//this line allows you to choose the name of the table to clear
		System.out.println("Please enter the name of the table you'd like to clear.");
		name = " " + sc.next();
		
		//this is another confirmation failsafe, exactly the same as the DeleteUserTable() method above
		System.out.println("Are you sure you want to clear all data from this table? Type 'yes' to confirm or 'q' to return to menu.");
		choice = sc.next();
		if (choice.equalsIgnoreCase("q")) {
			return;
		}
		//this is where the table is cleared, if the user confirms it
		else if (choice.equalsIgnoreCase("yes")) {
			
			try {
				//DELETE FROM is what clears the table, since I don't list any specific rows/columns and just put name, it clears 
				//entire table
				delete = "DELETE FROM " + name;
				stmt = SqlConnection.connect().prepareStatement(delete);
				stmt.execute();
				System.out.println("Table has been cleared...");
			}
			
			//exception in case table doesnt exist, or other reason
			catch (Exception e) {

				System.err.println(e.getMessage());

			}
			
			//this is exactly the same as above, another failsafe in case user doesn't select 'q' or 'yes' 
			//they will be returned to the menu as to avoid accidentally clearing all data
		} else {
			System.out.println("Invalid choice! Returning to menu...");
			return;
		}
	}

}