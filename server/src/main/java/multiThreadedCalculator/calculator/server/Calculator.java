package multiThreadedCalculator.calculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

import java.util.*;
import operators.*;

@Component
public class Calculator {
	private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class.getName());
	private final OperatorFactory factoryOperator;
	private final PostfixGeneratorProxy postfixGeneratorProxy;
	
	@Autowired
	public Calculator(OperatorFactory factoryOperator, PostfixGeneratorProxy postfixGeneratorProxy) {
		LOGGER.debug( "Entered Calculator constructor!"); 
		this.factoryOperator = factoryOperator;
		this.postfixGeneratorProxy = postfixGeneratorProxy;
		LOGGER.debug("FactoryOperator reference:"+ factoryOperator); 
		LOGGER.debug("PostfixGeneratorProxy reference:"+ postfixGeneratorProxy); 
	}
	
	private int calculate(List<String> tokens) throws UnsupportedOperatorException {
		Stack<Integer> operandStack = new Stack<>();
		LOGGER.debug("Take postfix as input to calculate:" + tokens);
		LOGGER.debug("Postfix length:" + tokens.size());
		
		for (int i = 0 ; i < tokens.size() ; i++) {
			String currentCharacter = tokens.get(i);
			LOGGER.trace("Character by character:" + currentCharacter);
			LOGGER.trace("Character number:" + i); 
			if (currentCharacter.contains("1") || currentCharacter.contains("2") || currentCharacter.contains("3") || currentCharacter.contains("4")|| currentCharacter.contains("5") || 
				currentCharacter.contains("6") || currentCharacter.contains("7") || currentCharacter.contains("8") || currentCharacter.contains("9")){		
				operandStack.push(Integer.parseInt(String.valueOf(currentCharacter)));
				LOGGER.trace("OperandStack:" + operandStack.peek());
			} else { 
				int secondnNumber = operandStack.pop();
				int firstNumber = operandStack.pop();
				Operator currentOperator = factoryOperator.generateOperator(currentCharacter); 
				operandStack.push(currentOperator.operate(firstNumber, secondnNumber));
				LOGGER.trace("OperandStack.peek():"+ operandStack.peek());
			}
		}
		LOGGER.info("Answer: " + operandStack.peek());
		return operandStack.pop();
	}
	
	public int compute(String inputLine) throws UnsupportedOperatorException, InvalidExpressionLength {	
		List<String> tokens = postfixGeneratorProxy.convertingInfixToPostfix(inputLine);
		LOGGER.trace("Entered compute method!");
		return calculate(tokens);
	}
}