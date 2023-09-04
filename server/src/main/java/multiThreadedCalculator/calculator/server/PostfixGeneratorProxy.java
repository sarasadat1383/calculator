package multiThreadedCalculator.calculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

import java.util.*;

import operators.*;

@Component
public class PostfixGeneratorProxy extends PostfixGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(PostfixGeneratorProxy.class.getName());
	private OperatorFactory factoryOperator;
	
	@Autowired
	public PostfixGeneratorProxy(OperatorFactory factoryOperator) {
		super (factoryOperator);
		LOGGER.debug("Entered  PostfixGeneratorProxy constructor!");
		LOGGER.debug("FactoryOperator reference : " + factoryOperator );
	}
	
	public List<String> convertingInfixToPostfix(String inputLine) throws UnsupportedOperatorException {
		List<String> tokens = super.convertingInfixToPostfix(inputLine);
		LOGGER.info("Entered convertingInfixToPostfix method!" );
		return tokens;
	}
}