import java.sql.*;
import java.util.Scanner;

public class ViewTable {
	
	//declaring variables/scanner and using MySqlConnect to connect to database
	MySqlConnect SqlConnection = new MySqlConnect();
	String name;
	String query;	
	ResultSet rs = null;
	Statement stmt = null;
	Scanner sc = new Scanner(System.in);
	
	//method to view all of the data in the chosen table
	public void ViewTableData() {
		
		
		try {
			//this line is to take user input so you can select the table you're using
			System.out.println("Please enter your name: ");
			name = " " + sc.next();
			//selects everything
			query = "SELECT * FROM " + name;
			stmt = SqlConnection.connect().createStatement();
			rs = stmt.executeQuery(query);
			System.out.println("Displaying data... if no data is shown, table is empty... ");
			//while loop to print entire table to completion
			while (rs.next()) {
				
				//all of these lines are to instantiate java variables to my table column names, and then to output the result
				String name = rs.getString("name");
				int calories = rs.getInt("calories");
				int totalFat = rs.getInt("total_fat");
				int cholesterol = rs.getInt("cholesterol");
				int sodium = rs.getInt("sodium");
				int carbohydrate = rs.getInt("carbohydrate");
				int protein = rs.getInt("protein");
				Date date = rs.getDate("submission_date");
				System.out.println("FOOD: " + name + " " + "CALORIES: " + calories + " " + "TOTAL FAT: " + totalFat
						+ " " + "CHOLESTEROL: " + cholesterol + " " + "SODIUM: " + sodium + " " + "CARBOHYDRATES: "
						+ carbohydrate + " " + "PROTEIN: " + protein + " " + "DATE: " + date);
			}
			
			//closes stmt and disconnects from database
			stmt.close();
		} catch (Exception e) {
			e.getMessage();
		}
		
		SqlConnection.disconnect();
	}
	
}