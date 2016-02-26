import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseSingleton {
   private static DatabaseSingleton instance = null;
   private static ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
   private static ArrayList<User> users = new ArrayList<User>();
   
   private DatabaseSingleton() {
      
   }
   public static DatabaseSingleton getInstance() {
      if(instance == null) {
         instance = new DatabaseSingleton();
      }
      return instance;
   }
   
   public static ArrayList<Quiz> getQuizzes() {
	return quizzes;
}
public static void setQuizzes(ArrayList<Quiz> quizzes) {
	DatabaseSingleton.quizzes = quizzes;
}
public static ArrayList<User> getUsers() {
	return users;
}
public static void setUsers(ArrayList<User> users) {
	DatabaseSingleton.users = users;
}
public void loadDatabase(String fileName){
	   try {
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		String line = null;
		
		//the database file contains a list of questions, followed by a list of users
		int count = 0;
		boolean doneQuestions = false;
		while((line=file.readLine())!= null){
			String[] pieces = line.split(",");
			System.out.println("pieces: " + pieces[0]);
			if (!doneQuestions){
				count++;
				Question q = null;
				String quizId = null;
				if (line.startsWith("SA")){
					q = new Question(pieces[1],pieces[2]);
					quizId = pieces[3];
				}
				else if (line.startsWith("MC")){
					String[] options = pieces[2].split("#");
					ArrayList<String> o = new ArrayList<String>();
					for (int i = 0; i < options.length; i++)
						o.add(options[i]);
					q = new MultipleChoiceQuestion(pieces[1],pieces[3],o);	
					quizId = pieces[4];
				}
				else if (line.startsWith("C")){
					q = new CodingQuestion(pieces[1],pieces[2],pieces[3],pieces[4]);	
					quizId = pieces[5];					
				}
				else if (line.startsWith("USERS")){
					doneQuestions = true;
					continue;
				}
				
				q.setId(pieces[0]);
				Quiz quiz = new Quiz("none");
				Quiz.setCount(Quiz.getCount()-1);	//we're creating a dummy object
				quiz.setId(quizId);
				if (quizzes.contains(quiz)){
					Quiz stored = quizzes.get(quizzes.indexOf(quiz));
					stored.addQuestion(q);
				}else{
					quiz.addQuestion(q);
					quizzes.add(quiz);
				}
			}else{
				if (pieces[0].startsWith("U")){
					User user = new User(pieces[1],pieces[2],pieces[3],pieces[4]);
					user.setId(pieces[0]);
					users.add(user);
				}else{
					Quiz quiz = new Quiz("none");
					Quiz.setCount(Quiz.getCount()-1);	//we're creating a dummy object
					quiz.setId(pieces[0]);
					Quiz stored = quizzes.get(quizzes.indexOf(quiz));
					stored.setName(pieces[1]);
				}
			}
		}
		Question.setCount(count);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   public User getUser(String lookup){
	   for (int i = 0; i < users.size(); i++){
		   User user = users.get(i);
		   if (user.getId().equals(lookup) || user.getName().equals(lookup) || user.getUsername().equals(lookup))
			   return user;
	   }
	   return null;
   }
   
   public Quiz getQuiz(String lookup){
	   for (int i = 0; i < quizzes.size(); i++){
		   Quiz quiz = quizzes.get(i);
		   if (quiz.getId().equals(lookup) || quiz.getName().equals(lookup))
			   return quiz;
	   }
	   return null;
   }   
   
}