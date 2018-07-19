package NewMenu;

//import de.michaprogs.crm.contact.ModelContact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelCustomer {

	private int customerID;
	private String salutation;
	private String name1;
	private String name2;
	private String street;
	private String land;
	private int zip;
	private String location;
	
	private String phone;
	private String mobile;
	private String fax;
	private String email;
	private String web;
	private String taxID;
	private String ustID;
	
	private String payment;
	private String IBAN;
	private String BIC;
	private String bank;
	private int paymentSkonto;
	private int paymentNetto;
	private int skonto;
	private String category;
	
	private String lastChange;
	private String notes;
	
	private int billingID;
	
	/* CONTACTS */
	//private ObservableList<ModelContact> obsListContacts = FXCollections.observableArrayList();
	
	/* SEARCH */
	private ObservableList<ModelCustomer> obsListSearch = FXCollections.observableArrayList();
	
	public ModelCustomer(){}
	
	/**
	 * Constructor for ObservableList (Customer Search)
	 * @param customerID
	 * @param name1
	 * @param name2
	 * @param street
	 * @param land
	 * @param zip
	 * @param location
	 * @param phone
	 */
	public ModelCustomer(	int customerID, 
							String name1, 
							String name2, 
							String street, 
							String land, 
							int zip,
							String location, 
							String phone) {
		
		this.customerID = customerID;
		this.name1 = name1;
		this.name2 = name2;
		this.street = street;
		this.land = land;
		this.zip = zip;
		this.location = location;
		this.phone = phone;
		
	}

	/**
	 * Constructor for Database (Insert Customer) <br>
	 * Constructor for Database (Update Customer)
	 * @param customerID
	 * @param salutation
	 * @param name1
	 * @param name2
	 * @param street
	 * @param land
	 * @param zip
	 * @param location
	 * @param phone
	 * @param mobile
	 * @param fax
	 * @param email
	 * @param web
	 * @param contact
	 * @param ustID
	 * @param payment
	 * @param IBAN
	 * @param BIC
	 * @param bank
	 * @param paymentSkonto
	 * @param paymentNetto
	 * @param skonto
	 * @param lastChange
	 * @param notes
	 * @param billingID
	 */
	public ModelCustomer(	int customerID, 
							String salutation, 
							String name1, 
							String name2, 
							String street, 
							String land,
							int zip, 
							String location, 
							String phone, 
							String mobile, 
							String fax, 
							String email, 
							String web, 
							String taxID,
							String ustID, 
							String payment, 
							String IBAN, 
							String BIC, 
							String bank, 
							int paymentSkonto, 
							int paymentNetto,
							int skonto, 
							String category,
							String lastChange, 
							String notes, 
							int billingID) {
		
		this.customerID = customerID;
		this.salutation = salutation;
		this.name1 = name1;
		this.name2 = name2;
		this.street = street;
		this.land = land;
		this.zip = zip;
		this.location = location;
		this.phone = phone;
		this.mobile = mobile;
		this.fax = fax;
		this.email = email;
		this.web = web;
		this.taxID = taxID;
		this.ustID = ustID;
		this.payment = payment;
		this.IBAN = IBAN;
		this.BIC = BIC;
		this.bank = bank;
		this.paymentSkonto = paymentSkonto;
		this.paymentNetto = paymentNetto;
		this.skonto = skonto;
		this.category = category;
		this.lastChange = lastChange;
		this.notes = notes;
		this.billingID = billingID;
		
	}
	
	/**
	 * Constructor for Database (Select Article)
	 * Constructor forDatabase (Delete Article)
	 * @param customerID
	 */
	public ModelCustomer(int customerID){
		this.customerID = customerID;
	}
	
	/**
	 * Constructor for Database (Search Article)
	 * @param customerID
	 * @param name1
	 * @param name2
	 * @param street
	 * @param land
	 * @param zip
	 * @param location
	 * @param phone
	 * @param mobile
	 * @param fax
	 * @param email
	 * @param category
	 */
	public ModelCustomer(	int customerID,
							String name1,
							String name2,
							String street,
							String land,
							int zip,
							String location,
							String phone,
							String mobile,
							String fax,
							String email,
							String category){
		
		this.customerID = customerID;
		this.name1 = name1;
		this.name2 = name2;
		this.street = street;
		this.land = land;
		this.zip = zip;
		this.location = location;
		this.phone = phone;
		this.mobile = mobile;
		this.fax = fax;
		this.email = email;
		this.category = category;
		
	}

	/*
	 * GETTER & SETTER
	 */
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getSalutation() {
		//Empty ComboBox = null
		if(salutation == null){
			salutation = "";
		}
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLand() {
		//Empty ComboBox = null
		if(land == null){
			land = "";
		}
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public String getUstID() {
		return ustID;
	}

	public void setUstID(String ustID) {
		this.ustID = ustID;
	}

	public String getPayment() {
		//Empty ComboBox = null
		if(payment == null){
			payment = "";
		}
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public String getBIC() {
		return BIC;
	}

	public void setBIC(String bIC) {
		BIC = bIC;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getPaymentSkonto() {
		return paymentSkonto;
	}

	public void setPaymentSkonto(int paymentSkonto) {
		this.paymentSkonto = paymentSkonto;
	}

	public int getPaymentNetto() {
		return paymentNetto;
	}

	public void setPaymentNetto(int paymentNetto) {
		this.paymentNetto = paymentNetto;
	}

	public int getSkonto() {
		return skonto;
	}

	public void setSkonto(int skonto) {
		this.skonto = skonto;
	}

	public String getCategory() {
		//Empty ComboBox = null
		if(category == null){
			category = "";
		}
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLastChange() {
		return lastChange;
	}

	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getBillingID() {
		return billingID;
	}

	public void setBillingID(int billingID) {
		this.billingID = billingID;
	}

	
//	public ObservableList<ModelContact> getObsListContacts() {
//		return obsListContacts;
//	}
//	
//
//	public void setObsListContacts(ObservableList<ModelContact> obsListContacts) {
//		this.obsListContacts = obsListContacts;
//	}

	public ObservableList<ModelCustomer> getObsListSearch() {
		return obsListSearch;
	}

	public void setObsListSearch(ObservableList<ModelCustomer> obsListSearch) {
		this.obsListSearch = obsListSearch;
	}
	
	
	
}