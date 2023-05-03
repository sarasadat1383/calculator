import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import operators.*;
public class Server implements Runnable   {
	private static Logger logger = LoggerFactory.getLogger(Server.class.getName());
	
	ServerSocket serverSocket = null;
	Socket clientSocket = null ;
	BufferedReader serverInputStream ;
	PrintStream serverOutputStream ;
	String line;
	String answer ="";
	int result;
		
	public Server(int port) throws IOException {
	serverSocket = new ServerSocket(65534);
	}
	
	public void run()  {
		Calculator mycalculator = new Calculator();		
		while (true) {
			try {
				logger.info("Waiting for a client ...");
				clientSocket= serverSocket.accept();
				logger.info("Client accepted!");
				serverInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
				serverOutputStream = new PrintStream(clientSocket.getOutputStream());   
				line = serverInputStream.readLine();
				logger.info("ServerInput : "+ line);	
				
				if( line.length()>15){
					throw(new InvalidExpressionLength("InvalidExpressionLength =>" , line.length())); 
				}

				result = mycalculator.compute(line);
				logger.info( " Server Output : \n Ok # "+ result );
				serverOutputStream.println("Ok # " + result );
			}
			
			catch(InvalidExpressionLength e){
				answer = e.getMyInvalidLength() + "\n";
				logger.info("ServerOutput : \n InvalidExpressionLength # "+ answer);
				serverOutputStream.println("InvalidExpressionLength # " + answer);
			}
			catch( UnsupportedOperatorException	e ){		
				answer = e.getMyInvalidOperator() + "\n";
				logger.info("ServerOutput : \n UnsupportedOperatorException # " + answer);
				serverOutputStream.println("UnsupportedOperatorException # " + answer);
			}
			catch (IOException e) {  
				logger.error("IOException : " + e);    
			}
		}
	}
	public static void main (String[]args) {
		try {
			(new Thread(new Server(65534))).start();	
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}		