package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.JobRole;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class AddNewUserController implements Initializable{
	
	@FXML private TextField name;
	@FXML private TextField password;
	@FXML private TextField question1;
	@FXML private TextField answer1;
	@FXML private TextField question2;
	@FXML private TextField answer2;
	@FXML private ComboBox<JobRole> jobRole = new ComboBox<JobRole>();
	@FXML private ArrayList<JobRole> jobs = new ArrayList<JobRole>();
	@FXML private ObservableList<JobRole> jobsList = FXCollections.observableArrayList(new ArrayList<JobRole>());
	@FXML private Button Save;
	@FXML private Button cancel;	
	@FXML private Button Back;	
	@FXML Label cur_user;

	private User currentUser;
		
	private ControllerLogic controller;
	
	public void initData(User u) {
		this.currentUser = u;
		cur_user.setText(currentUser.getUserName());
	  }
	
	@FXML private void addNewUserToDB(){
		
		
		if(!name.getText().isEmpty() && !password.getText().isEmpty() && !question1.getText().isEmpty() && !answer1.getText().isEmpty() 
				&& !question2.getText().isEmpty() && !answer2.getText().isEmpty() && !jobRole.getValue().getJobRole().equals("")) {
			
			User u = new User (name.getText(), password.getText() , question1.getText() , answer1.getText()
					,question2.getText() ,answer2.getText() ,jobRole.getValue().getId());
			
			if(controller.addNewUser(u)) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(MainClass.getPrimaryStage());
	            alert.setTitle("New User has been added successfuly");
	            alert.setHeaderText("User Action");
	            alert.setContentText("New User has been added successfulyl");
	            alert.showAndWait();
	            
	            try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainMenuController.class.getResource("/View/UsersManageScreen.fxml"));
					AnchorPane appSet = loader.load();
					Scene appSetScene = new Scene(appSet);
					
					MainClass.getPrimaryStage().setScene(appSetScene);
					MainClass.getPrimaryStage().setFullScreenExitHint("");
					MainClass.getPrimaryStage().setFullScreen(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			else if (!controller.addNewUser(u)) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(MainClass.getPrimaryStage());
	            alert.setTitle("There was a problem adding the new user");
	            alert.setHeaderText("User Action");
	            alert.setContentText("There was a problem adding new user");
	            alert.showAndWait();
				
			}
		}
		
		else {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(MainClass.getPrimaryStage());
            alert.setTitle("There was a problem adding the new user");
            alert.setHeaderText("User Action");
            alert.setContentText("Please fill all of the fields before pressing the SAVE button");
            alert.showAndWait();
		}
		
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
			MainClass.getPrimaryStage().setFullScreenExitHint("");
			MainClass.getPrimaryStage().setFullScreen(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

@FXML private void logOut() {
	 System.exit(0);
}

	
	@FXML private void cancelAndGoBack(){
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/UsersManageScreen.fxml"));
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		controller = new ControllerLogic();
		jobs = controller.getAllJobRoles();
		
		for(JobRole j : jobs) {
			jobsList.add(j);
		}
		
		jobRole.setItems(jobsList);
		
		controller.setShadowEffect(Save);
		controller.setShadowEffect(cancel);
		controller.setShadowEffect(Back);
	}

}
