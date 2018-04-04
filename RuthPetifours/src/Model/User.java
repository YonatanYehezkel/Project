package Model;

public class User {
	
	public int id;
	public String userName;
	public String password;
	public String question1;
	public String question2;
	public String answer1;
	public String answer2;
	public int idJobRole;
	
	public User() {
		super();		
	}
	
	public User(int id, String userName, String password, String question1, String question2, String answer1,
			String answer2, int idJobRole) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.question1 = question1;
		this.question2 = question2;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.idJobRole = idJobRole;
	}
	
	public User(String userName, String password, String question1, String question2, String answer1,
			String answer2, int idJobRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.question1 = question1;
		this.question2 = question2;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.idJobRole = idJobRole;
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

	public int getIdJobRole() {
		return idJobRole;
	}

	public void setIdJobRole(int idJobRole) {
		this.idJobRole = idJobRole;
	}
	
	
	
	
	

}
