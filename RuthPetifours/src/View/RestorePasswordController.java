package View;

import java.awt.Button;
import java.io.IOException;

import javax.swing.JOptionPane;

import Controller.ControllerLogic;
import Model.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RestorePasswordController {
	
	private User currentUser;
	@FXML private AnchorPane screen;
	@FXML private Text answer1;
	@FXML private TextField yourAnswer;
	@FXML private Text getSecondQ;
	@FXML private Button save;
	
	  //ControllerLogic reference pointer
	private static ControllerLogic controller;
    public static Stage primaryStage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;
	private int count;
	

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
		if(count == 0 && !yourAnswer.getText().equals(currentUser.getAnswer1())) {
			getSecondQ.setVisible(true);
		}
		else if(count == 0 && yourAnswer.getText().equals(currentUser.getAnswer1())) {
			JOptionPane.showMessageDialog(null, currentUser.getPassword());
		}
		
		//else if(count == 1 && !yourAnswer.getText().equals(currentUser.getAnswer2())) {
		else if(count == 1 && (yourAnswer.getText() == null || !yourAnswer.getText().equals(currentUser.getAnswer2()))) {
			getSecondQ.setText("Your answer is wrong. Contact your manager.");
			getSecondQ.setVisible(true);
		}
		else if(count == 1 && yourAnswer.getText().equals(currentUser.getAnswer2())) {
			JOptionPane.showMessageDialog(null, currentUser.getPassword());
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
        
        screen.setVisible(false);
	}


}
