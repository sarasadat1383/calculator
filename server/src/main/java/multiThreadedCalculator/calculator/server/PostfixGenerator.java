package multiThreadedCalculator.calculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

import java.util.*;

import operators.*;

@Component
public class PostfixGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(PostfixGenerator.class.getName());
	private final OperatorFactory factoryOperator;
	
	@Autowired
	public PostfixGenerator(OperatorFactory factoryOperator) {
		LOGGER.debug("Entered PostfixGenerator constructor!");
		this.factoryOperator = factoryOperator;
		LOGGER.debug("FactoryOperator reference : " + factoryOperator );
	}
	
	public List<String> convertingInfixToPostfix(String inputLine) throws UnsupportedOperatorException {
		LOGGER.info("Entered class PostfixGenerator!");		
		List<String> array = new ArrayList<>();
		Stack <String> operatorStack = new Stack<>();
		StringBuilder stringbuilder = new StringBuilder();
		LOGGER.trace("Input:" +	 inputLine);
		
		for ( int j = 0 ; j < inputLine.length() ; j++) {
			String character = Character.toString(inputLine.charAt(j));
			LOGGER.trace("Array size:" + array.size());
			LOGGER.trace("Charater by character:" + character);
			if ( character.contains("(")) {
				operatorStack.push(character);
			} else if ( character.contains(")")) {
				while ( !operatorStack.isEmpty() && !(operatorStack.peek().equals("(")) ) {
					if(stringbuilder.length() > 0){
					array.add(stringbuilder.toString());
					}
					stringbuilder.setLength(0);
					array.add(operatorStack.pop());
				}
				operatorStack.pop();
			}
			else if (Character.isDigit(inputLine.charAt(j))) {
				stringbuilder.append(inputLine.charAt(j));
				LOGGER.trace("Postfix after taking operands and operators:" + array);
				LOGGER.trace("Stringbuilder:" + stringbuilder);
			}
			else {
				Operator currentOperator = factoryOperator.generateOperator(character);
				if (stringbuilder.length()>0){
					array.add(stringbuilder.toString());
					stringbuilder.setLength(0);
				}
				LOGGER.trace("Operator by operator:" + character);
				if (operatorStack.isEmpty()){
					operatorStack.push(character); 
				} else if ( operatorStack.peek().equals( "(")) {
					operatorStack.push(character); 
				} else {
					Operator operatorInStack = factoryOperator.generateOperator(operatorStack.peek());
					if( (operatorStack.size() > 0) && (operatorInStack.precedence()) >= (currentOperator.precedence())) {
						array.add(operatorStack.pop());
						if ((operatorStack.size() > 0 ) && (operatorStack.peek().equals( "("))) {
							operatorStack.push(character);
						} else if (operatorStack.size() > 0 ) {
							Operator operatorLeftInsideStack = factoryOperator.generateOperator(operatorStack.peek());
							if ((operatorLeftInsideStack.precedence()) == (currentOperator.precedence())) {
								array.add(operatorStack.pop());
								operatorStack.push(character);
							} else if ((operatorLeftInsideStack.precedence()) < (currentOperator.precedence())) {
								operatorStack.push(character);    
							}
						} else if (operatorStack.isEmpty()) {
							operatorStack.push(character);
						}
					} else if (( operatorStack.size() > 0) && (operatorInStack.precedence()) < (currentOperator.precedence())) {
						operatorStack.push(character);
					}
				}
			}
		}
		
		if (stringbuilder.length() > 0) {
			array.add(stringbuilder.toString());
			LOGGER.debug("Postfix:" + array);	
		} 
		while (!operatorStack.isEmpty()) {
			array.add(operatorStack.pop());
			LOGGER.debug("Final postfix:" + array);
		}
		return array;
	}
}