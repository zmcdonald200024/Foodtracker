/*Created by Zachary McDonald, Central Michigan University
 * Program uses MYSQL, creates tables, stores "food data" (nutritional values) into tables, deletes tables, clears tables of all data
 * and displays data in tables, all data is stored using MYSQL database
 * 
 * My initial plan was to make each user have their own table since this is such a small-scale project 
 * I realize that on a large scale project every person having their own table is not scalable, and this is bad practice
 * This is my first program using MYSQL, so I apologize for the sloppiness in syntax
*/
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//variable declaration
		int userChoice;
		String choices;
		//scanner for use of menu system
		Scanner sc = new Scanner(System.in);
		//declaring class objects 
		AddFoodToTable addFood = new AddFoodToTable();
		ModifyTable modifyTable = new ModifyTable();
		ViewTable view = new ViewTable();
		

		System.out.println("Hello! Please enter a number to continue: ");
		
		//loop to iterate until user chooses to quit
		while (true) {
			//menu choices
			System.out.println(
					"1 - create new user (table) " + "\n2 - add data to existing user's table" + "\n3 - view existing user's data table"
							+ "\n4 - delete data from existing user's table" + "\n5 - delete existing user's table" + "\n6 - quit");

			userChoice = sc.nextInt();
			
			//if statements (opted to not use cases here) for menu options, usually just calls another method
			if (userChoice < 1 || userChoice > 6) {

				System.out.println("Invalid choice! Please enter a number 1-6 to continue: ");
				continue;
			}

			if (userChoice == 1) {

				System.out.println("Creating new table...");

				modifyTable.addNewUserTable();

			}

			if (userChoice == 2) {

				addFood.addFood();

			}

			if (userChoice == 3) {

				view.ViewTableData();

			}

			if (userChoice == 4) {

				modifyTable.ClearUserTable();
			}

			if (userChoice == 5) {

				modifyTable.DeleteUserTable();

			}
			//exit choice simply breaks
			if (userChoice == 6) {

				System.out.println("Exiting...");
				break;
			}

		}

	}

}
