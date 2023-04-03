package myCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.lang.*;
import operators.*;
public class Calculator {
	private static Logger logger = LoggerFactory.getLogger(Calculator.class.getName());
	private int calculate( List<String> tokens) throws UnsupportedOperatorException {
		OperatorFactory OperatorGenerator = new OperatorFactory();
		Stack<Integer> operandstack = new Stack<>();
		logger.debug("take postfix as input to calculate:" + tokens);
		logger.debug("postfix length:" + tokens.size());
		for( int i=0 ; i< tokens.size() ; i++){
			String myCharacter = tokens.get(i);
			logger.trace( "character by character:" + myCharacter);
			logger.trace("character number:" + i); 
			if( myCharacter.contains("1")|| myCharacter.contains("2") || myCharacter.contains("3")||myCharacter.contains("4") || myCharacter.contains("5") ||myCharacter.contains("6")||myCharacter.contains("7") || myCharacter.contains("8")|| myCharacter.contains("9")){
				operandstack.push(Integer.parseInt(String.valueOf(myCharacter)));
				logger.trace("operandstack3:" + operandstack.peek());
			}
			else { 
				int number2= operandstack.pop();
				int number1= operandstack.pop();
				Operator myOperator = OperatorGenerator.generateOperator(myCharacter); 
				operandstack.push(myOperator.operate(number1,number2));
				logger.trace("operandstack.peek():"+ operandstack.peek());
			}
		}
		logger.info("answer:" +	operandstack.peek());
		return operandstack.pop();
	}
	public int calculating(String inputLine) throws UnsupportedOperatorException {	
		PostfixGenerator postfixGenerate = new PostfixGenerator();
		List<String> tokens = postfixGenerate.convertingInfixToPostfix(inputLine);
		return calculate(tokens);
	}
}