//switch all of this to main method later

import java.sql.*;
import java.util.*;

public class AddFoodToTable {

	public void addFood() {

		//declaring variables and scanner
		String name;
		Scanner sc = new Scanner(System.in);
		//using MySqlConnect class to connect to MYSQL database
		MySqlConnect SqlConnection = new MySqlConnect();
		//calendar object for use in time-stamping data in table
		Calendar calendar = Calendar.getInstance();
		java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
		
		
		try {
			//entering the "name" of user essentially operates as "enter table name", this line allows you to select the table
			//that you are inserting this data into.
			System.out.println("Enter the name of the existing user: ");
			//this variable is equivalent to "tableName"
			name = " " + sc.next();
			
			//MYSQL syntax to insert the user-input values into the table that was chosen earlier
			String sql = " insert into" + name
					+ " (name, calories, total_fat, cholesterol, sodium, carbohydrate, protein, submission_date)"
					+ " values (?,?, ?, ?, ?, ?, ?, ?)";
			
			//prepared statement to get user input and apply it to table
			PreparedStatement Stmt = SqlConnection.connect().prepareStatement(sql);

			System.out.println("Enter the name of the food: ");
			Stmt.setString(1, sc.next());
			System.out.println("Enter the amount of calories in the serving: ");
			Stmt.setInt(2, sc.nextInt());
			System.out.println("Enter the total amount of fat in the serving: ");
			Stmt.setInt(3, sc.nextInt());
			System.out.println("Enter the total amount of cholesterol in the serving: ");
			Stmt.setInt(4, sc.nextInt());
			System.out.println("Enter the total amount of sodium in the serving: ");
			Stmt.setInt(5, sc.nextInt());
			System.out.println("Enter the total amount of carbohydrates in the serving: ");
			Stmt.setInt(6, sc.nextInt());
			System.out.println("Enter the total amount of protein in the serving: ");
			Stmt.setInt(7, sc.nextInt());
			Stmt.setDate(8, startDate);

			Stmt.execute();
			System.out.println("The food has been entered into the table for: " + name);
			SqlConnection.connect().close();
			
			//catching any exceptions (table doesn't exist, or wrong input type
		} catch (Exception e) {
			System.err.println("Error: ");
			System.err.println(e.getMessage());
		}
		//disconnecting from database
		SqlConnection.disconnect();

	}

}

