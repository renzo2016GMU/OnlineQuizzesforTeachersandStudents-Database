import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CodingQuestion extends Question{

	private String code;
	private String input;
	
	public CodingQuestion(String text, String answer, String code,
			String input) {
		super(text, answer);
		this.code = code;
		this.input = input;
		setId("C"+getCount());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
	public void wrapCodeInFile(String code, String fileName) throws IOException{
		code = "public class "+fileName+"{/n/n/tpublic static void main(String[] args){\n\n"+code+"\n\t}\n\n}\n";
		BufferedWriter file = new BufferedWriter(new FileWriter(fileName+".java"));
		file.write(code);
		file.close();
	}
	
	public boolean check(String userAnswer){
		StringBuffer correctResult = new StringBuffer();
		StringBuffer testResult = new StringBuffer();
		try
		{
			//System.err.println("doing "+ cmd);
			wrapCodeInFile(getAnswer(),"Correct");
			wrapCodeInFile(userAnswer,"Test");
			BufferedWriter input = new BufferedWriter(new FileWriter("input.txt"));
			input.write(input+"\n");
			input.close();
			
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec("javac Correct.java");
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = null;
			while ((line=buf.readLine())!=null) 
				correctResult.append(line+"\n");
			buf.close();
			buf = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			while ((line=buf.readLine())!=null) 
				correctResult.append(line+"\n");	
			buf.close();
			pr.getOutputStream().close();
			pr.destroy();
			
			pr = run.exec("javac Test.java");
			buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			while ((line=buf.readLine())!=null) 
				testResult.append(line+"\n");
			buf.close();
			buf = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			while ((line=buf.readLine())!=null) 
				testResult.append(line+"\n");	
			buf.close();
			pr.getOutputStream().close();
			pr.destroy();		
			
			return testResult.toString().trim().equals(correctResult.toString().trim());
		}
		catch (Exception e)
		{
			return false;
		}
	}	
	
	
}
