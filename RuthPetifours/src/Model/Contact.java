package Model;

public class Contact {
	
	public String contactName;
	public int phoneNumber1;
	public int phoneNumber2;
	public String email1;
	public String email2;
	public String jobRole;
	public String CompanyName;
	


	/*
	 * full constructor
	 */
	public Contact(String contactName, int phoneNumber1, int phoneNumber2, String email1, 
			String email2, String jobRole, String CompanyName) {
		super();
		this.contactName = contactName;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.email1 = email1;
		this.email2 = email2;
		this.jobRole = jobRole;
		this.CompanyName = CompanyName;
	}
	
	public Contact(String contactName, int phoneNumber1, String email1, 
		 String jobRole, String CompanyName) {
		super();
		this.contactName = contactName;
		this.phoneNumber1 = phoneNumber1;
		this.email1 = email1;
		this.jobRole = jobRole;
		this.CompanyName = CompanyName;
	}
	
	

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public int getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(int phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public int getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(int phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	
	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	
	
	
	
	

}
