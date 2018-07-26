package NewMenu;

import Model.Customer;
//import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoadCustomerAdd {

	@FXML private AnchorPane root;
	@FXML private ControllerCustomerAdd controller;
	private Stage stage = new Stage();
	
	public LoadCustomerAdd(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomerAdd.fxml"));
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
	
	public LoadCustomerAdd(Customer customerToEdit){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomerAdd.fxml"));
			root = loader.load();
			
			controller = loader.getController();
			loadCustomerToEdit(customerToEdit);
			
			
			
				
				Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setWidth(primaryScreenBounds.getWidth());
			
				
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void loadCustomerToEdit(Customer customerToEdit) {
		controller.getControllerDeliveryAdress().getTfCustomerID().setText(customerToEdit.getCustomerName());
		controller.getControllerDeliveryAdress().getTfStreet().setText(customerToEdit.getAdress());
		controller.getControllerDeliveryAdress().gettfComment().setText(customerToEdit.getComment());
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerCustomerAdd getController(){
		return controller;
	}
	
}
