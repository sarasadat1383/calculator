package multiThreadedCalculator.calculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.*;

import java.util.*;

import operators.*;

public class Main {
	public static void main(String[]args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("multiThreadedCalculator.calculator.server", "operators");
		context.refresh();
		Server object = context.getBean(Server.class);
		object.server();
	}
}