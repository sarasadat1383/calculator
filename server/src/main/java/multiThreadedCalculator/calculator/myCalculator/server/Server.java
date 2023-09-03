package multiThreadedCalculator.calculator.myCalculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;
import java.util.*;
import java.lang.*;
import operators.*;


public class Server {
	private static Logger logger = LoggerFactory.getLogger(Server.class.getName());
	private Calculator calculator;
	private int port;

	
    public void setPort(int port){ 
		logger.debug("Entered setPort method!");
        this.port = port;
    }
	
	public Server( Calculator calculator ){
		this.calculator=calculator;
	}
	public void server (){
		logger.debug(" port reference:"+ port); 
		logger.debug("Entered Server constructor!");
		ServerSocket serverSocket = null;
		Socket clientSocket = null ; 
		BufferedReader serverInputStream = null;
	    PrintStream serverOutputStream= null ; 
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Integer counter=0;
		try {
			serverSocket = new ServerSocket(port);
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
				Future<?> result = (threadPool.submit( new ServerRunnable(clientSocket,calculator)));
			}
		}
		catch (IOException e) {  
			logger.error("IOException : " + e);    
		}
	}
}	