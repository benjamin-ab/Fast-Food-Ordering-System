/*
 * <pre> 
 * Class: <b>Node</b> 
 * File: Node.java 
 * Course: TCSS 342 – Winter 2016
 * Assignment 1 – Burger Baron
 * Copyright 2016 Benjamin Abdipour
 * </pre>
 */

public class Node <Type> {
	private Node<Type> myNextNode;
	private Type myData;

	public Node(Type theType) {
		myData = theType;
		myNextNode = null;
	}

	public void setNext(Node<Type> theNextNode) {
		myNextNode = theNextNode;
	}

	public Node(Type theData, Node<Type> theNextNode) { 
		myData = theData;
		myNextNode = theNextNode;
	}

	public Node<Type> getNext() {
		return myNextNode;
	}

	@Override
	public String toString() {
		return myData.toString();
	}
}