package db;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import Model.City;
import Model.Contact;
import Model.Customer;
import Model.JobRole;
import Model.Order;
import Model.Product;
import Model.User;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




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
		"jdbc:mysql://localhost:3306/ruth_db?useSSL=false","root","Longshot747"
			/*"jdbc:mysql://localhost:3306/ruth_db?useSSL=false","root","Longshot747"*/
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
	
	
	public  HashMap<String, Customer> getAllCustomers() {
		HashMap<String, Customer> customers = new HashMap<String, Customer>();
		
		if(setConnection()) {
			try {
				stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from customer");  
				while(rs.next())  {
					//System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
					Customer c = new Customer(rs.getString(1),rs.getString(2), rs.getString(3));
					c.setContacts(getContactsOfCustomer(c));
					customers.put(rs.getString(1), c);
					
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
	
	public  HashMap<String, User> searchUsers(String uRole, String uName) {
		HashMap<String, User> users = new HashMap<String, User>();
		
		if(setConnection()) {
			try {

//				
//				PreparedStatement statement = con.prepareStatement("select * from ruth_db.user where username like ? "
//						+ "and jobrole like ? ");    
//				statement.setString(1, uName); 
//				statement.setString(2, uRole);
//			
//				ResultSet rs = statement.executeQuery(); 
//				
				
//				PreparedStatement statement = con.prepareStatement("select * from (select iduser, username, password, question1,"
//						+ "question2, answer1, answer2,jobrole, jobRolecol from ruth_db.user u "
//						+ "left join ruth_db.jobrole r on u.jobrole = r.idjobRole) d "
//						+ "where d.username like ? and d.jobRolecol like ? ");  
				
				PreparedStatement statement = con.prepareStatement("select * from (select iduser, username, password, question1,"
						+ "question2, answer1, answer2,jobrole, jobRolecol from ruth_db.user u "
						+ "left join ruth_db.jobrole r on u.jobrole = r.idjobRole) d "
						+ "where d.username like ? and d.jobRolecol like ? ");  
				
				statement.setString(1, uName); 
				statement.setString(2, uRole);
				
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					  
					User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
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
	
	public  HashMap<String, Customer> searchCustomers(String customer, String address, String comment) {
		HashMap<String, Customer> customers = new HashMap<String, Customer>();
		
		if(setConnection()) {
			try {
//				stmt = con.createStatement();
//				ResultSet rs=stmt.executeQuery("select * from customer"); 
				
				PreparedStatement statement = con.prepareStatement("select * from customer where customername like ? "
						+ "and adress like ? and comment like ?");    
				statement.setString(1, "%" + customer + "%"); 
				statement.setString(2, "%" + address + "%");
				statement.setString(3, "%" + comment + "%");
				ResultSet rs = statement.executeQuery(); 
				
				while(rs.next())  {
			
					Customer c = new Customer(rs.getString(1),rs.getString(2), rs.getString(3));
					c.setContacts(getContactsOfCustomer(c));
					customers.put(rs.getString(1), c);
					
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
	
	
	public  HashMap<Integer, Product> getAllProducts() {
		HashMap<Integer, Product> products = new HashMap<Integer, Product>();
		
		if(setConnection()) {
			try {
				stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from product");  
				while(rs.next())  {
					Product p = new Product (rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4));
					System.out.println(p.toString());
					//c.setContacts(getContactsOfCustomer(c));
					products.put(rs.getInt(1), p);
					
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
	
	
	
	public ArrayList<Contact> getContactsOfCustomer(Customer cus) {
		ArrayList<Contact> contacts = new ArrayList();
		if(setConnection()) {
			try {
				
				PreparedStatement statement = con.prepareStatement("select * from contact where customer = ?");    
				statement.setString(1, cus.getCustomerName()); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					Contact c = new Contact(rs.getString(1),rs.getInt(2),rs.getInt(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
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
					Contact c = new Contact(rs.getString(1),rs.getInt(2),rs.getInt(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
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
	
	public float getValueOfOrder(String orderID) {
		float value = 0;
		if(setConnection()) {
			try {
				
				PreparedStatement statement = con.prepareStatement("select d.quantity, p.price "
						+ "from order_details as d "
						+ "left join product as p "
						+ "on d.idproduct = p.idproduct "
						+ "where d.idorder = ?");    
				statement.setString(1, orderID); 
				ResultSet rs = statement.executeQuery(); 
				
				while(rs.next())  {
					value = value + rs.getFloat(1) * rs.getFloat(2);
				}
				con.close();
				return value;
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		return value;
	}
	
	public Customer getCustomerByName(String name) {
		Customer c = null;
		if(setConnection()) {
			try {
				
				PreparedStatement statement = con.prepareStatement("select * from customer where customername = ?");    
				statement.setString(1, name); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					c = new Customer(rs.getString(1),rs.getString(2), rs.getString(3));
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
	
	public HashMap<String, User> getAllUsersWithRole (){
		HashMap<String, User> users = new HashMap<String, User>();
		if(setConnection()) {
			try {
				
				PreparedStatement statement = con.prepareStatement("select iduser, username, password, question1,"
						+ "question2, answer1, answer2,jobrole, jobRolecol from ruth_db.user u "
						+ "left join ruth_db.jobrole r on u.jobrole = r.idjobRole");    
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					  
					User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
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
				String query = "insert into contact (name, phonenumber1, phonenumber2, email1, "
						+ "email2, jobrole, customer)"
					        + " values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setString (1, contact.getContactName());
				statement.setInt (2, contact.getPhoneNumber1());
				statement.setInt (3, contact.getPhoneNumber2());
				statement.setString (4, contact.getEmail1());
				statement.setString (5, contact.getEmail2());
				statement.setString (6, contact.getJobRole());
				statement.setString (7, customer.getCustomerName());

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
	
	public boolean deleteContactsOfCustomer(String customer) {
		if(setConnection()) {
			try {
				String query = "DELETE FROM contact WHERE customer = ?";
				PreparedStatement statement = con.prepareStatement(query);    
				//ResultSet rs = statement.executeQuery(); 
				statement.setString(1, customer);

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
	

	
	public boolean deleteCustomer (String s) {
		deleteContactsOfCustomer(s);
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
	
	
	
	//"select * from ruth_db.order as o left join order_details as d on o.ordernum=d.idorder"
	
	
	
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
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	// this method is for report number 1 - best selling products
	public ArrayList <Product> getBestSelllingProducts(){
		
		ArrayList <Product> products = new ArrayList<Product>();
		
		if(setConnection()) {
			try {
				stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery("SELECT order_details.idproduct,product.title,quantity\r\n" + 
											   "FROM order_details,product\r\n" + 
											   "where order_details.idproduct = product.idproduct\r\n" + 
											   "group by order_details.idproduct,order_details.quantity\r\n" + 
											   "order by order_details.quantity\r\n" +  
											   "Desc");
				
				int i=0;
				
				while(rs.next() && i<10)  {
					
					Product p = new Product (rs.getInt(1), rs.getString(2), rs.getFloat(3));
					products.add(p);
					i++;
				
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
	
	// this method is for report number 2 - most profitable customers
			public ArrayList <Customer> getMostProfitableCustomers(){
				
				ArrayList <Customer> Customers = new ArrayList<Customer>();
				
				if(setConnection()) {
					try {
						stmt = con.createStatement();
						ResultSet rs=stmt.executeQuery("SELECT customername1, sum(order_sum) FROM ruth_db.order\r\n" + 
													   "group by customername1\r\n" + 
													   "order by sum(order_sum) Desc;");  
						
						while(rs.next())  {
							
							Customer c = new Customer (rs.getString(1), rs.getFloat(2));
							Customers.add(c);
							
						}
						con.close();
						return Customers;
					} catch (SQLException e) {
						e.printStackTrace();
					}  
				}
				else 
					System.out.println("DB is not available");
				return null;
		}
			
		// this method is for report number 3 - most profitable customers
		public ArrayList <City> getDemandByLocation(){
				
				ArrayList <City> Cities = new ArrayList<City>();
				
				if(setConnection()) {
					try {
						
						stmt = con.createStatement();
						
						ResultSet rs=stmt.executeQuery("select count(city),city\r\n" + 
													   "from customer\r\n" + 
													   "group by city\r\n" + 
													   "order by count(city) desc;");  
						
						while(rs.next())  {
							
							City c = new City (rs.getString(2), rs.getDouble(1));
							
							Cities.add(c);
							
						}
						
						con.close();
						return Cities;
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
				String query = "insert into ruth_db.order (ordernum, submitted_date, delivery_eta, submitted_by, update_date, update_by, discount, actual_delivery_date, payment_date, customername1)"
					        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = con.prepareStatement(query);    
		
				statement.setString (1, o.getId());
				statement.setDate (2, fixDate(o.getSubmitted_date()));
				statement.setDate (3, fixDate(o.getDelivery_eta()));
				statement.setInt (4, getUserByUsername(o.getSubmitted_by()).getId());
				statement.setDate (5, fixDate(o.getUpdate_date()));
				statement.setInt (6, getUserByUsername(o.getUpdated_by()).getId());
				statement.setFloat (7, o.getDiscount());
				statement.setDate (8, fixDate(o.getActual_delivery_date()));
				statement.setDate (9, fixDate(o.getPayment_date()));
				statement.setString(10,  o.getCustomer());
	
			 
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
	
	public Date fixDate(LocalDate d) {
		if(d == null)
			return null;
		else
			return Date.valueOf(d);
	}
	
	public LocalDate fixDate(Date d) {
		if(d == null)
			return null;
		else
			return d.toLocalDate();
	}
	
	public boolean updateCustomer (Customer c) {
		
		if(setConnection()) {
			try {
				 String query = "update customer set customername = ?, adress = ?, comment = ? "
				 		+ "where customername = ?";
			     PreparedStatement preparedStmt = con.prepareStatement(query);
			     preparedStmt.setString  (1, c.getCustomerName());
			     preparedStmt.setString(2, c.getAdress());
			     preparedStmt.setString(3, c.getComment());
			     preparedStmt.setString(4, c.getCustomerName());
	
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

	public boolean importCustomersFromXLS (File f) {
		if(setConnection()) {
			try {
				
				  FileInputStream input = new FileInputStream(f);
		          POIFSFileSystem fs = new POIFSFileSystem(input);
		          HSSFWorkbook wb = new HSSFWorkbook(fs);
		          HSSFSheet sheet = wb.getSheetAt(0);
		          Row row;
		          for(int i=1; i<=sheet.getLastRowNum(); i++){
		                row = sheet.getRow(i);
		                //int id = (int) row.getCell(0).getNumericCellValue();
		                String name = row.getCell(0).getStringCellValue();
		                String adress = row.getCell(1).getStringCellValue();
		                String comment = row.getCell(2).getStringCellValue();
		                
		                //String sql = "INSERT INTO tablename customer ('"+id+"','"+name+"','"+address+"')";
		                String query = "insert into customer (customername, adress, comment)"
						        + " values (?, ?, ?)";
					    PreparedStatement statement = con.prepareStatement(query);    
					
					    statement.setString (1, name);
					    statement.setString (2, adress);
					    statement.setString (3, comment);
				
		                //pstm = (PreparedStatement) con.prepareStatement(sql);
		                //pstm.execute();
					    statement.execute();
					    statement.close();
					    input.close();
		                System.out.println("Import rows "+i);
		            }
		            con.close();
		            System.out.println("Success import excel to mysql table");
				
				return true;
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println("Duplicated rows");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 System.out.println("input error");
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
			
		
		return false;
	}
	public boolean importOrdersFromXLS (File f) {
		if(setConnection()) {
			try {
				
				  FileInputStream input = new FileInputStream(f);
		          POIFSFileSystem fs = new POIFSFileSystem(input);
		          HSSFWorkbook wb = new HSSFWorkbook(fs);
		          HSSFSheet sheet = wb.getSheetAt(0);
		          Row row;
		          for(int i=1; i<=sheet.getLastRowNum(); i++){
		                row = sheet.getRow(i);
		                
		                String idorder = String.valueOf(row.getCell(0).getNumericCellValue());
		                float discount = (float)row.getCell(1).getNumericCellValue();
		                String customername = row.getCell(2).getStringCellValue();
		                int idproduct = ((Double)row.getCell(3).getNumericCellValue()).intValue();
		                float quantity = (float)row.getCell(4).getNumericCellValue();
		                
		                if(i == 1) 
		                	addNewOrder(new Order(idorder, getSubmitted_date(), "TestUser", getSubmitted_date(), "TestUser",
		                			customername, discount));
		                
		                String query = "insert into order_details (idorder, idproduct, quantity)"
						        + " values (?, ?, ?)";
		                setConnection();
					    PreparedStatement statement = con.prepareStatement(query);    
					
					    statement.setString (1, idorder);
					    statement.setInt (2, idproduct);
					    statement.setFloat (3, quantity);
				
		
				
		                statement.execute();
					    statement.close();
					    input.close();
		                System.out.println("Import rows "+i);
		            }
		            con.close();
		            System.out.println("Success import excel to mysql table");
				
				return true;
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println("Duplicated rows");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 System.out.println("input error");
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
			
		
		return false;
	}
	
	public boolean importCustomersFromXLSX (File f) {
		if(setConnection()) {
			try {
				
				
				FileInputStream fis = new FileInputStream(f);
		        XSSFWorkbook wb = new XSSFWorkbook(fis);
		       
		        XSSFSheet sheet = wb.getSheetAt(0);
		          Row row;
		          for(int i=1; i<=sheet.getLastRowNum(); i++){
		                row = sheet.getRow(i);
		                String name = row.getCell(0).getStringCellValue();
		                String adress = row.getCell(1).getStringCellValue();
		                String comment = row.getCell(2).getStringCellValue();
		     
		                String query = "insert into customer (customername, adress, comment)"
						        + " values (?, ?, ?)";
					    PreparedStatement statement = con.prepareStatement(query);    
					
					    statement.setString (1, name);
					    statement.setString (2, adress);
					    statement.setString (3, comment);
				
		
					    statement.execute();
					    statement.close();
		                System.out.println("Import rows "+i);
		            }
		            con.close();
		            System.out.println("Success import excel to mysql table");
				
				return true;
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println("Duplicated rows");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 System.out.println("input error");
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
			
		
		return false;
	}
	
	
	public boolean importOrdersFromXLSX (File f) {
		if(setConnection()) {
			try {
				
				
				FileInputStream fis = new FileInputStream(f);
		        XSSFWorkbook wb = new XSSFWorkbook(fis);
		       
		        XSSFSheet sheet = wb.getSheetAt(0);
		          Row row;
		          for(int i=1; i<=sheet.getLastRowNum(); i++){
		                row = sheet.getRow(i);
		                String idorder = String.valueOf(row.getCell(0).getNumericCellValue());
		                float discount = (float)row.getCell(1).getNumericCellValue();
		                String customername = row.getCell(2).getStringCellValue();
		                int idproduct = ((Double)row.getCell(3).getNumericCellValue()).intValue();
		                float quantity = (float)row.getCell(4).getNumericCellValue();
		                
		                if(i == 1) 
		                	addNewOrder(new Order(idorder, getSubmitted_date(), "TestUser", getSubmitted_date(), "TestUser",
		                			customername, discount));
		                
		                	
		     
		                String query = "insert into order_details (idorder, idproduct, quantity)"
						        + " values (?, ?, ?)";
		                setConnection();
					    PreparedStatement statement = con.prepareStatement(query);    
					
					    statement.setString (1, idorder);
					    statement.setInt (2, idproduct);
					    statement.setFloat (3, quantity);
				
		
					    statement.execute();
					    statement.close();
		                System.out.println("Import rows "+i);
		            }
		            con.close();
		            System.out.println("Success import excel to mysql table");
				
				return true;
			} catch (MySQLIntegrityConstraintViolationException e) {
				//System.out.println("Duplicated rows");
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 System.out.println("input error");
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
		
		
		return false;
	}
	
	public LocalDate getSubmitted_date() {
		return LocalDate.now();
	}
	
	public boolean importProductsFromXLSX(File f) {
	if(setConnection()) {
		try {
			
			
			FileInputStream fis = new FileInputStream(f);
	        XSSFWorkbook wb = new XSSFWorkbook(fis);
	       
	        XSSFSheet sheet = wb.getSheetAt(0);
	          Row row;
	          for(int i=1; i<=sheet.getLastRowNum(); i++){
	                row = sheet.getRow(i);
	                int idproduct = ((Double)row.getCell(0).getNumericCellValue()).intValue();
	                String title = row.getCell(1).getStringCellValue();
	                float price = (float)row.getCell(2).getNumericCellValue();
	                String unit = row.getCell(3).getStringCellValue();
	     
	                String query = "insert into product (idproduct, title, price, unit)"
					        + " values (?, ?, ?, ?)";
				    PreparedStatement statement = con.prepareStatement(query);    
				
				    statement.setInt (1, idproduct);
				    statement.setString (2, title);
				    statement.setFloat (3, price);
				    statement.setString(4, unit);
			
	
				    statement.execute();
				    statement.close();
	                System.out.println("Import rows "+i);
	            }
	            con.close();
	            System.out.println("Success import excel to mysql table");
			
			return true;
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Duplicated rows");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 System.out.println("input error");
			e.printStackTrace();
		}  
	}
	else 
		System.out.println("DB is not available");
		
	
	return false;
	}
	
	public boolean importProductsFromXLS (File f) {
		if(setConnection()) {
			try {
				
				  FileInputStream input = new FileInputStream(f);
		          POIFSFileSystem fs = new POIFSFileSystem(input);
		          HSSFWorkbook wb = new HSSFWorkbook(fs);
		          HSSFSheet sheet = wb.getSheetAt(0);
		          Row row;
		          for(int i=1; i<=sheet.getLastRowNum(); i++){
		                row = sheet.getRow(i);
		                int idproduct = ((Double)row.getCell(0).getNumericCellValue()).intValue();
		                String title = row.getCell(1).getStringCellValue();
		                float price = (float)row.getCell(2).getNumericCellValue();
		                String unit = row.getCell(3).getStringCellValue();
		                
		                String query = "insert into product (idproduct, title, price, unit)"
						        + " values (?, ?, ?, ?)";
					    PreparedStatement statement = con.prepareStatement(query);    
					
					    statement.setInt (1, idproduct);
					    statement.setString (2, title);
					    statement.setFloat (3, price);
					    statement.setString(4, unit);
					    
					    statement.execute();
					    statement.close();
					    input.close();
		                System.out.println("Import rows "+i);
		            }
		            con.close();
		            System.out.println("Success import excel to mysql table");
				
				return true;
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println("Duplicated rows");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 System.out.println("input error");
				e.printStackTrace();
			}  
		}
		else 
			System.out.println("DB is not available");
			
		
		return false;
	}
	
	
	public  HashMap<String, Order> searchOrders(String customer) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ?");    
				statement.setString(1, customer); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchOrders(String customer, Date d) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and actual_delivery_date like ?");    
				statement.setString(1, customer);
				statement.setString(2, "%" + d + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchOrders(String customer, Date d1, Date d2) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and actual_delivery_date like ? and payment_date like ?");    
				statement.setString(1, customer);
				statement.setString(2, "%" + d1 + "%"); 
				statement.setString(3, "%" + d2 + "%");
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchOrders(String customer, Date d1, Date d2, Date d3) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and actual_delivery_date like ? and payment_date like ? and delivery_eta like ?");    
				statement.setString(1, customer);
				statement.setString(2, "%" + d1 + "%"); 
				statement.setString(3, "%" + d2 + "%");
				statement.setString(4, "%" + d3 + "%");
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchOrders2(String customer, Date d1) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and payment_date like ?");    
				statement.setString(1, customer);
				statement.setString(2, "%" + d1 + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchOrders2(String customer, Date d1, Date d2) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and payment_date like ? and delivery_eta like ?");    
				statement.setString(1, customer);
				statement.setString(2, "%" + d1 + "%"); 
				statement.setString(3, "%" + d2 + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchOrders3(String customer, Date d1) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and delivery_eta like ?");    
				statement.setString(1, customer);
				statement.setString(2, "%" + d1 + "%");  
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchOrders4(String customer, Date d1, Date d2) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and payment_date like ? and delivery_eta like ?");    
				statement.setString(1, customer);
				statement.setString(2, "%" + d1 + "%"); 
				statement.setString(3, "%" + d2 + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchAwaitingPaimentOrders(String customer) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and payment_date is null");    
				statement.setString(1, customer); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {  
					
					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchAwaitingPaimentOrders(String customer, Date d) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and payment_date is null and actual_delivery_date like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchAwaitingShipOrders(String customer, Date d) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {
	
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and payment_date like ? and actual_delivery_date is null");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
	
					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchAwaitingShipOrders(String customer) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {
	
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and actual_delivery_date is null");    
				statement.setString(1, customer); 
				
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
	
					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchAwaitingPaimentOrders_eta(String customer, Date d, Date d2) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {
					
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and payment_date is null and actual_delivery_date like ? and delivery_eta like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d + "%"); 
				statement.setString(3, "%" + d2 + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {					
					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchAwaitingShipOrders_eta(String customer, Date d, Date d2) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {
					
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and actual_delivery_date is null and payment_date like ? and delivery_eta like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d + "%"); 
				statement.setString(3, "%" + d2 + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {					
					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchAwaitingPaimentOrders_eta(String customer, Date d2) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {
					
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and payment_date is null and actual_delivery_date is null and delivery_eta like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d2 + "%"); 
				
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {					
					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	
	
	public  HashMap<String, Order> searchAwaitingShipmentOrders(String customer) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {
				//stmt = con.createStatement();
				//ResultSet rs=stmt.executeQuery("select * from ruth_db.order where customername1 = ?");  
				
				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and actual_delivery_date is null");    
				statement.setString(1, customer); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					//System.out.println(rs.getString(1) + rs.getDate(2) + rs.getDate(3) +rs.getInt(4)  +
						//	rs.getDate(5) + rs.getInt(6) + rs.getInt(7)+  rs.getFloat(8) + rs.getDate(9) +
							//rs.getDate(10));  
					
					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchShippedOrders(String customer) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and actual_delivery_date is not null");    
				statement.setString(1, customer); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchShippedOrders(String customer, Date d) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and actual_delivery_date like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchShippedOrders(String customer, Date d1, Date d2) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? and actual_delivery_date like ? and payment_date like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d1 + "%");
				statement.setString(3, "%" + d2 + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchShippedOrders(String customer, Date d1, Date d2, Date d3) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and actual_delivery_date like ? and payment_date like ? and delivery_eta like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d1 + "%");
				statement.setString(3, "%" + d2 + "%"); 
				statement.setString(4, "%" + d3 + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchShippedOrders4(String customer, Date d1, Date d2) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and actual_delivery_date like ? and and delivery_eta like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d1 + "%");
				statement.setString(3, "%" + d2 + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchShippedOrders3(String customer, Date d1) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and actual_delivery_date is not null and delivery_eta like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d1 + "%");
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchShippedOrders2(String customer, Date d1, Date d2) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and actual_delivery_date is not null and payment_date like ? and delivery_eta like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d1 + "%");
				statement.setString(3, "%" + d2 + "%"); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchShippedOrders2(String customer, Date d1) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {

				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where customername1 like ? "
						+ "and actual_delivery_date is not null and payment_date like ?");    
				statement.setString(1, customer); 
				statement.setString(2, "%" + d1 + "%");
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {

					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	public  HashMap<String, Order> searchByShipOrders(String s, Date d) {
		HashMap<String, Order> orders = new HashMap<String, Order>();
		
		if(setConnection()) {
			try {
				//stmt = con.createStatement();
				//ResultSet rs=stmt.executeQuery("select * from ruth_db.order where customername1 = ?");  
				
				
				PreparedStatement statement = con.prepareStatement("select * from ruth_db.order where actual_delivery_date = ? and customername1 like ?");    
				statement.setDate(1, d); 
				statement.setString(2, s); 
				ResultSet rs = statement.executeQuery(); 
				while(rs.next())  {
					//System.out.println(rs.getString(1) + rs.getDate(2) + rs.getDate(3) +rs.getInt(4)  +
						//	rs.getDate(5) + rs.getInt(6) + rs.getInt(7)+  rs.getFloat(8) + rs.getDate(9) +
							//rs.getDate(10));  
					
					Order o = new Order(
							rs.getString(1), 
							fixDate(rs.getDate(2)), 
							fixDate(rs.getDate(3)),
							getUserByID(rs.getInt(4)).getUserName(), 
							fixDate(rs.getDate(5)), 
							getUserByID(rs.getInt(6)).getUserName(), 
							rs.getString(10),
							rs.getFloat(7), 
							fixDate(rs.getDate(8)), 
							fixDate(rs.getDate(9)));
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
	
	
	
} 

		


