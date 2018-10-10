package NewMenu;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Controller.ControllerLogic;
import Model.Contact;
import Model.Customer;
import Model.User;
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

public class ControllerUserInfo {

	/* MAIN DATA */
	@FXML private TextField tfCustomerID;
	//@FXML private ComboBox<String> cbSalutation;

	//@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	//@FXML private ComboBox<String> cbLand;
//	@FXML private TextField tfZip;
	@FXML private TextField tfLocation;
//	@FXML private TextField tfPhone;
//	@FXML private TextField tfMobile;
//	@FXML private TextField tfFax;
//	@FXML private TextField tfEmail;
//	@FXML private TextField tfWeb;
	
	@FXML private Label message;
	
	/* CONTACT1 DATA */

	@FXML private TextField tfPassword;
	@FXML private TextField tfFSQ;
	@FXML private TextField tfAFSQ;
	@FXML private TextField tfASSQ;
	@FXML private TextField tfSSQ;

	/* CONTACT2 DATA */

	@FXML private ComboBox tfTitle2;
//	@FXML private ComboBox tfName2;
//	@FXML private TextField tfPhone2;
//	@FXML private TextField tfEmail2;
	
//	@FXML private TextArea tfComment;

	
	/* BILLING */
	private int billingID;
	
	/* BUTTONS */
	@FXML private Button btnCustomerSearch;
	@FXML private HBox hboxBtnSearchSmall;
	
	private int selectedCustomerID;
	
	private ControllerLogic controller;
	
	public ControllerUserInfo(){}
	
	@FXML private void initialize(){
		controller = new ControllerLogic(); 
		
		initBtnCustomerSearch();
		
		initTextFields();
		
		enableFields();
		
		initJobRoleComboBox();
	}
	
	private void initJobRoleComboBox() {
		tfTitle2.getItems().addAll(controller.getAllJobRoles());
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

		tfLocation.setOnMouseClicked(event);
//		tfZip.setOnMouseClicked(event);
//		tfPhone.setOnMouseClicked(event);
//		tfMobile.setOnMouseClicked(event);
//		tfFax.setOnMouseClicked(event);
//		tfEmail.setOnMouseClicked(event);
//		tfWeb.setOnMouseClicked(event);
		

		tfPassword.setOnMouseClicked(event);
		tfFSQ.setOnMouseClicked(event);
		tfAFSQ.setOnMouseClicked(event);
		tfASSQ.setOnMouseClicked(event);
		tfSSQ.setOnMouseClicked(event);

		/* CONTACT2 DATA */

		tfTitle2.setOnMouseClicked(event);
//		tfName2.setOnMouseClicked(event);
//		tfPhone2.setOnMouseClicked(event);
//		tfEmail2.setOnMouseClicked(event);
		
//		tfComment.setOnMouseClicked(event);
		
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
		clearFields();
		User u = controller.getUserByUsername(tfCustomerID.getText());
	
		if(u == null) {
			
		}
		else 
			loadCustomer(u);
//		LoadCustomerSearch customerSearch = new LoadCustomerSearch(true);
//		if(customerSearch.getController().getSelectedCustomerID() != 0){
//			selectDeliveryAdress(customerSearch.getController().getSelectedCustomerID());	
//		}
		
	}
	
	private void loadCustomer(User u) {
		tfPassword.setText(u.getPassword());
		tfFSQ.setText(u.getQuestion1());
		tfAFSQ.setText(u.getAnswer1());
		tfSSQ.setText(u.getQuestion2());
		tfASSQ.setText(u.getAnswer2());
		
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
	public void enableFields(){
		
		//tfCustomerID.setDisable(false);
		tfStreet.setDisable(false);

		tfLocation.setDisable(false);
//		tfZip.setDisable(false);
//		tfPhone.setDisable(false);
//		tfMobile.setDisable(false);
//		tfFax.setDisable(false);
//		tfEmail.setDisable(false);
//		tfWeb.setDisable(false);
		
		tfPassword.setDisable(false);
		tfFSQ.setDisable(false);
		tfAFSQ.setDisable(false);
		tfASSQ.setDisable(false);
		tfSSQ.setDisable(false);
		
		tfTitle2.setDisable(false);
//		tfName2.setDisable(false);
//		tfPhone2.setDisable(false);
//		tfEmail2.setDisable(false);
//		tfComment.setDisable(false);
		
		
	}
	
	public void disableFields(){
		
//		tfCustomerID.setDisable(true);
//		tfStreet.setDisable(true);
//
//		tfLocation.setDisable(true);
//		tfZip.setDisable(true);
//		tfPhone.setDisable(true);
//		tfMobile.setDisable(true);
//		tfFax.setDisable(true);
//		tfEmail.setDisable(true);
//		tfWeb.setDisable(true);
		
//		tfTitle1.setDisable(true);
//		tfName1.setDisable(true);
//		tfPhone1.setDisable(true);
//		tfEmail1.setDisable(true);
		
//		tfTitle2.setDisable(true);
//		tfName2.setDisable(true);
//		tfPhone2.setDisable(true);
//		tfEmail2.setDisable(true);
//		tfComment.setDisable(true);

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
		
		//tfCustomerID.clear();
		tfStreet.clear();

		tfLocation.clear();
//		tfZip.clear();
//		tfPhone.clear();
//		tfMobile.clear();
//		tfFax.clear();
//		tfEmail.clear();
//		tfWeb.clear();
		
		tfPassword.clear();
		tfFSQ.clear();
		tfAFSQ.clear();
		tfASSQ.clear();
		tfSSQ.clear();
		
//		tfTitle2.clear();
//		tfName2.clear();
//		tfPhone2.clear();
//		tfEmail2.clear();
//		tfComment.clear();

		
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



//	public TextField getTfZip() {
//		return tfZip;
//	}
//
//	public void setTfZip(TextField tfZip) {
//		this.tfZip = tfZip;
//	}

	public TextField getTfLocation() {
		return tfLocation;
	}

	public void setTfLocation(TextField tfLocation) {
		this.tfLocation = tfLocation;
	}

//	public TextField getTfPhone() {
//		return tfPhone;
//	}
//
//	public void setTfPhone(TextField tfPhone) {
//		this.tfPhone = tfPhone;
//	}
//
//	public TextField getTfMobile() {
//		return tfMobile;
//	}
//
//	public void setTfMobile(TextField tfMobile) {
//		this.tfMobile = tfMobile;
//	}
//
//	public TextField getTfFax() {
//		return tfFax;
//	}
//
//	public void setTfFax(TextField tfFax) {
//		this.tfFax = tfFax;
//	}
//
//	public TextField getTfEmail() {
//		return tfEmail;
//	}
//
//	public void setTfEmail(TextField tfEmail) {
//		this.tfEmail = tfEmail;
//	}
//
//	public TextField getTfWeb() {
//		return tfWeb;
//	}
	
	public Label getMessage() {
		return message;
	}

	public void setMessage(Label message) {
		this.message = message;
	}
	
	public TextField gettfPassword() {
		return tfPassword;
	}

//	public void setTfWeb(TextField tfWeb) {
//		this.tfWeb = tfWeb;
//	}
	
	public void settfPassword(TextField tfPassword) {
		this.tfPassword = tfPassword;
	}
	
	public TextField gettfFSQ() {
		return tfFSQ;
	}
	
	public ControllerLogic getController() {
		return controller;
	}

	public void settfFSQ(TextField tfFSQ) {
		this.tfFSQ = tfFSQ;
	}

	public TextField gettfAFSQ() {
		return tfAFSQ;
	}
	
	public TextField gettfASSQ() {
		return tfASSQ;
	}

	public void settfAFSQ(TextField tfAFSQ) {
		this.tfAFSQ = tfAFSQ;
	}

	public void settfASSQ(TextField tfASSQ) {
		this.tfASSQ = tfASSQ;
	}
	
	public TextField gettfSSQ() {
		return tfSSQ;
	}

	public void settfSSQ(TextField tfSSQ) {
		this.tfSSQ = tfSSQ;
	}
	
	
	public ComboBox gettfTitle2() {
		return tfTitle2;
	}
	
	public void settfTitle2(ComboBox tfTitle2) {
		this.tfTitle2 = tfTitle2;
	}
	
//	public ComboBox getTfName2() {
//		return tfName2;
//	}
//
//	public void setTfName2(ComboBox tfName2) {
//		this.tfName2 = tfName2;
//	}

//	public TextField gettfPhone2() {
//		return tfPhone2;
//	}
//
//	public void settfPhone2(TextField tfPhone2) {
//		this.tfPhone2 = tfPhone2;
//	}
//
//	public TextField gettfEmail2() {
//		return tfEmail2;
//	}
//
//	public void settfEmail2(TextField tfEmail2) {
//		this.tfEmail2 = tfEmail2;
//	}

	
	
//	public TextArea gettfComment() {
//		return tfComment;
//	}
//
//	public void settfComment(TextArea tfComment) {
//		this.tfComment = tfComment;
//	}

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
