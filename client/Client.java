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
		Socket clientSocket= null ;
		BufferedReader inputClient = null ;
		PrintWriter outputClient = null ;
		try{
			clientSocket = new Socket("localhost", 65534); 
			logger.info("Connected!");
			inputClient = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));  
            outputClient = new PrintWriter(clientSocket.getOutputStream());     
		}
		catch( UnknownHostException e ){
			logger.error( "unknown host!");
		}
		catch(IOException e){
			logger.error("IOException !");
		}
		if (clientSocket != null && inputClient != null) {
			try {
				String inputLine;
				File file = new File("C:\\repos\\calculator\\myCalculator\\input"); 
				File filesList[] = file.listFiles(); //List of all files and directories
				logger.trace("filesList:" +filesList);
				if (filesList != null && filesList.length != 0) { 
					logger.trace("filesList.length:" +filesList.length);
					for(File myfile : filesList) {
						(new Thread( new FileCalculator(myfile))).start();
					}
				}
				if (filesList == null && filesList.length == 0) {
					File Files = new File("C:\\repos\\calculator\\myCalculator\\input.txt" );
					(new Thread( new FileCalculator(Files))).start(); 	
				}
				while( (inputLine= inputClient.readLine())!= null){
					outputClient.println(inputLine);
				}
				inputClient.close();
				outputClient.close();
				clientSocket.close(); 
			} 
			catch (IOException e) {  
				logger.error("IOException !");  
			}
		}
	}
}	

		