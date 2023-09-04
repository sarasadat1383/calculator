package multiThreadedCalculator.calculator.server;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint; 
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import operators.*;

@Aspect
public class LoggerAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class.getName());
	
	@Pointcut("execution( public * operate(..))")
	public void logPointcut() {}
	
	@Around("logPointcut()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
      LOGGER.trace("Entered Around advice...");
	  String operatorBeingOperated = proceedingJoinPoint.getTarget().getClass().getSimpleName(); 
	  LOGGER.info("OperatorBeingOperated:"+ operatorBeingOperated);
      Object[] operands = proceedingJoinPoint.getArgs();
	  
		if (operands.length>0) {
			LOGGER.info("Operands passed: " );
			for (int i = 0 ; i < operands.length; i++) {
				LOGGER.info("Operand " + (i+1) + ": " + operands[i]);
			}
		}
		Object result = proceedingJoinPoint.proceed(operands);
		LOGGER.info("Result after operating:" + result);
		return result;
	}	
	
	@Before("logPointcut()")
	public void log() {
      LOGGER.info("Operating operands!" );
	}
}