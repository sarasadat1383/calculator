package multiThreadedCalculator.calculator.myCalculator.server;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint; 
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.lang.*;
import operators.*;

@Aspect
public class LoggerAspect {
	private static Logger logger = LoggerFactory.getLogger(LoggerAspect.class.getName());
	@Pointcut("execution( public * operate(..))")
	public void logPointcut() {}
	@Around("logPointcut()")
	public Object  aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
      logger.trace(" Entered Around advice...");
	  String operatorBeingOperated = proceedingJoinPoint.getTarget().getClass().getSimpleName(); 
	  logger.info("operatorBeingOperated:"+ operatorBeingOperated);
      Object [] operands = proceedingJoinPoint.getArgs();
		if(operands.length>0){
			logger.info("Operands passed: " );
			for (int i = 0 ; i < operands.length; i++) {
				logger.info("operand "+ (i+1) +": "+ operands[i]);
			}
		}
		Object result = proceedingJoinPoint.proceed(operands);
		logger.info("Result after operating:" + result);
		return result;
	}	
	@Before("logPointcut()")
	public void log(  ){
      logger.info("Operating operands!" );
	}
}