package Model;


import java.time.LocalDate;
import java.util.HashMap;

public class Order {
	
	public String id;
	public LocalDate submitted_date;
	public LocalDate delivery_eta;
	public String submitted_by;
	public LocalDate update_date;
	public String updated_by;
	public String customer;
	public float discount;
	public LocalDate actual_delivery_date;
	public LocalDate payment_date;
	public HashMap <Integer, Product> listOfProdcuts;
	
	/*
	 * full constructor
	 */
	public Order(String id, LocalDate submitted_date, LocalDate delivery_eta, String submitted_by, LocalDate update_date, String updated_by,
			String customer, float discount, LocalDate actual_delivery_date, LocalDate payment_date) {
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
	/*
	 * import from excel constructor
	 */
	public Order(String id, LocalDate submitted_date, String submitted_by, LocalDate update_date, String updated_by,
			String customer, float discount) {
		super();
		this.id = id;
		this.submitted_date = submitted_date;
		this.submitted_by = submitted_by;
		this.update_date = update_date;
		this.updated_by = updated_by;
		this.customer = customer;
		this.discount = discount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getSubmitted_date() {
		return submitted_date;
	}

	public void setSubmitted_date(LocalDate submitted_date) {
		this.submitted_date = submitted_date;
	}

	public LocalDate getDelivery_eta() {
		return delivery_eta;
	}

	public void setDelivery_eta(LocalDate delivery_eta) {
		this.delivery_eta = delivery_eta;
	}

	public String getSubmitted_by() {
		return submitted_by;
	}

	public void setSubmitted_by(String submitted_by) {
		this.submitted_by = submitted_by;
	}

	public LocalDate getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(LocalDate update_date) {
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

	public LocalDate getActual_delivery_date() {
		return actual_delivery_date;
	}

	public void setActual_delivery_date(LocalDate actual_delivery_date) {
		this.actual_delivery_date = actual_delivery_date;
	}

	public LocalDate getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(LocalDate payment_date) {
		this.payment_date = payment_date;
	}
	
	
	
	
	

}
