import java.util.ArrayList;


public class User {
	private String id;
	private String name;
	private ArrayList<Quiz> quizzes;
	private String username;
	private String password;
	private String role;
	private String email;
	
	public User(String name, String role, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(ArrayList<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Question answerQuestion(String quizId, String questionId){
		Quiz q = new Quiz(quizId);
		Quiz quiz = quizzes.get(quizzes.indexOf(q));
		return quiz.answerQuestion(questionId);
	}
	
}
