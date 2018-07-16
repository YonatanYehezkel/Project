package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import Controller.ControllerLogic;
import Controller.MainClass;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class LoginScreenController implements Initializable {
	
	@FXML private ComboBox<String> userList;
	@FXML private PasswordField password;
	@FXML private Button Login;
	@FXML private Text errorUser;
	@FXML private Text errorPassword;
	@FXML private GridPane gridPane;
	@FXML private AnchorPane anchorPane;
	@FXML private GridPane grid;
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
	
  	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillComboBox();
		currentUser = new User();
		errorUser.setVisible(false);
	  	errorPassword.setVisible(false);
	  	
	  	
	  	gridPane.setAlignment(Pos.CENTER);
	  	
	  	gridPane.setHgap(10);
	  	gridPane.setVgap(12);

	  	ColumnConstraints column1 = new ColumnConstraints();
	  	column1.setHalignment(HPos.RIGHT);
	  	gridPane.getColumnConstraints().add(column1); 

	  	ColumnConstraints column2 = new ColumnConstraints();
	  	column2.setHalignment(HPos.LEFT);
	  	gridPane.getColumnConstraints().add(column2); 
	  	
	  	//newgrid();
	  	
	  	
	  	
	   }
	
  	private void newgrid() {
  		System.out.println("FFFFFFFFFFFFF");
  	
         grid.setAlignment(Pos.CENTER);
         grid.setHgap(10);
         grid.setVgap(10);
         grid.setPadding(new Insets(25, 25, 25, 25));

         Text scenetitle = new Text("Welcome");
         scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
         grid.add(scenetitle, 0, 0, 2, 1);

         Label userName = new Label("User Name:");
         grid.add(userName, 0, 1);

         TextField userTextField = new TextField();
         grid.add(userTextField, 1, 1);

         Label pw = new Label("Password:");
         grid.add(pw, 0, 2);

         PasswordField pwBox = new PasswordField();
         grid.add(pwBox, 1, 2);

         Button btn = new Button("Sign in");
         HBox hbBtn = new HBox(10);
         hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
         hbBtn.getChildren().add(btn);
         grid.add(hbBtn, 1, 4);

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
				MainClass.getPrimaryStage().setMaximized(true);
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
