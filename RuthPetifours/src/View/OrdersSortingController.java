package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;


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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


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
	@FXML private TextField sumSmallCar;
	@FXML private TextField sumBigCar;
	@FXML private TextField sumAll;

	
	
	@FXML private ObservableList<Order> orders;
	
	private ControllerLogic controller;
	private User currentUser;
	private Integer index = null;
	private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

	private String[] wayp;
	private DirectionsRoute[] routes;
	private int[] opt_route;
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
                    
                                      
                    
                    sumSmallCar.setText(Float.toString(calculateOrdersSum(smallCarTable)));
                    sumAll.setText(Float.toString(calculateOrdersSum(OrderTable)));
		    		
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
                    
                    
                    sumBigCar.setText(Float.toString(calculateOrdersSum(bigCarTable)));
                    sumAll.setText(Float.toString(calculateOrdersSum(OrderTable)));
		    		
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


	
	@FXML private void buildRoutSmall() {
		
		
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(API_KEY)
			    .build();
		    		
		    DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
		    apiRequest.origin("Karmiel");
		    apiRequest.destination("Karmiel");
		    apiRequest.optimizeWaypoints(true);
	

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
		           System.out.println(Arrays.toString(routes[0].waypointOrder));
		           opt_route = routes[0].waypointOrder;
		            /*bestRout.setText(getOptimalRoute()); */
		            System.out.println(getOptimalRoute());
		            //getOptimalRoute();
		            //bestRout.setText(Arrays.toString(routes[0].waypointOrder));
			        //System.out.println(gson.toJson(routes));
		            sortTablebyOptimalRoute(smallCarTable);
		            controller.exportToExcelRoute(smallCarTable, "smallCar");
			        latch.countDown();
		        }

		        

				private String getOptimalRoute() {
					String s ="Optimal route: ";
		        	//Arrays.toString(routes[0].waypointOrder);
		        	
		        	for( int i = 0; i <= routes[0].waypointOrder.length - 1; i++) {
		        		//System.out.println(wayp[routes[0].waypointOrder[i]].toString());
		        		s = s.concat(wayp[routes[0].waypointOrder[i]].toString()).concat(", ");
		        	}
		        	
		        	//System.out.println(routes[0].waypointOrder);
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
	
	@FXML private void buildRoutBig() {
		
		
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(API_KEY)
			    .build();
		    		
		    DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
		    apiRequest.origin("Karmiel");
		    apiRequest.destination("Karmiel");
		    apiRequest.optimizeWaypoints(true);
	

		    List<String> columnData = new ArrayList<>();
		    for (Order item : bigCarTable.getItems()) {
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
		           System.out.println(Arrays.toString(routes[0].waypointOrder));
		           opt_route = routes[0].waypointOrder;
		            /*bestRout.setText(getOptimalRoute()); */
		            System.out.println(getOptimalRoute());
		            //getOptimalRoute();
		            //bestRout.setText(Arrays.toString(routes[0].waypointOrder));
			        //System.out.println(gson.toJson(routes));
		            sortTablebyOptimalRoute(bigCarTable);
		            controller.exportToExcelRoute(bigCarTable, "bigCar");
			        latch.countDown();
		        }

		        

				private String getOptimalRoute() {
					String s ="Optimal route: ";
		        	//Arrays.toString(routes[0].waypointOrder);
		        	
		        	for( int i = 0; i <= routes[0].waypointOrder.length - 1; i++) {
		        		//System.out.println(wayp[routes[0].waypointOrder[i]].toString());
		        		s = s.concat(wayp[routes[0].waypointOrder[i]].toString()).concat(", ");
		        	}
		        	
		        	//System.out.println(routes[0].waypointOrder);
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
	
	private void sortTablebyOptimalRoute(TableView<Order> table) {
			
		 ObservableList<Order> orders = FXCollections.observableArrayList();
		for(int i=0; i<opt_route.length; i++) {
			orders.add(table.getItems().get(opt_route[i]));
			
		}
		
		table.setItems(orders);
		
		
		
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
		
		 sumAll.setText(Float.toString(calculateOrdersSum(OrderTable)));
	}

	public void initData(User currentUser) {
		// TODO Auto-generated method stub
		
	}
	
	

}
