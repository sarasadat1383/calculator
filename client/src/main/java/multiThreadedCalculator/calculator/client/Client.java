package multiThreadedCalculator.calculator.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.*;

@Component
@PropertySource("client.properties")
public class Client { 
	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class.getName());
	
	@Value("${port}")
	private int port;
	
	@Value("${ip}")
	private String ip;
	
    public void setIp(String ip) {
        this.ip = ip;
		LOGGER.debug("Entered setIp method!");
    }
	
    public void setPort(int port) {
		LOGGER.debug("Entered setPort method!");
        this.port = port;
    }
	
	public void Client() {
		LOGGER.debug("Entered Client constructor!");
		ExecutorService threadPool = Executors.newCachedThreadPool();
		File file = new File("C:\\projects\\client\\src\\main\\java\\input"); 
		File filesList[] = file.listFiles(); //List of all files and directories
		
		if ((filesList != null) && (filesList.length != 0)) { 
			LOGGER.trace("FilesListLength : " + filesList.length);
			for(File eachFile : filesList) {
				LOGGER.trace("File : " + eachFile);
				Future<?> result = (threadPool.submit(new FileCalculator(eachFile, ip, port)));
			}
		} else if ((filesList == null) || (filesList.length == 0)) {
			File File = new File("C:\\projects\\client\\src\\main\\java\\input.txt" );
			LOGGER.trace("File : " + File);
			Future<?> finalResult = (threadPool.submit(new FileCalculator(File, ip, port)));
		}
	}
}		