package repos.calculator.myCalculator;
import java.io.*;
import java.util.*;
import java.lang.*;
import operators.*;
public class FileCalculator implements Runnable{
	private final File file;
	
	public FileCalculator(File file){
		this.file = file;
	}
	
	public void run(){
		try  { 
			FileReader filereader = new FileReader(file );   //reads the file  
			FileWriter filewriter = new FileWriter("result." + file.getName());
			BufferedReader bufferdreader = new BufferedReader(filereader); 			//creates a buffering character input stream , its converting fileReader to bufferedReader
			Calculator MyCalculator = new Calculator();
			String inputLine;  // read until end of file
			String answer ="";
			while((inputLine = bufferdreader.readLine())!=null) {  
				try{
					answer += MyCalculator.calculating(inputLine) + "\n";
				}
				catch( UnsupportedOperatorException	e ){		
					answer += e.getMessage() + e.getMyInvalidOperator() + "\n";
				}
			}
			filewriter.write(answer);
			filereader.close();    //closes the stream and release the resources that were busy in stream
			filewriter.close();
		}
		catch(IOException e)  {
			
			
			e.printStackTrace();  
		}
	}
}