package NewMenu;

import Controller.MainClass;
import View.MainMenuController;
//import de.michaprogs.crm.GraphicButton;
//import de.michaprogs.crm.Main;
//import de.michaprogs.crm.article.data.LoadArticleData;
//import de.michaprogs.crm.customer.data.LoadCustomerData;
//import de.michaprogs.crm.documents.deliverybill.data.LoadDeliverybillData;
//import de.michaprogs.crm.documents.invoice.data.LoadInvoiceData;
//import de.michaprogs.crm.documents.offer.data.LoadOfferData;
//import de.michaprogs.crm.documents.order.data.LoadOrderData;
//import de.michaprogs.crm.stock.add.LoadStockAdd;
//import de.michaprogs.crm.supplier.data.LoadSupplierData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControllerNavigation {
	
	@FXML private AnchorPane apSide;
	@FXML private ScrollPane spSide;
	@FXML private BorderPane bpSide;
	
	@FXML private Button btnCollapse;
	@FXML private Button btnbestSellersReport;
	@FXML private Button btnareasDemand;
	@FXML private Button profitableCustomers;
	@FXML public Button btnCustomer;
	@FXML private Button btnArticle;
	@FXML private Button btnSupplier;
	//@FXML private Button btnOffer;
	//@FXML private Button btnOrder;
	//@FXML private Button btnDeliverybill;
	//@FXML private Button btnInvoice;
	@FXML private Button btnStock;
	
	@FXML private TitledPane tpMainData;
	@FXML private TitledPane tpDocuments;
	
	private LoadCustomerData customerData;
//	private LoadArticleData articleData;
	private LoadSupplierData supplierData;
	private LoadUserData userData;
	private LoadProductData productsData;
//	private LoadStockAdd stockAdd = new LoadStockAdd(false);
//	private LoadOfferData offerData;
//	private LoadOrderData orderData;
//	private LoadDeliverybillData deliverybillData;
//	private LoadInvoiceData invoiceData;
	
	private NewMenu main;
	
	public ControllerNavigation(){}
	
	@FXML private void initialize(){	
		
		customerData = new LoadCustomerData(false, main);
		//articleData = new LoadArticleData(false, 0, main);
		supplierData = new LoadSupplierData(false, 0, main);
		userData = new LoadUserData(false, main);
		productsData = new LoadProductData(false, main);
//		offerData = new LoadOfferData(false,0,0, main);
//		orderData = new LoadOrderData(false, 0, 0, main);
//		deliverybillData = new LoadDeliverybillData(false, 0, 0, main);
//		invoiceData = new LoadInvoiceData(false, 0, 0, main);		
//		
		//Buttons
		initBtnCollapse();
		initBtnbestSellersReport();
		initBtnareasDemand();
		initBtnProfitableCustomers();
		initBtnCustomer();
		initBtnArticle();
		initBtnSupplier();
		initBtnStock();
		//initBtnOffer();
		//initBtnOrder();
		//initBtnDeliverybill();
		//initBtnInvoice();
		
	}
	
	/**
	 *  set by main-class via loading class  
	 */		
	public void setMain(NewMenu main){
		this.main = main;
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnCollapse(){
		
		btnCollapse.setGraphic(new GraphicButton("menu_collapse_32.png").getGraphicButton());
		btnCollapse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(spSide.getPrefWidth() != 90){
					tpMainData.setText("ITEMS");
					tpDocuments.setText("REPs");
					
					btnCustomer.setText("");
					btnArticle.setText("");
					btnSupplier.setText("");
					//btnOffer.setText("");
					//btnOrder.setText("");
					btnStock.setText("");
					
					spSide.setPrefWidth(90);
					bpSide.setPrefWidth(90);
					apSide.setPrefWidth(90);
				}else{
					/* SAME TEXT AS IN THE FXML FILE */
					tpMainData.setText("MAIN ITEMS");
					btnCustomer.setText("USERS");
					btnArticle.setText("PRODUCTS");
					btnSupplier.setText("ORDERS");
					btnStock.setText("CUSTOMERS");
					
					tpDocuments.setText("REPORTS");
					//btnOffer.setText("ORDERS");
					//btnOrder.setText("CUSTOMERS");
					/* SAME WIDTH AS IN THE FXML FILE */
					spSide.setPrefWidth(250);
					bpSide.setPrefWidth(250);
					apSide.setPrefWidth(250);
				}
				
				
			}
		});
		
	}
	
	private void initBtnCustomer(){
		
		btnCustomer.setGraphic(new GraphicButton("customer_32_blue.png").getGraphicButton());
		btnCustomer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {	
				System.out.println(main.currentUser.getJobRole());
				if(main.currentUser.getJobRole().equals("driver") || main.currentUser.getJobRole().equals("stockman")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(MainClass.getPrimaryStage());
		            alert.setHeaderText("Permissions denied.");
		            alert.setContentText("You don't have permissions to access this page.");
		            alert.showAndWait();
				}
				else {
					main.getContentPane().setCenter(userData.getContent());
					main.getStage().setTitle(main.getProgramName() + " - Users");
					setSizeToScene();
				}
				
			}
		});
		
	}
	
	private void initBtnArticle(){
		
		btnArticle.setGraphic(new GraphicButton("article_32_blue.png").getGraphicButton());
		btnArticle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(main.currentUser.getJobRole().equals("driver")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(MainClass.getPrimaryStage());
		            alert.setHeaderText("Permissions denied.");
		            alert.setContentText("You don't have permissions to access this page.");
		            alert.showAndWait();
				}
				else {
					main.getContentPane().setCenter(productsData.getContent());
					main.getStage().setTitle(main.getProgramName() + " - Products");
					setSizeToScene();
				}
				
			}
		});
		
	}
	
	private void initBtnSupplier(){
		
		btnSupplier.setGraphic(new GraphicButton("supplier_32_blue.png").getGraphicButton());
		btnSupplier.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(supplierData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Orders");
				setSizeToScene();
			}
		});
		
	}
	
	private void initBtnStock(){
		
		btnStock.setGraphic(new GraphicButton("warehouse_32_blue.png").getGraphicButton());
		btnStock.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(main.currentUser.getJobRole().equals("driver")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(MainClass.getPrimaryStage());
		            alert.setHeaderText("Permissions denied.");
		            alert.setContentText("You don't have permissions to access this page.");
		            alert.showAndWait();
				}
				else {
					main.getContentPane().setCenter(customerData.getContent());
					main.getStage().setTitle(main.getProgramName() + " - Customers");
					setSizeToScene();
				}
				
			}
		});
		
	}
	
//	private void initBtnOffer(){
//		
//		btnOffer.setGraphic(new GraphicButton("offer_32_blue.png").getGraphicButton());
//		btnOffer.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				main.getContentPane().setCenter(offerData.getContent());
//				main.getStage().setTitle(main.getProgramName() + " - Angebot");
//				setSizeToScene();
//			}
//		});
//		
//	}
	
//	private void initBtnDeliverybill(){
//		
//		btnDeliverybill.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				main.getContentPane().setCenter(deliverybillData.getContent());
//				main.getStage().setTitle(main.getProgramName() + " - Lieferschein");
//				setSizeToScene();
//			}
//		});
//		
//	}
	
//	private void initBtnInvoice(){
//		
//		btnInvoice.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				main.getContentPane().setCenter(invoiceData.getContent());
//				main.getStage().setTitle(main.getProgramName() + " - Rechnung");
//				setSizeToScene();
//			}
//		});
//		
//	}
	
//	private void initBtnOrder(){
//		
//		//TODO setGraphic
//		btnOrder.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				main.getContentPane().setCenter(orderData.getContent());
//				main.getStage().setTitle(main.getProgramName() + " - Bestellung");
//				setSizeToScene();
//			}
//		});
//		
//	}
	
	private void setSizeToScene(){
		if(! main.getStage().isMaximized())
			main.getStage().sizeToScene();
	}
	
	private void initBtnbestSellersReport(){
		
		//TODO setGraphic
		btnbestSellersReport.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(main.currentUser.getJobRole().equals("driver") || main.currentUser.getJobRole().equals("stockman")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(MainClass.getPrimaryStage());
		            alert.setHeaderText("Permissions denied.");
		            alert.setContentText("You don't have permissions to access this page.");
		            alert.showAndWait();
				}
				else
					goToBestSellingReport();
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
	
	private void initBtnareasDemand() {
		
		//TODO setGraphic
		btnareasDemand.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if(main.currentUser.getJobRole().equals("driver") || main.currentUser.getJobRole().equals("stockman")) {
							Alert alert = new Alert(AlertType.WARNING);
							alert.initOwner(MainClass.getPrimaryStage());
				            alert.setHeaderText("Permissions denied.");
				            alert.setContentText("You don't have permissions to access this page.");
				            alert.showAndWait();
						}
						else
							goToMostDemandingArea();
					}
				});
		
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
	
	private void initBtnProfitableCustomers() {
		
		profitableCustomers.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(main.currentUser.getJobRole().equals("driver") || main.currentUser.getJobRole().equals("stockman")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(MainClass.getPrimaryStage());
		            alert.setHeaderText("Permissions denied.");
		            alert.setContentText("You don't have permissions to access this page.");
		            alert.showAndWait();
				}
				else
					goToMostDemandingProfitable();
			}
		});
		
	}
	
}
