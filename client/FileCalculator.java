import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import operators.*;
public class FileCalculator implements Runnable{
	private static Logger logger = LoggerFactory.getLogger(FileCalculator.class.getName());
	private  File file;
	Socket clientSocket= null ;
	BufferedReader clientInputStream = null ;
	PrintStream clientRequest;
	
	public FileCalculator(File file ){
		this.file = file;
	}
	
	public void run(){
		try  { 
			FileReader filereader = new FileReader(file );   //reads the file  
			FileWriter filewriter = new FileWriter("result." + file.getName());
			BufferedReader bufferdreader = new BufferedReader(filereader); 			//creates a buffering character input stream , its converting fileReader to bufferedReader
			String inputLine;  // read until end of file
			String answer ="";
		
			clientSocket = new Socket("localhost", 65534); 
			logger.info("Connected!");
			clientRequest = new PrintStream(clientSocket.getOutputStream());
		
			while((inputLine = bufferdreader.readLine())!=null) {
				logger.trace("inputLine:"+inputLine); 
				clientRequest.println(inputLine);
				
			}
			
			clientInputStream = new BufferedReader( new InputStreamReader(clientSocket.getInputStream())); 
			filereader.close();    //closes the stream and release the resources that were busy in stream
			filewriter.close();
			clientRequest.close();
			clientInputStream.close();
			clientSocket.close();		
		}
		catch( UnknownHostException e ){
			logger.error( "unknown host:"+e);
		}
		catch(IOException e){
			logger.error("IOException :"+ e);
		}
	}
}