package Controller;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Contact;
import Model.Customer;
import Model.I_ModelLogic;
import Model.JobRole;
import Model.ModelLogic;
import Model.Order;
import Model.User;
import Utils.Constants;
//import Utils.Constants;
import View.ViewLogic;
import db.DB;

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

	public HashMap<Integer, Customer> getAllCustomers() {
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
	
	public boolean addNewUser (User u) {
		if(db.addNewUser(u))
				return true;
		return false;
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
	
	public boolean deleteCustomer (int id) {
		if(db.deleteCustomer(id))
				return true;
		return false;
	}
	
	public boolean deleteCustomer (String s) {
		if(db.deleteCustomer(s))
				return true;
		return false;
	}
	
}
