package NewMenu;


import java.io.IOException;

//import de.michaprogs.crm.database.CreateTables;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class NewMenu extends Application {
	
		private static Stage stage;
		private BorderPane contentPane = new BorderPane();
		private String programName = "Ruth Petifours";
		
		
		private double xOffset = 0;
	    private double yOffset = 0;
	    public static Stage primaryStage;
	    
		@Override
		public void start(Stage stage) {

			try {
				
				this.stage = stage;
				
				primaryStage = new Stage();
				
				try {
					openNewLogIn();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				//new CreateTables();
				
				LoadMenuBar menubar = new LoadMenuBar(false, this);			
				LoadNavigation navigation = new LoadNavigation(this);			
				LoadCustomerData customerData = new LoadCustomerData(false, this);	
				
				contentPane.setTop(menubar.getContent());
				contentPane.setLeft(navigation.getContent());			
				contentPane.setCenter(customerData.getContent());
				
				Scene scene = new Scene(contentPane);
				scene.getStylesheets().add("style.css");
				
				stage.setScene(scene);
				stage.setTitle(programName);
				stage.getIcons().add(new Image("file:Images/app_icon.png"));
				stage.setWidth(1200);
				stage.setHeight(600);
				stage.setMaximized(true);
				//stage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}	
		
		public static void main(String[] args) {
			launch(args);
		}
		
		public static Stage getStage(){
			return stage;
		}
		
		public BorderPane getContentPane(){
			return contentPane;
		}
		
		public String getProgramName(){
			return programName;
		}

		private void openNewLogIn() throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("/logIn/profile2.fxml"));

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
		
		public static Stage getPrimaryStage() {
	        return primaryStage;
	    }
	


}
