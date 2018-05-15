package View;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.User;
import googleMap.DirectionsApi;
import googleMap.DirectionsApiRequest;
import googleMap.DirectionsResult;
import googleMap.DirectionsRoute;
import googleMap.GeoApiContext;
import googleMap.LatLng;
import googleMap.PendingResult;
import googleMap.TravelMode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BestRouteController implements Initializable{
	
	@FXML private TextField origin;
	@FXML private TextField destination;
	@FXML private TextField waypoints;
	@FXML private TextField bestRout;
	@FXML private Button run;
	@FXML private Button Back;
	@FXML private Label cur_user;
	
	private ControllerLogic controller;
	private User currentUser;
	private String[] wayp;
	private DirectionsRoute[] routes;
	private static final String API_KEY = "AIzaSyAIgMRRrFNahxoMfyQdsi7T07SeQ79lEgY";
	
	
	@FXML private void calculateOptimalRoute() {
		
			GeoApiContext context = new GeoApiContext.Builder()
				    .apiKey(API_KEY)
				    .build();
			    		
			    DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
			    apiRequest.origin(origin.getText());
			    apiRequest.destination(destination.getText());
			    apiRequest.optimizeWaypoints(true);
			   // String[] waypoints = {"32.794044, 34.989571", "32.8499966, 35.249999", "32.109333, 34.855499"};
				apiRequest.waypoints(waypoints.getText().split(","));
				wayp = waypoints.getText().split(",");
				
			    apiRequest.mode(TravelMode.DRIVING); 


			    final CountDownLatch latch = new CountDownLatch(1);
			    
			    apiRequest.setCallback(new PendingResult.Callback<DirectionsResult>() {
			    	 
			        @Override
			        public void onResult(DirectionsResult result) {
			            routes = result.routes;
			           
			            Gson gson = new GsonBuilder().setPrettyPrinting().create();
			           //routes[1].waypointOrder.toString();
			           //System.out.println(Arrays.toString(routes[0].waypointOrder));
			            bestRout.setText(getOptimalRoute());
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
	
	@FXML private void goBackToMainMenu() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/MainMenuScreen.fxml"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			MainMenuController cont = 
				    loader.<MainMenuController>getController();
				  cont.initData(currentUser);
			
			MainClass.getPrimaryStage().setScene(appSetScene);
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setFullScreen(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML private void logOut() {
		System.exit(0);
	}
	
	public void initData(User u) {
		this.currentUser = u;
		cur_user.setText(currentUser.getUserName());
	  }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		controller = new ControllerLogic();
	}
}
