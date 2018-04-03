package Controller;

import java.io.IOException;

import db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDateTime;

public class MainClass extends Application {
	
	public static Stage primaryStage;
    private static AnchorPane rootLayout;
    
  //ControllerLogic reference pointer
  	private static ControllerLogic controller;
  	
  	public static Stage getPrimaryStage() {
        return primaryStage;
    }

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Ruth Petifours");
        this.primaryStage.setFullScreenExitHint("");
        //this.primaryStage.setFullScreen(true);
       initRootLayout();
     
	}
	
	

	public static void initRootLayout() {
		 try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainClass.class.getResource("/View/LoginScreen.fxml"));
	            rootLayout = (AnchorPane) loader.load();
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.setFullScreenExitHint("");
	            //primaryStage.setFullScreen(true);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	public static void main(String[] args) {
		launch(args);
		//DB db = new DB();
		//db.getAllCustomers();
		controller = new ControllerLogic();
		//System.out.println(controller.getAllOrders().get("1").getId());
		
		
		LocalDateTime updated_date=LocalDateTime.now();
	    //Timestamp timestamp = new Timestamp(updated_date.getTime());
	    //updated_date = timestamp;
		System.out.println(updated_date);
	}
}
