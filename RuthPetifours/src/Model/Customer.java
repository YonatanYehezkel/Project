package Model;

import java.util.ArrayList;

public class Customer {
	
	public int id;
	public String customerName;
	public String adress;
	public String comment;
	public ArrayList<Contact> contacts;
	
	/*
	 * Customer constructor w/o list of contacts
	 */
	public Customer(int id, String customerName, String adress, String comment) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.adress = adress;
		this.comment = comment;
	}
	
	public Customer(int id, String customerName, String adress, String comment, ArrayList contacts) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.adress = adress;
		this.comment = comment;
		this.contacts = contacts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	
	
	
	
	
	

}
