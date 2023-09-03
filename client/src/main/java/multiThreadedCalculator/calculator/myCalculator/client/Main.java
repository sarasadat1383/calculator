package multiThreadedCalculator.calculator.myCalculator.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;
import java.util.*;
import java.lang.*;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class.getName());
	
	public static void main (String[]args) {	
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationBeans.class);
		Client obj = (Client)context.getBean(Client.class);
		obj.Client();
	}
}