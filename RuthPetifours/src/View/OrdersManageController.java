package View;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.ControllerLogic;
import Controller.MainClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class OrdersManageController implements Initializable{
	
@FXML private Button Back;

private ControllerLogic controller;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		controller = new ControllerLogic();
		
		
	}
	
	@FXML private void goBackToMainMenu(){
		
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
	
	@FXML private void importOrderFromExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a file for import");
		ExtensionFilter filter = new ExtensionFilter("Excel", "*.xlsx", "*.xls");
		fileChooser.getExtensionFilters().add(filter);
		File f = fileChooser.showOpenDialog(MainClass.getPrimaryStage());
		
		controller.importOrdersFromExcel(f);
	}
	
	@FXML private void importProductsFromExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a file for import");
		ExtensionFilter filter = new ExtensionFilter("Excel", "*.xlsx", "*.xls");
		fileChooser.getExtensionFilters().add(filter);
		File f = fileChooser.showOpenDialog(MainClass.getPrimaryStage());
		
		controller.importProductsFromExcel(f);
	}

}
