package Model;


import java.time.LocalDate;
import java.util.HashMap;

public class Order {
	
	private String id;
	private LocalDate submitted_date;
	private LocalDate delivery_eta;
	private String submitted_by;
	private LocalDate update_date;
	private String updated_by;
	private String customer;
	private float discount;
	private LocalDate actual_delivery_date;
	private LocalDate payment_date;
	private HashMap <Integer, Product> listOfProdcuts;
	private String adress;
	private float value;
	
	/*
	 * full constructor
	 */
	public Order(String id, LocalDate submitted_date, LocalDate delivery_eta, String submitted_by, LocalDate update_date, String updated_by,
			String customer, float discount, LocalDate actual_delivery_date, LocalDate payment_date, String adress, float value) {
		super();
		this.id 					=	 id;
		this.submitted_date 		=	 submitted_date;
		this.delivery_eta 			=	 delivery_eta;
		this.submitted_by			=	 submitted_by;
		this.update_date 			=	 update_date;
		this.updated_by				=	 updated_by;
		this.customer 				=	 customer;
		this.discount 				=	 discount;
		this.actual_delivery_date 	=	 actual_delivery_date;
		this.payment_date 			=	 payment_date;
		this.adress 				=	 adress;
		this.value 					=	 value;
	}

	/*
	 * partial constructor
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
	
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
	
	
	

}
