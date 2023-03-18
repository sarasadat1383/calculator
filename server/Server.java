package repos.calculator.myCalculator.server;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import repos.calculator.myCalculator.*;
public class Server {
	public static void main (String[]args){
		ServerSocket serverSocket = null;
		Socket clientSocket = null ; 
		String line;
		try {
            serverSocket = new ServerSocket(5000);
			clientSocket= serverSocket.accept();
			InputStreamReader inputServer = new InputStreamReader(serverSocket.getInputStream());  
            OutputStreamWriter outputServer = new OutputStreamWriter(serverSocket.getOutputStream());  
            BufferedReader bufferedReader = new BufferedReader(inputServer);  
            BufferedWriter bufferedWriter = new BufferedWriter(outputServer); 
			line = inputServer.readLine();
			String [] myExpressions = line.split("\n");
			for (String expression : myExpressions) {
                int result;
				result = Calculator.calculating(expression);
			}
			clientSocket.close();  
            inputServer.close();  
            outputServer.close();  
            bufferedReader.close();  
            bufferedWriter.close();
		}			
		catch (IOException e) {  
			e.printStackTrace();  
        }
	}
}		