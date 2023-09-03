package multiThreadedCalculator.calculator.myCalculator.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import java.lang.*;

@Configuration
@PropertySource("client.properties")
public class ApplicationBeans {
	
	@Value("${port}")
	private int port;
	
	@Value("${ip}")
	private String ip;
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	@Bean
	public Client client(){
		Client client = new Client();
		client.setPort(port);
		client.setIp(ip);
		return client;
	}
}