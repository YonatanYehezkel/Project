package View;

import java.io.IOException;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Customer;
import Model.JobRole;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;


public class LoginScreenController {
	
	@FXML private ComboBox<String> userList;
	@FXML private PasswordField password;
	@FXML private Button Login;
	
	  //ControllerLogic reference pointer
  	private static ControllerLogic controller;
  	
  	/*
  	 * constructor
  	 */
  	public LoginScreenController() {
  		controller = new ControllerLogic();
  	}
	
  	@FXML public void validateUserInput () {
  		//System.out.println(controller.getAllUsers().get(userList.getSelectionModel().getSelectedItem()).getPassword() + " db");
  		//System.out.println(password.getText());
  		if(password.getText().equals(controller.getAllUsers().get(userList.getSelectionModel().getSelectedItem()).getPassword())) {
			System.out.println("ok");
			goToMainMenu();
		}
  		//controller.deleteCustomer("test");
  		//controller.addNewCustomer(new Customer ("test", "test", "test"));
				
	}
	
	  @FXML
	    private void initialize() {
		  fillComboBox();
	   }
	
	 @FXML private void fillComboBox(){
		// System.out.println(controller.getAllUsers().get(1).getUserName());
		 userList.getItems().addAll(controller.getAllUsers().get("TestUser").getUserName());
	    }
	 
	 @FXML private void goToMainMenu(){
		 
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

	
}
