package NewMenu;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Customer;
import Model.Order;
import View.MainMenuController;
import View.ShowCustomerDetails;
//import de.michaprogs.crm.DeleteAlert;
//import de.michaprogs.crm.GraphicButton;
//import de.michaprogs.crm.Main;
//import de.michaprogs.crm.Validate;
//import de.michaprogs.crm.contact.data.ControllerContactData;
//import de.michaprogs.crm.customer.DeleteCustomer;
//import de.michaprogs.crm.customer.ModelCustomer;
//import de.michaprogs.crm.customer.SelectCustomer;
//import de.michaprogs.crm.customer.UpdateCustomer;
//import de.michaprogs.crm.customer.ValidateCustomerSave;
//import de.michaprogs.crm.customer.add.LoadCustomerAdd;
//import de.michaprogs.crm.customer.search.LoadCustomerSearch;
//import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
//import de.michaprogs.crm.customer.ui.deliveryadress.ControllerDeliveryAdress;
//import de.michaprogs.crm.documents.deliverybill.ModelDeliverybill;
//import de.michaprogs.crm.documents.deliverybill.SelectDeliverybill;
//import de.michaprogs.crm.documents.deliverybill.SelectDeliverybill.DeliverybillSelection;
//import de.michaprogs.crm.documents.deliverybill.add.LoadDeliverybillAdd;
//import de.michaprogs.crm.documents.deliverybill.data.LoadDeliverybillData;
//import de.michaprogs.crm.documents.invoice.ModelInvoice;
//import de.michaprogs.crm.documents.invoice.SelectInvoice;
//import de.michaprogs.crm.documents.invoice.SelectInvoice.InvoiceSelection;
//import de.michaprogs.crm.documents.invoice.add.LoadInvoiceAdd;
//import de.michaprogs.crm.documents.invoice.data.LoadInvoiceData;
//import de.michaprogs.crm.documents.offer.ModelOffer;
//import de.michaprogs.crm.documents.offer.SelectOffer;
//import de.michaprogs.crm.documents.offer.SelectOffer.OfferSelection;
//import de.michaprogs.crm.documents.offer.add.LoadOfferAdd;
//import de.michaprogs.crm.documents.offer.data.LoadOfferData;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

public class ControllerCustomerData {

	@FXML private Label lblSubHeadline;
	

	/* NOTES */
	@FXML private TextArea taNotes;
	@FXML private TextField tfSupplierID;
	@FXML private TextField tfPhone;
	@FXML private TextField cbPayment;
	
	/* LAST CHANGE */
	@FXML private Label lblLastChange;
	
	/* OFFER */
	@FXML private TableView<Customer> tvOffer;
	
	@FXML private TableColumn<Customer,String> tcOfferClerk;
	@FXML private TableColumn<Customer,String> tcOfferRequest;
	@FXML private TableColumn<Customer,String> tcOfferDate;

	private ObservableList<Customer> Customers;
	


	/* BUTTONS */
	//@FXML private Button btnSearch;
	@FXML private Button btnNew;
	//@FXML private Button btnEdit;
	      private Button btnEditSave = new Button("Speichern");
	      private Button btnEditAbort = new Button("Abbrechen");
	@FXML private Button btnDelete;
	@FXML private Button btnImport;
	@FXML private Button btnSearch;
	@FXML private Button btnRemovefilters;
	@FXML private Button btnRefresh;
	
	@FXML private HBox hboxBtnTopbar;
	
	private Stage stage;
	private NewMenu main;
	
	private ControllerLogic controller;
	
	public ControllerCustomerData(){}
	
	@FXML private void initialize(){
		
		controller = new ControllerLogic(); 

		
		/* BUTTONS */
		
		initBtnNew();
		//initBtnEdit();
		initBtnEditSave();
		initBtnEditAbort();
		initBtnDelete();
		initBtnImport();
		initBtnRemovefilters();
		initBtnSearch();
		initBtnRefresh();
		
		/* TABLES */
		initTableOffer();
		
		
		loadDataFromDB();
		
		setButtonState();
		
		tvOffer.setOnMouseClicked(( event) -> {
	        if (event.getButton().equals(MouseButton.SECONDARY)) {
	            
	        	LoadCustomerAdd customerAdd = new LoadCustomerAdd(tvOffer.getSelectionModel().getSelectedItem());
	        	}
	    });
		
	}
	

	
	/*
	 * BUTTONS
	 */

	
	private void initBtnRefresh() {
		btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						loadDataFromDB();
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.initOwner(MainClass.getPrimaryStage());
			            alert.setContentText("Table was refreshed");
			            alert.showAndWait();
					}
				});
	}
	
	private void initBtnRemovefilters(){
		
		btnRemovefilters.setStyle("-fx-background-color: #669999;");
		//btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnRemovefilters.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				resetAllFields();
				loadDataFromDB();
				
			}
		});
		
	}
	private void initBtnSearch(){
		
	
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				loadNewDataFromDB();
		
			}
		});
		
	}
	private String revertString(String s) {
		if(s.isEmpty())
			s = "%";
		return s;
	}
	
	private void loadNewDataFromDB(){
		
		HashMap<String, Customer> rs = null;
		
		this.Customers = FXCollections.observableArrayList();
		
		
		
		rs = controller.searchCustomers(revertString(tfSupplierID.getText()), revertString(tfPhone.getText()), revertString(cbPayment.getText()));
				//HashMap<String, Order> rs = new HashMap<String, Order>();
				//ArrayList<Customer> customers = new ArrayList<Customer>();
				
				
				
				ArrayList<Customer> customers = new ArrayList<Customer>();
				if(rs != null) {
					customers.addAll(rs.values());
				}
				
				for(Customer c : customers) {
					Customers.add(c);
				}
				
				tvOffer.setItems(Customers);
	}

	private void resetAllFields(){
		
		this.cbPayment.clear();
		this.tfPhone.clear();
		this.tfSupplierID.clear();
		
		loadDataFromDB();
		

	

	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("edit_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadCustomerAdd customerAdd = new LoadCustomerAdd(true);
				if(customerAdd.getController().getCreatedCustomer() != null){
					Customers.add(customerAdd.getController().getCreatedCustomer());
					//selectCustomer(customerAdd.getController().getCreatedCustomerID());
				}
				
			}
		});
		
	}
	
	private void initBtnImport(){
		
		btnImport.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnImport.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select a file for import");
				ExtensionFilter filter = new ExtensionFilter("Excel", "*.xlsx", "*.xls", "csv");
				fileChooser.getExtensionFilters().add(filter);
				File f = null;
				f = fileChooser.showOpenDialog(MainClass.getPrimaryStage());
				if(f != null)
					controller.importCustomersFromExcel(f);
					loadNewDataFromDB();
			}
		});
		
	}
	
//	private void initBtnEdit(){
//		
//		btnEdit.setGraphic(new GraphicButton("edit_32.png").getGraphicButton());
//		btnEdit.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				
//				if(tvOffer.getSelectionModel().getSelectedItem() != null) {
//					LoadCustomerAdd customerAdd = new LoadCustomerAdd(tvOffer.getSelectionModel().getSelectedItem());
//				}
//				else {
//					Alert alert = new Alert(AlertType.WARNING);
//					alert.initOwner(MainClass.getPrimaryStage());
//		        
//		            alert.setContentText("Select a customer for editing.");
//		            alert.showAndWait();
//				}
//				
//				
//			
//
//				
//				enableFields();				
//				setButtonState();
//				
//			}
//		});		
//		
//	}
	
	private void initBtnEditSave(){
		
		btnEditSave.getStyleClass().add("btnTopbar");
		btnEditSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnEditSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				

			}
			
		});		
	}
	
	private void initBtnEditAbort(){
		
		btnEditAbort.getStyleClass().add("btnTopbar");
		btnEditAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnEditAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				hboxBtnTopbar.getChildren().remove(btnEditAbort);
				hboxBtnTopbar.getChildren().remove(btnEditSave);
				
				disableFields();
				setButtonState();

				//Reload CustomerData
				//selectCustomer(new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));				
				
			}
		});
		
	}
	
	private void initBtnDelete(){
		
		btnDelete.setGraphic(new GraphicButton("delete_32.png").getGraphicButton());
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(tvOffer.getSelectionModel().getSelectedItem() != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.initOwner(MainClass.getPrimaryStage());
			        alert.setHeaderText("Confirm before delete");
			        alert.setContentText("Are you sure yo want to remove the customer from the system?");
			        alert.showAndWait();
			       
			        if(alert.getResult().getText().equals("OK")) {
			        	Customer del = tvOffer.getSelectionModel().getSelectedItem();
			        	String del_id = del.getCustomerName();
			        	if(controller.deleteCustomer(del_id)) {
			        		int selectedIndex = tvOffer.getSelectionModel().getSelectedIndex();
			        	    if (selectedIndex >= 0) {
			        	    	tvOffer.getItems().remove(selectedIndex);
			        	    }
			        	    
			        	    Alert alert2 = new Alert(AlertType.INFORMATION);
			        	    alert2.initOwner(MainClass.getPrimaryStage());
			        	  
			        	    alert2.setHeaderText("Delete Confirmation");
			        	    alert2.setContentText("Selected customer has been successfully removed.");
			        	    alert2.showAndWait();	    	
			        	}
			        }
				}
				else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(MainClass.getPrimaryStage());
		        
		            alert.setContentText("Select a customer for deletion.");
		            alert.showAndWait();
				}
			}
		});
		
	}
	
	
	
	/*
	 * TABLES
	 */
	private void initTableOffer(){
		
	
		
		tcOfferClerk.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		tcOfferRequest.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tcOfferDate.setCellValueFactory(new PropertyValueFactory<>("comment"));
		
		tvOffer.setContextMenu(new ContextMenuTableOffer());
		
		
		
		
		tvOffer.prefHeightProperty().bind(NewMenu.getStage().heightProperty());
		tvOffer.prefWidthProperty().bind(NewMenu.getStage().widthProperty());
        
		tvOffer.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					goToOffer();
				}
				
			}
		});
		
	}
	
	private void loadDataFromDB(){
		
		this.Customers = FXCollections.observableArrayList();
		
		
		HashMap<String, Customer> rs = controller.getAllCustomers();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.addAll(rs.values());
		
		for(Customer c : customers) {
			Customers.add(c);
			
		}
		
		tvOffer.setItems(Customers);
	}
		

	
		

	
	/*
	 * DATABASE METHODS
	 */

	

	
	/*
	 * UI METHODS
	 */
	private void enableFields(){
		
//		deliveryAdressController.enableFields();
//		taNotes.setEditable(true);
		
	}
	
	private void disableFields(){
		
//		deliveryAdressController.disableFields();		
//		taNotes.setEditable(false);
		
	}
	

			
	private void setButtonState(){
		
//		if(deliveryAdressController.getTfCustomerID().getText().equals("")){
//			
//			btnEdit.setDisable(true);
//			btnDelete.setDisable(true);
//			
//			/* BILLING */
//			billingAdressController.getBtnBillingAdd().setDisable(true);
//			billingAdressController.getBtnBillingDelete().setDisable(true);
//			
//			/* CONTACTS */
//			contactDataController.getButtonContactAdd().setDisable(true);
//			
//		}else{
//			
//			btnEdit.setDisable(false);
//			btnDelete.setDisable(false);
//			
//			//if the hboxBtnTopbar contains btnEditAbort and btnEditSave it means btnEdit was pressed 
//			if(hboxBtnTopbar.getChildren().contains(btnEditAbort) && hboxBtnTopbar.getChildren().contains(btnEditSave)){
//				
//				btnDelete.setDisable(true);
//				btnNew.setDisable(true);
//				btnSearch.setDisable(true);
//				btnEdit.setDisable(true);
//				
//				/* BILLING */
//				billingAdressController.setButtonState();				
//								
//				/* CONTACTS */
//				contactDataController.getButtonContactAdd().setDisable(false);
//				if(contactDataController.getObsListContact().size() > 0){
//					contactDataController.getButtonContactDelete().setDisable(false);
//					contactDataController.getButtonContactEdit().setDisable(false);
//				}
//				
//			}else{
//				
//				btnSearch.setDisable(false);
//				btnNew.setDisable(false);
//				btnEdit.setDisable(false);
//				btnDelete.setDisable(false);
//				
//				/* BILLING */
//				billingAdressController.getBtnBillingAdd().setDisable(true);
//				billingAdressController.getBtnBillingDelete().setDisable(true);
//				
//				/* CONTACTS */
//				contactDataController.getButtonContactAdd().setDisable(true);
//				contactDataController.getButtonContactDelete().setDisable(true);
//				contactDataController.getButtonContactEdit().setDisable(true);
//				
//			}
//		}
	}
	
	private boolean editable(){
		
		if(hboxBtnTopbar.getChildren().contains(btnEditSave)){
			return true;
		}else{
			return false;
		}
		
	}
	
	private void goToOffer(){
		
//		if(tvOffer.getSelectionModel().getSelectedItems().size() == 1){
//			main.getContentPane().setCenter(new LoadOfferData(	false, 
//																tvOffer.getItems().get(tvOffer.getSelectionModel().getSelectedIndex()).getOfferID(), 
//																Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()),
//																main
//											).getContent());				
//		}else{
//			System.out.println("Bitte 1 Zeile markieren!");
//		}
//		
	}
	
	private void goToDeliverybill(){
		
//		if(tvDeliverybill.getSelectionModel().getSelectedItems().size() == 1){
//			
//			main.getContentPane().setCenter(new LoadDeliverybillData(	false, 
//																tvDeliverybill.getItems().get(tvDeliverybill.getSelectionModel().getSelectedIndex()).getDeliverybillID(), 
//																Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()),
//																main
//											).getContent());				
//		}else{
//			System.out.println("Bitte 1 Zeile markieren!");
//		}
		
	}
	
	private void goToInvoice(){
		
//		if(tvInvoice.getSelectionModel().getSelectedItems().size() == 1){
//			
//			main.getContentPane().setCenter(new LoadInvoiceData(	false, 
//																	tvInvoice.getItems().get(tvInvoice.getSelectionModel().getSelectedIndex()).getInvoiceID(), 
//																	Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()),
//																	main)
//																	.getContent());				
//		}else{
//			System.out.println("Bitte 1 Zeile markieren!");
//		}
//		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setMain(NewMenu main){
		this.main = main;
	}

	/*
	 * CONTEXT MENU
	 */
	private class ContextMenuTableOffer extends ContextMenu{
		
		private MenuItem itemGoTo = new MenuItem("Gehe zu..");
		private MenuItem itemNew = new MenuItem("Hinzuf�gen..");
		
		public ContextMenuTableOffer(){
			
			initItemGoTo();
			initItemNew();
			
			this.getItems().addAll(	itemGoTo,
									itemNew);
			
//			this.setOnShowing(new EventHandler<WindowEvent>() {
//
//				@Override
//				public void handle(WindowEvent event) {
//					
//					if(deliveryAdressController.getTfCustomerID().getText().equals("")){
//						itemNew.setDisable(true);
//						itemGoTo.setDisable(true);
//					}else{
//						
//						if(editable()){
//							itemNew.setDisable(true);
//							itemGoTo.setDisable(true);
//						}else{
//						
//							itemNew.setDisable(false);
//							if(tvOffer.getItems().size() > 0){
//								itemGoTo.setDisable(false);
//							}else{
//								itemGoTo.setDisable(true);
//							}
//						}
//					}
//					
//				}
//			});
			
		}
		
		private void initItemGoTo(){
			
			itemGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	
					goToOffer();
				}
			});
			
		}
		
		private void initItemNew(){
			
//			itemNew.setOnAction(new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent event) {
//					LoadOfferAdd offerAdd = new LoadOfferAdd(true, new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));
//					if(offerAdd.getController().getCreatedOfferID() != 0){
//						selectOffer(Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()));
//					}
//				}
//			});
			
		}
		
	}
	
	private class ContextMenuTableDeliverybill extends ContextMenu{
		
		private MenuItem itemGoTo = new MenuItem("Gehe zu..");
		private MenuItem itemNew = new MenuItem("Hinzuf�gen..");
		
		public ContextMenuTableDeliverybill(){
			
			initItemGoTo();
			initItemNew();
			
			this.getItems().addAll(	itemGoTo,
									itemNew);
			
//			this.setOnShowing(new EventHandler<WindowEvent>() {
//
//				@Override
//				public void handle(WindowEvent event) {
//					
//					if(deliveryAdressController.getTfCustomerID().getText().equals("")){
//						itemNew.setDisable(true);
//						itemGoTo.setDisable(true);
//					}else{
//						
//						if(editable()){
//							itemNew.setDisable(true);
//							itemGoTo.setDisable(true);
//						}else{
//						
//							itemNew.setDisable(false);
//							if(tvDeliverybill.getItems().size() > 0){
//								itemGoTo.setDisable(false);
//							}else{
//								itemGoTo.setDisable(true);
//							}
//						}
//					}
//					
//				}
//			});
			
		}
		
		private void initItemGoTo(){
			
			itemGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	
					goToDeliverybill();
				}
			});
			
		}
		
		private void initItemNew(){
			
//			itemNew.setOnAction(new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent event) {
//					LoadDeliverybillAdd DeliverybillAdd = new LoadDeliverybillAdd(true, main, new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));
//					if(DeliverybillAdd.getController().getCreatedDeliverybillID() != 0){
//						selectDeliverybill(Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()));
//					}
//				}
//			});
			
		}
		
	}
	
	private class ContextMenuTableInvoice extends ContextMenu{
		
		private MenuItem itemGoTo = new MenuItem("Gehe zu..");
		private MenuItem itemNew = new MenuItem("Hinzuf�gen..");
		
		public ContextMenuTableInvoice(){
			
			initItemGoTo();
			initItemNew();
			
			this.getItems().addAll(	itemGoTo,
									itemNew);
			
//			this.setOnShowing(new EventHandler<WindowEvent>() {
//
//				@Override
//				public void handle(WindowEvent event) {
//					
//					if(deliveryAdressController.getTfCustomerID().getText().equals("")){
//						itemNew.setDisable(true);
//						itemGoTo.setDisable(true);
//					}else{
//						
//						if(editable()){
//							itemNew.setDisable(true);
//							itemGoTo.setDisable(true);
//						}else{
//						
//							itemNew.setDisable(false);
//							if(tvInvoice.getItems().size() > 0){
//								itemGoTo.setDisable(false);
//							}else{
//								itemGoTo.setDisable(true);
//							}
//						}
//					}
//					
//				}
//			});
			
		}
		
		private void initItemGoTo(){
			
			itemGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	
					goToInvoice();
				}
			});
			
		}
		
		private void initItemNew(){
			
//			itemNew.setOnAction(new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent event) {
//					LoadInvoiceAdd InvoiceAdd = new LoadInvoiceAdd(true, main, new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));
//					if(InvoiceAdd.getController().getCreatedInvoiceID() != 0){
//						selectInvoice(Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()));
//					}
//				}
//			});
			
		}
		
	}
	
	
	
}
