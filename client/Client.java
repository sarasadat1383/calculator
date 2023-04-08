import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import operators.*;
public class Client { 
	private static Logger logger = LoggerFactory.getLogger(Client.class.getName());
	public static void main (String[]args){
		ServerSocket serverSocket=null;
		Socket clientSocket= null ; 
		try {
			clientSocket = new Socket("localhost", 65534); 
			logger.info("Connected!");
			InputStreamReader inputClient = new InputStreamReader(clientSocket.getInputStream());  
            OutputStreamWriter outputClient = new OutputStreamWriter(clientSocket.getOutputStream());  
            BufferedReader bufferedReader = new BufferedReader(inputClient);  
            BufferedWriter bufferedWriter = new BufferedWriter(outputClient); 
			String Line; 
			File file = new File("input"); 
			File filesList[] = file.listFiles(); //List of all files and directories
			if (filesList != null || filesList.length != 0) { 
				for(File myfile : filesList) {
					(new Thread( new FileCalculator(myfile))).start();
				}
			}
			if (filesList == null || filesList.length == 0) {
				File myFile = new File("input.txt" );
				(new Thread( new FileCalculator(myFile))).start(); 	
			}
			while( (Line= bufferedReader.readLine())!= null){
				bufferedWriter.write(Line);
			}
			inputClient.close();
			outputClient.close();
            bufferedReader.close();  
            bufferedWriter.close(); 
			clientSocket.close(); 
		} 
		catch (IOException e) {  
			e.printStackTrace();  
        }
	}
}	

		