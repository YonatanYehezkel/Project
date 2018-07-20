package NewMenu;

import java.util.ArrayList;
import java.util.HashMap;

import Controller.ControllerLogic;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import de.michaprogs.crm.GraphicButton;
//import de.michaprogs.crm.InitCombos;
//import de.michaprogs.crm.Validate;
//import de.michaprogs.crm.customer.ModelCustomer;
//import de.michaprogs.crm.customer.SearchCustomer;
//import de.michaprogs.crm.customer.category.ModelCustomerCategory;
//import de.michaprogs.crm.customer.category.SelectCustomerCategory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ControllerCustomerSearch {

	@FXML private Label lblSubHeadline;
	

	@FXML private TextField tfName1;
	@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	@FXML private ComboBox<String> cbLand;
	

	
	@FXML private TableView<Customer> tvCustomerSearch;
	@FXML private TableColumn<Customer, String> tcCustomerID;
	@FXML private TableColumn<Customer, String> tcName1;
	@FXML private TableColumn<Customer, String> tcName2;
	
 	
	@FXML private Button btnSearch;
	@FXML private Button btnReset;
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	private Stage stage;
	private String selectedCustomer = null;
	
	private ControllerLogic controller;
	private ObservableList<Customer> Customers;
	
	public ControllerCustomerSearch(){}
	
	@FXML private void initialize(){
		
		//new InitCombos().initComboLand(cbLand);
		
		//cbCategory.setItems(new SelectCustomerCategory(new ModelCustomerCategory()).getModelCustomerCategory().getObsListCustomerCategoriesComboBox());
		
		controller = new ControllerLogic(); 
		
		
		initBtnSearch();
		initBtnReset();
		initBtnSelect();
		initBtnAbort();
		
		initTextFields();
		
		initTableCustomerSearch();
		
		loadDataFromDB();
		
		
	}
	
	private void loadDataFromDB(){
		
		this.Customers = FXCollections.observableArrayList();
		
		
		HashMap<String, Customer> rs = controller.getAllCustomers();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.addAll(rs.values());
		
		for(Customer c : customers) {
			Customers.add(c);
		}
		
		System.out.println(Customers.get(2).getAdress());
		tvCustomerSearch.setItems(Customers);
	}
		
	
	/*
	 * BUTTONS
	 */
	private void initBtnSearch(){
		
		btnSearch.setGraphic(new GraphicButton("search_32.png").getGraphicButton());
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				search();
			}
		});
		
	}
	
	private void initBtnReset(){
		
		btnReset.setGraphic(new GraphicButton("clear_32.png").getGraphicButton());
		btnReset.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				reset();
			}
		});
		
	}
	
	private void initBtnSelect(){
		
		btnSelect.setGraphic(new GraphicButton("select_32.png").getGraphicButton());
		btnSelect.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				select();
			}
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(stage != null){
					stage.close();
				}else{
					reset();
				}
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableCustomerSearch(){
		
		this.tcCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		this.tcName1.setCellValueFactory(new PropertyValueFactory<>("adress"));
		this.tcName2.setCellValueFactory(new PropertyValueFactory<>("comment"));
		

		
		tvCustomerSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				//Double Left Click
				if(	event.getButton().equals(MouseButton.PRIMARY) &&
					event.getClickCount() == 2){
					select();
				}
				
			}
		});
		
		tvCustomerSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().equals(KeyCode.ENTER)){
					select();
				}
				
			}
		});
		
	}
	
	public void fitTableToStage() {
		tvCustomerSearch.prefHeightProperty().bind(stage.heightProperty());
		tvCustomerSearch.prefWidthProperty().bind(stage.widthProperty());
	}
	
	/*
	 * TEXTFIELDS
	 */
	private void initTextFields(){
		
		EventHandler<KeyEvent> ke = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().equals(KeyCode.ENTER)){
					search();
				}
				
			}
		};
		
		
		tfName1.setOnKeyPressed(ke);
		tfName2.setOnKeyPressed(ke);
		tfStreet.setOnKeyPressed(ke);
		
	}
	
	
	/*
	 * DATABASE METHODS
	 */
	private void search(){
		
//		ModelCustomer searchCustomer = new SearchCustomer(new ModelCustomer(
//			new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()), 
//			tfName1.getText(), 
//			tfName2.getText(), 
//			tfStreet.getText(), 
//			cbLand.getSelectionModel().getSelectedItem(), 
//			new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfZip.getText()), 
//			tfLocation.getText(), 
//			tfPhone.getText(), 
//			tfMobile.getText(), 
//			tfFax.getText(), 
//			tfEmail.getText(),
//			cbCategory.getSelectionModel().getSelectedItem()
//			)).getModelCustomer();
//		
//		tvCustomerSearch.setItems(searchCustomer.getObsListSearch());
//		if(tvCustomerSearch.getItems().size() > 0){
//			tvCustomerSearch.getSelectionModel().selectFirst();
//			tvCustomerSearch.requestFocus();
//		}
//		
//		if(tvCustomerSearch.getItems().size() == 1){
//			lblSubHeadline.setText("(" + String.valueOf(tvCustomerSearch.getItems().size()) + " Suchergebnis)" );
//		}else{
//			lblSubHeadline.setText("(" + String.valueOf(tvCustomerSearch.getItems().size()) + " Suchergebnisse)" );
//		}
		
	}
	
	private void select(){
		
		if(tvCustomerSearch.getSelectionModel().getSelectedItems().size() == 1 ){
			selectedCustomer = tvCustomerSearch.getItems().get(tvCustomerSearch.getSelectionModel().getSelectedIndex()).getCustomerName();
			
			if(stage != null){
				stage.close();
			}else{
				reset();
			}
			
		}else{
			System.out.println("Bitte 1 Zeile auswählen!");
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	private void reset(){
		
		
		tfName1.clear();
		tfName2.clear();
		tfStreet.clear();
		cbLand.getSelectionModel().select("");
	
	
		
		tvCustomerSearch.getItems().clear();
		
		lblSubHeadline.setText("");
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public String getSelectedCustomer(){
		return selectedCustomer;
	}
	
}
