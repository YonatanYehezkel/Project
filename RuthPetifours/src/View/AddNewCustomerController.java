package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class AddNewCustomerController implements Initializable {
	
	//@FXML private TextField id;
	
	@FXML private TextField name;
	@FXML private TextField street;
	@FXML private TextField city;
	@FXML private TextField postCode;
	@FXML private TextField contact1Name;
	@FXML private TextField contact1Phone;
	@FXML private TextField contact1Job;
	@FXML private TextField contact1Email;
	@FXML private TextField contact2Name;
	@FXML private TextField contact2Phone;
	@FXML private TextField contact2Job;
	@FXML private TextField contact2Email;
	@FXML private TextArea comment_new;
	@FXML private Button Save;
	@FXML private Button cancel;
	@FXML private Button Back;
	@FXML Label cur_user;
	
	
	private User currentUser;
	private Customer cus;
	
	private ControllerLogic controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		controller = new ControllerLogic();
		
		controller.setShadowEffect(Save);
		controller.setShadowEffect(cancel);
		controller.setShadowEffect(Back);
		
	}
	
	public void initData(User u) {
		this.currentUser = u;
		cur_user.setText(currentUser.getUserName());
	  }
	
	private String isEmptyField(TextField t) {
		String s;
		if(t.getText().isEmpty())
			s = null;
		else
			s = t.getText();
		return s;
	}
	
	private String isEmptyField(TextArea t) {
		String s;
		if(t.getText().isEmpty())
			s = null;
		else
			s = t.getText();
		return s;
	}
	

	
	private void addNewContact() {
		if(!isEmptyField(contact1Name).isEmpty()){
			Contact c = new Contact(contact1Name.getText(), 
					Integer.valueOf(contact1Phone.getText()), 0, contact1Email.getText()
					, null, contact1Job.getText(), name.getText());
			controller.addNewContact(c, cus);
		}
	}

	@FXML private void addNewCustomerToDB(){
		
		
		String ad = street.getText() +" " + city.getText() + " " + postCode.getText();
	
		
		if(!name.getText().isEmpty()) {
			
			cus = new Customer (name.getText(), ad, isEmptyField(comment_new));
			
			if(controller.addNewCustomer(cus)) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(MainClass.getPrimaryStage());
	            alert.setTitle("New Customer has been added successfuly");
	            alert.setHeaderText("Customer Action");
	            alert.setContentText("New Customer has been added successfulyl");
	            alert.showAndWait();
	            addNewContact();
	            
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
			
			else if (!controller.addNewCustomer(cus)) {
				
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
	
	@FXML private void logOut() {
		 System.exit(0);
	 }
	
@FXML private void goBackToMainMenu(){
		
		/*try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/MainMenuScreen.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			MainMenuController cont = 
				    loader.<MainMenuController>getController();
				  cont.initData(currentUser);
			
			MainClass.getPrimaryStage().setScene(appSetScene);
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setFullScreen(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
	}
}
