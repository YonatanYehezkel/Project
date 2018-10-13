package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MostProfitableCustomersController implements Initializable{
	
	private ControllerLogic controller;
	
	@FXML private Button back;
	
	@FXML private CategoryAxis xAxis;
	
	@FXML private NumberAxis yAxis;
	
	@FXML private BarChart<?, ?> chart;
	
	ArrayList<Customer> CustomersToShow = new ArrayList<Customer>();
	
	@FXML private void goBack() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenuController.class.getResource("/View/ReportsOptionsScreen.fxml‬"));
			AnchorPane appSet = loader.load();
			Scene appSetScene = new Scene(appSet);
			
			MainClass.getPrimaryStage().setScene(appSetScene);
			//MainClass.getPrimaryStage().setFullScreenExitHint("");
			//MainClass.getPrimaryStage().setMaximized(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		controller = new ControllerLogic();
		CustomersToShow = controller.getMostProfitableCustomers();
		
		xAxis.setTickLabelRotation(90);
		xAxis.setLabel("Customer Name");  
        yAxis.setLabel("Orders Sum Value"); 
        
        XYChart.Series Data = new XYChart.Series<>();
        
        for (Customer c : CustomersToShow) {
        	Data.getData().add(new XYChart.Data(c.getCustomerName(), c.getOrdersSum()));
        	System.out.println(c.toString());
        }
        
        chart.getData().addAll(Data);
	}

}
