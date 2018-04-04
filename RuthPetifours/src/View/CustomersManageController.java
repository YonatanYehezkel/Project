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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CustomersManageController implements Initializable{
	
	@FXML Button Back;
	
	@FXML Button Add;
	
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
}
