import java.util.Scanner;
import java.sql.*;

class DBHandler
{
	
	public void insert_account_record(String account_name, String username, String password)
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql:///bank", "root", "Shaan@123");
			String cmd = String.format("INSERT INTO account(account_name, username, password, balance) VALUES( '%s', '%s', '%s', 0 );", account_name, username, password);
			Statement stmt=con.createStatement();
			stmt.executeUpdate(cmd);

			ResultSet rs=stmt.executeQuery("SELECT account_number FROM account;");

			long acc_no = 0;

			while(rs.next())
				acc_no = rs.getInt(1);

			System.out.println("\nYour account number is " + acc_no);

			stmt.close();
			con.close();  
		}
		catch(Exception e)
		{ 
			System.out.println("Something went wrong...Try again...\n" + e);
		}    
	}

	public void get_account_balance(long account_number, String username, String password)
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql:///bank", "root", "Shaan@123");

			Statement stmt=con.createStatement();

			String cmd = String.format("SELECT balance FROM account WHERE account_number = %d AND username = '%s' AND password = '%s';", account_number, username, password);

			ResultSet rs=stmt.executeQuery(cmd);

			double bal = 0.0;

			while(rs.next())
				bal = rs.getInt(1);

			System.out.println("\nYour account balance is " + bal);

			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong...Try again...\n" + e);
		}
	}

	public void update_add_account_balance(long account_number, String username, String password, double balance)
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql:///bank", "root", "Shaan@123");

			Statement stmt=con.createStatement();

			String cmd = String.format("SELECT balance FROM account WHERE account_number = %d AND username = '%s' AND password = '%s';", account_number, username, password);

			ResultSet rs=stmt.executeQuery(cmd);

			double bal = 0.0;

			while(rs.next())
				bal = rs.getInt(1);

			bal += balance;

			cmd = String.format("UPDATE account SET balance = '%f' WHERE account_number = %d AND username = '%s' AND password = '%s';", bal, account_number, username, password);
			stmt = con.createStatement();
			stmt.executeUpdate(cmd);

			System.out.println("\nYour account has been credited with " + balance);

			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong...Try again...\n" + e);
		}
	}

	public void update_deduct_account_balance(long account_number, String username, String password, double balance)
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql:///bank", "root", "Shaan@123");

			Statement stmt=con.createStatement();

			String cmd = String.format("SELECT balance FROM account WHERE account_number = %d AND username = '%s' AND password = '%s';", account_number, username, password);

			ResultSet rs=stmt.executeQuery(cmd);

			double bal = 0.0;

			while(rs.next())
				bal = rs.getInt(1);

			bal -= balance;

			cmd = String.format("UPDATE account SET balance = '%f' WHERE account_number = %d AND username = '%s' AND password = '%s';", bal, account_number, username, password);
			stmt = con.createStatement();
			stmt.executeUpdate(cmd);

			System.out.println("\n" + balance + " has been withdrawn from your account");

			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong...Try again...\n" + e);
		}
	}
}

//class Account
//{
//
//	private String account_name;
//	private long account_number;
//	private double balance;
//	private String username;
//	private String password;
//
//	public Account(String account_name, long account_number, double balance, String username, String password)
//	{
//		this.account_name = account_name;
//		this.account_number = account_number;
//		this.balance = balance;
//		this.username = username;
//		this.password = password;
//	}
//
//	public void save_account_data()
//	{
//
//	}
//
//}

class BankingSystem
{
	public static void create_account()
	{
		System.out.println("\n###### Create Your Account ######\n");

		String account_name, username, password;

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter your full name: ");
		account_name = sc.nextLine();

		System.out.print("Enter your username: ");
		username = sc.next();

		System.out.print("Enter your password: ");
		password = sc.next();

		DBHandler dbh = new DBHandler();
		dbh.insert_account_record(account_name, username, password);
	}

	public static void balance_enquiry()
	{
		System.out.println("\n###### Balance Enquiry ######\n");

		String username, password;
		long account_number;

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter your account number: ");
		account_number = sc.nextLong();

		System.out.print("Enter your username: ");
		username = sc.next();

		System.out.print("Enter your password: ");
		password = sc.next();

		DBHandler dbh = new DBHandler();
		dbh.get_account_balance(account_number, username, password);
	}
	
	public static void deposit_money()
	{
		System.out.println("\n###### Balance Deposit ######\n");

		String username, password;
		long account_number;
		double bal;

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter your account number: ");
		account_number = sc.nextLong();

		System.out.print("Enter your username: ");
		username = sc.next();

		System.out.print("Enter your password: ");
		password = sc.next();

		System.out.print("Enter amount to deposit: ");
		bal = sc.nextDouble();

		DBHandler dbh = new DBHandler();
		dbh.update_add_account_balance(account_number, username, password, bal);
	}
	
	public static void withdraw_money()
	{
		System.out.println("\n###### Balance Withdraw ######\n");

		String username, password;
		long account_number;
		double bal;

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter your account number: ");
		account_number = sc.nextLong();

		System.out.print("Enter your username: ");
		username = sc.next();

		System.out.print("Enter your password: ");
		password = sc.next();

		System.out.print("Enter amount to withdraw: ");
		bal = sc.nextDouble();

		DBHandler dbh = new DBHandler();
		dbh.update_deduct_account_balance(account_number, username, password, bal);
	}
	
	public static void exit_program()
	{
		System.out.println("\nThank you for using ABC Bank! Bye!");
		System.exit(1);
	}
	
	public static void main(String[] args)
	{
		
		int option;
		char choice = 'y';
		
		Scanner sc = new Scanner(System.in);
		
		while(choice == 'y' || choice == 'Y')
		{
			
			System.out.print("1. Create Account\n2. Balance Enquiry\n3. Deposit\n4. Withdraw\n5. Exit\n\nChoose your option: ");
			
			try
			{
				option = sc.nextInt();
				
				switch(option)
				{
					case 1: create_account(); break;
					case 2: balance_enquiry(); break;
					case 3: deposit_money(); break;
					case 4: withdraw_money(); break;
					case 5: exit_program(); break;
					default: System.out.println("\nOops! Wrong option...Try again...");
				}
			}
			catch(Exception e)
			{
				System.out.println("\nSomething went wrong...Try again...");
			}
			
			sc.nextLine();
			
			System.out.print("\nDo you want to continue?(y/n): ");
			choice = sc.nextLine().charAt(0);
			
			System.out.println();
		}
		
	}
}