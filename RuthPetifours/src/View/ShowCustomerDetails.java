package View;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ShowCustomerDetails implements Initializable{
	
	@FXML private Label name;
	@FXML private Label adress;
	@FXML private Label contact1Name;
	@FXML private Label contact1Phone;
	@FXML private Label contact1JobRole;
	@FXML private Label contact2Name;
	@FXML private Label contact2Phone;
	@FXML private Label contact2JobRole;
	@FXML private TextArea comment;
	

	private Customer curCustomer;

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void initData(Customer c) {
		this.curCustomer = c;
		//System.out.println(curCustomer.getAdress());
		setData();
	  }
	private void setData() {
		this.name.setText(curCustomer.getCustomerName());
		
		if(curCustomer.getAdress() != null)
			this.adress.setText(this.curCustomer.getAdress());
		else 
			this.adress.setText("none");
		if(!curCustomer.getContacts().isEmpty()) { 
			this.contact1Name.setText(this.curCustomer.getContacts().get(0).contactName);
			this.contact1Phone.setText(Integer.toString(this.curCustomer.getContacts().get(0).phoneNumber1));
			this.contact1JobRole.setText(this.curCustomer.getContacts().get(0).jobRole);
		}
		else {
			this.contact1Name.setText("none");
			this.contact1Phone.setText("none");
			this.contact1JobRole.setText("none");
			this.contact2Name.setText("none");
			this.contact2Phone.setText("none");
			this.contact2JobRole.setText("none");
		}
		
		if(curCustomer.getComment() != null) {
			this.comment.setText(this.curCustomer.getComment());
			this.comment.setEditable(false);
		}
		else {
			this.comment.setText("none");
			this.comment.setEditable(false);
		}
		
	}
}
