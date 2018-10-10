package NewMenu;

import java.time.LocalDate;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Customer;
import Model.User;
//import de.michaprogs.crm.AbortAlert;
//import de.michaprogs.crm.DeleteAlert;
//import de.michaprogs.crm.GraphicButton;
//import de.michaprogs.crm.InitCombos;
//import de.michaprogs.crm.Validate;
//import de.michaprogs.crm.articlecategory.ModelArticleCategory;
//import de.michaprogs.crm.articlecategory.SelectArticleCategory;
//import de.michaprogs.crm.contact.data.ControllerContactData;
//import de.michaprogs.crm.customer.InsertCustomer;
//import de.michaprogs.crm.customer.ModelCustomer;
//import de.michaprogs.crm.customer.SelectCustomer;
//import de.michaprogs.crm.customer.ValidateCustomerSave;
//import de.michaprogs.crm.customer.category.ModelCustomerCategory;
//import de.michaprogs.crm.customer.category.SelectCustomerCategory;
//import de.michaprogs.crm.customer.search.LoadCustomerSearch;
//import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
//import de.michaprogs.crm.customer.ui.deliveryadress.ControllerDeliveryAdress;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerUserAdd {
	
//	/* DELIVERYADRESS - NESTED CONTROLLER */
	@FXML private ControllerUserInfo userInfoController; //fx:id + 'Controller'
//	
//	/* BILLINGADRESS - NESTED CONTROLLER */
//	@FXML private ControllerBillingAdress billingAdressController; //fx:id + 'Controller'
//	
//	/* CONTACTS - NESTED CONTROLLER */
//	@FXML private ControllerContactData contactDataController; //fx:id + 'Controller'
	
	/* NOTES */
	@FXML private TextArea taNotes;
	
	/* LAST CHANGE */
	@FXML private Label lblLastChange;
	
	/* BUTTONS */
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	@FXML private Button btnClear;

	
	@FXML private Button btnBillingAdd;
	@FXML private Button btnBillingDelete;
	
	private Stage stage;
	private User createdUser = null;
	
	
	
	////////////////////////////
	
	@FXML private TextField tfCustomerID;
	@FXML private TextField tfStreet;
	@FXML private TextField tfLocation;
	
	@FXML private Label message;
	
	/* CONTACT1 DATA */

	@FXML private TextField tfPassword;
	@FXML private TextField tfFSQ;
	@FXML private TextField tfAFSQ;
	@FXML private TextField tfASSQ;
	@FXML private TextField tfSSQ;

	/* CONTACT2 DATA */

	@FXML private ComboBox tfTitle2;

	
	/* BUTTONS */
	@FXML private Button btnCustomerSearch;
	@FXML private HBox hboxBtnSearchSmall;
	
	private int selectedCustomerID;
	
	private ControllerLogic controller;
	
	/////////////////////////////
	
	public ControllerUserAdd(){}
	
	@FXML private void initialize(){
		
		//deliveryAdressController.showSearchButtonSmall(true);
		//userInfoController = new ControllerUserInfo();
		//userInfoController.enableFields();
		
		controller = new ControllerLogic();
		
		initBtnSave();
		initBtnAbort();
		initBtnClear();
		
		
		initBtnCustomerSearch();
		
		initTextFields();
		
		enableFields();
		
		initJobRoleComboBox();
		
	}
	
	private void initBtnCustomerSearch(){
		
		
		btnCustomerSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				searchCompany();
			}
		});
		
	}
	

	
	private void initJobRoleComboBox() {
		tfTitle2.getItems().addAll(controller.getAllJobRoles());
	}
	
	
public void enableFields(){
		
		tfCustomerID.setDisable(false);
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


private void loadCustomer(User u) {
	tfPassword.setText(u.getPassword());
	tfFSQ.setText(u.getQuestion1());
	tfAFSQ.setText(u.getAnswer1());
	tfSSQ.setText(u.getQuestion2());
	tfASSQ.setText(u.getAnswer2());
	
}

	
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
	private void initBtnSave(){
	
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				System.out.println(tfCustomerID.getText() + "kkkkkkkk");
				
				
				if(tfCustomerID.getText().isEmpty()
						|| tfStreet.getText().isEmpty()
						|| tfLocation.getText().isEmpty()
						|| tfPassword.getText().isEmpty()
						|| tfTitle2.getSelectionModel().getSelectedItem() == null
						) {
					message.setText("All mandatory feilds should be filled.");
					message.setTextFill(Color.web("#cc3300"));
					message.setVisible(true);
					
				}
				else if(tfFSQ.getText().isEmpty() && !tfAFSQ.getText().isEmpty()) {
					message.setText("You should fill first security question.");
					message.setTextFill(Color.web("#cc3300"));
					message.setVisible(true);
				}
				
				else if(!tfFSQ.getText().isEmpty() && tfAFSQ.getText().isEmpty()) {
					message.setText("You should fill answer on first security question.");
					message.setTextFill(Color.web("#cc3300"));
					message.setVisible(true);
				}
				
				else if(!tfSSQ.getText().isEmpty() && tfASSQ.getText().isEmpty()) {
					message.setText("You should fill answer on second security question.");
					message.setTextFill(Color.web("#cc3300"));
					message.setVisible(true);
				}
				
				else if(tfSSQ.getText().isEmpty() && !tfASSQ.getText().isEmpty()) {
					message.setText("You should fill second security question.");
					message.setTextFill(Color.web("#cc3300"));
					message.setVisible(true);
				}
				
				else if(tfFSQ.getText().isEmpty() && !tfSSQ.getText().isEmpty()) {
					message.setText("You can't add 2nd question without 1st one.");
					message.setTextFill(Color.web("#cc3300"));
					message.setVisible(true);
				}
				
				else if(tfFSQ.getText().isEmpty() && tfSSQ.getText().isEmpty()) {
					//create user without questions
					if(controller.addNewUser(tfLocation.getText().toString(), tfStreet.getText().toString(), 
							tfCustomerID.getText().toString(), tfPassword.getText().toString(), 
							tfTitle2.getSelectionModel().getSelectedItem().toString())) {
						message.setText("New user was added successfully.");
						message.setTextFill(Color.web("#009933"));
						message.setVisible(true);
					}
				}
				
				else if(!tfFSQ.getText().isEmpty() && tfSSQ.getText().isEmpty()) {
					//create user with 1st question
					
					if(controller.addNewUser(tfLocation.getText().toString(), tfStreet.getText().toString(), 
							tfCustomerID.getText().toString(), tfPassword.getText().toString(), 
							tfTitle2.getSelectionModel().getSelectedItem().toString(), tfFSQ.getText().toString(), tfAFSQ.getText().toString())) {
						message.setText("New user was added successfully.");
						message.setTextFill(Color.web("#009933"));
						message.setVisible(true);
					}
				}
				
				else if (!tfFSQ.getText().isEmpty() && tfSSQ.getText().isEmpty()) {
					//create user with 1st and 2nd questions
				}
				
//				else {
//					String ad = deliveryAdressController.getTfStreet().getText() +" " 
//							+ deliveryAdressController.getTfLocation().getText() + " " 
//							+ deliveryAdressController.getTfZip().getText();
//					
//					String comment = null;
//					if(!deliveryAdressController.gettfComment().getText().isEmpty()) {
//						comment = deliveryAdressController.gettfComment().getText();
//					}
//					createdCustomer = new Customer (deliveryAdressController.getTfCustomerID().getText(), ad, comment);
//					
//					if(deliveryAdressController.checkAdress(ad)) {
//						if(deliveryAdressController.getController().addNewCustomer(createdCustomer)) {
//							
//							Alert alert = new Alert(AlertType.CONFIRMATION);
//							alert.initOwner(MainClass.getPrimaryStage());
//				            alert.setTitle("New Customer has been added successfuly");
//				            alert.setHeaderText("Customer Action");
//				            alert.setContentText("New Customer has been added successfulyl");
//				            alert.showAndWait();
//						}
//						else {
//							Alert alert = new Alert(AlertType.CONFIRMATION);
//							alert.initOwner(MainClass.getPrimaryStage());
//				            alert.setTitle("There was a problem adding new customer");
//				            alert.setHeaderText("Customer Action");
//				            alert.setContentText("There was a problem adding new customer");
//				            alert.showAndWait();
//						}
//					}
//					else {
//						System.out.println("adress not valid");
//					}
//				}
//				
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
		        alert.setContentText("Are you sure you want to close this window?");
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
				
				userInfoController.clearFields();
				
			}
		});
		
	}
		
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public User getCreatedUser(){
		return createdUser;
	}
	
	public ControllerUserInfo getControllerUserInfo() {
		return userInfoController;
	}
	
}
