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
	PrintStream clientRequest = null;
	
	public FileCalculator(File file ){
		this.file = file;
	}
	
	public void run() {
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
				logger.info("ClientRequest : "+inputLine); 
				clientRequest.println(inputLine);
				
			}
			
			clientInputStream = new BufferedReader( new InputStreamReader(clientSocket.getInputStream())); 
			String finalAnswer = clientInputStream.readLine();
			String[] tokens = finalAnswer.split( "#" );
			String responseStatus = tokens[0];
			String responseMessage = tokens[1];

			if(responseStatus.equals("Ok ")){
				answer = "Your final answer is : "+ responseMessage ;
				logger.info("ClientOutput : \n "+ answer); 
				filewriter.write(answer);
			}
			if(responseStatus.equals("UnsupportedOperatorException ")){
				answer = "Your entered operator is unsupported!";
				logger.info("ClientOutput : \n "+ answer); 
				filewriter.write(answer);
			}
			if(responseStatus.equals("InvalidExpressionLength ")){
				answer = "Your entered expressions length is invalid!";
				logger.info("ClientOutput : \n "+ answer); 
				filewriter.write(answer);
			}
			
			filereader.close();    //closes the stream and release the resources that were busy in stream
			filewriter.close();
			clientRequest.close();
			clientInputStream.close();
					
		}
		catch( UnknownHostException e ){
			logger.error( "unknown host:"+e);
		}
		catch(IOException e){
			logger.error("IOException :"+ e);
		}
	}
}