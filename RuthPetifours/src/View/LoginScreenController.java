package View;

import java.io.IOException;
import javax.swing.JOptionPane;
import Controller.ControllerLogic;
import Controller.MainClass;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class LoginScreenController {
	
	@FXML private ComboBox<String> userList;
	@FXML private PasswordField password;
	@FXML private Button Login;
	@FXML private Text errorUser;
	@FXML private Text errorPassword;
	
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
  		if (userList.getSelectionModel().isEmpty())
  			errorUser.setVisible(true);
  		else if(password.getText().equals(controller.getAllUsers().get(userList.getSelectionModel().getSelectedItem()).getPassword())) {
			goToMainMenu();
		}
  		else if(password.getText().equals(""))
  			errorPassword.setVisible(true);	
  		else if(!password.getText().equals(controller.getAllUsers().get(userList.getSelectionModel().getSelectedItem()).getPassword())) {
  			errorPassword.setText("password is wrong");
  			errorPassword.setVisible(true);
  		}
  			
	}
  	
  	@FXML public void hideErrorPassword() {
  		errorPassword.setVisible(false);	
  	}
	
	  @FXML
	    private void initialize() {
		  fillComboBox();
		  currentUser = new User();
		  errorUser.setVisible(false);
	  	  errorPassword.setVisible(false);
	   }
	
	 @FXML private void fillComboBox(){
		 userList.getItems().addAll(controller.getAllUsers().keySet());
	    }
	 
	 @FXML private void goToMainMenu(){
		 
		/* try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(OrdersSortingController.class.getResource("/View/OrdersSortingScreen.fxml"));
				AnchorPane appSet = loader.load();
				Scene appSetScene = new Scene(appSet);
				OrdersSortingController cont = 
					    loader.<OrdersSortingController>getController();
					  cont.initData(currentUser);
					  
				MainClass.getPrimaryStage().setScene(appSetScene);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		 
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
				MainClass.getPrimaryStage().setFullScreen(true);
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
			 JOptionPane.showMessageDialog(null, "select User");
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
		 errorUser.setVisible(false);
		 String selected_text = userList.getSelectionModel().getSelectedItem();
		 currentUser = controller.getUserByUsername(selected_text);
		 }

	 
	
}
