package View;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Contact;
import Model.Customer;
import Model.Order;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class OrdersManageController implements Initializable{
	
@FXML private Button Back;
@FXML private Button importProducts;
@FXML private Button orderSorting;
@FXML private Button importOrders;
@FXML private TableView<Order> OrdersTable;
@FXML private TableColumn<Order,String> ordernum;
@FXML private TableColumn<Order,String> customer;
@FXML private TableColumn<Order,String> city;
@FXML private TableColumn<Order,String> value;
@FXML private ObservableList<Order> orders;
@FXML Label cur_user;


private ControllerLogic controller;
	
private User currentUser;
	
	public void initData(User u) {
		this.currentUser = u;
		cur_user.setText(currentUser.getUserName());
	  }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		controller = new ControllerLogic();
		
		ordernum.setCellValueFactory(new PropertyValueFactory<>("id"));
		customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
		city.setCellValueFactory(new PropertyValueFactory<>("adress"));
		value.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		loadDataFromDB();
		
		controller.setShadowEffect(Back);
		controller.setShadowEffect(importProducts);
		controller.setShadowEffect(orderSorting);
		controller.setShadowEffect(importOrders);
		
	}
	
	private void loadDataFromDB(){
		
		this.orders = FXCollections.observableArrayList();
		
		
		HashMap<String, Order> rs = controller.getAllOrders();
		
		ArrayList<Order> orders1 = new ArrayList<Order>();
		
		orders1.addAll(rs.values());
	
		for(Order c : orders1) {
			c.setAdress(controller.getCustomerByName(c.getCustomer()).getAdress());
			c.setValue(controller.getValueOfOrder(c.getId()));
			orders.add(c);
		}
		
		OrdersTable.setItems(orders);
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
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setFullScreen(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML private void importOrderFromExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a file for import");
		ExtensionFilter filter = new ExtensionFilter("Excel", "*.xlsx", "*.xls");
		fileChooser.getExtensionFilters().add(filter);
		File f = fileChooser.showOpenDialog(MainClass.getPrimaryStage());
		
		controller.importOrdersFromExcel(f);
	}
	
	@FXML private void importProductsFromExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a file for import");
		ExtensionFilter filter = new ExtensionFilter("Excel", "*.xlsx", "*.xls");
		fileChooser.getExtensionFilters().add(filter);
		File f = fileChooser.showOpenDialog(MainClass.getPrimaryStage());
		
		controller.importProductsFromExcel(f);
	}
	
	@FXML private void logOut() {
		 System.exit(0);
	 }

}
