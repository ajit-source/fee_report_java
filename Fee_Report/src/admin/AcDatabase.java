package admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class AcDatabase implements AccDao {
	Scanner sc = new Scanner(System.in);
	
	FeeReportMain fmain = new FeeReportMain();
	Admin admin = new Admin();
	Accountant accountant = new Accountant();
	
	
	@Override
	public void verifyAdmin() {
		System.out.println("Enter Admin name");
		String name=sc.next();
		System.out.println("Enter Admin password");
		String password=sc.next();
		if(adminLogin(name, password))
		{
			fmain.switchCase();
		}
		
	}
	
	@Override
	public void adminSection() {
		
			int flag=0;
			while(flag==0)
			{
				System.out.println("\n wellcome to Admin Section \n");
				System.out.println("1. Add Accountant");
				System.out.println("2. Display Accountant");
				System.out.println("3. exit");
				int ch=sc.nextInt();
				if(ch==1)
					addAccountant();
				else if(ch==2)
					viewAccountant();
				else if(ch==3)
					flag = 1;
				else
					System.out.println("number is invalid");
			}
			
		
	}
	
	@Override
	public boolean adminLogin(String username, String pass) {
		admin.setUsername("AdminAj");
		admin.setPass("ajit");
		
		boolean logstatus = false;
		if(admin.getUsername().equals(username)&& admin.getPass().equals(pass))
			logstatus = true;
		else 
			logstatus = false;
		return logstatus;
		
	}

	@Override
	public void addAccountant() {
		String name,password,email,contact;
		System.out.println("\n Enter Accountant information \n");
		
		System.out.println("Enter Name");
		while (!(name = sc.nextLine()).trim().matches("[A-Za-z ]+")||name.length()<3) 
		{System.out.print("\n Invalid re-enter : ");}accountant.setName(name);
		
		System.out.println("Enter Password");
		while(!((password=sc.nextLine()).trim().length()>4)) {
			System.out.print("\nShort password re-enter : ");}accountant.setPassword(password);
		
		System.out.println("Enter Email");
		while(!(email=sc.nextLine()).matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
			System.out.print("\nInvalid email re-enter : ");}accountant.setEmail(email);
		
		System.out.println("Enter contact");
		while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
			System.out.print("Invalid fee re-enter : ");}accountant.setContact_no(Long.parseLong(contact));

		try
		{
			String sql = "insert into accountant(name,password,email,contact_no) values (?, ?, ?, ?)";
            PreparedStatement st = fmain.cn.prepareStatement(sql);
            st.setString(1,accountant.getName());
            st.setString(2,accountant.getPassword());
            st.setString(3,accountant.getEmail());
            st.setLong(4,accountant.getContact_no());
            st.executeUpdate();
            System.out.println("\n Record Added successfully.\n");
 
		}
		catch(Exception e){
			e.printStackTrace();
			}

		
	}

	@Override
	public void viewAccountant() {
		try
		{
		    java.sql.Statement st = fmain.cn.createStatement();
		    ResultSet rs = ((java.sql.Statement) st).executeQuery("SELECT * FROM accountant");
		    while(rs.next()) {
		    	System.out.println(" A_NAME     A_PASSWORD        A_EMAIL                CONTACT_NO");
		    	System.out.println("|   "+rs.getString(1)+"   | "+rs.getString(2)+"    |   "+rs.getString(3)+"   |        "+rs.getLong(4)+"     |");
			    System.out.println("------------------------------------------------------------------------");
		    }    
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	

	
	
}
