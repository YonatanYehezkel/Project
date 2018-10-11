package Controller;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;

import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import exceptions.ApiException;
import googleMap.LatLng;
import googleMap.PendingResult;
import googleMap.TravelMode;
import googleMap.DirectionsApi;
import googleMap.DirectionsApiRequest;
import googleMap.DirectionsResult;
import googleMap.DirectionsRoute;
import googleMap.DistanceMatrix;
import googleMap.DistanceMatrixApi;
import googleMap.DistanceMatrixApiRequest;
import googleMap.GeoApiContext;
import googleMap.GeocodingApi;
import googleMap.GeocodingResult;




public class MainClass extends Application {
	
	private double xOffset = 0;
    private double yOffset = 0;
	
	public static Stage primaryStage;
    private static AnchorPane rootLayout;
    
  //ControllerLogic reference pointer
  	private static ControllerLogic controller;
  	
	private static final String API_KEY = "AIzaSyAIgMRRrFNahxoMfyQdsi7T07SeQ79lEgY";
	//private static final GeoApiContext context = new GeoApiContext().setApiKey(API_KEY);


    private Group container;
   // private RadialSettingsMenu radialMenu;
    private boolean visible = false;

  	
  	public static Stage getPrimaryStage() {
        return primaryStage;
    }

	@Override
	public void start(final Stage primaryStage) {
		this.primaryStage = primaryStage;
        /*this.primaryStage.setTitle("Ruth Petifours");
        this.primaryStage.setFullScreenExitHint("");
        this.primaryStage.setMaximized(true);
       initRootLayout();*/
		
		try {
			openNewLogIn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* radial menu */
		/*container = new Group();
		
		final Scene scene = new Scene(container, Color.TRANSPARENT);
		primaryStage.setResizable(true);
		primaryStage.setScene(scene);
		primaryStage.setWidth(350);
		primaryStage.setHeight(380);
		primaryStage.setAlwaysOnTop(true);
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(primaryScreenBounds.getMinX());
		primaryStage.setY(primaryScreenBounds.getMinY());
		//primaryStage.centerOnScreen();
		primaryStage.setTitle("Radial Settings Menu Demo");
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();

		radialMenu = new RadialSettingsMenu();
		container.getChildren().addAll(radialMenu);

		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

		    @Override
		    public void handle(final MouseEvent event) {
			if (event.isSecondaryButtonDown()) {
			    radialMenu.setTranslateX(event.getX());
			    radialMenu.setTranslateY(event.getY());
			}
		    }
		});

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

		    @Override
		    public void handle(final KeyEvent event) {
			System.out.println(event);
			if (event.getCode() == KeyCode.F5) {
				MainClass.this.takeSnapshot(scene);
			}
		    }
		});
*/
	    
     
	}
	
	/*int snapshotCounter = 0;

	    private void takeSnapshot(final Scene scene) {
		// Take snapshot of the scene
		final WritableImage writableImage = scene.snapshot(null);

		// Write snapshot to file system as a .png image
		final File outFile = new File("snapshot/radialmenu-snapshot-"
			+ snapshotCounter + ".png");
		outFile.getParentFile().mkdirs();
		try {
		    ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png",
			    outFile);
		} catch (final IOException ex) {
		    System.out.println(ex.getMessage());
		}

		snapshotCounter++;
	    }*/
	
	private void openNewLogIn() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/logIn/profile.fxml"));

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        
        Scene scene = new Scene(root);



        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	

	/*public static void initRootLayout() {
		
		 try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainClass.class.getResource("/View/LoginScreen.fxml"));
	            rootLayout = (AnchorPane) loader.load();
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.setFullScreenExitHint("");
	            primaryStage.setMaximized(true);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}*/

	public static void main(String[] args) {
		launch(args);
		//DB db = new DB();
		//db.getAllCustomers();
		controller = new ControllerLogic();
		//System.out.println(controller.getUserByUsername("TestUser").getPassword());
		//controller.addNewOrder(new Order());
		//System.out.println(controller.getProductByID(1111).getTitle());
		//System.out.println(controller.getAllOrders().get("1").getCustomer());
		//System.out.println(controller.getAllCustomers().get(1).getCustomerName());
		
		
		LocalDateTime updated_date=LocalDateTime.now();
	    //Timestamp timestamp = new Timestamp(updated_date.getTime());
	    //updated_date = timestamp;
		//System.out.println(updated_date);
		
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyAIgMRRrFNahxoMfyQdsi7T07SeQ79lEgY")
			    .build();
		GeocodingResult[] results = null;
			try {
				results = GeocodingApi.geocode(context,
				    "sdfsdf").await();
			} catch (ApiException | InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			System.out.println(gson.toJson(results[0].addressComponents));
			
		LatLng LatLng = new LatLng(32.919945, 35.290146);
		LatLng LatLngHaifa = new LatLng(32.794044, 34.989571);
		LatLng LatLngMisgav = new LatLng(32.8499966, 35.249999);
		DateTime dt = DateTime.now();
			
		//DistanceMatrix m = estimateRouteTime(dt, false, null, LatLng, LatLngHaifa);
			//System.out.println(m);
		
		//dir();
			
			
			

	}
	

	public static void g () {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyBgZxet8V_vUHpGyO9qmLaM46z6kTxDahs")
			    .build();
		    DistanceMatrixApiRequest req = DistanceMatrixApi.getDistanceMatrix(context,
		    new String[]{"Karmiel"},
		    new String[]{"Haifa", "Tel Aviv"});

		    final CountDownLatch latch = new CountDownLatch(1);
		    req.setCallback(new PendingResult.Callback<DistanceMatrix>() {
		      @Override
		      public void onResult(DistanceMatrix result) {
		        Gson gson = new GsonBuilder().setPrettyPrinting().create();
		        System.out.println(gson.toJson(result));
		        latch.countDown();
		      }

		      @Override
		      public void onFailure(Throwable e) {
		        System.out.println("Exception thrown: "+e);
		        latch.countDown();
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
	
	public static void dir () {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyAIgMRRrFNahxoMfyQdsi7T07SeQ79lEgY")
			    .build();
		    //DirectionsApiRequest req = DirectionsApi.getDirections(context, origin, destination)
		    		
		    DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
		    apiRequest.origin(new LatLng(32.919945, 35.290146));
		    apiRequest.destination(new LatLng(32.919945, 35.290146));
		    apiRequest.optimizeWaypoints(true);
		    String[] waypoints = {"32.794044, 34.989571", "32.8499966, 35.249999", "32.109333, 34.855499"};
			apiRequest.waypoints(waypoints);
			
		    
		    
		    
		    apiRequest.mode(TravelMode.DRIVING); 


		    final CountDownLatch latch = new CountDownLatch(1);
		    System.out.println("1");
		    apiRequest.setCallback(new PendingResult.Callback<DirectionsResult>() {
		    	 
		        @Override
		        public void onResult(DirectionsResult result) {
		        	System.out.println("2");
		            DirectionsRoute[] routes = result.routes;
		            Gson gson = new GsonBuilder().setPrettyPrinting().create();
			        System.out.println(gson.toJson(routes));
			        latch.countDown();
		        }

		        @Override
		        public void onFailure(Throwable e) {
		        	System.out.println("3");
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
	

		public static DistanceMatrix estimateRouteTime(DateTime time, Boolean isForCalculateArrivalTime, DirectionsApi.RouteRestriction routeRestriction, LatLng departure, LatLng... arrivals) {
			GeoApiContext context = new GeoApiContext.Builder()
				    .apiKey("AIzaSyBgZxet8V_vUHpGyO9qmLaM46z6kTxDahs")
				    .build();
			
			try {
		        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		        if (isForCalculateArrivalTime) {
		            req.departureTime(time);
		        } else {
		            req.arrivalTime(time);
		        }
		        if (routeRestriction == null) {
		            routeRestriction = DirectionsApi.RouteRestriction.TOLLS;
		        }
		        DistanceMatrix trix = req.origins(departure)
		                .destinations(arrivals)
		                .mode(TravelMode.DRIVING)
		                .avoid(routeRestriction)
		                .language("fr-FR")
		                .await();
		        Gson gson = new GsonBuilder().setPrettyPrinting().create();
				System.out.println(gson.toJson(trix.rows.toString()));
				
		        return trix;

		    } catch (ApiException e) {
		        System.out.println(e.getMessage());
		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		    }
		    return null;
		
	}
}
