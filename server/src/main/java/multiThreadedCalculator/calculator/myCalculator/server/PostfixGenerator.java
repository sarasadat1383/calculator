package multiThreadedCalculator.calculator.myCalculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.lang.*;
import operators.*;

public class PostfixGenerator {
	private static Logger logger = LoggerFactory.getLogger(PostfixGenerator.class.getName());
	private OperatorFactory factoryOperator;
	public PostfixGenerator(OperatorFactory factoryOperator) {
		logger.debug("Entered PostfixGenerator constructor!");
		this.factoryOperator = factoryOperator;
		logger.debug("factoryOperator reference : "+ factoryOperator );
	}
	public List<String> convertingInfixToPostfix(String inputLine) throws UnsupportedOperatorException {
		logger.info("entered class PostfixGenerator");		
		List<String> array = new ArrayList<>();
		Stack <String> operatorstack = new Stack<>();
		StringBuilder stringbuilder = new StringBuilder();
		logger.trace("input:" +	 inputLine);
		for( int j=0 ; j< inputLine.length(); j++){
			String character = Character.toString(inputLine.charAt(j));
			logger.trace("array size:" + array.size());
			logger.trace("charater by character:" + character);
			if( character.contains("(")) {
				operatorstack.push(character);
			}
			else if( character.contains(")")){
				while (!operatorstack.isEmpty() && !(operatorstack.peek().equals("("))){
					if(stringbuilder.length()>0){
					array.add(stringbuilder.toString());
					}
					stringbuilder.setLength(0);
					array.add(operatorstack.pop());
				}
				operatorstack.pop();
			}
			else if (Character.isDigit(inputLine.charAt(j))){
				stringbuilder.append(inputLine.charAt(j));
				logger.trace("postfix after taking operands and operators1:" + array);
				logger.trace("Stringbuilder:"+ stringbuilder);
			}
			else {
				Operator currentOperator = factoryOperator.generateOperator(character);
				if(stringbuilder.length()>0){
					array.add(stringbuilder.toString());
					stringbuilder.setLength(0);
				}
				logger.trace("operator by operator:" + character);
				if( operatorstack.isEmpty()){
					operatorstack.push(character); 
				}
				else if( operatorstack.peek().equals( "(")){
					operatorstack.push(character); 
				}
				else {
					Operator stackOperator = factoryOperator.generateOperator(operatorstack.peek());
					if( (operatorstack.size()> 0)&& (stackOperator.precedence())>= (currentOperator.precedence())){
						array.add(operatorstack.pop());
						if ((operatorstack.size()> 0 )&& (operatorstack.peek().equals( "("))){
							operatorstack.push(character);
						}
						else if (operatorstack.size()> 0 ){
							Operator secondStackOperator = factoryOperator.generateOperator(operatorstack.peek());
							if ((secondStackOperator.precedence())== (currentOperator.precedence())){
								array.add(operatorstack.pop());
								operatorstack.push(character);
							}
							else if ((secondStackOperator.precedence())< (currentOperator.precedence())){
								operatorstack.push(character);    
							}
						}
						else if(operatorstack.isEmpty()){
							operatorstack.push(character);
						}
					}
					else if(( operatorstack.size()> 0)&& (stackOperator.precedence())< (currentOperator.precedence())){
						operatorstack.push(character);
					}
				}
			}
		}
		
		if(stringbuilder.length()> 0){
			array.add(stringbuilder.toString());
			logger.debug("postfix:" + array);	
		} 
		while (!operatorstack.isEmpty()){
			array.add(operatorstack.pop());
			logger.debug("final postfix:" + array);
		}
		return array;
	}
}