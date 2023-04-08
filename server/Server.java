
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import operators.*;
public class Server {
	public static void main (String[]args){
		ServerSocket serverSocket = null;
		Socket clientSocket = null ; 
		String line;
		try {
            serverSocket = new ServerSocket( 65534 );
			clientSocket= serverSocket.accept();
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
				System.out.println("result:" + result );
			}
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