package multiThreadedCalculator.calculator.myCalculator.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;
import java.util.*;
import java.lang.*;
import operators.*;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class.getName());

	public static void main (String[]args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationBeans.class);
		Server obj = (Server)context.getBean(Server.class);
		obj.server();
	}
}