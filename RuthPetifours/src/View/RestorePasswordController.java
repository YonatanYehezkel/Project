package View;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Controller.ControllerLogic;
import Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RestorePasswordController implements Initializable{
	
	private User currentUser;
	@FXML private AnchorPane screen;
	//@FXML private BorderPane border;
	@FXML private Text answer1;
	@FXML private TextField yourAnswer;
	@FXML private Text getSecondQ;
	@FXML private Button save;
	@FXML
    Circle min;
    @FXML
    Circle close;
    ActionEvent event;
	
	  //ControllerLogic reference pointer
	private static ControllerLogic controller;
    public static Stage primaryStage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;
	private int count=0;
	

  	/*
  	 * constructor
  	 */
  	public RestorePasswordController() {
  		controller = new ControllerLogic();
  	}
	
	public void initData(User u) {
		this.currentUser = u;
		answer1.setText(currentUser.getQuestion1());
		System.out.println(currentUser.getQuestion1());
		count = 0;
		getSecondQ.setVisible(false);
	  }
	
	public void hideError() {
		getSecondQ.setVisible(false);
	}
	
	public void validateAnswer() {
		//JOptionPane.showMessageDialog(null, "ok");
		if(count == 0 && !yourAnswer.getText().equals(currentUser.getAnswer1().toString())) {
			getSecondQ.setVisible(true);
		}
		else if(count == 0 && yourAnswer.getText().equals(currentUser.getAnswer1().toString())) {
			//JOptionPane.showMessageDialog(null, currentUser.getPassword());
			Alert alert = new Alert(AlertType.INFORMATION);
			 alert.setTitle("Password Restore");
			 alert.setHeaderText(null);
			 alert.setContentText("Your password is: \n"+currentUser.getPassword());
			 alert.showAndWait();
		}
		
		//else if(count == 1 && !yourAnswer.getText().equals(currentUser.getAnswer2())) {
		else if(count == 1 && (yourAnswer.getText() == null || !yourAnswer.getText().equals(currentUser.getAnswer2()))) {
			getSecondQ.setText("Your answer is wrong. Contact your manager.");
			getSecondQ.setVisible(true);
		}
		else if(count == 1 && yourAnswer.getText().equals(currentUser.getAnswer2())) {
			//JOptionPane.showMessageDialog(null, currentUser.getPassword());
			 Alert alert = new Alert(AlertType.INFORMATION);
			 alert.setTitle("Password Restore");
			 alert.setHeaderText(null);
			 alert.setContentText("Your password is: \n"+currentUser.getPassword());
			 alert.showAndWait();
		}
		
		/*if(yourAnswer.getText().equals(currentUser.getAnswer1()))
			JOptionPane.showMessageDialog(null, currentUser.getPassword());
		else if(answer1.getText().equals(currentUser.getQuestion2()) && !yourAnswer.getText().equals(currentUser.getAnswer2()))
			JOptionPane.showMessageDialog(null, "contact your manager");
		else if(answer1.getText().equals(currentUser.getQuestion2()) && yourAnswer.getText().equals(currentUser.getAnswer2()))
			JOptionPane.showMessageDialog(null, "ok");
		else {
			answer1.setText(currentUser.getQuestion2());
			yourAnswer.setText(null);*/
		
			
	}
	
	public void getSecondQuestion() {
		primaryStage = new Stage();
		count = 1;
		answer1.setText(currentUser.getQuestion2());
		yourAnswer.setText(null);
		getSecondQ.setVisible(false);
	}
	
	public void openNewLogIn() throws IOException {
		
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
        
        //border.setVisible(false);
        screen.setVisible(false);
        
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}


}
