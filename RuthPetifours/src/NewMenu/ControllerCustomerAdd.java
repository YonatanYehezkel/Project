package NewMenu;

import java.time.LocalDate;
import java.util.ArrayList;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Contact;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControllerCustomerAdd {
	
//	/* DELIVERYADRESS - NESTED CONTROLLER */
//	@FXML private ControllerDeliveryAdress deliveryAdressController; //fx:id + 'Controller'

	
	/* NOTES */
	@FXML private TextArea taNotes;
	
	/* LAST CHANGE */
	@FXML private Label lblLastChange;
	
	/* BUTTONS */
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	@FXML private Button btnClear;
	
//	@FXML private Button btnBillingAdd;
//	@FXML private Button btnBillingDelete;
	
	
	////////////////////////////////////////
	
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
	@FXML private Label messageC1;

	/* CONTACT2 DATA */

	@FXML private TextField tfTitle2;
	@FXML private TextField tfName2;
	@FXML private TextField tfPhone2;
	@FXML private TextField tfEmail2;
	
	@FXML private TextArea tfComment;
	@FXML private Label messageC2;
	
	/* BILLING */
	private int billingID;
	
	/* BUTTONS */
	@FXML private Button btnCustomerSearch;
	@FXML private HBox hboxBtnSearchSmall;
	
	private int selectedCustomerID;
	
	private ControllerLogic controller;
	
	///////////////////////////////////////
	
	private Stage stage;
	private Customer createdCustomer = null;
	
	public ControllerCustomerAdd(){}
	
	@FXML private void initialize(){
		
		controller = new ControllerLogic(); 
		
		//deliveryAdressController.showSearchButtonSmall(true);
		//deliveryAdressController.enableFields();
		
		initBtnSave();
		initBtnAbort();
		initBtnClear();
		
		initBtnCustomerSearch();
		
		initTextFields();
		
		enableFields();
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
				clearFields();
				searchCompany();
			}
		});
		
	}
	
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
		if(s.getZipcode() != 0)
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
			if(arr.get(0).getPhoneNumber1() != 0)
				tfPhone1.setText(String.valueOf(arr.get(0).getPhoneNumber1()));
			tfEmail1.setText(arr.get(0).getEmail1());
			
			if(arr.size()>1) {
				/*second contact */
				tfTitle2.setText(arr.get(1).getJobRole());
				tfName2.setText(arr.get(1).getContactName());
				if (arr.get(1).getPhoneNumber1() != 0)
					tfPhone2.setText(String.valueOf(arr.get(1).getPhoneNumber1()));
				tfEmail2.setText(arr.get(1).getEmail1());
			}
		}
		
	}
	
	private void initBtnSave(){
	
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(tfCustomerID.getText().isEmpty()
						|| tfStreet.getText().isEmpty()
						|| tfLocation.getText().isEmpty()
						) {
					message.setVisible(true);
					
				}
				if (tfName1.getText().isEmpty() && (!tfTitle1.getText().isEmpty() || !tfPhone1.getText().isEmpty() || !tfEmail1.getText().isEmpty())) {
					messageC1.setVisible(true);
				}
				
				if (tfName2.getText().isEmpty() && (!tfTitle2.getText().isEmpty() ||  !tfPhone2.getText().isEmpty() || !tfEmail2.getText().isEmpty())) {
					messageC2.setVisible(true);
				}
				
				else {
					
					/* address */
					String ad = tfStreet.getText() +" " 
							+ tfLocation.getText() + " " 
							+ tfZip.getText();
					
					/* comment */
//					String comment = null;
//					if(!tfComment.getText().isEmpty()) {
//						comment = tfComment.getText();
//					}
					
					String comment = null;
					
					
					/* zipcode */
					int zipcode = 0;
					if(!tfZip.getText().isEmpty()) {
						try {
							zipcode = Integer.valueOf(tfZip.getText());
						}
						catch(NumberFormatException e) {
							zipcode = 0;
						}
						
					}
					
					/* phone1 */
					
					String phone1 = null;
					if(tfPhone.getText() != null) {
						phone1 = tfPhone.getText();
					}
					
					/* phone2 */
					String phone2 = null;
					if(tfMobile.getText() != null) {
						phone2 = tfMobile.getText();
					}
					
					/* fax */
					String fax = null;
					if(tfFax.getText() != null) {
						fax = tfFax.getText();
					}
					
					/* email */
					String email = null;
					if(tfEmail.getText() != null) {
						email = tfEmail.getText();
					}
					
					/* web */
					String web = null;
					if(tfWeb.getText() != null) {
						web = tfWeb.getText();
					}
					
					/* first contact */
					String jobrole1 = null;
					if(tfTitle1.getText() != null) {
						jobrole1 = tfTitle1.getText();
					}
					String name1 = null;
					if(tfName1.getText() != null) {
						name1 = tfName1.getText();
					}
					int phonenumber1 = 0;
					if(tfPhone1.getText() != null && tfPhone1.getText() != "" ) {
						try {
							phonenumber1 = Integer.valueOf(tfPhone1.getText());
						}
						catch(NumberFormatException e) {
							phonenumber1 = 0;
						}
					}
					String email1 = null;
					if(tfEmail1.getText() != null) {
						email1 = tfEmail1.getText();
					}
					
					
					/* second contact */
					String jobrole2 = null;
					if(tfTitle2.getText() != null ) {
						jobrole2 = tfTitle2.getText();
					}
					String name2 = null;
					if(tfName2.getText() != null) {
						name2 = tfName2.getText();
					}
					int phonenumber2 = 0;
					if(tfPhone2.getText() != null) {
						try {
							phonenumber2 = Integer.valueOf(tfPhone2.getText());
						}
						catch(NumberFormatException e) {
							phonenumber2 = 0;
						}
						
					}
					String email2 = null;
					if(tfEmail2.getText() != null) {
						email2 = tfEmail2.getText();
					}
					
					createdCustomer = new Customer (tfCustomerID.getText(), ad, comment,
							tfLocation.getText(), tfStreet.getText(),
							zipcode, phone1, phone2, fax, email, web);
					
					Contact contact1 = new Contact (name1, phonenumber1, email1, jobrole1, tfCustomerID.getText());
					Contact contact2 = new Contact (name2, phonenumber2, email2, jobrole2, tfCustomerID.getText());
					
					if(contact1.getContactName() != null)
						createdCustomer.contacts.add(contact1);
					if(contact2.getContactName() != null)
						createdCustomer.contacts.add(contact2);
					
					createdCustomer.getCustomerName();
					
					if(controller.addNewCustomer(createdCustomer)) {
						
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.initOwner(MainClass.getPrimaryStage());
			            alert.setTitle("New Customer has been added successfuly");
			            alert.setHeaderText("Customer Action");
			            alert.setContentText("New Customer has been added successfulyl");
			            alert.showAndWait();
					}
					else {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.initOwner(MainClass.getPrimaryStage());
			            alert.setTitle("There was a problem adding new customer");
			            alert.setHeaderText("Customer Action");
			            alert.setContentText("There was a problem adding new customer");
			            alert.showAndWait();
					}
					

				}
				
			}
			
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(MainClass.getPrimaryStage());
		        alert.setHeaderText("Confirm before closing.");
		        alert.setContentText("Are you sure yo want to close this window without saving?");
		        alert.showAndWait();
		        
		        if(alert.getResult().getText().equals("OK")) {
		        	
		        	stage.close();
		        }
								
			}
		});
		
	}
	
private void initBtnClear(){
		
		btnClear.setGraphic(new GraphicButton("clear_32.png").getGraphicButton());
		btnClear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				clearFields2();
				
			}
		});
		
	}
		

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


public void clearFields(){
	
	//tfCustomerID.clear();
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

public void clearFields2(){
	
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
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public Customer getCreatedCustomer(){
		return createdCustomer;
	}
	
//	public ControllerDeliveryAdress getControllerDeliveryAdress() {
//		return deliveryAdressController;
//	}
	
}
