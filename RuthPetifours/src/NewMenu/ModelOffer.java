package NewMenu;

import java.math.BigDecimal;

//import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelOffer {

	private int offerID;
	private String offerDate;
	private String request;
	private String requestDate;
	
	//TODO CLERK INSERT
	
	private String notes;
	
	/* CUSTOMER */
	private int customerID;
	
	/* CLERK */
	private int clerkID;
	private String clerk;
	
	/* ARTICLE */
	private int articleID;
	private double amount;
	private BigDecimal ek;
	private BigDecimal vk;
	private int amountOfPositions;
	private BigDecimal total;
	
	//private ObservableList<ModelArticle> obsListArticle = FXCollections.observableArrayList();
	
	public ModelOffer(){}
	
	/**
	 * Constructor for ObservableList (Offer Search)
	 * @param _offerID
	 * @param _offerDate
	 * @param _customerID
	 * @param _request
	 * @param _requestDate
	 */
	public ModelOffer(	int _offerID,
						String _offerDate,
						int _customerID,
						String _request,
						String _requestDate){
		
		this.offerID = _offerID;
		this.offerDate = _offerDate;
		this.customerID = _customerID;
		this.request = _request;
		this.requestDate = _requestDate;
		
	}
	
	/**
	 * Constructor for Database (Insert Offer)
	 * Constructor for Database (Update Offer)
	 * @param _offerID
	 * @param _offerDate
	 * @param _request
	 * @param _requestDate
	 * @param _notes
	 * @param _customerID
	 * @param _clerkID
	 * @param _obsListArticle
	 */
	public ModelOffer(	int _offerID,
						String _offerDate,
						String _request,
						String _requestDate,
						String _notes,
						int _customerID,
						int _clerkID,
						int amountOfPositions,
						BigDecimal total
						//,ObservableList<ModelArticle> _obsListArticle
						){

		this.offerID = _offerID;
		this.offerDate = _offerDate;
		this.request = _request;
		this.requestDate = _requestDate;
		this.notes = _notes;
		this.customerID = _customerID;
		this.clerkID = _clerkID;
		this.amountOfPositions = amountOfPositions;
		this.total = total;
		//this.obsListArticle = _obsListArticle;

	}
	
	/**
	 * Constructor for Database (Select Offer) <br>
	 * Constructor for Database (Delete Offer)
	 * @param _offerID
	 * @param _customerID
	 */
	public ModelOffer(	int _offerID, int _customerID){		
		this.offerID = _offerID;		
		this.customerID = _customerID;
	}
	
	/**
	 * Constructor for Database (Select Offer Customer)
	 * @param _customerID
	 */
	public ModelOffer(	int _customerID){
		this.customerID = _customerID;
	}

	/**
	 * Constructor for ObservableList (Customer Offers)
	 * @param offerID
	 * @param offerDate
	 * @param customerID
	 * @param request
	 * @param requestDate
	 * @param amountOfPositions
	 * @param total
	 */
	public ModelOffer(	int offerID, 
						String offerDate, 
						String request, 
						String requestDate,
						String clerk,
						int amountOfPositions,
						BigDecimal total) {
		this.offerID = offerID;
		this.offerDate = offerDate;
		this.request = request;
		this.requestDate = requestDate;
		this.clerk = clerk;
		this.amountOfPositions = amountOfPositions;
		this.total = total;
	}

	/*
	 * GETTER & SETTER
	 */
	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public String getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public int getClerkID() {
		return clerkID;
	}

	public void setClerkID(int clerkID) {
		this.clerkID = clerkID;
	}

	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}

	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public BigDecimal getEk() {
		return ek;
	}

	public void setEk(BigDecimal ek) {
		this.ek = ek;
	}

	public BigDecimal getVk() {
		return vk;
	}

	public void setVk(BigDecimal vk) {
		this.vk = vk;
	}
	
	public int getAmountOfPositions() {
		return amountOfPositions;
	}

	public void setAmountOfPositions(int amountOfPositions) {
		this.amountOfPositions = amountOfPositions;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

//	public ObservableList<ModelArticle> getObsListArticle() {
//		return obsListArticle;
//	}
//
//	public void setObsListArticle(ObservableList<ModelArticle> obsListArticle) {
//		this.obsListArticle = obsListArticle;
//	}
	
}
