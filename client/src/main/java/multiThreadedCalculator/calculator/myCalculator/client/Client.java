package multiThreadedCalculator.calculator.myCalculator.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.lang.*;

public class Client { 
	private static Logger logger = LoggerFactory.getLogger(Client.class.getName());
	
	public static void main (String[]args) {	
		String inputLine;
		File file = new File("C:\\projects\\client\\src\\main\\java\\input"); 
		File filesList[] = file.listFiles(); //List of all files and directories
		if (filesList != null || filesList.length != 0) { 
			logger.trace("filesListLength:" +filesList.length);
			for(File eachFile : filesList) {
				logger.trace(" File : "+eachFile);
				(new Thread(new FileCalculator(eachFile))).start();
			}
		}

		if (filesList == null || filesList.length == 0) {
			File File = new File("C:\\projects\\client\\src\\main\\java\\input.txt" );
			logger.trace("File:" +File);
			(new Thread(new FileCalculator(File))).start();
		}
	}
}		