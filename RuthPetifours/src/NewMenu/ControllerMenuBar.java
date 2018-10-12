package NewMenu;




import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

public class ControllerMenuBar {

	@FXML private MenuItem itemCustomer;
	@FXML private MenuItem itemArticle;
	@FXML private MenuItem itemSupplier;
	@FXML private MenuItem itemStock;
	
	@FXML private MenuItem itemOffer;
	
	@FXML private MenuItem itemProperties;
	
	@FXML private MenuItem close;
	
	//private LoadCustomerData customerData;
	//private LoadArticleData articleData;
	//private LoadSupplierData supplierData;
	//private LoadOfferData offerData;
	//private LoadStockAdd stockAdd = new LoadStockAdd(false);
	
	private NewMenu main;
	
	public ControllerMenuBar(){}
	
	@FXML private void initialize(){
		
		//Content
		//customerData = new LoadCustomerData(false, main);
		//articleData  = new LoadArticleData(false, 0, main);
		//supplierData =  new LoadSupplierData(false, 0, main);
		//offerData = new LoadOfferData(false, 0, 0, main);
		
		//MenuItems
		initItemCustomer();
		initItemArticle();
		initItemSupplier();
		initItemStock();
		
		initItemOffer();
		
		initItemProperties();
		
	}
	
	/*
	 * MENUITEMS
	 */
	private void initItemArticle(){
		
		itemArticle.setGraphic(new GraphicMenuItem("file:resources/article_32_white.png").getGraphicMenuItem());
		itemArticle.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(articleData.getContent());
				
			}
		});
		
	}
	
	private void initItemSupplier(){
		
		itemSupplier.setGraphic(new GraphicMenuItem("file:Images/supplier_32_blue.png").getGraphicMenuItem());
		itemSupplier.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(supplierData.getContent());
			}
		});
		
	}
	
	private void initItemStock(){
		
		itemStock.setGraphic(new GraphicMenuItem("file:Images/warehouse_32_blue.png").getGraphicMenuItem());
		itemStock.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(stockAdd.getContent());
			}
		});
		
	}
	
	private void initItemCustomer(){
		
		itemCustomer.setGraphic(new GraphicMenuItem("file:Images/customer_32_blue.png").getGraphicMenuItem());
		itemCustomer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(customerData.getContent());
			}
		});
		
	}
	
	private void initItemOffer(){
		
		itemOffer.setGraphic(new GraphicMenuItem("file:Images/offer_32_blue.png").getGraphicMenuItem());
		itemOffer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//main.getContentPane().setCenter(offerData.getContent());
			}
		});

	}
	
	private void initItemProperties(){
		
//		itemProperties.setGraphic(); TODO
		itemProperties.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//new LoadProperties(true).getController().setMain(main);
			}
		});
		
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
