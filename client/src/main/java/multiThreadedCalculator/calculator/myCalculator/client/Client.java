package multiThreadedCalculator.calculator.myCalculator.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.*;
import java.util.*;
import java.lang.*;

public class Client { 
	private static Logger logger = LoggerFactory.getLogger(Client.class.getName());
	private String ip;
	private int port;
    public void setIp(String ip) {
        this.ip = ip;
		logger.debug("Entered setIp method!");
    }
    public void setPort(int port) {
		logger.debug("Entered setPort method!");
        this.port = port;
    }
	public void Client (){
		logger.debug("Entered Client constructor!");
		ExecutorService threadPool = Executors.newCachedThreadPool();
		String inputLine;
		File file = new File("C:\\projects\\client\\src\\main\\java\\input"); 
		File filesList[] = file.listFiles(); //List of all files and directories
		if (filesList != null || filesList.length != 0) { 
			logger.trace("filesListLength:" +filesList.length);
			for(File eachFile : filesList) {
				logger.trace(" File : "+eachFile);
				Future<?> result =(threadPool.submit(new FileCalculator(eachFile, ip, port)));
			}
		}

		if (filesList == null || filesList.length == 0) {
			File File = new File("C:\\projects\\client\\src\\main\\java\\input.txt" );
			logger.trace("File:" +File);
			Future<?> finalResult =(threadPool.submit(new FileCalculator(File, ip, port)));
		}
	}
}		