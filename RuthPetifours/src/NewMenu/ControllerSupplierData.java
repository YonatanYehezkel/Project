package NewMenu;

import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.*;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Order;
import googleMap.DirectionsApi;
import googleMap.DirectionsApiRequest;
import googleMap.DirectionsResult;
import googleMap.DirectionsRoute;
import googleMap.GeoApiContext;
import googleMap.PendingResult;
import googleMap.TravelMode;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;

public class ControllerSupplierData {

	@FXML private Label lblSubHeadline;
	
	@FXML private ComboBox tfSupplierID;
	@FXML private TextField tfName1;

	
	@FXML private HBox tfPhone;
	@FXML private HBox tfFax;

	private DatePicker datePicker;
	private DatePicker datePicker2;
	private DatePicker datePicker3;
	
	
	@FXML private HBox cbPayment;
	//@FXML private TextField tfIBAN;

	
	/* ORDER */
	@FXML private TableView<Order> tvOrder;
	@FXML private TableColumn<Order, String> tcOrderID;
	@FXML private TableColumn<Order, String> tcOrderRequest;
	@FXML private TableColumn<Order, String> tcOrderDate;
	@FXML private TableColumn<Order, String> tcClerk;

	
	/* Sorting */
	@FXML private TableView<Order> tvOrder2;
	@FXML private TableColumn<Order, String> tcOrderID2;
	@FXML private TableColumn<Order, String> tcOrderRequest2;
	@FXML private TableColumn<Order, String> tcOrderDate2;
	@FXML private TableColumn<Order, String> tcClerk2;
	
	@FXML private TableView<Order> tvOrder1;
	@FXML private TableColumn<Order, String> tcOrderID1;
	@FXML private TableColumn<Order, String> tcOrderRequest1;
	@FXML private TableColumn<Order, String> tcOrderDate1;
	@FXML private TableColumn<Order, String> tcClerk1;
	
	@FXML private TableView<Order> tvOrder3;
	@FXML private TableColumn<Order, String> tcOrderID3;
	@FXML private TableColumn<Order, String> tcOrderRequest3;
	@FXML private TableColumn<Order, String> tcOrderDate3;
	@FXML private TableColumn<Order, String> tcClerk3;
	
	@FXML private TextField sumTbl1;
	@FXML private TextField sumTbl2;
	@FXML private TextField sumTbl3;
	
	@FXML private Button buildTbl1;
	@FXML private Button exportRoute1;
	@FXML private Button buildTbl3;
	@FXML private Button exportRoute3;
	@FXML private Button btnSearch;
	@FXML private Button btnRemovefilters;
	
	/* CONTACTS - NESTED CONTROLLER! */
	//@FXML private ControllerContactData contactDataController; //fx:id + 'Controller'
		
	//Buttons
	//@FXML private Button btnSearch;
	@FXML private Button btnNew;
	@FXML private Button btnEdit;
	      private Button btnEditSave = new Button("Speichern"); //Initialized in Java-Code
	      private Button btnEditAbort = new Button("Abbrechen"); //Initialized in Java-Code
	@FXML private Button btnDelete;
	
	//Panels & Nodes
	@FXML private HBox hboxBtnTopbar;
	
	private Stage stage;
	private NewMenu main;
	
	private ObservableList<Order> orders;
	private ControllerLogic controller;
	
	
	private Integer index = null;
	private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");
	private static final String payment_date = "payment_date";
	private static final String wait_shipment_date = "wait_shipment_date";
	private static final String shipment_date = "shipment_date";
	
	private String[] wayp;
	private DirectionsRoute[] routes;
	private int[] opt_route;
	private static final String API_KEY = "AIzaSyBgZxet8V_vUHpGyO9qmLaM46z6kTxDahs";
	
	
	
	public ArrayList <String> OptimalRoute1 = new ArrayList<String>();
	public String OptimalRoute3 = "";
	
	public ControllerSupplierData(){}
	
	@FXML private void initialize(){
		
		controller = new ControllerLogic();
		
		loadDataFromDB();
		
		//tfSupplierID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		//ComboBoxes
		initStatuses();
		//new InitCombos().initComboLand(cbLand);
		//new InitCombos().initComboPayment(cbPayment);
		
		//Buttons
	
		initBtnNew();
		initBtnEdit();
		initBtnEditSave();
		initBtnEditAbort();
		initBtnDelete();
		initBtnBuildRouteTbl1();
		initBtnBuildRouteTbl3();
		initBtnSearch();
		initBtnRemovefilters();
		
		initDatePicker();
		
		//TABLES
		initTableOrder();
		initTableOrder1();
		initTableOrder2();
		initTableOrder3();
		
		simpleDragDrop();
		
		//disable all fields from beginning
		//disableAllFields();
		
		setButtonState();
		
		
	}
	
	private void initDatePicker() {
		 datePicker = new DatePicker();
		 tfPhone.getChildren().addAll(datePicker);
		 
		 datePicker2 = new DatePicker();
		 tfFax.getChildren().addAll(datePicker2);
		 
		 datePicker3 = new DatePicker();
		 cbPayment.getChildren().addAll(datePicker3);
		 
	}
	
	private void loadDataFromDB(){
		
		this.orders = FXCollections.observableArrayList();
		
		
		HashMap<String, Order> rs = controller.getAllOrders();
		
		ArrayList<Order> orders1 = new ArrayList<Order>();
		
		orders1.addAll(rs.values());
	
		for(Order c : orders1) {
			c.setAdress(controller.getCustomerByName(c.getCustomer()).getAdress());
			c.setValue(controller.getValueOfOrder(c.getId()));
			orders.add(c);
		}
		
		tvOrder.setItems(orders);
		tvOrder2.setItems(orders);
	}
	
	private void loadNewDataFromDB(){
		
		HashMap<String, Order> rs = null;
		
		this.orders = FXCollections.observableArrayList();
		
		String s = tfName1.getText();
		if(s.isEmpty())
			s = "%";
		else
			s = "%"+s+"%";
		
		//LocalDate localDate = datePicker.getValue();
		
//		if(datePicker.getValue() != null) {
//			Date ship_d = Date.valueOf(datePicker.getValue());
//			rs = controller.searchByShipOrders(s, ship_d);
//		}
		
		
		//if(ship_d != null )
		
		String flag=defineStatus();
		if(flag == null) {
			if(datePicker.getValue() == null && datePicker2.getValue() == null && datePicker3.getValue() == null) {
				rs = controller.searchOrders(s);
			}

			if(datePicker.getValue() != null && datePicker3.getValue() == null && datePicker3.getValue() == null) {
				Date ship_d = Date.valueOf(datePicker.getValue());
				rs = controller.searchOrders(s, ship_d);
			}
			
			if(datePicker.getValue() != null && datePicker2.getValue() != null && datePicker3.getValue() == null) {
			
				Date ship_d = Date.valueOf(datePicker.getValue());
				Date pay_d = Date.valueOf(datePicker2.getValue());
				rs = controller.searchOrders(s, ship_d, pay_d);
			}
			
			if(datePicker.getValue() != null && datePicker2.getValue() != null && datePicker3.getValue() != null) {
			
				Date ship_d = Date.valueOf(datePicker.getValue());
				Date pay_d = Date.valueOf(datePicker2.getValue());
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchOrders(s, ship_d, pay_d, eta_d);
			}
			
			if(datePicker.getValue() == null && datePicker2.getValue() != null && datePicker3.getValue() == null) {
				
				Date pay_d = Date.valueOf(datePicker2.getValue());
				rs = controller.searchOrders2(s, pay_d);
			}
			if(datePicker.getValue() == null && datePicker2.getValue() != null && datePicker3.getValue() != null) {
				
				Date pay_d = Date.valueOf(datePicker2.getValue());
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchOrders2(s, pay_d, eta_d);
			}
			
			if(datePicker.getValue() == null && datePicker2.getValue() == null && datePicker3.getValue() != null) {
				
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchOrders3(s, eta_d);
			}
			
			if(datePicker.getValue() != null && datePicker2.getValue() == null && datePicker3.getValue() != null) {
				Date ship_d = Date.valueOf(datePicker.getValue());
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchOrders4(s,ship_d, eta_d);
			}
		}
		if (flag == payment_date) {
			if(datePicker.getValue() == null && datePicker3.getValue() == null) {
				rs = controller.searchAwaitingPaimentOrders(s);
			}
			
			if(datePicker.getValue() != null && datePicker3.getValue() == null) {
				Date ship_d = Date.valueOf(datePicker.getValue());
				//System.out.println(ship_d.toString());
				rs = controller.searchAwaitingPaimentOrders(s, ship_d);
			}
			
			if(datePicker.getValue() != null && datePicker3.getValue() != null) {
				Date ship_d = Date.valueOf(datePicker.getValue());
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchAwaitingPaimentOrders_eta(s, ship_d, eta_d);
			}
			
			if(datePicker.getValue() == null && datePicker3.getValue() != null) {
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchAwaitingPaimentOrders_eta(s, eta_d);
			}

			
		}
		if (flag == wait_shipment_date) {
			if(datePicker2.getValue() == null && datePicker3.getValue() == null) {
				rs = controller.searchAwaitingShipOrders(s);
			}
			
			if(datePicker2.getValue() != null && datePicker3.getValue() == null) {
				Date pay_d = Date.valueOf(datePicker2.getValue());
				rs = controller.searchAwaitingShipOrders(s, pay_d);
			}
			
			if(datePicker2.getValue() != null && datePicker3.getValue() != null) {
				Date pay_d = Date.valueOf(datePicker2.getValue());
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchAwaitingShipOrders_eta(s, pay_d, eta_d);
			}
			
			if(datePicker2.getValue() == null && datePicker3.getValue() != null) {
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchAwaitingPaimentOrders_eta(s, eta_d);
			}
		}
		if (flag == shipment_date) {
			if(datePicker.getValue() == null && datePicker2.getValue() == null && datePicker3.getValue() == null) {
				rs = controller.searchShippedOrders(s);
			}

			if(datePicker.getValue() != null && datePicker3.getValue() == null && datePicker3.getValue() == null) {
				Date ship_d = Date.valueOf(datePicker.getValue());
				rs = controller.searchShippedOrders(s, ship_d);
			}
			
			if(datePicker.getValue() != null && datePicker2.getValue() != null && datePicker3.getValue() == null) {
			
				Date ship_d = Date.valueOf(datePicker.getValue());
				Date pay_d = Date.valueOf(datePicker2.getValue());
				rs = controller.searchShippedOrders(s, ship_d, pay_d);
			}
			
			if(datePicker.getValue() != null && datePicker2.getValue() != null && datePicker3.getValue() != null) {
			
				Date ship_d = Date.valueOf(datePicker.getValue());
				Date pay_d = Date.valueOf(datePicker2.getValue());
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchShippedOrders(s, ship_d, pay_d, eta_d);
			}
			
			if(datePicker.getValue() == null && datePicker2.getValue() != null && datePicker3.getValue() == null) {
				
				Date pay_d = Date.valueOf(datePicker2.getValue());
				rs = controller.searchShippedOrders2(s, pay_d);
			}
			if(datePicker.getValue() == null && datePicker2.getValue() != null && datePicker3.getValue() != null) {
				
				Date pay_d = Date.valueOf(datePicker2.getValue());
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchShippedOrders2(s, pay_d, eta_d);
			}
			
			if(datePicker.getValue() == null && datePicker2.getValue() == null && datePicker3.getValue() != null) {
				
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchShippedOrders3(s, eta_d);
			}
			
			if(datePicker.getValue() != null && datePicker2.getValue() == null && datePicker3.getValue() != null) {
				Date ship_d = Date.valueOf(datePicker.getValue());
				Date eta_d = Date.valueOf(datePicker3.getValue());
				rs = controller.searchShippedOrders4(s,ship_d, eta_d);
			}
		}
			
		
		
		//rs = controller.searchOrders(s);
		//HashMap<String, Order> rs = new HashMap<String, Order>();
		ArrayList<Order> orders1 = new ArrayList<Order>();
		
		if(rs != null) {
			orders1.addAll(rs.values());
		}
		
	
		for(Order c : orders1) {
			c.setAdress(controller.getCustomerByName(c.getCustomer()).getAdress());
			c.setValue(controller.getValueOfOrder(c.getId()));
			orders.add(c);
		}
		
		tvOrder.setItems(orders);
		tvOrder2.setItems(orders);
	}
	
	private String defineStatus() {
		String s=null;
		if(tfSupplierID.getSelectionModel().getSelectedItem() != null) {
			
		
		if(tfSupplierID.getSelectionModel().getSelectedItem().toString().equals(OrderStatus.values()[0].name())) {
			return payment_date;
		}
		if(tfSupplierID.getSelectionModel().getSelectedItem().toString().equals(OrderStatus.values()[1].name())) {
			return wait_shipment_date;
		}
		if(tfSupplierID.getSelectionModel().getSelectedItem().toString().equals(OrderStatus.values()[2].name())) {
			return shipment_date;
		}
		}
		return s;
	}
	/*
	 * BUTTONS
	 */
//	private void initBtnSearch(){
//		
//		btnSearch.setGraphic(new GraphicButton("search_32.png").getGraphicButton());
//		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				
//				LoadSupplierSearch supplierSearch = new LoadSupplierSearch(true);
//				int selectedSupplierID = supplierSearch.getController().getSelectedSupplierID();
//				if(selectedSupplierID != 0){
//					selectSupplier(selectedSupplierID);
//				}
//				
//			}
//		});
//		
//	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
//				
//				LoadSupplierAdd supplierAdd = new LoadSupplierAdd(true);
//				int createdSupplierID = supplierAdd.getController().getCreatedSupplierID();
//				if(createdSupplierID != 0){
//					selectSupplier(createdSupplierID);
//				}
				
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select a file for import");
				ExtensionFilter filter = new ExtensionFilter("Excel", "*.xlsx", "*.xls", "csv");
				fileChooser.getExtensionFilters().add(filter);
				File f = null;
				f = fileChooser.showOpenDialog(MainClass.getPrimaryStage());
				if(f != null)
					controller.importOrdersFromExcel(f);
					loadNewDataFromDB();
				
			}
		});
		
	}
	
	private void initBtnEdit(){
		
		btnEdit.setGraphic(new GraphicButton("edit_32.png").getGraphicButton());
		btnEdit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				hboxBtnTopbar.getChildren().add(hboxBtnTopbar.getChildren().indexOf(btnEdit) + 1, btnEditSave);
				hboxBtnTopbar.getChildren().add(hboxBtnTopbar.getChildren().indexOf(btnEdit) + 2, btnEditAbort);
				
				enableAllFields();
				setButtonState();
				
			}
		});
		
	}
	
	private void initBtnEditSave(){
		
		btnEditSave.getStyleClass().add("btnTopbar");
		btnEditSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnEditSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
//				if(new ValidateSupplierSave(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), 
//											tfName1.getText()).isValid()){
//					
//					new UpdateSupplier(new ModelSupplier(
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), 
//						tfName1.getText(), 
//						tfName2.getText(), 
//						tfStreet.getText(), 
//						cbLand.getSelectionModel().getSelectedItem(), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfZip.getText()), 
//						tfLocation.getText(), 
//						tfPhone.getText(), 
//						tfFax.getText(), 
//						tfEmail.getText(), 
//						tfWeb.getText(), 
//						tfContact.getText(), 
//						tfUstID.getText(), 
//						cbPayment.getSelectionModel().getSelectedItem(), 
//						tfIBAN.getText(), 
//						tfBIC.getText(), 
//						tfBank.getText(), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentSkonto.getText()),
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentNetto.getText()),
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSkonto.getText()),
//						String.valueOf(LocalDate.now()),
//						taNotes.getText()),
//						contactDataController.getObsListContact()
//					);
//					
//					hboxBtnTopbar.getChildren().remove(btnEditSave);
//					hboxBtnTopbar.getChildren().remove(btnEditAbort);
//					
//					disableAllFields();
//					setButtonState();
//					
//				}
				
			}
		});
		
	}
	
	private void initBtnEditAbort(){
		
		btnEditAbort.getStyleClass().add("btnTopbar");
		btnEditAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnEditAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
//				AbortAlert abort = new AbortAlert();
//				if(abort.getAbort()){
//					if(stage != null){
//						stage.close();
//					}else{
//						selectSupplier(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()));
//					}
//					
//					hboxBtnTopbar.getChildren().remove(btnEditSave);
//					hboxBtnTopbar.getChildren().remove(btnEditAbort);
//					
//					disableAllFields();
//					setButtonState();
//					
//				}
				
			}
		});
		
	}
	
	private void initBtnDelete(){
		
		btnDelete.setGraphic(new GraphicButton("delete_32.png").getGraphicButton());
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
//				DeleteAlert delete = new DeleteAlert();
//				if(delete.getDelete()){
//					
//					new DeleteSupplier(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()));
//					resetAllFields();
//					setButtonState();
//					
//				}
				
			}
		});
		
	}
	
	
	private void initBtnBuildRouteTbl1(){
		
		//btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		buildTbl1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				buildRouteTbl1();
			}
		});
		
	}
	
	private void initBtnBuildRouteTbl3(){
		
		//btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		buildTbl3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				buildRouteTbl3();
			}
		});
		
	}
	
	private void initBtnSearch(){
		
		//btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				loadNewDataFromDB();
				//buildRouteTbl3();
			}
		});
		
	}
	
	private void initBtnRemovefilters(){
		
		btnRemovefilters.setStyle("-fx-background-color: #669999;");
		//btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnRemovefilters.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				resetAllFields();
				loadDataFromDB();
				
			}
		});
		
	}
	/*
	 * ComboBox
	 */
	
	private void initStatuses() {
		tfSupplierID.getItems().setAll(OrderStatus.values());
		
		tfSupplierID.valueProperty().addListener((ev) -> {
			//System.out.println(defineStatus());
	            if (defineStatus() == payment_date) {
	            	datePicker2.setValue(null);
	            	datePicker2.setDisable(true);
	            	datePicker.setDisable(false);
	            } 
	      
	            if(defineStatus() == wait_shipment_date) {
	            	datePicker.setValue(null);
	            	datePicker.setDisable(true);
	            	datePicker2.setDisable(false);
	            }
	            if(defineStatus() == shipment_date) {
	            	datePicker.setDisable(false);
	            	datePicker2.setDisable(false);
	            }
	        });
	}
	
	/*
	 * TABLES
	 */
	private void initTableOrder(){
		
		tcOrderID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcOrderRequest.setCellValueFactory(new PropertyValueFactory<>("customer"));
		tcOrderDate.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tcClerk.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		tvOrder.prefHeightProperty().bind(NewMenu.getStage().heightProperty());
		tvOrder.prefWidthProperty().bind(NewMenu.getStage().widthProperty());
				
		tvOrder.setContextMenu(new ContextMenuTableOrder());
		
		tvOrder.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					goToOrder(); 
				}
				
			}
		});
		
	}
	
	private void initTableOrder1(){
		
		tcOrderID1.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcOrderRequest1.setCellValueFactory(new PropertyValueFactory<>("customer"));
		tcOrderDate1.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tcClerk1.setCellValueFactory(new PropertyValueFactory<>("value"));
	
		
		sumTbl1.setEditable(false);
		tvOrder1.prefWidth(500);
		
				
		tvOrder1.setContextMenu(new ContextMenuTableOrder());
		
		tvOrder1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					goToOrder(); 
				}
				
			}
		});
		
	}
	
	private void initTableOrder2(){
		
		tcOrderID2.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcOrderRequest2.setCellValueFactory(new PropertyValueFactory<>("customer"));
		tcOrderDate2.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tcClerk2.setCellValueFactory(new PropertyValueFactory<>("value"));
	
		
		tvOrder2.prefWidth(500);
		
				
		tvOrder2.setContextMenu(new ContextMenuTableOrder());
		
		tvOrder2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					goToOrder(); 
				}
				
			}
		});
		
	}
	
	private void initTableOrder3(){
		
		tcOrderID3.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcOrderRequest3.setCellValueFactory(new PropertyValueFactory<>("customer"));
		tcOrderDate3.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tcClerk3.setCellValueFactory(new PropertyValueFactory<>("value"));
	
		
		tvOrder3.prefWidth(500);
		
				
		tvOrder3.setContextMenu(new ContextMenuTableOrder());
		
		tvOrder3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					goToOrder(); 
				}
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	public void selectSupplier(int _supplierID){
		
//		ModelSupplier supplier = new SelectSupplier(new ModelSupplier(_supplierID)).getModelSupplier();
//			
//		/* MAIN DATA */
//		this.tfSupplierID.setText(String.valueOf(supplier.getSupplierID()));
//		this.tfName1.setText(supplier.getName1());
//		this.tfName2.setText(supplier.getName2());
//		this.tfStreet.setText(supplier.getStreet());
//		this.cbLand.getSelectionModel().select(supplier.getLand());
//		this.tfZip.setText(String.valueOf(supplier.getZip()));
//		this.tfLocation.setText(supplier.getLocation());
//		
//		/* CONTACT DATA */
//		this.tfPhone.setText(supplier.getPhone());
//		this.tfFax.setText(supplier.getFax());
//		this.tfEmail.setText(supplier.getEmail());
//		this.tfWeb.setText(supplier.getWeb());
//		this.tfContact.setText(supplier.getContact());
//		this.tfUstID.setText(supplier.getUstID());
//		
//		/* PAYMENT DATA */
//		this.cbPayment.getSelectionModel().select(supplier.getPayment());
//		this.tfIBAN.setText(supplier.getIBAN());
//		this.tfBIC.setText(supplier.getBIC());
//		this.tfBank.setText(supplier.getBank());
//		this.tfPaymentSkonto.setText(String.valueOf(supplier.getPaymentSkonto()));
//		this.tfPaymentNetto.setText(String.valueOf(supplier.getPaymentNetto()));
//		this.tfSkonto.setText(String.valueOf(supplier.getSkonto()));
//		
//		/* NOTES */
//		this.taNotes.setText(supplier.getNotes());
//		
//		/* TITLE */
//		this.lblSubHeadline.setText("- " + supplier.getSupplierID() + " " + supplier.getName1() + ", " + supplier.getZip() + " " + supplier.getLocation());
//		main.getStage().setTitle(main.getProgramName() + " - Artikelstamm " + supplier.getSupplierID() + " " + supplier.getName1() + ", " + supplier.getZip() + " " + supplier.getLocation());
//		
//		/* CONTACTS */
//		this.contactDataController.setTableData(supplier.getObsListContacts());
//		
//		/* ORDER */
//		selectOrder(supplier.getSupplierID());
//		
//		setButtonState();
		
	}
	
	private void selectOrder(int _supplierID){
		
//		if(_supplierID != 0){
//			tvOrder.setItems(new SelectOrder(new ModelOrder(_supplierID), OrderSelection.ALL_ORDER_TO_SUPPLIER).getModelOrder().getObsListSupplierOrder());
//			
//		}else{
//			System.out.println("Bitte g�ltige Kundennummer w�hlen!");
//		}
//		
	}
	
	/*
	 * UI METHODS
	 */
	private void disableAllFields(){
		
		this.tfSupplierID.setDisable(true);
		this.tfName1.setDisable(true);
		//this.tfPhone.setDisable(true);
		this.tfFax.setDisable(true);
//		this.cbPayment.setDisable(true);
//		this.tfIBAN.setDisable(true);
		
	}
	
	private void enableAllFields(){
		
//		this.tfSupplierID.setDisable(false); should never be enabled!
		this.tfName1.setDisable(false);
//		this.tfName2.setDisable(false);
//		this.tfStreet.setDisable(false);
//		this.cbLand.setDisable(false);
//		this.tfZip.setDisable(false);
//		this.tfLocation.setDisable(false);
		//this.tfPhone.setDisable(false);
		this.tfFax.setDisable(false);
//		this.tfEmail.setDisable(false);
//		this.tfWeb.setDisable(false);
//		this.tfContact.setDisable(false);
//		this.tfUstID.setDisable(false);
//		this.cbPayment.setDisable(false);
//		this.tfIBAN.setDisable(false);
//		this.tfBIC.setDisable(false);
//		this.tfBank.setDisable(false);
//		this.tfPaymentSkonto.setDisable(false);
//		this.tfPaymentNetto.setDisable(false);
//		this.tfSkonto.setDisable(false);
//		this.taNotes.setDisable(false);
		
	}
	
	private void resetAllFields(){
		
		this.tfSupplierID.getSelectionModel().clearSelection();
		this.tfName1.clear();

		datePicker.setValue(null);
		datePicker2.setValue(null);
		datePicker3.setValue(null);
		datePicker.setDisable(false);
		datePicker2.setDisable(false);
		datePicker3.setDisable(false);
		
		loadDataFromDB();
		
	}
	
	private void setButtonState(){
		
		//Supplier is selected
		if(! tfName1.getText().isEmpty()){
			
			//btnSearch.setDisable(false);
			btnNew.setDisable(false);
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
		
			//Supplier edit is active
			if(	hboxBtnTopbar.getChildren().contains(btnEditSave) &&
				hboxBtnTopbar.getChildren().contains(btnEditAbort)){
				
				//btnSearch.setDisable(true);
				btnNew.setDisable(true);
				btnEdit.setDisable(true);
				btnDelete.setDisable(true);
				
				/* CONTACTS */
//				contactDataController.getButtonContactAdd().setDisable(false);
//				if(contactDataController.getObsListContact().size() > 0){
//					contactDataController.getButtonContactEdit().setDisable(false);
//					contactDataController.getButtonContactDelete().setDisable(false);
//				}
				
			}else{
				
				/* CONTACTS */
//				contactDataController.getButtonContactAdd().setDisable(true);
//				contactDataController.getButtonContactEdit().setDisable(true);
//				contactDataController.getButtonContactDelete().setDisable(true);
//				
			}
			
		}else{
			
			//btnSearch.setDisable(false);
			btnNew.setDisable(false);
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			
			/* CONTACTS */
//			contactDataController.getButtonContactAdd().setDisable(true);
//			contactDataController.getButtonContactEdit().setDisable(true);
//			contactDataController.getButtonContactDelete().setDisable(true);
			
		}
		
	}
	
	private void goToOrder(){
		
//		if(tvOrder.getSelectionModel().getSelectedItems().size() == 1){
//			main.getContentPane().setCenter(new LoadOrderData(	false, 
//																tvOrder.getItems().get(tvOrder.getSelectionModel().getSelectedIndex()).getOrderID(), 
//																Integer.valueOf(tfSupplierID.getText()),
//																main
//											).getContent());				
//		}else{
//			System.out.println("Bitte 1 Zeile markieren!");
//		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setMain(NewMenu main){
		this.main = main;
	}
	
	/*
	 * CONTEXT MENU
	 */
	private class ContextMenuTableOrder extends ContextMenu{
		
		private MenuItem itemGoTo = new MenuItem("Gehe zu..");
		private MenuItem itemNew = new MenuItem("Hinzuf�gen..");
		
		public ContextMenuTableOrder(){
			
			initItemGoTo();
			initItemNew();
			
			this.getItems().addAll(	itemGoTo,
									itemNew);
			
//			this.setOnShowing(new EventHandler<WindowEvent>() {
//
//				@Override
//				public void handle(WindowEvent event) {
//					
//					if(tfSupplierID.getText().equals("")){
//						itemNew.setDisable(true);
//						itemGoTo.setDisable(true);
//					}else{
//						
//						if(	hboxBtnTopbar.getChildren().contains(btnEditAbort) &&
//							hboxBtnTopbar.getChildren().contains(btnEditSave)){
//							itemNew.setDisable(true);
//							itemGoTo.setDisable(true);
//						}else{
//						
//							itemNew.setDisable(false);
//							if(tvOrder.getItems().size() > 0){
//								itemGoTo.setDisable(false);
//							}else{
//								itemGoTo.setDisable(true);
//							}
//						}
//					}
//					
//				}
//			});
			
		}
		
		private void initItemGoTo(){
			
			itemGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	
					goToOrder();
				}
			});
			
		}
		
		private void initItemNew(){
			
			itemNew.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
//					LoadOrderAdd orderAdd = new LoadOrderAdd(true, new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), main);
//					if(orderAdd.getController().getCreatedOrderID() != 0){
//						selectOrder(Integer.valueOf(tfSupplierID.getText()));
//					}
					
				}
			});
			
		}
		
	}
	
	private void simpleDragDrop() {

		tvOrder2.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		   
		    	index = tvOrder2.getSelectionModel().getSelectedIndex();
		    	ClipboardContent cc = new ClipboardContent();
                cc.put(SERIALIZED_MIME_TYPE, index);
                Dragboard db = tvOrder2.startDragAndDrop(TransferMode.MOVE);
            
                //db.setDragView(row.snapshot(null, null));
                
                                
                db.setContent(cc);
                event.consume();
		    }
		});
		
		tvOrder1.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	  
		              Dragboard db = event.getDragboard();
		              if (db.hasContent(SERIALIZED_MIME_TYPE)) {
		             
		                  event.acceptTransferModes( TransferMode.MOVE );
		              }
		           
		        event.consume();
		    }
		});
		
		tvOrder3.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	  
		              Dragboard db = event.getDragboard();
		              if (db.hasContent(SERIALIZED_MIME_TYPE)) {
		             
		                  event.acceptTransferModes( TransferMode.MOVE );
		              }
		           
		        event.consume();
		    }
		});
		
		tvOrder1.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
				    	
		         if (event.getGestureSource() != tvOrder1 &&
		                 event.getDragboard().hasString()) {
		        	 tvOrder1.setVisible(false);
		         }
		                
		         event.consume();
		    }
		});
		
		tvOrder1.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
	
		    	if(event.getGestureSource() == tvOrder2 && !index.equals(null) ) {
		    		tvOrder1.getItems().add(tvOrder2.getItems().get(index));
           
		    		tvOrder2.getItems().remove(index.intValue());
		    		index = null;
                    event.setDropCompleted(true);
                    
                                      
                    
                    sumTbl1.setText(Float.toString(calculateOrdersSum(tvOrder1)));
                    sumTbl2.setText(Float.toString(calculateOrdersSum(tvOrder2)));
                    
                    //opt_label_small.setVisible(false);
		    		
		    	}
		    		
		                
		         event.consume();
		    }
		});
		
		tvOrder3.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
	
		    	if(event.getGestureSource() == tvOrder2 && !index.equals(null) ) {
		    		tvOrder3.getItems().add(tvOrder2.getItems().get(index));
           
		    		tvOrder2.getItems().remove(index.intValue());
		    		index = null;
                    event.setDropCompleted(true);
                    
                    
                    sumTbl3.setText(Float.toString(calculateOrdersSum(tvOrder3)));
                    sumTbl2.setText(Float.toString(calculateOrdersSum(tvOrder)));
                    
                    //opt_label_big.setVisible(false);
		    		
		    	}
		    		
		                
		         event.consume();
		    }
		});
	}
	
	protected float calculateOrdersSum(TableView<Order> table) {
		 float sum = 0;
        for (Order item : table.getItems()) {
		        sum = sum + item.getValue();
		    }
		return sum;
	}
	
	
	private void buildRouteTbl1() {
		
		//this.exportRoute1.setDisable(false);
				
		if(tvOrder1.getItems().size()==0){
			System.out.println("list is empty");
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(MainClass.getPrimaryStage());
            alert.setHeaderText("List is empty!");
            alert.setContentText("You should add orders to table.");
            alert.showAndWait();
		}
		
		else {
			

		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(API_KEY)
			    .build();
		    		
		    DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
		    apiRequest.origin("Karmiel");
		    apiRequest.destination("Karmiel");
		    apiRequest.optimizeWaypoints(true);
	
		    
		    List<String> columnData = new ArrayList<>();
		    for (Order item : tvOrder1.getItems()) {
		        columnData.add(item.getAdress());
		    }
		    wayp = columnData.toArray(new String[0]);
		    apiRequest.waypoints(wayp);
			

		    apiRequest.mode(TravelMode.DRIVING); 

		    
		    final CountDownLatch latch = new CountDownLatch(1);
		    
		    //System.out.println(latch.toString());
		    
		    apiRequest.setCallback(new PendingResult.Callback<DirectionsResult>() {
		    	
		        //@Override
		        public void onResult(DirectionsResult result) {
		        	routes = result.routes;
		            Gson gson = new GsonBuilder().setPrettyPrinting().create();
		           //routes[1].waypointOrder.toString();
		           //System.out.println(Arrays.toString(routes[0].waypointOrder));
		           opt_route = routes[0].waypointOrder;
		            /*bestRout.setText(getOptimalRoute()); */
		            System.out.println(getOptimalRoute());
		            //getOptimalRoute();
		            //bestRout.setText(Arrays.toString(routes[0].waypointOrder));
			        //System.out.println(gson.toJson(routes));
		            sortTablebyOptimalRoute(tvOrder1);
		          	            
		            //opt_label_small.setVisible(true);
		            
			        latch.countDown();
		        }

		        

				protected String getOptimalRoute() {
					
					//ArrayList<String> routeText = new ArrayList <String>();

					String s ="The Optimal route is: #\n";
		        	//Arrays.toString(routes[0].waypointOrder);
		        	
		        	for( int i = 0; i <= routes[0].waypointOrder.length - 1; i++) {
		        		//System.out.println(wayp[routes[0].waypointOrder[i]].toString());
		        		s = s.concat("# "+(i+1)+"* "+wayp[routes[0].waypointOrder[i]].toString()).concat(", ") + "#\n";
		        		OptimalRoute1.add((i+1)+"* "+wayp[routes[0].waypointOrder[i]].toString());
		        	}
		        	
		        	//System.out.println(routes[0].waypointOrder);
		        	
		        	//System.out.println(OptimalRoute1);
		        	
		        	//OptimalRoute1=s;
		        	
					return s;
					
				}

				@Override
		        public void onFailure(Throwable e) {
		        	
		        }
		    });
		    

		    // We have to hold the main thread open until callback is called by OkHTTP.
		    try {
			    //System.out.println("stage 6");
		    	latch.await();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    	
		}

		//this.exportRoute1.setDisable(false);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Route Has Been Successfully Built!");
		alert.setContentText("You can view it through the table below \n and export it to a PDF file");

		alert.showAndWait();
		
	    exportRoute1.setDisable(false);
	}
	
	public void exportRoute1ToPDF() throws Exception {	
		
		if(tvOrder1.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Empty Route PDF File");
			alert.setContentText("No destinations will be shown in the file, \nsince the orders table is empty!");
			alert.showAndWait();
		}
		
		Image img = Image.getInstance("src/login/ruth.jpg");
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String dateString = df.format(calobj.getTime()).toString();
		
	
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);		
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new FileChooser().showSaveDialog(stage)));
		document.open();					
		Paragraph title = new Paragraph("Driving Instructions For Today's Deliveries Route", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255,17)));
		Chapter chapter1 = new Chapter(title, 1);
		chapter1.setNumberDepth(0);
		document.add(chapter1);
		document.add(img);
		Paragraph dateText = new Paragraph("\n The date is: "+dateString+"\n");
		document.add(dateText);
		int rows = 0;
		for (Object row: tvOrder1.getItems()) {
			document.add(new Paragraph("\n\nDestination # "+(rows+1)+" is: "+tvOrder1.getItems().get(rows).getAdress()+
									   "\nCustomer "+tvOrder1.getItems().get(rows).getCustomer()+
									   "\nOrder Sum is: "+tvOrder1.getItems().get(rows).getValue()+"\n\n"));	
			rows++;
		}		
		document.close();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Route Exported Successfully!");
		alert.setContentText("The PDF route has been Successfully exported!");
		alert.showAndWait();

	}
	
	private void buildRouteTbl3() {
		if(tvOrder3.getItems().size()==0){
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(MainClass.getPrimaryStage());
            alert.setHeaderText("List is empty!");
            alert.setContentText("You should add orders to table.");
            alert.showAndWait();
		}
		
		else {
		
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(API_KEY)
			    .build();
		    		
		    DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
		    apiRequest.origin("Karmiel");
		    apiRequest.destination("Karmiel");
		    apiRequest.optimizeWaypoints(true);
	

		    List<String> columnData = new ArrayList<>();
		    for (Order item : tvOrder3.getItems()) {
		        columnData.add(item.getAdress());
		    }
		    wayp = columnData.toArray(new String[0]);
		    apiRequest.waypoints(wayp);
			
			
		    apiRequest.mode(TravelMode.DRIVING); 


		    final CountDownLatch latch = new CountDownLatch(1);
		    
		    apiRequest.setCallback(new PendingResult.Callback<DirectionsResult>() {
		    	 
		        @Override
		        public void onResult(DirectionsResult result) {
		            routes = result.routes;
		           
		            Gson gson = new GsonBuilder().setPrettyPrinting().create();
		           //routes[1].waypointOrder.toString();
		           //System.out.println(Arrays.toString(routes[0].waypointOrder));
		           opt_route = routes[0].waypointOrder;
		            /*bestRout.setText(getOptimalRoute()); */
		            //System.out.println(getOptimalRoute());
		            //getOptimalRoute();
		            //bestRout.setText(Arrays.toString(routes[0].waypointOrder));
			        //System.out.println(gson.toJson(routes));
		            sortTablebyOptimalRoute(tvOrder3);
		            		            
		            //opt_label_small.setVisible(true);
		            
			        latch.countDown();
		        }

		        

				private String getOptimalRoute() {
					String s ="The Optimal route is: ";
		        	//Arrays.toString(routes[0].waypointOrder);
		        	
		        	for( int i = 0; i <= routes[0].waypointOrder.length - 1; i++) {
		        		//System.out.println(wayp[routes[0].waypointOrder[i]].toString());
		        		s = "\n"+s.concat(wayp[routes[0].waypointOrder[i]].toString()).concat(", ");
		        	}
		        	
		        	//System.out.println(routes[0].waypointOrder);
		        	OptimalRoute3=s;
					return s;
				}

				@Override
		        public void onFailure(Throwable e) {
		        	
		        }
		    });

		    // We have to hold the main thread open until callback is called by OkHTTP.
		    try {
				latch.await();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		    Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Route Has Been Successfully Built!");
			alert.setContentText("You can view it through the table below \n and export it to a PDF file");

			alert.showAndWait();
		  
		    exportRoute3.setDisable(false);
		}
		
	}
	
	public void exportRoute3ToPDF() throws Exception {
		
		if(tvOrder3.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Empty Route PDF File");
			alert.setContentText("No destinations will be shown in the file, \nsince the orders table is empty!");
			alert.showAndWait();
		}
		
		Image img = Image.getInstance("src/login/ruth.jpg");
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		
		String dateString = df.format(calobj.getTime()).toString();
				
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new FileChooser().showSaveDialog(stage)));
				
		document.open();
						
		Paragraph title = new Paragraph("Driving Instructions For Today's Deliveries Route", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255,17)));
				    
		Chapter chapter1 = new Chapter(title, 1);
				       
		chapter1.setNumberDepth(0);
		
		document.add(chapter1);
		
		document.add(img);
		
		Paragraph dateText = new Paragraph("\n The date is: "+dateString+"\n");
		
		document.add(dateText);
		
		int rows = 0;

		
		for (Object row: tvOrder3.getItems()) {
			
			document.add(new Paragraph("\n\nDestination # "+(rows+1)+" is: "+tvOrder3.getItems().get(rows).getAdress()+
									   "\nCustomer "+tvOrder3.getItems().get(rows).getCustomer()+
									   "\nOrder Sum is: "+tvOrder3.getItems().get(rows).getValue()+"\n\n"));
			
			rows++;
		}
				
		document.close();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Route Exported Successfully!");
		alert.setContentText("The PDF route has been Successfully exported!");

		alert.showAndWait();
		
	}
	
	private void sortTablebyOptimalRoute(TableView<Order> table) {
		
		 ObservableList<Order> orders = FXCollections.observableArrayList();
		for(int i=0; i<opt_route.length; i++) {
			orders.add(table.getItems().get(opt_route[i]));
			
		}
		
		table.setItems(orders);
				
	}
	
}

