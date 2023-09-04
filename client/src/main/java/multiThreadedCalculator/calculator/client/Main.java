package multiThreadedCalculator.calculator.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

public class Main {
	public static void main(String[]args) {	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("multiThreadedCalculator.calculator.client");
		context.refresh();
		Client object = context.getBean(Client.class);
		object.Client();
	}
}