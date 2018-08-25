package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import Model.City;
import Model.Contact;
import Model.Customer;
import Model.I_ModelLogic;
import Model.JobRole;
import Model.ModelLogic;
import Model.Order;
import Model.Product;
import Model.User;
import Utils.Constants;
//import Utils.Constants;
import View.ViewLogic;
import db.DB;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;


public class ControllerLogic {
	
	public DB db = new DB();
	
	/**
	 * Singleton instance of this class, loaded on the first execution of
	 * ControllerLogic.createInstance()
	 */
	private static ControllerLogic instance;
	/** ModelLogic reference pointer */
	private static I_ModelLogic model;
	private static ViewLogic view;

	/**Constructor**/
	public ControllerLogic() {
		model = new ModelLogic();
		//model.rollTheDice();
		new Constants();
	}
	
	public void Serialize(){
		model.Serialize();
	}
	
	//receiving the data to the DB
	public void inputSerialize(){
		model.inputSerialize();
	}
	
	protected static ControllerLogic CreateInstance() {
		try {
			if (instance == null) {
				model = ModelLogic.CreateInstance();
				model.inputSerialize();
				setView(ViewLogic.getInstance());
				instance = new ControllerLogic();
				return instance;
			} else
				return instance;
		} catch (Exception e) { //instead of IOException as before

			e.printStackTrace(); 
		}
		return null;
	}
	
	public static ControllerLogic getInstance() {
		return instance;
	}
	
	public static ViewLogic getView() {
		return view;
	}
	
	public static I_ModelLogic getModel() {
		return model;
	}
	
	public static void setView(ViewLogic view) {
		ControllerLogic.view = view;
	}
	
	public void setShadowEffect(Button button) {
		DropShadow shadow = new DropShadow();
		//Adding the shadow when the mouse cursor is on
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	button.setEffect(shadow);
		        }
		});
		//Removing the shadow when the mouse cursor is off
		button.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	button.setEffect(null);
		        }
		});
	}
	
	public ArrayList<Customer> getMostProfitableCustomers() {
		return db.getMostProfitableCustomers();	 
	}
	
	public ArrayList<Product> getBestSelllingProducts() {
		return db.getBestSelllingProducts();	 
	}
	
	public ArrayList<City> getDemandByLocation() {
		return db.getDemandByLocation();	 
	}
	
	public HashMap<String, Customer> getAllCustomers() {
		return db.getAllCustomers();	 
	}
	
	public HashMap<String, Order> getAllOrders() {
		return db.getAllOrders();
	}
	
	public HashMap<String, Contact> getAllContacts(){
		return db.getAllContacts();		
	}
	
	public ArrayList<JobRole> getAllJobRoles(){
		return db.getAllJobRoles();
	}
	
	public HashMap<String, User> getAllUsers(){
		return db.getAllUsers();		
	}
	
	public HashMap<Integer, Product> getAllProducts(){
		return db.getAllProducts();		
	}
	
	public boolean addNewUser (User u) {
		if(db.addNewUser(u))
				return true;
		return false;
	}
	
	public boolean addNewOrder (Order o) {
		if(db.addNewOrder(o))
				return true;
		return false;
	}
	
	public HashMap<String, Order> searchOrders(String customer) {
		return db.searchOrders(customer);
	}
	public HashMap<String, Order> searchAwaitingPaimentOrders(String customer) {
		return db.searchAwaitingPaimentOrders(customer);
	}
	
	public HashMap<String, Order> searchAwaitingPaimentOrders(String customer, Date d) {
		return db.searchAwaitingPaimentOrders(customer, d);
	}
	
	public HashMap<String, Order> searchAwaitingShipOrders(String customer, Date d) {
		return db.searchAwaitingShipOrders(customer, d);
	}
	
	public HashMap<String, Order> searchAwaitingPaimentOrders_eta(String customer, Date d, Date d2) {
		return db.searchAwaitingPaimentOrders_eta(customer, d, d2);
	}
	public HashMap<String, Order> searchAwaitingShipOrders_eta(String customer, Date d, Date d2) {
		return db.searchAwaitingShipOrders_eta(customer, d, d2);
	}
	
	public HashMap<String, Order> searchAwaitingPaimentOrders_eta(String customer, Date d2) {
		return db.searchAwaitingPaimentOrders_eta(customer, d2);
	}
	
	public HashMap<String, Order> searchAwaitingShipmentOrders(String customer) {
		return db.searchAwaitingShipmentOrders(customer);
	}
	public HashMap<String, Order> searchShippedOrders(String customer) {
		return db.searchShippedOrders(customer);
	}
	
	public HashMap<String, Order> searchByShipOrders(String s, Date d) {
		return db.searchByShipOrders(s, d);
	}
	
	public boolean deleteUser (int id) {
		if(db.deleteUser(id))
				return true;
		return false;
	}
	
	public boolean addNewJobRole (JobRole j) {
		if(db.addNewJobRole(j))
				return true;
		return false;
	}
	
	public boolean deleteJobRole (String s) {
		if(db.deleteJobRole(s))
				return true;
		return false;
	}
	
	public boolean addNewContact (Contact con, Customer cus) {
		if(db.addNewContact(con, cus))
				return true;
		return false;
	}
	
	public boolean deleteContact (String s) {
		if(db.deleteContact(s))
				return true;
		return false;
	}
	
	public boolean addNewCustomer (Customer cus) {
		if(db.addNewCustomer(cus))
				return true;
		return false;
	}
	
	
	
	public boolean deleteCustomer (String s) {
		if(db.deleteCustomer(s))
				return true;
		return false;
	}
	
	public User getUserByID(int id) {
		return db.getUserByID(id);
	}
	
	public Product getProductByID(int id) {
		return db.getProductByID(id);
	}
	
	public boolean deleteProduct (int id) throws MySQLIntegrityConstraintViolationException {
		try {
			if(db.deleteProduct(id))
					return true;
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new MySQLIntegrityConstraintViolationException();
		}
		return false;
	}
	
	public boolean addNewProduct(Product p) {
		if(db.addNewProduct(p))
			return true;		
		return false;
	}
	
	public boolean updateCustomer (Customer c) {
		if(db.updateCustomer(c))
			return true;
		return false;
	}
	
	public User getUserByUsername(String username) {
		return db.getUserByUsername(username);
	}
	
	
	public boolean updateUser(User u) {
		if(db.updateUser(u))
			return true;
		return false;
	}
	
	public boolean importCustomersFromExcel(File f) {
		if(!f.equals(null)) {
		String ext2 = FilenameUtils.getExtension(f.getAbsolutePath());
		if(ext2.equals("xls")) {
			if(db.importCustomersFromXLS(f))
				return true;
		}
		else {
			if(db.importCustomersFromXLSX(f)) {
				return true;
			}
		}
		}
			
		return false;		
	}
	
	public boolean importOrdersFromExcel(File f) {
		if(f != null) {
		String ext2 = FilenameUtils.getExtension(f.getAbsolutePath());
		if(ext2.equals("xls")) {
			if(db.importOrdersFromXLS(f))
				return true;
		}
		else {
			if(db.importOrdersFromXLSX(f)) 
				return true;
		}
		}
			
		return false;
	}
	
	public boolean importProductsFromExcel(File f) {
		if(f != null) {
		String ext2 = FilenameUtils.getExtension(f.getAbsolutePath());
		if(ext2.equals("xls")) {
			if(db.importProductsFromXLS(f))
				return true;
		}
		else {
			if(db.importProductsFromXLSX(f)) 
				return true;
		}
		}
			
		return false;
	}
	
	public Customer getCustomerByName(String name) {
		if(db.getCustomerByName(name) != (null))
			return db.getCustomerByName(name);
		return null;
	}
	
	public float getValueOfOrder(String idorder) {
		return db.getValueOfOrder(idorder);
	}
	
	
	public void exportToExcelRoute(TableView<Order> table, String filename) {
		 Workbook workbook = new HSSFWorkbook();
	     Sheet spreadsheet = workbook.createSheet("sample");

	     Row row = spreadsheet.createRow(0);

	     for (int j = 0; j < table.getColumns().size(); j++) {
	         row.createCell(j).setCellValue(table.getColumns().get(j).getText());
	     }

	     for (int i = 0; i < table.getItems().size(); i++) {
	         row = spreadsheet.createRow(i + 1);
	         for (int j = 0; j < table.getColumns().size(); j++) {
	             if(table.getColumns().get(j).getCellData(i) != null) { 
	                 row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString()); 
	             }
	             else {
	                 row.createCell(j).setCellValue("");
	             }   
	         }
	     }

	     FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("C:\\Users\\Public\\Documents\\"+ filename + ".xls");
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
			
		}


}
