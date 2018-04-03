package View;

import java.io.IOException;

import Controller.MainClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainMenuController {
	
	@FXML Button Orders;
	
	@FXML Button Customers;
	
	@FXML Button Drivers;
	
	@FXML Button Users;
	
	@FXML Button Vehichles;
	
	@FXML Button LogOut;

	 @FXML private void initialize() {
		 
	 }
	 
	 @FXML private void goToOrders() {
		 
		 try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainMenuController.class.getResource("/View/NewOrdersScreen.fxml"));
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
	 
	 @FXML private void goToCustomers() {
		 
		 try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainMenuController.class.getResource("/View/CustomersManageScreen.fxml"));
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
	 
	 @FXML private void goToDrivers() {
		 
		 try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainMenuController.class.getResource("/View/DriversManageScreen.fxml"));
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
	 
	 @FXML private void goToUsers() {
		 
		 try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainMenuController.class.getResource("/View/UsersManageScreen.fxml"));
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
	 
	 @FXML private void goToVehichles() {
		 
		 try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainMenuController.class.getResource("/View/VehichlesManagment.fxml"));
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
	 
	 @FXML private void logOut() {
		 System.exit(0);
	 }
}
