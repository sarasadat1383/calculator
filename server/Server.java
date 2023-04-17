import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import operators.*;
public class Server {
	private static Logger logger = LoggerFactory.getLogger(Server.class.getName());
	public static void main (String[]args){
		ServerSocket serverSocket = null;
		Socket clientSocket = null ;
		BufferedReader serverInputStream ;
		PrintStream serverOutputStream ;
		String line;
		String answer ="";
		int result;
		try {
            serverSocket = new ServerSocket( 65534 );
			logger.info("Server started!");
			while (true) {
				logger.info("Waiting for a client ...");
				clientSocket= serverSocket.accept();
				logger.info("Client accepted!");
				serverInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
				serverOutputStream = new PrintStream(clientSocket.getOutputStream());   
				line = serverInputStream.readLine(); 
				logger.info("serverinput:"+ line);
				Calculator mycalculator = new Calculator();	
				result = mycalculator.calculating(line);
				serverOutputStream.println( result);
				logger.info( line +"="+ result );
				
			}
		
		}			
		catch( UnsupportedOperatorException	e ){		
			answer += e.getMessage() + e.getMyInvalidOperator() + "\n";
			logger.trace("answer:"+ answer);
		}
		catch (IOException e) {  
			logger.error("IOException : " + e);    
        }
	}
}		