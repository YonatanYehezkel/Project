package View;

import Controller.ControllerLogic;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;


public class LoginScreenController {
	
	@FXML private ComboBox<String> userList;
	@FXML private PasswordField password;
	
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
		}
				
	}
	
	  @FXML
	    private void initialize() {
		  fillComboBox();
	   }
	
	 @FXML private void fillComboBox(){
		 //System.out.println(controller.getAllUsers().get(1).getUserName());
		 userList.getItems().addAll(controller.getAllUsers().get("TestUser").getUserName());
	    }
	 
	

	
}
