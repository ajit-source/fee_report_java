package accountant;
import java.sql.*;
import java.util.Scanner;

import admin.FeeReportMain;
public class StudDatabase implements StudentDao{
	
	Scanner sc = new Scanner(System.in);
	Student student = new Student();

	@Override
	public void accLogin() {
		String name,pass;
		try {
			System.out.println("Enter User Name : ");
			name = sc.next();
			System.out.println("Enter Password :");
			pass = sc.next();
		PreparedStatement st = FeeReportMain.cn.prepareStatement("select name from accountant where name =? and password=?");
		st.setString(1,name);
		st.setString(2,pass);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			accSection();
			
		}
		else {
			System.out.println("invalid Name or password !!");
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	
	@Override
	public void accSection() {
		int flag=0;
		while(flag==0)
		{
			System.out.println("\n wellcome to Accountant Section \n");
			System.out.println("1. Add Student");
			System.out.println("2. Display Student");
			System.out.println("3. Edit Student");
			System.out.println("4. Due Fee");
			System.out.println("5. exit");
			int ch=sc.nextInt();
			if(ch==1)
				addStudent();
			else if(ch==2)
				displayStudent();
			else if(ch==3)
				editStudent();
			else if(ch==4)
				dueFee();
				
			else if(ch==5)
				flag = 1;
			else
				System.out.println("number is invalid");
		}
		
	}

	@Override
	public void addStudent() {
		String flag = "y";
		while(flag.equalsIgnoreCase("y")) {
		
		String name,email,course,fee,paid,address,city,state,country,contact;
		System.out.println("\n Enter student information \n");
		
		System.out.println("Enter name");
		while (!(name = sc.nextLine()).trim().matches("[A-Za-z ]+")||name.length()<3) 
		{System.out.print("\n Invalid re-enter : ");}student.setName(name);
		
		System.out.println("Enter Email");
		while (!(email = sc.nextLine()).trim().matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
			System.out.print("Invalid e-mail re-enter : ");}student.setEmail(email);
			
		System.out.println("Enter course");
		while (!(course = sc.nextLine()).trim().matches("[A-Za-z]+")) {
			System.out.print("Invalid re-enter : ");}student.setCourse(course);
		
		System.out.println("Enter fee");
		while (!(fee = sc.nextLine()).trim().matches("[0-9]+")) {
			System.out.print("Invalid fee re-enter : ");}student.setFee(Integer.parseInt(fee));
		System.out.println("Enter paid");
		
		//student.setPaid(sc.nextInt());
		while (!(paid = sc.nextLine()).trim().matches("[0-9]+")|Integer.parseInt(fee)<Integer.parseInt(paid)) {
			System.out.print("Invalid paid re-enter : ");}student.setPaid(Integer.parseInt(paid));
			
		student.setDue(student.getFee()-student.getPaid());
		
		System.out.println("Enter address");
		while (!(address = sc.nextLine()).trim().matches("[A-Za-z0-9 -]+")) {
			System.out.print("Invalid characters re-enter : ");}student.setAddress(address);
		
		System.out.println("Enter city");
		while (!(city = sc.nextLine()).trim().matches("[A-Za-z]+")) {
			System.out.print("Invalid characters re-enter : ");}student.setCity(city);
		
		System.out.println("Enter state");
		while (!(state = sc.nextLine()).trim().matches("[A-Za-z]+")) {
			System.out.print("Invalid characters re-enter : ");}student.setState(state);
		
		System.out.println("Enter country");
		while (!(country = sc.nextLine()).trim().matches("[A-Za-z ]+")) {
			System.out.print("Invalid characters re-enter : ");}student.setCountry(country);
		
		System.out.println("Enter contact");
		while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
			System.out.print("Invalid fee re-enter : ");}student.setContact(Long.parseLong(contact));
		
		try
		{
	        String sql = "INSERT INTO student(name,email,course,fee,paid,due,address,city,state,country,contact_no) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = FeeReportMain.cn.prepareStatement(sql);
            st.setString(1,student.getName());
            st.setString(2,student.getEmail());
            st.setString(3,student.getCourse());
            st.setInt(4,student.getFee());
            st.setInt(5,student.getPaid());
            st.setInt(6,student.getDue());
            st.setString(7,student.getAddress());
            st.setString(8,student.getCity());
            st.setString(9,student.getState());
            st.setString(10,student.getCountry());
            st.setLong(11,student.getContact());
            st.executeUpdate();
            System.out.println("\n student add in database \n");
            
            System.out.println("Do You Want Add More Student [Y/N] : ");
            flag = sc.next();
            
		}
		catch(Exception e){System.out.println(e);}}
		
	}

	@Override
	public void displayStudent() {
		try
		{
		    Statement st = FeeReportMain.cn.createStatement();
		    ResultSet rs = st.executeQuery("select * from student");
			  while(rs.next()) { 
				  System.out.print("Name = "+rs.getString(1)+"\t");
			      System.out.print("Email = "+rs.getString(2)+"\t");
			      System.out.print("Course = "+rs.getString(3)+"\t");
			      System.out.print("Fee = "+rs.getInt(4)+"\t"); 
			      System.out.print("Paid = "+rs.getInt(5)+"\t");
			      System.out.print("Due = "+rs.getInt(6)+"\t");
			      System.out.print("Address = "+rs.getString(7)+"\t");
			      System.out.print("City = "+rs.getString(8)+"\t");
			      System.out.print("State = "+rs.getString(9)+"\t");
			      System.out.print("Country = "+rs.getString(10)+"\t");
			      System.out.print("Contact = "+rs.getLong(11)+"\t"); 
			      System.out.println(); }
			
		      System.out.println(" ");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void editStudent() {
		System.out.println("");
		int flag=0;
		while(flag==0)
		{
			System.out.println("\n Choose What You Want To Edit : \n");
			System.out.println("1. Email");
			System.out.println("2. Course");
			System.out.println("3. Fee");
			System.out.println("4. Paid");
			System.out.println("5. Due");
			System.out.println("6. Address");
			System.out.println("7. City");
			System.out.println("8. State");
			System.out.println("9. Country");
			System.out.println("10.Contact_No");
			System.out.println("11. exit");
			int ch=sc.nextInt();
			if(ch==1)
				//email
			else if(ch==2)
				//Course
			else if(ch==3)
				editStudent();
			else if(ch==4)
				dueFee();
			else if(ch==2)
				displayStudent();
			else if(ch==3)
				editStudent();
			else if(ch==4)
				dueFee();
				
			else if(ch==11)
				flag = 1;
			else
				System.out.println("number is invalid");
		}
		
	}

	@Override
	public void dueFee() {
		// TODO Auto-generated method stub
		
	}

	

}
