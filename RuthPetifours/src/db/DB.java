package db;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Contact;
import Model.Customer;
import Model.JobRole;



public class DB {
	
	private static Connection con;
	private static Statement stmt;
	
	private static DB db;
	
	protected static DB CreateDB() {
		try {
			if (db == null) {
				db = new DB();
				return db;
			} else
				return db;
		} catch (Exception e) { //instead of IOException as before
			e.printStackTrace(); 
		}
		return null;
	}
	
	public static boolean setConnection () {
	
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/ruth_db","root","1234");  

		/*Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from permissions");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getInt(2));  
		con.close();  */
		return true;
		}catch(Exception e){ 
			System.out.println(e);} 
	return false;
	}
	
	public  HashMap<Integer, Customer> getAllCustomers() {
		HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();
		
		if(setConnection()) {
			try {
				stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from customer");  
				while(rs.next())  {
					//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
					Customer c = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
					c.setContacts(getContactsOfCustomer(c));
					customers.put(rs.getInt(1), c);
					
				}
				con.close();
				return customers;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return null;
	}
	
	public ArrayList<Contact> getContactsOfCustomer(Customer cus) {
		ArrayList<Contact> contacts = new ArrayList();
		if(setConnection()) {
			try {
				
				PreparedStatement statement = con.prepareStatement("select * from contact where idCustomer = ?");    
				statement.setInt(1, cus.getId()); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
					Contact c = new Contact(rs.getString(1),rs.getInt(2),rs.getInt(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
					contacts.add(c);
					
				}
				con.close();
				return contacts;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return null;
	}
	
	public  HashMap<String, Contact> getAllContacts() {
		HashMap<String, Contact> contacts = new HashMap<String, Contact>();
		
		if(setConnection()) {
			try {
				stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from contact");  
				while(rs.next())  {
					//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
					Contact c = new Contact(rs.getString(1),rs.getInt(2),rs.getInt(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
					c.setCompanyName(getCompanyByID(c.getIdCustomer()).getCustomerName());
					contacts.put(rs.getString(1), c);
					
				}
				con.close();
				return contacts;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return null;
	}
	
	public Customer getCompanyByID(int id) {
		Customer c = null;
		if(setConnection()) {
			try {
				
				PreparedStatement statement = con.prepareStatement("select * from customer where idcustomer = ?");    
				statement.setInt(1, id); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
					c = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
					c.setContacts(getContactsOfCustomer(c));
				}
				con.close();
				return c;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return null;
	}
	
	public ArrayList<JobRole> getAllJobRoles (){
		ArrayList<JobRole> roles = new ArrayList();
		if(setConnection()) {
			try {
				
				PreparedStatement statement = con.prepareStatement("select * from jobrole");    
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
					JobRole r = new JobRole(rs.getInt(1),rs.getString(2));
					roles.add(r);
					
				}
				con.close();
				return roles;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return null;
	}
}
		


