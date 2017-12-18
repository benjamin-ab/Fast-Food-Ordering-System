/*
 * <pre> 
 * Class: <b>MyStack</b> 
 * File: MyStack.java 
 * Course: TCSS 342 – Winter 2016
 * Assignment 1 – Burger Baron
 * Copyright 2016 Benjamin Abdipour
 * </pre>
 */

public class MyStack <Type> {
	private int size;
	private Node<Type> myTop;

	public MyStack(Type theType) {
		size = 0;
		myTop = null;
	}

	public boolean isEmpty() {
		return(size() == 0);
	}

	public void push(Type item) {
		if(size() == 0) {
			myTop =  new Node<Type>(item);
			size++;
		} else {
			Node<Type> localNode = new Node<Type>(item, myTop);
			myTop = localNode;
			size++;
		}
	}

	@SuppressWarnings("unchecked")
	public Type pop() {
		Node<Type> localNode = myTop;
		if(size > 0) {
			myTop = myTop.getNext();
			size--;
		}

		return (Type) localNode.toString();
	}

	@SuppressWarnings("unchecked")
	public Type peek() {
		return (Type) myTop;
	}

	public int size() {
		return size;
	}

	public String toString() {
		String localStr = "";
		Node<Type> localNode = myTop;

		for(int i = 0; i < size; i++) {
			if(i != size - 1) {
				localStr += localNode.toString() + ", ";	
				localNode = localNode.getNext();
			} else {
				localStr += localNode.toString();
			}
		}

		if(!"".equals(localStr)) {
			localStr = "[" + localStr + "]";
		}

		return localStr;
	}	
}

/**
MyStack <Type> () a constructor that initializes an empty MyStack.
boolean isEmpty() returns true if the MyStack is empty.
void push(Type item) this method adds the item to the top of the MyStack .
Type pop() this method removes and returns the item on the top of the MyStack .
Student peek() this method returns the item on the top of the MyStack but does not alter the MyStack .
int size() this method returns the number of items in the MyStack.
String toString() this method converts the contents of the MyStack to a String for display.
 */