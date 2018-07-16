package View;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import Controller.ControllerLogic;
import Controller.MainClass;

import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class CustomersManageController implements Initializable{
	
	@FXML Button Back;
	@FXML Button Add;
	@FXML Button Remove;
	@FXML Button fromExcel;
	@FXML Button Edit;
	
	@FXML private TableView<Customer> customersTable;
	@FXML private TableColumn<Customer,String> customerName;
	@FXML private TableColumn<Customer,String> adress;
	@FXML private TableColumn<Customer,String> comment;
	//@FXML private TableColumn<Customer, ArrayList<Contact>> contacts;
	@FXML private ObservableList<Customer> Customers;
	
	@FXML Label cur_user;

	private User currentUser;
	private ControllerLogic controller;
	
	public void initData(User u) {
		this.currentUser = u;
		cur_user.setText(currentUser.getUserName());
	  }


	@FXML private void goBackToMainMenu(){
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/MainMenuScreen.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			MainMenuController cont = 
				    loader.<MainMenuController>getController();
				  cont.initData(currentUser);
			
			MainClass.getPrimaryStage().setScene(appSetScene);
			MainClass.getPrimaryStage().setFullScreenExitHint("");
			MainClass.getPrimaryStage().setMaximized(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		controller = new ControllerLogic();
		
		customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
		comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
		//contacts.setCellValueFactory(new PropertyValueFactory<>("contacts"));
		
		loadDataFromDB();
		
		controller.setShadowEffect(Back);
		controller.setShadowEffect(Add);
		controller.setShadowEffect(Remove);
		controller.setShadowEffect(fromExcel);
		controller.setShadowEffect(Edit);
		
		customersTable.setOnMouseClicked(( event) -> {
	        if (event.getButton().equals(MouseButton.SECONDARY)) {
	            //hidePopup();
	        	showDetails(customersTable.getSelectionModel().getSelectedItem());
	        	}
	    });
            
    	
	}
	
	@FXML private void loadDataFromDB(){
		
		this.Customers = FXCollections.observableArrayList();
		
		//DBC.setConnection();
		
		HashMap<String, Customer> rs = controller.getAllCustomers();
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		customers.addAll(rs.values());
		
		for(Customer c : customers) {
			Customers.add(c);
		}
		
		customersTable.setItems(Customers);
	}
	
	@FXML private void goToAddScreen(){		
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/AddNewCustomer.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			AddNewCustomerController cont = 
				    loader.<AddNewCustomerController>getController();
				  cont.initData(currentUser);
			Stage s = new Stage ();
			s.setScene(appSetScene);
			s.setFullScreenExitHint("");
			s.show();
			s.setMaximized(true);
			
				  //MainClass.getPrimaryStage().setScene(appSetScene);
					//MainClass.getPrimaryStage().setFullScreenExitHint("");
					//MainClass.getPrimaryStage().show();
					//MainClass.getPrimaryStage().setMaximized(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML private void removeCustomer() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(MainClass.getPrimaryStage());
        alert.setTitle("Confirm before delete");
        alert.setHeaderText("Customer Action");
        alert.setContentText("Are you sure yo want to remove the customre from the system?");
        alert.showAndWait();
       
        if(alert.getResult().getText().equals("OK")) {
        	Customer del = customersTable.getSelectionModel().getSelectedItem();
        	String del_id = del.getCustomerName();
        	if(controller.deleteCustomer(del_id)) {
        		int selectedIndex = customersTable.getSelectionModel().getSelectedIndex();
        	    if (selectedIndex >= 0) {
        	    	customersTable.getItems().remove(selectedIndex);
        	    }
        	    
        	    Alert alert2 = new Alert(AlertType.INFORMATION);
        	    alert2.initOwner(MainClass.getPrimaryStage());
        	    alert2.setTitle("Delete Confirmation");
        	    alert2.setHeaderText("Customer Action");
        	    alert2.setContentText("Selected customer has been successfully removed");
        	    alert2.showAndWait();	    	
        	}
        }
	}
	
	@FXML private void editCustomerDetails() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(MainClass.getPrimaryStage());
        alert.setTitle("Confirm before changes");
        alert.setHeaderText("Customer Action");
        alert.setContentText("Are you sure yo want to change this customer's details?");
        alert.showAndWait();
		
        if(alert.getResult().getText().equals("OK")) {
        	
        	//customersTable.getSelectionModel().selectedItemProperty().addListener(
                    //(observable, oldValue, newValue) -> showPersonDetails(newValue));
        	
        	customersTable.setEditable(true);
        	Customer edit = customersTable.getSelectionModel().getSelectedItem();
        	//int edit_id = edit.getId();
        	
        	if(controller.updateCustomer(edit)) {
        		
        		loadDataFromDB();
        		
        	    Alert alert2 = new Alert(AlertType.INFORMATION);
        	    alert2.initOwner(MainClass.getPrimaryStage());
        	    alert2.setTitle("Edit Confirmation");
        	    alert2.setHeaderText("Customer Action");
        	    alert2.setContentText("Selected customer's details has been successfully updated");
        	    alert2.showAndWait();
        	    
            	//customersTable.setEditable(false);

        	}
        }
        
	}
	
	private void showDetails(Customer c) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/ShowCustomerDetails.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			ShowCustomerDetails cont = 
				    loader.<ShowCustomerDetails>getController();
				  cont.initData(c);
			Stage s = new Stage();
		   
			s.setScene(appSetScene);
			s.setFullScreenExitHint("");
			//s.setMaximized(true);
			s.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML private void exportFromExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a file for import");
		ExtensionFilter filter = new ExtensionFilter("Excel", "*.xlsx", "*.xls");
		fileChooser.getExtensionFilters().add(filter);
		File f = null;
		f = fileChooser.showOpenDialog(MainClass.getPrimaryStage());
		if(f != null)
			controller.importCustomersFromExcel(f);
	}
	
	@FXML private void logOut() {
		 System.exit(0);
	 }
}
