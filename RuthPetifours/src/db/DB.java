package db;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import Model.Contact;
import Model.Customer;
import Model.JobRole;
import Model.Order;
import Model.Product;
import Model.User;



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
		/*"jdbc:mysql://localhost:3306/sys","root","Longshot747"*/
			"jdbc:mysql://localhost:3306/ruth_db","root","1234"
		 );  
		 
				

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
	
	public HashMap<String, User> getAllUsers (){
		HashMap<String, User> users = new HashMap<String, User>();
		if(setConnection()) {
			try {
				
				PreparedStatement statement = con.prepareStatement("select * from user");    
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
					User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getInt(1));
					users.put(u.getUserName(), u);
					
				}
				con.close();
				return users;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return null;
	}
	
	public boolean addNewUser (User u) {
		if(setConnection()) {
			try {
				String query = "insert into user (username, password, question1, question2, answer1, answer2, jobrole)"
					        + " values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setString (1, u.getUserName());
				statement.setString (2, u.getPassword());
				statement.setString (3, u.getQuestion1());
				statement.setString (4, u.getQuestion2());
				statement.setString (5, u.getAnswer1());
				statement.setString (6, u.getAnswer2());
				statement.setInt (7, u.getIdJobRole());

			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}
	
	public boolean deleteUser (int id) {
		if(setConnection()) {
			try {
				String query = "DELETE FROM user WHERE iduser = ?";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setInt(1, id);

			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}
	
	public boolean deleteOrder (int id) {
		if(setConnection()) {
			try {
				String query = "DELETE FROM order WHERE ordernum = ?";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setInt(1, id);

			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}
	
	public boolean addNewJobRole (JobRole j) {
		if(setConnection()) {
			try {
				String query = "insert into jobrole (jobRolecol)"
					        + " values (?)";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setString (1, j.getJobRole());
				

			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}
	
	public boolean deleteJobRole (String role) {
		if(setConnection()) {
			try {
				String query = "DELETE FROM jobrole WHERE jobRolecol = ?";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setString(1, role);

			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}
	
	public boolean addNewContact (Contact contact, Customer customer) {
		if(setConnection()) {
			try {
				String query = "insert into contact (name, phonenumber1, phonenumber1, email1, "
						+ "email1, jobrole, idcustomer)"
					        + " values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setString (1, contact.getContactName());
				statement.setInt (2, contact.getPhoneNumber1());
				statement.setInt (3, contact.getPhoneNumber2());
				statement.setString (4, contact.getEmail1());
				statement.setString (5, contact.getEmail2());
				statement.setString (6, contact.getJobRole());
				statement.setInt (7, customer.getId());

			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}
	
	public boolean deleteContact (String name) {
		if(setConnection()) {
			try {
				String query = "DELETE FROM contact WHERE name = ?";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setString(1, name);

			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}
	
	public boolean addNewCustomer (Customer customer) {
		if(setConnection()) {
			try {
				String query = "insert into customer (customername, adress, comment)"
					        + " values (?, ?, ?)";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setString (1, customer.getCustomerName());
				statement.setString (2, customer.getAdress());
				statement.setString (3, customer.getComment());
		
			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}
	
	public boolean deleteCustomer (int id) {
		if(setConnection()) {
			try {
				String query = "DELETE FROM customer WHERE idcustomer = ?";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setInt(1, id);

			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}
	
	public boolean deleteCustomer (String s) {
		if(setConnection()) {
			try {
				String query = "DELETE FROM customer WHERE customername = ?";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setString(1, s);

			      // execute the preparedstatement
				statement.execute();
			      
			    con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return false;
	}



public User getUserByID(int id) {
	User u = null;
	if(setConnection()) {
		try {
			
			PreparedStatement statement = con.prepareStatement("select * from user where iduser = ?");    
			statement.setInt(1, id); 
			ResultSet rs = statement.executeQuery(); 
			while(rs.next())  {
				//System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5) + rs.getString(6) + rs.getString(7) + rs.getInt(8));  
				u = new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				//c.setContacts(getContactsOfCustomer(c));
			}
			con.close();
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
	return null;
}

public User getUserByUsername(String username) {
	User u = null;
	if(setConnection()) {
		try {
			
			PreparedStatement statement = con.prepareStatement("select * from user where username = ?");    
			statement.setString(1, username); 
			ResultSet rs = statement.executeQuery(); 
			while(rs.next())  {
				//System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5) + rs.getString(6) + rs.getString(7) + rs.getInt(8));  
				u = new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				//c.setContacts(getContactsOfCustomer(c));
			}
			con.close();
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
	return null;
}

public Customer getCustomerByName(String name) {
	Customer c = null;
	if(setConnection()) {
		try {
			
			PreparedStatement statement = con.prepareStatement("select * from customer "
					+ "where customername = ?");    
			statement.setString(1, name); 
			ResultSet rs = statement.executeQuery(); 
			while(rs.next())  {
				//System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5) + rs.getString(6) + rs.getString(7) + rs.getInt(8));  
				c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				//c.setContacts(getContactsOfCustomer(c));
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

public  HashMap<String, Order> getAllOrders() {
	HashMap<String, Order> orders = new HashMap<String, Order>();
	
	if(setConnection()) {
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from ruth_db.order");  
			while(rs.next())  {
				//System.out.println(rs.getString(1) + rs.getDate(2) + rs.getDate(3) +rs.getInt(4)  +
					//	rs.getDate(5) + rs.getInt(6) + rs.getInt(7)+  rs.getFloat(8) + rs.getDate(9) +
						//rs.getDate(10));  
				
				Order o = new Order(
						rs.getString(1), 
						rs.getDate(2), 
						rs.getDate(3),
						getUserByID(rs.getInt(4)).getUserName(), 
						rs.getDate(5), 
						getUserByID(rs.getInt(6)).getUserName(), 
						getCompanyByID(rs.getInt(7)).getCustomerName(), 
						rs.getFloat(8), 
						rs.getDate(9), 
						rs.getDate(10));
				//o.setContacts(getContactsOfCustomer(c));
				orders.put(rs.getString(1), o);
				//System.out.println(rs.getInt(1));
			
			}
			con.close();
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
	return null;
}

public ArrayList <Product> getAllProductsbyOrder(String orderID){
	ArrayList <Product> products = new ArrayList();
	
	if(setConnection()) {
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from ruth_db.order_details where idorder = ?");  
			while(rs.next())  {
				//System.out.println(rs.getString(1) + rs.getDate(2) + rs.getDate(3) +rs.getInt(4)  +
					//	rs.getDate(5) + rs.getInt(6) + rs.getInt(7)+  rs.getFloat(8) + rs.getDate(9) +
						//rs.getDate(10));  
				
				//Product p = new Product(
					//	rs.getString(1), 
						//rs.getInt(2), 
						//rs.getFloat(3)
					//);
				//o.setContacts(getContactsOfCustomer(c));
				//orders.put(rs.getString(1), o);
				//System.out.println(rs.getInt(1));
			
			}
			con.close();
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
	return null;
}

public Product getProductByID(int id) {
	Product p = null;
	if(setConnection()) {
		try {
			
			PreparedStatement statement = con.prepareStatement("select * from product where idproduct = ?");    
			statement.setInt(1, id); 
			ResultSet rs = statement.executeQuery(); 
			while(rs.next())  {
				//System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5) + rs.getString(6) + rs.getString(7) + rs.getInt(8));  
				p = new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4));
				//c.setContacts(getContactsOfCustomer(c));
			}
			con.close();
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
	return null;
}

public boolean deleteProduct (int id) throws MySQLIntegrityConstraintViolationException {
	if(setConnection()) {
		try {
			String query = "DELETE FROM product WHERE idproduct = ?";
			PreparedStatement statement = con.prepareStatement(query);    
			//ResultSet rs = statement.executeQuery(); 
			statement.setInt(1, id);

		      // execute the preparedstatement
			statement.execute();
		      
		    con.close();
			return true;
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new MySQLIntegrityConstraintViolationException();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
	return false;
}

public boolean addNewProduct (Product p) {
	if(setConnection()) {
		try {
			String query = "insert into product (idproduct, title, price, unit)"
				        + " values (?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(query);    
			//ResultSet rs = statement.executeQuery(); 
			statement.setInt (1, p.getId());
			statement.setString (2, p.getTitle());
			statement.setFloat (3, p.getPrice());
			statement.setString (4, p.getUnit());
			

		      // execute the preparedstatement
			statement.execute();
		      
		    con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
	return false;
}

public boolean addNewOrder(Order o) {
	if(setConnection()) {
		try {
			String query = "insert into order (ordernum, submitted_date, delivery_eta, "
					+ "submitted_by, update_date, update_by, customer_id, discount,"
					+ "actual_delivery_date, payment_date)"
				        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(query);    
			//ResultSet rs = statement.executeQuery(); 
			statement.setString (1, o.getId());
			statement.setDate (2, o.getSubmitted_date());
			statement.setDate (3, o.getDelivery_eta());
			statement.setInt (4, getUserByUsername(o.getSubmitted_by()).getId());
			statement.setDate (5, o.getUpdate_date());
			statement.setInt (6, getUserByUsername(o.getUpdated_by()).getId());
			statement.setInt (7, getCustomerByName(o.getCustomer()).getId());
			statement.setFloat (8, o.getDiscount());
			statement.setDate (9, o.getActual_delivery_date());
			statement.setDate (10, o.getPayment_date());

		      // execute the preparedstatement
			statement.execute();
		      
		    con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
	return false;
}

public boolean updateCustomer (Customer c) {
	
	if(setConnection()) {
		try {
			 String query = "update customer set customername = ?, adress = ?, comment = ? "
			 		+ "where idcustomer = ?";
		     PreparedStatement preparedStmt = con.prepareStatement(query);
		     preparedStmt.setString  (1, c.getCustomerName());
		     preparedStmt.setString(2, c.getAdress());
		     preparedStmt.setString(3, c.getComment());
		     preparedStmt.setInt(4, c.getId());

		     // execute the java preparedstatement
		     preparedStmt.executeUpdate();
		      
		    con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
		
	return false;
}


public boolean updateUser(User u) {
	if(setConnection()) {
		try {
			 String query = "update user set username = ?, password = ?, question1 = ?,"
			 		+ " question2 = ?, answer1 = ?, answer2 = ?, jobrole = ? "
			 		+ "where iduser = ?";
		     PreparedStatement preparedStmt = con.prepareStatement(query);
		     preparedStmt.setString  (1, u.getUserName());
		     preparedStmt.setString(2, u.getPassword());
		     preparedStmt.setString(3, u.getQuestion1());
		     preparedStmt.setString(4, u.getQuestion2());
		     preparedStmt.setString(5, u.getAnswer1());
		     preparedStmt.setString(6, u.getAnswer2());
		     preparedStmt.setInt(7, u.getIdJobRole());
		     preparedStmt.setInt(8, u.getId());

		     // execute the java preparedstatement
		     preparedStmt.executeUpdate();
		      
		    con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
		
	return false;
}

} 

		


