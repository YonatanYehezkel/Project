package NewMenu;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadUserData {

	private AnchorPane root;
	private ControllerUserData controller = new ControllerUserData();
	private Stage stage = new Stage();
	
	public LoadUserData(boolean createDialog, NewMenu main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewUserData.fxml"));
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
	
	public ControllerUserData getController(){
		return controller;
	}
	
}
