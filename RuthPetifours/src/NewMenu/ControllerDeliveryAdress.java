package NewMenu;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Controller.ControllerLogic;
import Model.Contact;
import Model.Customer;
import exceptions.ApiException;
import googleMap.GeoApiContext;
import googleMap.GeocodingApi;
import googleMap.GeocodingResult;
//import de.michaprogs.crm.InitCombos;
//import de.michaprogs.crm.customer.ModelCustomer;
//import de.michaprogs.crm.customer.SelectCustomer;
//import de.michaprogs.crm.customer.category.ModelCustomerCategory;
//import de.michaprogs.crm.customer.category.SelectCustomerCategory;
//import de.michaprogs.crm.customer.search.LoadCustomerSearch;
//import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControllerDeliveryAdress {

	/* MAIN DATA */
	@FXML private TextField tfCustomerID;

	@FXML private TextField tfStreet;
	@FXML private TextField tfZip;
	@FXML private TextField tfLocation;
	@FXML private TextField tfPhone;
	@FXML private TextField tfMobile;
	@FXML private TextField tfFax;
	@FXML private TextField tfEmail;
	@FXML private TextField tfWeb;
	
	@FXML private Label message;
	
	/* CONTACT1 DATA */

	@FXML private TextField tfTitle1;
	@FXML private TextField tfName1;
	@FXML private TextField tfPhone1;
	@FXML private TextField tfEmail1;

	/* CONTACT2 DATA */

	@FXML private TextField tfTitle2;
	@FXML private TextField tfName2;
	@FXML private TextField tfPhone2;
	@FXML private TextField tfEmail2;
	
	@FXML private TextArea tfComment;

	
	/* BILLING */
	private int billingID;
	
	/* BUTTONS */
	@FXML private Button btnCustomerSearch;
	@FXML private HBox hboxBtnSearchSmall;
	
	private int selectedCustomerID;
	
	private ControllerLogic controller;
	
	public ControllerDeliveryAdress(){}
	
	@FXML private void initialize(){
		controller = new ControllerLogic(); 
		
		initBtnCustomerSearch();
		
		initTextFields();
	}
	
	private void initTextFields() {
		
		EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				message.setVisible(false);
			}
		};
		
		tfCustomerID.setOnMouseClicked(event);
		tfStreet.setOnMouseClicked(event);
		tfZip.setOnMouseClicked(event);
		tfLocation.setOnMouseClicked(event);
		tfPhone.setOnMouseClicked(event);
		tfMobile.setOnMouseClicked(event);
		tfFax.setOnMouseClicked(event);
		tfEmail.setOnMouseClicked(event);
		tfWeb.setOnMouseClicked(event);
		

		tfTitle1.setOnMouseClicked(event);
		tfName1.setOnMouseClicked(event);
		tfPhone1.setOnMouseClicked(event);
		tfEmail1.setOnMouseClicked(event);

		/* CONTACT2 DATA */

		tfTitle2.setOnMouseClicked(event);
		tfName2.setOnMouseClicked(event);
		tfPhone2.setOnMouseClicked(event);
		tfEmail2.setOnMouseClicked(event);
		
		tfComment.setOnMouseClicked(event);
		
	}
	
	
	/*
	 * BUTTONS
	 */
	private void initBtnCustomerSearch(){
		
		
		btnCustomerSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				searchCompany();
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	public void searchCompany(){
		Customer s = controller.getCustomerByName(tfCustomerID.getText());
	
		if(s == null) {
			
		}
		else 
			loadCustomer(s);
		
	}
	
	private void loadCustomer(Customer s) {
		ArrayList <Contact> arr = s.getContacts();
		
		
		tfCustomerID.setText(s.getCustomerName());
		tfStreet.setText(s.getStreet());
		tfLocation.setText(s.getCity());
		tfZip.setText(String.valueOf(s.getZipcode()));
		tfPhone.setText(s.getPhone1());
		tfMobile.setText(s.getPhone2());
		tfFax.setText(s.getFax());
		tfEmail.setText(s.getEmail());
		tfWeb.setText(s.getWeb());
		tfComment.setText(s.getComment());
		if(!arr.isEmpty()) {
			/* first contact */
			tfTitle1.setText(arr.get(0).getJobRole());
			tfName1.setText(arr.get(0).getContactName());
			tfPhone1.setText(String.valueOf(arr.get(0).getPhoneNumber1()));
			tfEmail1.setText(arr.get(0).getEmail1());
			
			/*second contact */
			tfTitle2.setText(arr.get(1).getJobRole());
			tfName2.setText(arr.get(1).getContactName());
			tfPhone2.setText(String.valueOf(arr.get(1).getPhoneNumber1()));
			tfEmail2.setText(arr.get(1).getEmail1());
		}
		
	}
	
	public void selectDeliveryAdress(int customerID){
		
//		ModelCustomer customer = new SelectCustomer(new ModelCustomer(customerID)).getModelCustomer();
//		
//		tfCustomerID.setText(String.valueOf(customer.getCustomerID()));
//		cbSalutation.getSelectionModel().select(customer.getSalutation());
//		tfName1.setText(customer.getName1());
//		tfName2.setText(customer.getName2());
//		tfStreet.setText(customer.getStreet());
//		cbLand.getSelectionModel().select(customer.getLand());
//		tfZip.setText(String.valueOf(customer.getZip()));
//		tfLocation.setText(customer.getLocation());
//		
//		tfPhone.setText(customer.getPhone());
//		tfMobile.setText(customer.getMobile());
//		tfFax.setText(customer.getFax());
//		tfEmail.setText(customer.getEmail());
//		tfWeb.setText(customer.getWeb());
//		tfTaxID.setText(customer.getTaxID());
//		tfUstID.setText(customer.getUstID());
//		
//		cbPayment.getSelectionModel().select(customer.getPayment());
//		tfBank.setText(customer.getBank());
//		tfIBAN.setText(customer.getIBAN());
//		tfBIC.setText(customer.getBIC());
//		tfBank.setText(customer.getBank());
//		tfPaymentSkonto.setText(String.valueOf(customer.getPaymentSkonto()));
//		tfPaymentNetto.setText(String.valueOf(customer.getPaymentNetto()));
//		tfSkonto.setText(String.valueOf(customer.getSkonto()));
//		cbCategory.getSelectionModel().select(customer.getCategory());
//		
//		billingID = customer.getBillingID();
//		
	}
	
	public boolean checkAdress(String s) {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyAIgMRRrFNahxoMfyQdsi7T07SeQ79lEgY")
			    .build();
		GeocodingResult[] results = null;
			try {
				results = GeocodingApi.geocode(context,
				    s).await();
				
				
			} catch (ArrayIndexOutOfBoundsException | ApiException | InterruptedException | IOException e) {
				
				message.setText("Adress is not valid.");
				message.setVisible(true);
				tfStreet.setFocusTraversable(true);
				return false;
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try {
			System.out.println(gson.toJson(results[0].addressComponents));
			}catch (ArrayIndexOutOfBoundsException e) {
								
				message.setText("Adress is not valid.");
				message.setVisible(true);
				tfStreet.requestFocus();
				return false;
			}
			return true;
	}
		
	/*
	 * UI METHODS
	 */
	/**
	 * 
	 */
	public void enableFields(){
		
		tfCustomerID.setDisable(false);
		tfStreet.setDisable(false);
		tfZip.setDisable(false);
		tfLocation.setDisable(false);
		
		tfPhone.setDisable(false);
		tfMobile.setDisable(false);
		tfFax.setDisable(false);
		tfEmail.setDisable(false);
		tfWeb.setDisable(false);
		
		tfTitle1.setDisable(false);
		tfName1.setDisable(false);
		tfPhone1.setDisable(false);
		tfEmail1.setDisable(false);
		
		tfTitle2.setDisable(false);
		tfName2.setDisable(false);
		tfPhone2.setDisable(false);
		tfEmail2.setDisable(false);
		tfComment.setDisable(false);
		
		
	}
	
	public void disableFields(){
		
		tfCustomerID.setDisable(true);
		tfStreet.setDisable(true);
		tfZip.setDisable(true);
		tfLocation.setDisable(true);
		
		tfPhone.setDisable(true);
		tfMobile.setDisable(true);
		tfFax.setDisable(true);
		tfEmail.setDisable(true);
		tfWeb.setDisable(true);
		
		tfTitle1.setDisable(true);
		tfName1.setDisable(true);
		tfPhone1.setDisable(true);
		tfEmail1.setDisable(true);
		
		tfTitle2.setDisable(true);
		tfName2.setDisable(true);
		tfPhone2.setDisable(true);
		tfEmail2.setDisable(true);
		tfComment.setDisable(true);

	}
	
	/**
	 * shows a little search-icon beside the customerID textfield
	 * @param boolean b - true = show
	 */
	public void showSearchButtonSmall(boolean b){
		
		if(b){
			hboxBtnSearchSmall.getChildren().add(btnCustomerSearch);
		}else{
			hboxBtnSearchSmall.getChildren().remove(btnCustomerSearch);
		}
		
	}
	
	public void clearFields(){
		
		tfCustomerID.clear();
		tfStreet.clear();
		tfZip.clear();
		tfLocation.clear();
		
		tfPhone.clear();
		tfMobile.clear();
		tfFax.clear();
		tfEmail.clear();
		tfWeb.clear();
		
		tfTitle1.clear();
		tfName1.clear();
		tfPhone1.clear();
		tfEmail1.clear();
		
		tfTitle2.clear();
		tfName2.clear();
		tfPhone2.clear();
		tfEmail2.clear();
		tfComment.clear();

		
	}

	/*
	 * GETTER & SETTER
	 */
	public TextField getTfCustomerID() {
		return tfCustomerID;
	}

	public void setTfCustomerID(TextField tfCustomerID) {
		this.tfCustomerID = tfCustomerID;
	}






	public TextField getTfStreet() {
		return tfStreet;
	}

	public void setTfStreet(TextField tfStreet) {
		this.tfStreet = tfStreet;
	}



	public TextField getTfZip() {
		return tfZip;
	}

	public void setTfZip(TextField tfZip) {
		this.tfZip = tfZip;
	}

	public TextField getTfLocation() {
		return tfLocation;
	}

	public void setTfLocation(TextField tfLocation) {
		this.tfLocation = tfLocation;
	}

	public TextField getTfPhone() {
		return tfPhone;
	}

	public void setTfPhone(TextField tfPhone) {
		this.tfPhone = tfPhone;
	}

	public TextField getTfMobile() {
		return tfMobile;
	}

	public void setTfMobile(TextField tfMobile) {
		this.tfMobile = tfMobile;
	}

	public TextField getTfFax() {
		return tfFax;
	}

	public void setTfFax(TextField tfFax) {
		this.tfFax = tfFax;
	}

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(TextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public TextField getTfWeb() {
		return tfWeb;
	}
	
	public Label getMessage() {
		return message;
	}

	public void setMessage(Label message) {
		this.message = message;
	}
	
	public TextField gettfTitle1() {
		return tfTitle1;
	}

	public void setTfWeb(TextField tfWeb) {
		this.tfWeb = tfWeb;
	}
	
	public void settfTitle1(TextField tfTitle1) {
		this.tfTitle1 = tfTitle1;
	}
	
	public TextField getTfName1() {
		return tfName1;
	}
	
	public ControllerLogic getController() {
		return controller;
	}

	public void setTfName1(TextField tfName1) {
		this.tfName1 = tfName1;
	}

	public TextField gettfPhone1() {
		return tfPhone1;
	}

	public void settfPhone1(TextField tfPhone1) {
		this.tfPhone1 = tfPhone1;
	}

	public TextField gettfEmail1() {
		return tfEmail1;
	}

	public void settfEmail1(TextField tfEmail1) {
		this.tfEmail1 = tfEmail1;
	}
	
	
	public TextField gettfTitle2() {
		return tfTitle2;
	}
	
	public void settfTitle2(TextField tfTitle2) {
		this.tfTitle2 = tfTitle2;
	}
	
	public TextField getTfName2() {
		return tfName2;
	}

	public void setTfName2(TextField tfName2) {
		this.tfName2 = tfName2;
	}

	public TextField gettfPhone2() {
		return tfPhone2;
	}

	public void settfPhone2(TextField tfPhone2) {
		this.tfPhone2 = tfPhone2;
	}

	public TextField gettfEmail2() {
		return tfEmail2;
	}

	public void settfEmail2(TextField tfEmail2) {
		this.tfEmail2 = tfEmail2;
	}

	
	
	public TextArea gettfComment() {
		return tfComment;
	}

	public void settfComment(TextArea tfComment) {
		this.tfComment = tfComment;
	}

	public Button getBtnCustomerSearch() {
		return btnCustomerSearch;
	}

	public void setBtnCustomerSearch(Button btnCustomerSearch) {
		this.btnCustomerSearch = btnCustomerSearch;
	}

	public int getSelectedCustomerID() {
		return selectedCustomerID;
	}

	public void setSelectedCustomerID(int selectedCustomerID) {
		this.selectedCustomerID = selectedCustomerID;
	}

	public int getBillingID() {
		return billingID;
	}

	public void setBillingID(int billingID) {
		this.billingID = billingID;
	}
	
	
	
}
