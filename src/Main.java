/*
 * <pre> 
 * Class: <b>Main</b> 
 * File: Main.java 
 * Course: TCSS 342 – Winter 2016
 * Assignment 1 – Burger Baron
 * Copyright 2016 Benjamin Abdipour
 * </pre>
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static final String MY_INPUT_FILENAME = "customer.txt";
	private static Burger localBurger;
	private static int orderNumber = 0;
	private final static int MAX_ORDER_NUMBER = 99;

	public static void main(String[] args) throws FileNotFoundException {
		File localFile = new File(MY_INPUT_FILENAME);
		Scanner localScanner = new Scanner(localFile);
		while (localScanner.hasNextLine()) {
			String nextLine = localScanner.nextLine().trim().toLowerCase();
			if(!"".equals(nextLine.trim())) {
				parseLine(nextLine);
				
				if(orderNumber >= MAX_ORDER_NUMBER) {
					orderNumber = 0;
				}
				
				if(!"".equals(localBurger.toString().trim())) {
					orderNumber++;
				}
				System.out.printf("%2s %s %s %n", orderNumber, " ", localBurger.toString());
			}
		}

		testMyStack();
		testBurger();
	}

	public static void parseLine(String line) {
		if(line.contains("baron burger")) {
			localBurger = new Burger(true);
		} else {
			localBurger = new Burger(false);
		}

		boolean addingMode = false;
		boolean omittingMode = false;

		for (int i = 0; i < line.split(" ").length; i++) {
			switch (line.split(" ")[i]) {
			case "double": localBurger.addPatty(); break;
			case "triple": localBurger.addPatty(); localBurger.addPatty(); break;
			case "chicken": localBurger.changePatties("chicken"); break;
			case "veggie": localBurger.changePatties("veggie"); break;
			case "with": addingMode = true; omittingMode = false; break;
			case "no": omittingMode = true; addingMode = false; break;
			case "but": omittingMode = !omittingMode; addingMode = !addingMode; break;
			case "and": break;
			default: if(addingMode == true) {
				switch (line.split(" ")[i]) {
				case "cheese": localBurger.addCategory("cheese"); break;
				case "veggies": localBurger.addCategory("veggies"); break;
				case "sauce": localBurger.addCategory("sauce"); break;
				default: localBurger.addIngredient(line.split(" ")[i]); break;
				}
			} else if (omittingMode == true) {
				switch (line.split(" ")[i]) {
				case "cheese": localBurger.removeCategory("cheese"); break;
				case "veggies": localBurger.removeCategory("veggies"); break;
				case "sauce": localBurger.removeCategory("sauce"); break;
				default: localBurger.removeIngredient(line.split(" ")[i]); break;
				}
			}
			}
		}
	}

	public static void testMyStack() {
		MyStack<String> myStack = new MyStack<String>("");

		System.out.println("--------------------------");
		System.out.println("TESTING MyStack()...");
		myStack.push("a");
		System.out.println("ELEMENT 'a' IS ADDED. NOW MyStack IS AS FOLLOW: " + myStack);
		myStack.push("b");
		System.out.println("ELEMENT 'b' IS ADDED. NOW MyStack IS AS FOLLOW: " + myStack);
		myStack.push("c");
		System.out.println("ELEMENT 'c' IS ADDED. NOW MyStack IS AS FOLLOW: " + myStack);
		System.out.println("THIS IS A PEEK TO THE LAST ELEMENT IN MyStack: " + String.valueOf(myStack.peek()) + " NOW MyStack IS AS FOLLOW: " + myStack);
		myStack.pop();
		System.out.println("THE LAST ELEMENT IS REMOVED. NOW MyStack IS AS FOLLOW: " + myStack);
		System.out.println("IS MyStack EMPTY? " + myStack.isEmpty());
		System.out.println("WHAT IS THE SIZE OF MyStack? " + myStack.size());
	}

	public static void testBurger() {
		Burger myBurger = null;

		System.out.println("--------------------------");
		System.out.println("TESTING Burger()...");
		System.out.println("THIS IS A BURGER: " + new Burger(false).toString());
		System.out.println("THIS IS A BARON BURGER: " + new Burger(true).toString());
		System.out.print("THIS IS A DOUBLE BURGER: ");
		myBurger = new Burger(false);
		myBurger.addPatty();
		System.out.println(myBurger.toString());

		System.out.print("THIS IS A DOUBLE BURGER WITH CHEDDAR: ");
		myBurger = new Burger(false);
		myBurger.addPatty();
		myBurger.addIngredient("cheddar");
		System.out.println(myBurger.toString());

		System.out.print("THIS IS THE DOUBLE BURGER WITHOUT CHEDDAR: ");
		myBurger.removeIngredient("cheddar");
		System.out.println(myBurger.toString());

		System.out.print("THIS IS THE DOUBLE BURGER REMOVING ONE PATTY: ");
		myBurger.removePatty();
		System.out.println(myBurger.toString());

		System.out.print("THIS IS THE DOUBLE BURGER CHANGING PATTY TO CHICKEN: ");
		myBurger.changePatties("chicken");
		System.out.println(myBurger.toString());

		System.out.print("THIS IS THE CHICKEN BURGER WITH SAUCE: ");
		myBurger.addCategory("sauce");
		System.out.println(myBurger.toString());

		System.out.print("THIS IS THE CHICKEN BURGER WITHOUT SAUCE: ");
		myBurger.removeCategory("sauce");
		System.out.println(myBurger.toString());
	}
}

/**
void main(String[] args) static main method used to run the program and test the program elements.
void parseLine(String line) parses a line of input from the file and outputs the burger.
void testMyStack() test method for MyStack.
void testBurger() test method for Burger.
 */
