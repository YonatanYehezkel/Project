package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.ControllerLogic;
import Controller.MainClass;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class BestSellingReportController implements Initializable {
	
	private ControllerLogic controller;
	
    @FXML PieChart chart;
	

	
	ArrayList<Product> ProductsToShow;
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		controller = new ControllerLogic();
		ProductsToShow = controller.getBestSelllingProducts();
		System.out.println(ProductsToShow.toString());
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
		
		for (Product p:ProductsToShow) {
			pieChartData.add(new PieChart.Data(p.getTitle(),p.getSalesQuantity()));
		}
		
		chart.setData(pieChartData);
	}

}
