package NewMenu;

//import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadCustomerSearch {

	private AnchorPane root;
	private ControllerCustomerSearch controller;
	private Stage stage = new Stage();
	
	public LoadCustomerSearch(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomerSearch.fxml"));
			root = loader.load();
			controller = loader.getController();
			
			if(createDialog){
				Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setWidth(primaryScreenBounds.getWidth());
				stage.setHeight(primaryScreenBounds.getHeight());
				
				controller.setStage(stage);
				controller.fitTableToStage();
				new CreateDialog("", stage, new Scene(root));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerCustomerSearch getController(){
		return controller;
	}
	
}
