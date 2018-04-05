package Model;

public class JobRole {
	
	public int id;
	public String jobRole;
	
	
	public JobRole(int id, String jobRole) {
		super();
		this.id = id;
		this.jobRole = jobRole;
	}
	
	public JobRole(String jobRole) {
		super();
		this.jobRole = jobRole;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	@Override
	public String toString() {
		return jobRole;
	}
	
	

}
