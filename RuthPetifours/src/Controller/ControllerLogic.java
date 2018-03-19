package Controller;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Contact;
import Model.Customer;
import Model.I_ModelLogic;
import Model.JobRole;
import Model.ModelLogic;
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
	
	public HashMap<String, Contact> getAllContacts(){
		return db.getAllContacts();		
	}
	
	public ArrayList<JobRole> getAllJobRoles(){
		return db.getAllJobRoles();
	}
}
