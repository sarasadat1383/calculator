package multiThreadedCalculator.calculator.myCalculator.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

public class FileCalculator {
	private static Logger logger = LoggerFactory.getLogger(FileCalculator.class.getName());
	private  File file;
	Socket clientSocket= null ;
	BufferedReader clientInputStream = null ;
	PrintStream clientRequest = null;
	
	public FileCalculator(File file ){
		this.file = file;
	}
	
	public void fileCalculate() {
		try  { 
			FileReader filereader = new FileReader(file );   //reads the file  
			FileWriter filewriter = new FileWriter("result." + file.getName());
			BufferedReader bufferdreader = new BufferedReader(filereader); 			//creates a buffering character input stream , its converting fileReader to bufferedReader
			String inputLine;  // read until end of file
			String answer ;
			String clientInput;
			while((inputLine = bufferdreader.readLine())!=null) {
				clientSocket = new Socket("localhost", 65534); 
				logger.info("Connected!");
				clientRequest = new PrintStream(clientSocket.getOutputStream());
				clientRequest.println(inputLine);
				logger.info("ClientRequest : "+inputLine);
				clientInputStream = new BufferedReader( new InputStreamReader(clientSocket.getInputStream())); 
				clientInput = clientInputStream.readLine();
				logger.trace("Client Input : "+ clientInput);
				String[] tokens = clientInput.split( "#" );
				String responseStatus = tokens[0];
				
				if(responseStatus.equals("Ok ")){
					String responseMessage = tokens[1];
					answer = "Your final answer is : "+ responseMessage ;
					logger.info("ClientOutput : \n "+ answer); 
					filewriter.write(answer+"\n");
				}
				if(responseStatus.equals("UnsupportedOperatorException ")){
					String responseMessage = tokens[1];
					answer = "Your entered operator is unsupported!";
					logger.info("ClientOutput : \n "+ answer); 
					filewriter.write(answer+"\n");
				}
				if(responseStatus.equals("InvalidExpressionLength ")){
					answer = "Your entered expressions length is invalid!";
					logger.info("ClientOutput : \n "+ answer); 
					filewriter.write(answer +"\n");
				}
			}
			filereader.close();    //closes the stream and release the resources that were busy in stream
			filewriter.close();			
		}	
		catch( UnknownHostException e ){
			logger.error( "unknown host:"+e);
		}
		catch(IOException e){
			logger.error("IOException :"+ e);
		}
	}
}