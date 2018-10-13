package Model;

import java.util.ArrayList;

public class Customer {
	
	public String customerName;
	public String adress;
	public String comment;
	public ArrayList<Contact> contacts;
	public int ordersAmount;
	public float ordersSum;
	
	public String city;
	public String street;
	public int zipcode;
	public String phone1;
	public String phone2;
	public String fax;
	public String email;
	public String web;
	
	
	
	/*
	 * full constructor
	 */
	public Customer(String customerName, String adress, String comment, ArrayList<Contact> contacts, int ordersAmount,
			float ordersSum, String city, String street, int zipcode, String phone1, String phone2, String fax,
			String email, String web) {
		super();
		this.customerName = customerName;
		this.adress = adress;
		this.comment = comment;
		this.contacts = contacts;
		this.ordersAmount = ordersAmount;
		this.ordersSum = ordersSum;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.fax = fax;
		this.email = email;
		this.web = web;
		
	}
	
	
	/*
	 * getByID constructor
	 */

	public Customer(String customerName, String adress, String comment, String city, String street, int zipcode, String phone1, String phone2, String fax,
			String email, String web) {
		super();
		this.customerName = customerName;
		this.adress = adress;
		this.comment = comment;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.fax = fax;
		this.email = email;
		this.web = web;
		
		contacts = new ArrayList<Contact>();
	}
	
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
	
	

	public int getOrdersAmount() {
		return ordersAmount;
	}

	public void setOrdersAmount(int ordersAmount) {
		this.ordersAmount = ordersAmount;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public void setOrdersSum(float ordersSum) {
		this.ordersSum = ordersSum;
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", adress=" + adress + ", comment=" + comment
				+ ", contacts=" + contacts + ", ordersAmount=" + ordersAmount + "]";
	}

}
