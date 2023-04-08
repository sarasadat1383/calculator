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
		String line;
		try {
            serverSocket = new ServerSocket( 65534 );
			logger.info("Server started!");
			logger.info("Waiting for a client ...");
			clientSocket= serverSocket.accept();
			logger.info("Client accepted!");
			InputStreamReader inputServer = new InputStreamReader(clientSocket.getInputStream());  
            OutputStreamWriter outputServer = new OutputStreamWriter(clientSocket.getOutputStream());  
            BufferedReader bufferedReader = new BufferedReader(inputServer);  
            BufferedWriter bufferedWriter = new BufferedWriter(outputServer); 
			line = bufferedReader.readLine();
			String [] myExpressions = line.split("\n");
			for (String expression : myExpressions) {
                int result;
				Calculator mycalculator = new Calculator();
				result = mycalculator.calculating(expression);
				logger.info("result:" + result );
			}
			inputServer.close();
			outputServer.close();
			bufferedReader.close();  
            bufferedWriter.close();
			logger.info("Closing connection");
			clientSocket.close();  
		}			
		catch( UnsupportedOperatorException	e ){		
			e.printStackTrace();
		}
		catch (IOException e) {  
			e.printStackTrace();  
        }
	}
}		