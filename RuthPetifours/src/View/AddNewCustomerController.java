package View;

import java.io.IOException;

import Controller.MainClass;
import Model.Customer;
import db.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

public class AddNewCustomerController {
	
	@FXML private TextField id;
	
	@FXML private TextField name;
	
	@FXML private TextField address;
	
	@FXML private TextField comments;
	
	@FXML private Button Save;
	
	@FXML private Button cancel;
	
	//@FXML private TextField contacts;
	
	private DB DBC = new DB();

	@FXML private void addNewCustomerToDB(){
		
		
		if(!id.getText().isEmpty() && !name.getText().isEmpty() && !address.getText().isEmpty() && !comments.getText().isEmpty()) {
			
			Customer c = new Customer (name.getText(),address.getText(),comments.getText());
			
			if(DBC.addNewCustomer(c)) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(MainClass.getPrimaryStage());
	            alert.setTitle("New Customer has been added successfuly");
	            alert.setHeaderText("Customer Action");
	            alert.setContentText("New Customer has been added successfulyl");
	            alert.showAndWait();
	            
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
			
			else if (!DBC.addNewCustomer(c)) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(MainClass.getPrimaryStage());
	            alert.setTitle("There was a problem adding new customer");
	            alert.setHeaderText("Customer Action");
	            alert.setContentText("There was a problem adding new customer");
	            alert.showAndWait();
				
			}
		}
		
		else {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(MainClass.getPrimaryStage());
            alert.setTitle("There was a problem adding new customer");
            alert.setHeaderText("Customer Action");
            alert.setContentText("Please fill all of the fields before pressing the SAVE button");
            alert.showAndWait();
		}
		
	}
	
	@FXML private void cancelAndGoBack(){
		
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
}
