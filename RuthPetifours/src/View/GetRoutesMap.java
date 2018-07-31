package View;

import java.util.Locale;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GetRoutesMap {
	
	protected GoogleMapView mapComponent;
    protected GoogleMap map;
    protected DirectionsPane directions;
    
    public void main (String args[]) {
    	mapComponent = new GoogleMapView();
    }
	

}
