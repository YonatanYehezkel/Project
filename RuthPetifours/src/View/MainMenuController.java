package View;


import java.io.IOException;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainMenuController {
	
	@FXML Button Orders;
	@FXML Button Customers;
	@FXML Button Drivers;
	@FXML Button Users;
	@FXML Button Vehichles;
	@FXML Button Vehichles1;
	@FXML Button buildRoute;
	@FXML Label cur_user;
	
	
	  //ControllerLogic reference pointer
  	private static ControllerLogic controller;
  	
	private User currentUser;
	
	public void initData(User u) {
		this.currentUser = u;
		cur_user.setText(currentUser.getUserName());
	  }

	@FXML private void initialize() {
	
		controller = new ControllerLogic();
		controller.setShadowEffect(Orders);
		controller.setShadowEffect(Customers);
		controller.setShadowEffect(Drivers);
		controller.setShadowEffect(Users);
		controller.setShadowEffect(Vehichles);
		controller.setShadowEffect(Vehichles1);
		controller.setShadowEffect(buildRoute);
	 }
	
	
	 @FXML private void goToOrders() {
		 
		 try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainMenuController.class.getResource("/View/NewOrdersScreen.fxml"));
				AnchorPane appSet = loader.load();
				Scene appSetScene = new Scene(appSet);
				OrdersManageController cont = 
					    loader.<OrdersManageController>getController();
					  cont.initData(currentUser);
				
				MainClass.getPrimaryStage().setScene(appSetScene);
				MainClass.getPrimaryStage().setFullScreenExitHint("");
				MainClass.getPrimaryStage().setMaximized(true);
				
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
				CustomersManageController cont = 
					    loader.<CustomersManageController>getController();
					  cont.initData(currentUser);
				
				
				MainClass.getPrimaryStage().setScene(appSetScene);
				MainClass.getPrimaryStage().setFullScreenExitHint("");
				MainClass.getPrimaryStage().setMaximized(true);
				
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
				MainClass.getPrimaryStage().setFullScreenExitHint("");
				MainClass.getPrimaryStage().setMaximized(true);
				
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
				UsersManageController cont = 
					    loader.<UsersManageController>getController();
					  cont.initData(currentUser);
				
				MainClass.getPrimaryStage().setScene(appSetScene);
				MainClass.getPrimaryStage().setFullScreenExitHint("");
				MainClass.getPrimaryStage().setMaximized(true);
				
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
				MainClass.getPrimaryStage().setFullScreenExitHint("");
				MainClass.getPrimaryStage().setMaximized(true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	 }
	 
@FXML private void goToBuildRoute() {
		 
		 try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainMenuController.class.getResource("/View/BestRoute.fxml"));
				AnchorPane appSet = loader.load();
				Scene appSetScene = new Scene(appSet);
				BestRouteController cont = 
					    loader.<BestRouteController>getController();
					  cont.initData(currentUser);
				
				MainClass.getPrimaryStage().setScene(appSetScene);
		
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	 }

	@FXML private void goToReportsScreen() {
				
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/ReportsOptionsScreen.fxml‬"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			
			MainClass.getPrimaryStage().setScene(appSetScene);
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setMaximized(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	 @FXML private void logOut() {
		 System.exit(0);
	 }
}
