package View;

import Model.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class RestorePasswordController {
	
	private User currentUser;
	@FXML private Text answer1;
	
	void initialize() {}
	void initData(User u) {
		this.currentUser = u;
		answer1.setText(currentUser.getQuestion1());
		System.out.println(currentUser.getQuestion1());
	  }

}
