package multiThreadedCalculator.calculator.myCalculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;
import java.lang.*;
import operators.*;

public class ServerRunnable implements Runnable{
	private static Logger logger = LoggerFactory.getLogger(Server.class.getName());
	ServerSocket serverSocket ;
	Socket clientSocket  ;
	BufferedReader serverInputStream = null;
	PrintStream serverOutputStream= null ;
	String line;
	String answer ="";
	int result;
	int counter;
	Calculator calculator = new Calculator();
	Object object = new Object();
	
	public ServerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
	
	public void run() {
		try {
		serverInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
		serverOutputStream = new PrintStream(clientSocket.getOutputStream());
		line = serverInputStream.readLine();
		logger.info("ServerInput : "+ line );
			
			if( line.length()>55){
				throw(new InvalidExpressionLength("InvalidExpressionLength =>" , line.length())); 
			}
			result = calculator.compute(line);
			serverOutputStream.println("Ok # " + result );
			logger.info( " Server Output : \n Ok # "+ result  );
		}
		catch(InvalidExpressionLength e){
			answer = e.getInvalidLength() + "\n";
			serverOutputStream.println("InvalidExpressionLength # " + answer);
			logger.info("ServerOutput : \n InvalidExpressionLength # "+ answer );
		}
		catch( UnsupportedOperatorException	e ){		
			answer = e.getInvalidOperator() + "\n";
			serverOutputStream.println("UnsupportedOperatorException # " + answer);
			logger.info("ServerOutput : \n UnsupportedOperatorException # " + answer);
		}
		catch (IOException e) {  
			logger.error("IOException : " + e);    
		}		
	}
}