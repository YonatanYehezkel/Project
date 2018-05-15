package View;

import java.net.URL;
import java.util.ResourceBundle;


import Model.Order;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class OrdersSortingController implements Initializable{
	
	@FXML private TableView<Order> OrderTable;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public void initData(User currentUser) {
		// TODO Auto-generated method stub
		
	}

}
