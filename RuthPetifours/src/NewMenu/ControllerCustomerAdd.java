package NewMenu;

import java.time.LocalDate;

import Controller.MainClass;
import Model.Customer;
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
import javafx.stage.Stage;

public class ControllerCustomerAdd {
	
//	/* DELIVERYADRESS - NESTED CONTROLLER */
	@FXML private ControllerDeliveryAdress deliveryAdressController; //fx:id + 'Controller'
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
	private Customer createdCustomer = null;
	
	public ControllerCustomerAdd(){}
	
	@FXML private void initialize(){
		
		//deliveryAdressController.showSearchButtonSmall(true);
		deliveryAdressController.enableFields();
		
		initBtnSave();
		initBtnAbort();
		initBtnClear();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSave(){
	
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(deliveryAdressController.getTfCustomerID().getText().isEmpty()
						|| deliveryAdressController.getTfStreet().getText().isEmpty()
						|| deliveryAdressController.getTfLocation().getText().isEmpty()
						) {
					deliveryAdressController.getMessage().setVisible(true);
					
				}
				else {
					String ad = deliveryAdressController.getTfStreet().getText() +" " 
							+ deliveryAdressController.getTfLocation().getText() + " " 
							+ deliveryAdressController.getTfZip().getText();
					
					String comment = null;
					if(!deliveryAdressController.gettfComment().getText().isEmpty()) {
						comment = deliveryAdressController.gettfComment().getText();
					}
					createdCustomer = new Customer (deliveryAdressController.getTfCustomerID().getText(), ad, comment);
					
					if(deliveryAdressController.getController().addNewCustomer(createdCustomer)) {
						
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
				
				deliveryAdressController.clearFields();
				
			}
		});
		
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
	
	public ControllerDeliveryAdress getControllerDeliveryAdress() {
		return deliveryAdressController;
	}
	
}
