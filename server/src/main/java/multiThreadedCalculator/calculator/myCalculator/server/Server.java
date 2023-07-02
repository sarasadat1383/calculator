package multiThreadedCalculator.calculator.myCalculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import operators.*;

public class Server {
	private static Logger logger = LoggerFactory.getLogger(Server.class.getName());
	
	public static void main (String[]args) {
		
		ServerSocket serverSocket = null;
		Socket clientSocket = null ;
		BufferedReader serverInputStream = null;
		PrintStream serverOutputStream= null ;
		String line;
		String answer ="";
		int result;
				
		try {
			serverSocket = new ServerSocket(65534);
		} 
		catch (IOException e) {
			logger.error(e.getMessage());
		}
	
		Calculator calculator = new Calculator();	
		try {
			while (true) { 
				logger.info("Waiting for a client ...");	
				clientSocket = serverSocket.accept();
				logger.info("Client accepted!");
				serverInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
				serverOutputStream = new PrintStream(clientSocket.getOutputStream());   
				line = serverInputStream.readLine();
				logger.info("ServerInput : "+ line);	
				try {
					if( line.length()>55){
						throw(new InvalidExpressionLength("InvalidExpressionLength =>" , line.length())); 
					}
					result = calculator.compute(line);
					serverOutputStream.println("Ok # " + result );
					logger.info( " Server Output : \n Ok # "+ result );
				}
				catch(InvalidExpressionLength e){
					answer = e.getInvalidLength() + "\n";
					serverOutputStream.println("InvalidExpressionLength # " + answer);
					logger.info("ServerOutput : \n InvalidExpressionLength # "+ answer);
				}
				catch( UnsupportedOperatorException	e ){		
					answer = e.getInvalidOperator() + "\n";
					serverOutputStream.println("UnsupportedOperatorException # " + answer);
					logger.info("ServerOutput : \n UnsupportedOperatorException # " + answer);
				}		
			}
		}
		catch (IOException e) {  
			logger.error("IOException : " + e);    
		}
	}
}	