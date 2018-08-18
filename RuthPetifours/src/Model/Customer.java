package Model;

import java.util.ArrayList;

public class Customer {
	
	public String customerName;
	public String adress;
	public String comment;
	public ArrayList<Contact> contacts;
	public int ordersAmount;
	public float ordersSum;
	
	/*
	 * Customer constructor for profitability report
	 */
	public Customer(String customerName, float ordersSum) {
		super();
		this.customerName = customerName;
		this.ordersSum = ordersSum;
	}
	
	/*
	 * Customer constructor w/o list of contacts
	 */
	public Customer(String customerName, String adress, String comment) {
		super();
		this.customerName = customerName;
		this.adress = adress;
		this.comment = comment;
	}

	/*
	 * full constructor
	 */
	public Customer(String customerName, String adress, String comment, ArrayList contacts) {
		super();
		this.customerName = customerName;
		this.adress = adress;
		this.comment = comment;
		this.contacts = contacts;
	}


	public String getCustomerName() {
		return customerName.toString();
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
	
	public float getOrdersSum() {
		return (ordersSum);
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", adress=" + adress + ", comment=" + comment
				+ ", contacts=" + contacts + ", ordersAmount=" + ordersAmount + "]";
	}

}
