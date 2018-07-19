package NewMenu;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class LoadNavigation {

	private AnchorPane root;
	private ControllerNavigation controller = new ControllerNavigation(); 
	
	public LoadNavigation(NewMenu main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewNavigation.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			root = loader.load();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerNavigation getController(){
		return controller;
	}
	
}
