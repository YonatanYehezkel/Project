package NewMenu;




import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

public class ControllerMenuBar {

	@FXML private MenuItem itemUsers;
	@FXML private MenuItem itemArticle;
	@FXML private MenuItem itemSupplier;
	@FXML private MenuItem itemStock;
	
	@FXML private MenuItem itemOffer;
	@FXML private MenuItem itemProperties;
	@FXML private MenuItem itemReport3;
	
	@FXML private MenuItem close;
	
	private LoadUserData userData;
	private LoadCustomerData customerData;
	private LoadProductData ProductData;
	private LoadSupplierData supplierData;
	//private LoadOfferData offerData;
	//private LoadStockAdd stockAdd = new LoadStockAdd(false);
	
	private NewMenu main;
	
	public ControllerMenuBar(){}
	
	@FXML private void initialize(){
		
		//Content
		//offerData = new LoadOfferData(false, 0, 0, main);
		
		userData = new LoadUserData(false, main);
		customerData = new LoadCustomerData(false, main);
		supplierData =  new LoadSupplierData(false, 0, main);
		ProductData  = new LoadProductData(false, main);

		
		//MenuItems
		inititemUsers();
		initItemArticle();
		initItemSupplier();
		initItemStock();
		
		initItemOffer();
		
		initItemProperties();
		
		initItemReport3();
		
	}
	
	/*
	 * MENUITEMS
	 */
	private void initItemArticle(){
		
		itemArticle.setGraphic(new GraphicMenuItem("file:resources/article_32_white.png").getGraphicMenuItem());
		itemArticle.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(ProductData.getContent());
				main.getContentPane().setCenter(customerData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Products");
				setSizeToScene();
			}
		});
		
	}
	
	private void initItemSupplier(){
		
		itemSupplier.setGraphic(new GraphicMenuItem("file:Images/supplier_32_blue.png").getGraphicMenuItem());
		itemSupplier.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(supplierData.getContent());
				main.getContentPane().setCenter(ProductData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Products");
				setSizeToScene();
			}
		});
		
	}
	
	private void initItemStock(){
		
		itemStock.setGraphic(new GraphicMenuItem("file:Images/warehouse_32_blue.png").getGraphicMenuItem());
		itemStock.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(stockAdd.getContent());
				main.getContentPane().setCenter(supplierData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Orders");
				setSizeToScene();
			}
		});
		
	}
	
	private void inititemUsers(){
		
		itemUsers.setGraphic(new GraphicMenuItem("file:Images/customer_32_blue.png").getGraphicMenuItem());
		itemUsers.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(customerData.getContent());
				main.getContentPane().setCenter(userData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Users");
				setSizeToScene();
			}
		});
		
	}
	
	private void setSizeToScene(){
		if(! main.getStage().isMaximized())
			main.getStage().sizeToScene();
	}
	
	private void initItemOffer(){
		
		itemOffer.setGraphic(new GraphicMenuItem("file:Images/offer_32_blue.png").getGraphicMenuItem());
		itemOffer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(offerData.getContent());
				goToBestSellingReport();
			}
		});

	}
	
	private void initItemProperties(){
		
		itemProperties.setGraphic(new GraphicMenuItem("file:Images/offer_32_blue.png").getGraphicMenuItem()); 
		itemProperties.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//new LoadProperties(true).getController().setMain(main);
				goToMostDemandingArea();
			}
		});
		
	}
	
	private void initItemReport3(){
		
		itemReport3.setGraphic(new GraphicMenuItem("file:Images/offer_32_blue.png").getGraphicMenuItem()); 
		itemReport3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//new LoadProperties(true).getController().setMain(main);
				goToMostDemandingProfitable();
			}
		});
		
	}
	
	private void goToBestSellingReport() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(NewMenu.class.getResource("/View/BestSellingReport.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			Stage s = new Stage();
			s.setScene(appSetScene);
			s.show();
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setMaximized(true);
			
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void goToMostDemandingArea() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(NewMenu.class.getResource("/View/MostDemandingAreas.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			Stage s = new Stage();
			s.setScene(appSetScene);
			s.show();
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setMaximized(true);
			
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void goToMostDemandingProfitable() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(NewMenu.class.getResource("/View/MostProfitableReport.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			Stage s = new Stage();
			s.setScene(appSetScene);
			s.show();
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setMaximized(true);
			
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  set by main-class via loader class
	 */	
	public void setMain(NewMenu main){
		this.main = main;
	}
	
	public void closeSystem() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Ruth Petifours System");
		alert.setContentText("Are You sure you want to exit the system?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
			System.exit(0);
		} else {
		    // ... user chose CANCEL or closed the dialog
			alert.close();
		}
		
	}
	
}
