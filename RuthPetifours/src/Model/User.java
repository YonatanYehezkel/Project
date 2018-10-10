package Model;

public class User {
	
	public int id;
	public String userName;
	public String password;
	public String question1;
	public String question2;
	public String answer1;
	public String answer2;
	//public int idJobRole;
	public String JobRole;
	public String firstName;
	public String secondName;
	
	public User() {
		super();		
	}
	
	
	
	public User(int id, String userName, String password, String question1, String question2, String answer1,
			String answer2, String jobRole, String firstName, String secondName) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.question1 = question1;
		this.question2 = question2;
		this.answer1 = answer1;
		this.answer2 = answer2;
		//this.idJobRole = idJobRole;
		JobRole = jobRole;
		this.firstName = firstName;
		this.secondName = secondName;
	}



	public User(int id, String userName, String password, String question1, String question2, String answer1,
			String answer2, String JobRole) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.question1 = question1;
		this.question2 = question2;
		this.answer1 = answer1;
		this.answer2 = answer2;
		//this.idJobRole = idJobRole;
		this.JobRole = JobRole;
	}
	
	public User(int id, String userName, String password, String question1, String question2, String answer1,
			String answer2) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.question1 = question1;
		this.question2 = question2;
		this.answer1 = answer1;
		this.answer2 = answer2;
		//this.idJobRole = idJobRole;
	}
	
	public User(String userName, String password, String question1, String question2, String answer1,
			String answer2) {
		super();
		this.userName = userName;
		this.password = password;
		this.question1 = question1;
		this.question2 = question2;
		this.answer1 = answer1;
		this.answer2 = answer2;
		//this.idJobRole = idJobRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	
	public String getJobRole() {
		return JobRole;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getSecondName() {
		return secondName;
	}



	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}



	public void setJobRole(String jobRole) {
		JobRole = jobRole;
	}
	
	
	
	
	

}
