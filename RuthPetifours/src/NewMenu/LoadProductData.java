package NewMenu;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadProductData {

	private AnchorPane root;
	private ControllerProductData controller = new ControllerProductData();
	private Stage stage = new Stage();
	
	public LoadProductData(boolean createDialog, NewMenu main){
	
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewProductsData.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			root = loader.load();
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerProductData getController(){
		return controller;
	}
	
}
