import java.util.ArrayList;


public class MultipleChoiceQuestion extends Question{
	
	private ArrayList<String> options;

	public MultipleChoiceQuestion(String text, String answer,
			ArrayList<String> options) {
		super(text, answer);
		this.options = options;
		setId("MC"+getCount());
	}
	
	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
	
	public String toString(){
		String result = "Multiple Choice Question:\n\t"+getText()+"\n\n";
		for (int i=0; i < options.size(); i++)
			result += i + ". " + options.get(i).toString() + "\n";
		result += "\n\tType the number of your answer here:";
		return result;
	}	

}
