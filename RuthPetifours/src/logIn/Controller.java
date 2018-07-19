package logIn;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.User;
import NewMenu.NewMenu;
import View.MainMenuController;
import View.RestorePasswordController;

public class Controller implements Initializable {
	
	@FXML private ComboBox<String> userList;
	@FXML private PasswordField password;
	@FXML private AnchorPane Login;
	@FXML private Label errorUser;
	@FXML private Label errorPassword;

    @FXML
    ImageView ic;
    @FXML
    Circle pic;
    @FXML
    Circle min;
    @FXML
    Circle close;
    ActionEvent event;

    @FXML
    Label login;
    private double xOffset = 0;
    private double yOffset = 0;
    
    //ControllerLogic reference pointer
  	private static ControllerLogic controller;
  	
  	private User currentUser;

    private void aa(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println("jsdfh");
      //  change(this);

    }


    /*
  	 * constructor
  	 */
  	public Controller() {
  		controller = new ControllerLogic();
  	}



    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


       

        //Image image = new Image(getClass().getResource("pic.jpg").toExternalForm());
        //pic.setFill(new ImagePattern(image));

                login.setText("Login");
                
                
                fillComboBox();
        		currentUser = new User();
        		errorUser.setVisible(false);
        	  	errorPassword.setVisible(false);

    }
    
    @FXML private void fillComboBox(){
		 userList.getItems().addAll(controller.getAllUsers().keySet());
	    }

    @FXML
    public void back(MouseEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("pin.fxml"));
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        blah.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        blah.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appStage.setX(event.getScreenX() - xOffset);
                appStage.setY(event.getScreenY() - yOffset);
            }
        });

        Scene scene = new Scene(blah);


        appStage.setScene(scene);
        appStage.show();

    }

    /**** minimize ****/
    @FXML
    public void minclick(MouseEvent event) throws IOException {

        ((Stage)((Circle)event.getSource()).getScene().getWindow()).setIconified(true);


    }

    /**** close screen ****/
    @FXML
    public void closeclick(MouseEvent event) throws IOException {


        System.exit(0);


    }
    
	@FXML public void validateUserInput () {
  		if (userList.getSelectionModel().isEmpty())
  			errorUser.setVisible(true);
  		else if(password.getText().equals(controller.getAllUsers().get(userList.getSelectionModel().getSelectedItem()).getPassword())) {
			goToMainMenu();
		}
  		else if(password.getText().equals(""))
  			errorPassword.setVisible(true);	
  		else if(!password.getText().equals(controller.getAllUsers().get(userList.getSelectionModel().getSelectedItem()).getPassword())) {
  			errorPassword.setText("password is wrong");
  			errorPassword.setVisible(true);
  		}
  			
	}
	
	 @FXML private void goToMainMenu(){
		 
			/* try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(OrdersSortingController.class.getResource("/View/OrdersSortingScreen.fxml"));
					AnchorPane appSet = loader.load();
					Scene appSetScene = new Scene(appSet);
					OrdersSortingController cont = 
						    loader.<OrdersSortingController>getController();
						  cont.initData(currentUser);
						  
					MainClass.getPrimaryStage().setScene(appSetScene);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			 
		 //NewMenu m = new NewMenu();
			try {
				
				
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainMenuController.class.getResource("/View/MainMenuScreen.fxml"));
					AnchorPane appSet = loader.load();
					Scene appSetScene = new Scene(appSet);
					MainMenuController cont = 
							    loader.<MainMenuController>getController();
							  cont.initData(currentUser);
					
					MainClass.getPrimaryStage().setScene(appSetScene);
					MainClass.getPrimaryStage().setFullScreenExitHint("");
					MainClass.getPrimaryStage().setMaximized(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		 }
		 
		 @FXML private void openResrorePasswordWindow() {
			 //JOptionPane.showMessageDialog(null, "ok");
			 if(userList.getSelectionModel().isEmpty()) {
				 //Alert alert = new Alert(AlertType.INFORMATION);
				 //alert.setContentText("Select User");
				 JOptionPane.showMessageDialog(null, "select User");
			 }
			 else {	 
				 try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(MainMenuController.class.getResource("/View/RestorePassword.fxml"));
						AnchorPane appSet = loader.load();
						Scene appSetScene = new Scene(appSet);
						  RestorePasswordController cont = 
								    loader.<RestorePasswordController>getController();
								  cont.initData(currentUser);
						MainClass.getPrimaryStage().setScene(appSetScene);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
		 }
		 
		 @FXML private void setSelectedUser() {
			 errorUser.setVisible(false);
			 String selected_text = userList.getSelectionModel().getSelectedItem();
			 currentUser = controller.getUserByUsername(selected_text);
			 }
		 
		 
		 
}