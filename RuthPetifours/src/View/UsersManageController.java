package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Customer;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class UsersManageController implements Initializable{
	
	@FXML Button Back;
	
	@FXML Button Add;
	
	@FXML Button Remove;
	
	@FXML Button Edit;
	
	@FXML private TableView<User> usersTable;
	
	@FXML private TableColumn<User,Integer> id;
	
	@FXML private TableColumn<User,String> userName;
	
	//@FXML private TableColumn<User,String> adress;
	
	@FXML private TableColumn<User,Integer> idJobRole;
	
	@FXML private ObservableList<User> Users;
		
	@FXML Label cur_user;

	private User currentUser;
		
	private ControllerLogic controller;
	
	public void initData(User u) {
		this.currentUser = u;
		cur_user.setText(currentUser.getUserName());
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
			MainClass.getPrimaryStage().setMaximized(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = new ControllerLogic();
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		idJobRole.setCellValueFactory(new PropertyValueFactory<>("idJobRole"));
		
		loadDataFromDB();
		
		controller.setShadowEffect(Back);
		controller.setShadowEffect(Add);
		controller.setShadowEffect(Remove);
		controller.setShadowEffect(Edit);
	}
	
	@FXML private void loadDataFromDB(){
		
		this.Users = FXCollections.observableArrayList();
		
		
		HashMap<String, User> rs = controller.getAllUsers();
		
		ArrayList<User> users = new ArrayList<User>();
		
		users.addAll(rs.values());
		
		for(User u : users) {
			Users.add(u);
		}
		
		usersTable.setItems(Users);
	}
	
	@FXML private void goToAddScreen(){		
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/AddNewUserScreen.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			AddNewUserController cont = 
				    loader.<AddNewUserController>getController();
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
	
	@FXML private void removeUser() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(MainClass.getPrimaryStage());
        alert.setTitle("Confirm before delete");
        alert.setHeaderText("User Action");
        alert.setContentText("Are you sure yo want to remove the user from the system?");
        alert.showAndWait();
       
        if(alert.getResult().getText().equals("OK")) {
        	User del = usersTable.getSelectionModel().getSelectedItem();
        	int del_id = del.getId();
        	if(controller.deleteUser(del_id)) {
        		int selectedIndex = usersTable.getSelectionModel().getSelectedIndex();
        	    if (selectedIndex >= 0) {
        	    	usersTable.getItems().remove(selectedIndex);
        	    }
        	    
        	    Alert alert2 = new Alert(AlertType.INFORMATION);
        	    alert2.initOwner(MainClass.getPrimaryStage());
        	    alert2.setTitle("Delete Confirmation");
        	    alert2.setHeaderText("User Action");
        	    alert2.setContentText("Selected user has been successfully removed");
        	    alert2.showAndWait();	    	
        	}
        }
	}
	
}
