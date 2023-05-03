import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import operators.*;
public class Client { 
	private static Logger logger = LoggerFactory.getLogger(Client.class.getName());
	
	public static void main (String[]args) throws InterruptedException{
		String inputLine;
		File file = new File("C:\\repos\\calculator\\myCalculator\\input"); 
		File filesList[] = file.listFiles(); //List of all files and directories
		if (filesList != null && filesList.length != 0) { 
			logger.trace("filesListLength:" +filesList.length);
			for(File myfile : filesList) {
				logger.trace(" myFile :"+myfile);
				(new Thread( new FileCalculator(myfile ))).start();
				Thread.sleep(1000);
			}
		}

		if (filesList == null || filesList.length == 0) {
			File myFile = new File("C:\\repos\\calculator\\myCalculator\\input.txt" );
			logger.trace("MyFile:" +myFile);
			(new Thread( new FileCalculator(myFile))).start();
		}
	}
}		