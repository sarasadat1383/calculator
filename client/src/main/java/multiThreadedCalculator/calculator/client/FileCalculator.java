package multiThreadedCalculator.calculator.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.*;

public class FileCalculator implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileCalculator.class.getName());
	private final File file;
	private final String ip;
	private final int port;
	Socket clientSocket = null;
	BufferedReader clientInputStream = null ;
	PrintStream clientRequest = null;
	
	public FileCalculator(File file, String ip, int port){
		LOGGER.debug("Entered FileCalculator constructor!");
		this.file = file;
		this.ip = ip;
		this.port = port;
	}
	
	public void run() {
		calculate();
	}	
	
	public void calculate() {
		try {  
			FileReader filereader = new FileReader(file);   //reads the file  
			FileWriter filewriter = new FileWriter("result." + file.getName());
			BufferedReader bufferdreader = new BufferedReader(filereader); 			//creates a buffering character input stream , its converting fileReader to bufferedReader
			String inputLine;  // read until end of file
			String answer;
			String clientInput;
			
			while ((inputLine = bufferdreader.readLine())!= null) {
				clientSocket = new Socket(ip, port); 
				LOGGER.info("Connected!");
				clientRequest = new PrintStream(clientSocket.getOutputStream());
				clientRequest.println(inputLine);
				LOGGER.info("ClientRequest : " + inputLine);
				clientInputStream = new BufferedReader( new InputStreamReader(clientSocket.getInputStream())); 
				clientInput = clientInputStream.readLine();
				LOGGER.trace("Client Input : " + clientInput);
				String[] tokens = clientInput.split( "#" );
				String responseStatus = tokens[0];
				
				if (responseStatus.equals("Ok ")) {
					String responseMessage = tokens[1];
					answer = inputLine + " Is a expression and the final answer is : " + responseMessage;
					LOGGER.info("ClientOutput : \n " + answer); 
					filewriter.write(answer + "\n");
				} else if (responseStatus.equals("UnsupportedOperatorException ")) {
					String responseMessage = tokens[1];
					answer = "Your entered operator is unsupported!";
					LOGGER.info("ClientOutput : \n " + answer); 
					filewriter.write(answer + "\n");
				} else if (responseStatus.equals("InvalidExpressionLength ")) {
					answer = "Your entered expressions length is invalid!";
					LOGGER.info("ClientOutput : \n " + answer); 
					filewriter.write(answer + "\n");
				}
			}
			filereader.close();    //closes the stream and release the resources that were busy in stream
			filewriter.close();					
		} catch ( UnknownHostException e) {
			LOGGER.error("Unknown host :" + e);
		} catch (IOException e) {
			LOGGER.error("IOException :" + e);
			e.printStackTrace();
		}
	}
}