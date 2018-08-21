package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.City;
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

public class MostDemandingAreasController implements Initializable{
	
	private ControllerLogic controller;
	
	@FXML private Button back;
	
	@FXML private CategoryAxis xAxis;
	
	@FXML private NumberAxis yAxis;
	
	@FXML private BarChart<?, ?> chart;
	
	private int total = 0;
	
	ArrayList<City> CitiesToShow;
	
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
		// TODO Auto-generated method stub
		controller = new ControllerLogic();
		CitiesToShow = new ArrayList<City>();
		CitiesToShow = controller.getDemandByLocation();
		
		xAxis.setTickLabelRotation(90);
		xAxis.setLabel("Orders Demand Percentage");  
        yAxis.setLabel("City"); 
        
        XYChart.Series Data = new XYChart.Series<>();
        
        for (City c : CitiesToShow) {
        	this.total += c.getDemandNumber();
        }
        
        for (City c : CitiesToShow) {
        	c.setCurrentPercantage(c.getDemandNumber()/(this.total));
        	System.out.println((1/8));
        }
        
        for (City c : CitiesToShow) {
        	Data.getData().add(new XYChart.Data(c.getName(), c.getCurrentPercantage()));
        }
        
        chart.getData().addAll(Data);
	}

}
