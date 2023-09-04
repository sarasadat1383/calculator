package multiThreadedCalculator.calculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.*;

import operators.*;

@Component
@PropertySource("server.properties")
public class Server {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class.getName());
	private final Calculator calculator;
	
	@Value("${port}")
	private int port;
	
	@Autowired
	public Server( Calculator calculator ) {
		this.calculator = calculator;
		LOGGER.debug("Entered Server constructor!");
	}
	
    public void setPort(int port) { 
		LOGGER.debug("Entered setPort method!");
        this.port = port;
    }
	
	public void server() {
		LOGGER.debug("Port reference:" + port); 
		LOGGER.debug("Entered Server method!");
		ServerSocket serverSocket = null;
		Socket clientSocket = null; 
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Integer counter = 0;
		 
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}	
		try {
			while (true) {	
				synchronized (counter) {
					counter++;
				}
				LOGGER.info("Waiting for a client ...");					
				clientSocket = serverSocket.accept();
				LOGGER.info("Client accepted! \n request number:" + counter);
				Future<?> result = (threadPool.submit( new ServerRunnable(clientSocket,calculator)));
			}
		} catch (IOException e) {  
			LOGGER.error("IOException : " + e);    
		}
	}
}	