package multiThreadedCalculator.calculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.*;

import operators.*;

public class ServerRunnable implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerRunnable.class.getName());
	private  final Calculator calculator; 
	Socket clientSocket = null;
	BufferedReader serverInputStream = null;
	PrintStream serverOutputStream = null ;
	String line;
	String answer = "";
	int result;	
	
	public ServerRunnable(Socket clientSocket, Calculator calculator) {
		LOGGER.debug( "Entered ServerRunnale constructor!");
		LOGGER.debug(" Calculator reference:"+ calculator); 
        this.clientSocket = clientSocket;
		this.calculator= calculator;
    }

	public void run() {
		calculate();
	}
	
	public void calculate(){
		try {
			serverInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
			serverOutputStream = new PrintStream(clientSocket.getOutputStream());
			line = serverInputStream.readLine();
			LOGGER.info("ServerInput : "+ line );
			
			if (line.length() > 55) {
				throw(new InvalidExpressionLength("InvalidExpressionLength =>" , line.length())); 
			}
			result = calculator.compute(line);
			serverOutputStream.println("Ok # " + result);
			LOGGER.info("Server Output : \n Ok # " + result);
		} catch (InvalidExpressionLength e) {
			answer = e.getInvalidLength() + "\n";
			serverOutputStream.println("InvalidExpressionLength # " + answer);
			LOGGER.info("ServerOutput : \n InvalidExpressionLength # " + answer );
		} catch (UnsupportedOperatorException e) { 		
			answer = e.getInvalidOperator() + "\n";
			serverOutputStream.println("UnsupportedOperatorException # " + answer);
			LOGGER.info("ServerOutput : \n UnsupportedOperatorException # " + answer);
		} catch (IOException e) {  
			LOGGER.error("IOException : " + e);    
		}		
	}
}