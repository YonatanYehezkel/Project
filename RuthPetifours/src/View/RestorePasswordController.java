package View;

import javax.swing.JOptionPane;

import Controller.ControllerLogic;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RestorePasswordController {
	
	private User currentUser;
	@FXML private Text answer1;
	@FXML private TextField yourAnswer;
	@FXML private Text getSecondQ;
	
	  //ControllerLogic reference pointer
	private static ControllerLogic controller;
	
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
		count = 1;
		answer1.setText(currentUser.getQuestion2());
		yourAnswer.setText(null);
		getSecondQ.setVisible(false);
	}

}
