import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Quiz{
	
	private ArrayList<Question> questions;
	private String name;
	private String id;
	private HashMap<String,Boolean> answers;
	private static int count = 0;
	private int currentQuestion = 0;
	
	public Quiz(String name){
		this.name = name;
		count++;
		id = "QUIZ"+count;
		questions = new ArrayList<Question>();
		answers = new HashMap<String,Boolean>();
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public Question getQuestion(int num){
		return getQuestions().get(num);
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String, Boolean> getAnswers() {
		return answers;
	}

	public void setAnswers(HashMap<String, Boolean> answers) {
		this.answers = answers;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Quiz.count = count;
	}

	public int getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(int currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	public void addQuestion(Question q){
		questions.add(q);
		answers.put(q.getId(),false);
	}
	
	public Question answerQuestion(String id){
		Question find = new Question(null,null);
		find.setId(id);
		int index = questions.indexOf(find);
		Question q = questions.get(index);
		System.out.println("Question "+ ++currentQuestion+", " +q.toString());
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine();
		if (answer.equals("quit"))
			return null;
		answers.put(id,q.check(answer));
		if (q.check(answer))
			System.out.println("You are correct!");
		else 
			System.out.println("You are wrong. You can try again in a bit or type quit to exit the quiz.");
		
		boolean done = true;
		System.err.println("answers.size() " + answers.size());
		if (currentQuestion >= answers.size()){
			currentQuestion = -1;
			Iterator<Boolean> i = answers.values().iterator();
			while (i.hasNext() && done){
				Boolean value = i.next();
				System.err.println("value " + value);
				if (value == false)
					done = false;
				currentQuestion++;
			}
		}else
			done = false;
		if (!done)
			return questions.get(currentQuestion);
		else
			return null;
	}
	
	public int score(){
		int score = 0;
		Iterator<Boolean> i = answers.values().iterator();
		while (i.hasNext()){
			if (i.next().equals(new Boolean(true)))
				score++;
		}
		return score;
	}
	
	public boolean equals(Object o){
		if (o instanceof Quiz){
			Quiz q = (Quiz) o;
			return q.getId().equals(getId());
		}
		else if (o instanceof String){
			return o.toString().equals(getId());
		}
		return false;
	}	
	
	public Quiz clone(){
		Quiz q = new Quiz(this.name);
		
		//we're not really creating a new object
		count--;
		
		//these are safe as shallow copies
		q.setId(this.id);
		q.setQuestions(this.questions);
		
		//this is a deep copy
		HashMap<String,Boolean> answersC = new HashMap<String,Boolean>();
		Iterator<String> i = answers.keySet().iterator();
		while (i.hasNext()){
			String key = i.next();
			Boolean value = new Boolean(answers.get(key).booleanValue());
			answersC.put(key, value);
		}
		q.setAnswers(answersC);
		
		return q;
	}

}
