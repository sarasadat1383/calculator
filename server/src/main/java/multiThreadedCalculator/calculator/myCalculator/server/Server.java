package multiThreadedCalculator.calculator.myCalculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;
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
		Integer counter=0;
		try {
			serverSocket = new ServerSocket(65534);
		} 
		catch (IOException e) {
			logger.error(e.getMessage());
		}	
		try {
			while (true) {	
				synchronized(counter){
					counter++;
				}
				logger.info("Waiting for a client ...");	
				clientSocket = serverSocket.accept();
				logger.info("Client accepted! \n request number:" + counter);
				(new Thread(new ServerRunnable(clientSocket))).start();
			}
		}
		catch (IOException e) {  
			logger.error("IOException : " + e);    
		}
	}
}	