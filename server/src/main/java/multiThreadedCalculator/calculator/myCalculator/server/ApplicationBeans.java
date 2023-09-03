package multiThreadedCalculator.calculator.myCalculator.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import java.lang.*;
import operators.*;

@Configuration
@PropertySource("server.properties")
public class ApplicationBeans {
	@Autowired 
	private	OperatorFactory factoryOperator;
	
	@Autowired 
	private	PostfixGeneratorProxy postfixGeneratorProxy;
	
	@Autowired 
	private	Calculator calculator;
	
	@Value("${port}")
	private int port;
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public PostfixGenerator PostfixGenerator(){
        return new PostfixGenerator(factoryOperator);
    }
	@Bean
	public Server server(){
		Server server =  new Server(calculator);
		server.setPort(port);
		return server;
	}
	@Bean
	public OperatorFactory factoryOperator(){
		return new OperatorFactory();
	}
	@Bean 
	public PostfixGeneratorProxy postfixGeneratorProxy(){
		return new PostfixGeneratorProxy(factoryOperator);
	}
	@Bean 
	public Calculator calculator(){
		return new Calculator(factoryOperator,postfixGeneratorProxy);
	}
}