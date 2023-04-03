package myCalculator;
import java.io.*;
import java.util.*;
import java.lang.*;
import operators.*;

public class Main {
	public static void main(String[] args){
		File file = new File("input"); 
		File filesList[] = file.listFiles(); //List of all files and directories
		if (filesList != null || filesList.length != 0) { 
			for(File myfile : filesList) {
				(new Thread( new FileCalculator(myfile))).start();
			}
		}
		if (filesList == null || filesList.length == 0) {
			try  {  
				File FileNearMain = new File("input.txt");    //creates a new file instance  
				FileReader filereader = new FileReader(FileNearMain);   //reads the file  
				FileWriter filewriter = new FileWriter("output.txt");
				BufferedReader bufferdreader= new BufferedReader(filereader); 			//creates a buffering character input stream , its converting fileReader to bufferedReader
				Calculator MyCalculator = new Calculator();
				String inputLine;  // read until end of file
				String answer="";
				while((inputLine = bufferdreader.readLine())!= null) {  
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
}       