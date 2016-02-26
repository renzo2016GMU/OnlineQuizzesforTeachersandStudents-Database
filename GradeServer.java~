import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class GradeServer implements Runnable{
 
 private static StringBuffer buffer = new StringBuffer();
 private static BufferedWriter gradeFile = null;
   

 public void run(){
  while (true) {
      try {
          Thread.sleep(6 * 1000);
          flush();
      }
      catch (Exception ie) {
          ie.printStackTrace();
      }
  }
  
 }
 
 public static void record(String item){
  //System.err.println("adding " + item + " to buffer");
  buffer.append(item);
 }
 
 private static void flush()throws IOException{
  gradeFile = new BufferedWriter(new FileWriter("grades.txt",true));
  //System.out.println("file flushed with " + buffer.toString());
  gradeFile.append(buffer.toString());
  buffer = new StringBuffer();
  gradeFile.close();
  log();
 }
 
 private static void log(){
  System.err.println("This is the mock logging");
 }
 
 public static ArrayList<String> lookup(String id){
  ArrayList<String> result = new ArrayList<String>();
  try {
   BufferedReader file = new BufferedReader(new FileReader("grades.txt"));
   String line = null;
   HashMap<String,String> newestData = new HashMap<String,String>();
   while ((line=file.readLine())!= null){
    String itemId = line.substring(0,line.indexOf(","));
    newestData.put(itemId, line);
   }
   file.close();
   Iterator<String> i = newestData.keySet().iterator();
   while (i.hasNext()){
    String key = i.next().toString();
    if (key.contains(id))
     result.add(newestData.get(key));
   }
  }catch (Exception e){
   result.add(e.getMessage());
  }
  return result;
 }
}
