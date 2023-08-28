package multiThreadedCalculator.calculator.myCalculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.lang.*;
import operators.*;

public class PostfixGeneratorProxy extends PostfixGenerator {
	private static Logger logger = LoggerFactory.getLogger(PostfixGeneratorProxy.class.getName());
	
	public PostfixGeneratorProxy(OperatorFactory factoryOperator){
		super(factoryOperator);
		logger.debug( "Entered  PostfixGeneratorProxy constructor!");
		logger.debug("factoryOperator reference : "+ factoryOperator );
	}
	public List<String> convertingInfixToPostfix(String inputLine) throws UnsupportedOperatorException {
		List<String> tokens = super.convertingInfixToPostfix( inputLine);
		logger.info("Entered convertingInfixToPostfix method!" );
		return tokens ;
	}
}