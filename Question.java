
public class Question {

	private String text;
	private String id;
	private String answer;
	private static int count = 0;
	
	public Question(String text, String answer) {
		this.text = text;
		this.id = "SA" + count;
		this.answer = answer;
		count++;
		
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Question.count = count;
	}

	public boolean check(String userAnswer){
		return answer.toUpperCase().trim().equals(userAnswer.toUpperCase().trim());
	}
	
	public String toString(){
		return "Short Answer Question:\n\t"+text+"\n\n\tType your answer here:";
	}
	
	public boolean equals(Object o){
		if (o instanceof Question){
			Question q = (Question) o;
			return q.getId().equals(getId());
		}
		else if (o instanceof String){
			return o.toString().equals(getId());
		}
		return false;
	}
	
}
