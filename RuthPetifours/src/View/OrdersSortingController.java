package View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Controller.ControllerLogic;
import Model.Order;
import Model.User;
import googleMap.DirectionsApi;
import googleMap.DirectionsApiRequest;
import googleMap.DirectionsResult;
import googleMap.DirectionsRoute;
import googleMap.GeoApiContext;
import googleMap.PendingResult;
import googleMap.TravelMode;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class OrdersSortingController implements Initializable{
	
	@FXML private TableView<Order> OrderTable;
	@FXML private TableColumn<Order,String> orderN;
	@FXML private TableColumn<Order,String> customer;
	@FXML private TableColumn<Order,String> adress;
	@FXML private TableColumn<Order,String> sum;
	
	@FXML private TableView<Order> smallCarTable;
	@FXML private TableColumn<Order,String> orderN_small;
	@FXML private TableColumn<Order,String> customer_small;
	@FXML private TableColumn<Order,String> address_small;
	@FXML private TableColumn<Order,String> sum_small;
	
	@FXML private TableView<Order> bigCarTable;
	@FXML private TableColumn<Order,String> orderN_big;
	@FXML private TableColumn<Order,String> customer_big;
	@FXML private TableColumn<Order,String> address_big;
	@FXML private TableColumn<Order,String> sum_big;
	
	
	@FXML private Button Build_Small_Car_Route;
	@FXML private Button Build_Big_Car_Route;

	
	
	@FXML private ObservableList<Order> orders;
	
	private ControllerLogic controller;
	private User currentUser;
	private Integer index = null;
	private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

	private String[] wayp;
	private DirectionsRoute[] routes;
	private static final String API_KEY = "AIzaSyAIgMRRrFNahxoMfyQdsi7T07SeQ79lEgY";
	
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		controller = new ControllerLogic();
		
		orderN.setCellValueFactory(new PropertyValueFactory<>("id"));
		customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
		adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
		sum.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		orderN_small.setCellValueFactory(new PropertyValueFactory<>("id"));
		customer_small.setCellValueFactory(new PropertyValueFactory<>("customer"));
		address_small.setCellValueFactory(new PropertyValueFactory<>("adress"));
		sum_small.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		orderN_big.setCellValueFactory(new PropertyValueFactory<>("id"));
		customer_big.setCellValueFactory(new PropertyValueFactory<>("customer"));
		address_big.setCellValueFactory(new PropertyValueFactory<>("adress"));
		sum_big.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		loadDataFromDB();
		
		simpleDragDrop();
		}
	
	private void simpleDragDrop() {

		OrderTable.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		   
		    	index = OrderTable.getSelectionModel().getSelectedIndex();
		    	ClipboardContent cc = new ClipboardContent();
                cc.put(SERIALIZED_MIME_TYPE, index);
                Dragboard db = OrderTable.startDragAndDrop(TransferMode.MOVE);
            
                //db.setDragView(row.snapshot(null, null));
                
                db.setContent(cc);
                event.consume();
		    }
		});
		
		smallCarTable.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	  
		              Dragboard db = event.getDragboard();
		              if (db.hasContent(SERIALIZED_MIME_TYPE)) {
		             
		                  event.acceptTransferModes( TransferMode.MOVE );
		              }
		           
		        event.consume();
		    }
		});
		
		bigCarTable.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	  
		              Dragboard db = event.getDragboard();
		              if (db.hasContent(SERIALIZED_MIME_TYPE)) {
		             
		                  event.acceptTransferModes( TransferMode.MOVE );
		              }
		           
		        event.consume();
		    }
		});
		
		smallCarTable.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
				    	
		         if (event.getGestureSource() != smallCarTable &&
		                 event.getDragboard().hasString()) {
		        	 smallCarTable.setVisible(false);
		         }
		                
		         event.consume();
		    }
		});
		
		smallCarTable.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
	
		    	if(event.getGestureSource() == OrderTable && !index.equals(null) ) {
		    		smallCarTable.getItems().add(OrderTable.getItems().get(index));
           
		    		OrderTable.getItems().remove(index.intValue());
		    		index = null;
                    event.setDropCompleted(true);
		    		
		    	}
		    		
		                
		         event.consume();
		    }
		});
		
		bigCarTable.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
	
		    	if(event.getGestureSource() == OrderTable && !index.equals(null) ) {
		    		bigCarTable.getItems().add(OrderTable.getItems().get(index));
           
		    		OrderTable.getItems().remove(index.intValue());
		    		index = null;
                    event.setDropCompleted(true);
		    		
		    	}
		    		
		                
		         event.consume();
		    }
		});
	}
	private void dragAndDrop() {
		
		OrderTable.setRowFactory(tv -> {
	            TableRow<Order> row = new TableRow<>();

	            row.setOnDragDetected(event -> {
	                if (! row.isEmpty()) {
	                    Integer index = row.getIndex();
	                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
	                    db.setDragView(row.snapshot(null, null));
	                    ClipboardContent cc = new ClipboardContent();
	                    cc.put(SERIALIZED_MIME_TYPE, index);
	                    db.setContent(cc);
	                    event.consume();
	                }
	            });

	            row.setOnDragOver(event -> {
	                Dragboard db = event.getDragboard();
	                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
	                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
	                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                        event.consume();
	                    }
	                }
	            });
	            
	            row.setOnDragDropped(event -> {
	                Dragboard db = event.getDragboard();
	                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
	                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
	                    Order draggedPerson = OrderTable.getItems().remove(draggedIndex);

	                    int dropIndex ; 

	                    if (row.isEmpty()) {
	                        dropIndex = OrderTable.getItems().size();
	                    } else {
	                        dropIndex = row.getIndex();
	                    }

	                    OrderTable.getItems().add(dropIndex, draggedPerson);
	                    //smallCarTable.getItems().add(dropIndex, draggedPerson);

	                    event.setDropCompleted(true);
	                    OrderTable.getSelectionModel().select(dropIndex);
	                    //smallCarTable.getSelectionModel().select(dropIndex);
	                    event.consume();
	                }
	            });

	            return row ;
	        });
			
		}
	
	@FXML private void buildRout() {
		
		exportToExcel();
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(API_KEY)
			    .build();
		    		
		    DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
		    apiRequest.origin("Karmiel");
		    apiRequest.destination("Karmiel");
		    apiRequest.optimizeWaypoints(true);
		   // String[] waypoints = {"32.794044, 34.989571", "32.8499966, 35.249999", "32.109333, 34.855499"};
			
		    //List<Order> orders = smallCarTable.getItems();
		    
		    //TableColumn<Order, String> column = ... ; // column you want

		    List<String> columnData = new ArrayList<>();
		    for (Order item : smallCarTable.getItems()) {
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
		            /*bestRout.setText(getOptimalRoute()); */
		            System.out.println(getOptimalRoute());
		            //getOptimalRoute();
		            //bestRout.setText(Arrays.toString(routes[0].waypointOrder));
			        System.out.println(gson.toJson(routes));
			        latch.countDown();
		        }

		        private String getOptimalRoute() {
					String s ="Optimal route: ";
		        	//Arrays.toString(routes[0].waypointOrder);
		        	
		        	for( int i = 0; i <= routes[0].waypointOrder.length - 1; i++) {
		        		//System.out.println(wayp[routes[0].waypointOrder[i]].toString());
		        		s = s.concat(wayp[routes[0].waypointOrder[i]].toString()).concat(", ");
		        	}
		        	
		        	
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
		  
	
	
}

	
private void exportToExcel() {
	 Workbook workbook = new HSSFWorkbook();
     Sheet spreadsheet = workbook.createSheet("sample");

     Row row = spreadsheet.createRow(0);

     for (int j = 0; j < smallCarTable.getColumns().size(); j++) {
         row.createCell(j).setCellValue(smallCarTable.getColumns().get(j).getText());
     }

     for (int i = 0; i < smallCarTable.getItems().size(); i++) {
         row = spreadsheet.createRow(i + 1);
         for (int j = 0; j < smallCarTable.getColumns().size(); j++) {
             if(smallCarTable.getColumns().get(j).getCellData(i) != null) { 
                 row.createCell(j).setCellValue(smallCarTable.getColumns().get(j).getCellData(i).toString()); 
             }
             else {
                 row.createCell(j).setCellValue("");
             }   
         }
     }

     FileOutputStream fileOut;
	try {
		fileOut = new FileOutputStream("workbook.xls");
		workbook.write(fileOut);
		fileOut.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
     
		
	}

private void dragAndDrop2() {
		
	smallCarTable.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (! row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });
            
            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    Order draggedPerson = OrderTable.getItems().remove(draggedIndex);

                    int dropIndex ; 

                    if (row.isEmpty()) {
                        dropIndex = smallCarTable.getItems().size();
                    } else {
                        dropIndex = row.getIndex();
                    }

                    smallCarTable.getItems().add(dropIndex, draggedPerson);
                    //smallCarTable.getItems().add(dropIndex, draggedPerson);

                    event.setDropCompleted(true);
                    smallCarTable.getSelectionModel().select(dropIndex);
                    //smallCarTable.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row ;
        });
		
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
		
		OrderTable.setItems(orders);
	}

	public void initData(User currentUser) {
		// TODO Auto-generated method stub
		
	}
	
	

}
