package View;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import Controller.MainClass;
import Model.Contact;
import Model.Customer;
import Model.User;
import db.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class CustomersManageController implements Initializable{
	
	@FXML Button Back;
	
	@FXML Button Add;
	
	@FXML Button Remove;
	
	@FXML Button fromExcel;
	
	@FXML private TableView<Customer> customersTable;
	
	@FXML private TableColumn<Customer,Integer> id;
	
	@FXML private TableColumn<Customer,String> customerName;
	
	@FXML private TableColumn<Customer,String> adress;
	
	@FXML private TableColumn<Customer,String> comment;
	
	@FXML private TableColumn<Customer, ArrayList<Contact>> contacts;
	
	@FXML private TableColumn<Customer,Integer> ordersAmount;
	
	@FXML private ObservableList<Customer> Customers;
	
	@FXML private DB DBC;
	
	
	@FXML private void goBackToMainMenu(){
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/MainMenuScreen.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			
			MainClass.getPrimaryStage().setScene(appSetScene);
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setFullScreen(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		DBC = new DB();
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
		comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
		contacts.setCellValueFactory(new PropertyValueFactory<>("contacts"));
		ordersAmount.setCellValueFactory(new PropertyValueFactory<>("ordersAmount"));
		
		loadDataFromDB();
	}
	
	@FXML private void loadDataFromDB(){
		
		this.Customers = FXCollections.observableArrayList();
		
		//DBC.setConnection();
		
		HashMap<Integer, Customer> rs = DBC.getAllCustomers();
		
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
			
			MainClass.getPrimaryStage().setScene(appSetScene);
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setFullScreen(true);
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
        	int del_id = del.getId();
        	if(DBC.deleteCustomer(del_id)) {
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
        alert.setTitle("Confirm before delete");
        alert.setHeaderText("Customer Action");
        alert.setContentText("Are you sure yo want to change the customre from the system?");
        alert.showAndWait();
		
	}
	
	@FXML private void exportFromExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.showOpenDialog(MainClass.getPrimaryStage());
	}
}
