package admin;
import java.sql.*;
import accountant.*;
import java.util.*;

import accountant.StudentDao;

public class FeeReportMain {
	static StudDatabase stdb = new StudDatabase();
	public static Connection cn;
	static AcDatabase acdb = new AcDatabase();
	public FeeReportMain() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","Aj2548@P");
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	void switchCase() {
		int ch;
		do {
			System.out.println(" 1. Operation on File\n 2. Operation On Database\n 3. Exit");
			Scanner sc =new Scanner(System.in);
			ch = sc.nextInt();
			switch(ch) {
			case 1:System.out.println("Null");
	         
	         break;
	     case 2:acdb.adminSection();
	         break;
	     case 3:
	    	 System.out.println("Exit");
	         break;
	     default:
	    	 System.out.println("Invalid Choice!!");
			}
		} while (ch != 3);
	}
	public static void main(String[] args) {
		FeeReportMain frm = new FeeReportMain();
		int ch;
		do {
			System.out.println(" Fee Report\n\n 1. Admin\n 2. Accountant\n 3. Exit");
			Scanner sc =new Scanner(System.in);
			ch = sc.nextInt();
			switch(ch) {
			case 1:acdb.verifyAdmin();
	         
	         break;
	     case 2:stdb.accLogin();
	         break;
	     case 3:
	    	 System.out.println("Exit");
	         break;
	     default:
	    	 System.out.println("Invalid Choice!!");
			}
		} while (ch != 3);
	}

}
