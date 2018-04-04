package View;

import java.io.IOException;

import javax.swing.JOptionPane;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Customer;
import Model.JobRole;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.AnchorPane;


public class LoginScreenController {
	
	@FXML private ComboBox<String> userList;
	@FXML private PasswordField password;
	@FXML private Button Login;
	
	  //ControllerLogic reference pointer
  	private static ControllerLogic controller;
  	
  	private User currentUser;
  	
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
		  currentUser = new User();
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
	 
	 @FXML private void openResrorePasswordWindow() {
		 //JOptionPane.showMessageDialog(null, "ok");
		 if(userList.getSelectionModel().isEmpty()) {
			 //Alert alert = new Alert(AlertType.INFORMATION);
			 //alert.setContentText("Select User");
			 JOptionPane.showMessageDialog(null, "thank you for using java");
		 }
		 else {	 
			 try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainMenuController.class.getResource("/View/RestorePassword.fxml"));
					AnchorPane appSet = loader.load();
					Scene appSetScene = new Scene(appSet);
					  RestorePasswordController cont = 
							    loader.<RestorePasswordController>getController();
							  cont.initData(currentUser);
					MainClass.getPrimaryStage().setScene(appSetScene);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
	 }
	 
	 @FXML private void setSelectedUser() {
		 String selected_text = userList.getSelectionModel().getSelectedItem();
		 currentUser = controller.getUserByUsername(selected_text);
		 }

	
}
