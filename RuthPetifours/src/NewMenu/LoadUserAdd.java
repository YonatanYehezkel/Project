package NewMenu;


import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoadUserAdd {

	@FXML private AnchorPane root;
	@FXML private ControllerUserAdd controller;
	private Stage stage = new Stage();
	
	public LoadUserAdd(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewUserAdd.fxml"));
			root = loader.load();
			controller = loader.getController();
			
			if(createDialog){
				
				Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setWidth(primaryScreenBounds.getWidth());
			
				
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public LoadUserAdd(User userToAdd){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewUserAdd.fxml"));
			root = loader.load();
			
			controller = loader.getController();
			//loadCustomerToEdit(userToAdd);
			
			
			
				
				Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setWidth(primaryScreenBounds.getWidth());
			
				
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
//	private void loadUserToAdd( customerToEdit) {
//		controller.getControllerDeliveryAdress().getTfCustomerID().setText(customerToEdit.getCustomerName());
//		controller.getControllerDeliveryAdress().getTfStreet().setText(customerToEdit.getAdress());
//		controller.getControllerDeliveryAdress().gettfComment().setText(customerToEdit.getComment());
//	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerUserAdd getController(){
		return controller;
	}
	
}
