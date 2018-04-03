package Model;

import java.sql.Date;
import java.util.HashMap;

public class Order {
	
	public String id;
	public Date submitted_date;
	public Date delivery_eta;
	public String submitted_by;
	public Date update_date;
	public String updated_by;
	public String customer;
	public float discount;
	public Date actual_delivery_date;
	public Date payment_date;
	public HashMap <Integer, Product> listOfProdcuts;
	
	/*
	 * full constructor
	 */
	public Order(String id, Date submitted_date, Date delivery_eta, String submitted_by, Date update_date, String updated_by,
			String customer, float discount, Date actual_delivery_date, Date payment_date) {
		super();
		this.id = id;
		this.submitted_date = submitted_date;
		this.delivery_eta = delivery_eta;
		this.submitted_by = submitted_by;
		this.update_date = update_date;
		this.updated_by = updated_by;
		this.customer = customer;
		this.discount = discount;
		this.actual_delivery_date = actual_delivery_date;
		this.payment_date = payment_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getSubmitted_date() {
		return submitted_date;
	}

	public void setSubmitted_date(Date submitted_date) {
		this.submitted_date = submitted_date;
	}

	public Date getDelivery_eta() {
		return delivery_eta;
	}

	public void setDelivery_eta(Date delivery_eta) {
		this.delivery_eta = delivery_eta;
	}

	public String getSubmitted_by() {
		return submitted_by;
	}

	public void setSubmitted_by(String submitted_by) {
		this.submitted_by = submitted_by;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomerID(String customer) {
		this.customer = customer;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Date getActual_delivery_date() {
		return actual_delivery_date;
	}

	public void setActual_delivery_date(Date actual_delivery_date) {
		this.actual_delivery_date = actual_delivery_date;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	
	
	
	
	

}
