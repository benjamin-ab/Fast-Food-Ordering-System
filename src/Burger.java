/*
 * <pre> 
 * Class: <b>Burger</b> 
 * File: Burger.java 
 * Course: TCSS 342 – Winter 2016
 * Assignment 1 – Burger Baron
 * Copyright 2016 Benjamin Abdipour
 * </pre>
 */

public class Burger {
	private MyStack<String> myStack; 
	private String myNonBaronRecipe;
	private String myBaronRecipe;
	private String myCheeseCategory;
	private String myVeggiesCategory;
	private String mySauceCategory;
	private MyStack<String> myStackTemp;
	private String myPattyType;
	private int myPattyCount;

	public void init() {
		myPattyCount = 0;
		myStackTemp = new MyStack<>(null);
		myPattyType = "beef";
		myStack = new MyStack<>(null);
		myCheeseCategory = "cheddar,mozzarella,pepperjack";
		myVeggiesCategory = "mushrooms,onions,tomato,lettuce,pickle";
		mySauceCategory = "ketchup,mustard,baron-sauce,mayonnaise";
		makeRecipe();
	}

	public void makeRecipe() {
		myNonBaronRecipe = "bun," + myPattyType +",bun";
		myBaronRecipe = "bun,ketchup,mustard,mushrooms," + myPattyType +",cheddar,mozzarella,pepperjack,"
				+ "onions,tomato,lettuce,baron-sauce,mayonnaise,bun,pickle";
	}

	public Burger (boolean theWorks) {
		init();
		if(theWorks) {
			addIngredientList(myBaronRecipe);
		} else {
			addIngredientList(myNonBaronRecipe);
		}
	}

	public void addIngredientList(String theList) {
		for (int i = 0; i < theList.split(",").length; i++) {
			myStack.push(theList.split(",")[i]);
		}
		myPattyCount++;
	}

	public void removePatty() {
		boolean pattyRemoved = false;
		while(myStack.size() != 0 && myPattyCount > 1 && !pattyRemoved) {
			if (myPattyType.equals(String.valueOf(myStack.peek()))) {
				myStack.pop();
				myPattyCount--;
				pattyRemoved = true;
			} else {
				myStackTemp.push(String.valueOf(myStack.pop()));
			} 
		}
		retrieveMyStack();
	}

	public void retrieveMyStack() {
		while(myStackTemp != null && myStackTemp.size() != 0) {
			myStack.push(String.valueOf(myStackTemp.pop()));
		}
	}

	public void addPatty() {
		while(myStack.size() != 0 
				&& !("pepperjack".equals(String.valueOf(myStack.peek()))
						|| "mozzarella".equals(String.valueOf(myStack.peek()))
						|| "cheddar".equals(String.valueOf(myStack.peek()))
						||  myPattyType.equals(String.valueOf(myStack.peek())))) {
			myStackTemp.push(String.valueOf(myStack.pop()));	
		}
		myStack.push(myPattyType);
		myPattyCount++;
		retrieveMyStack();
	}

	public void changePatties(String pattyType) {
		while(myStack.size() != 0 && !pattyType.equals(myPattyType)) {
			if (myPattyType.equals(String.valueOf(myStack.peek()))) {
				myStack.pop();
				myStack.push(pattyType);
			} else {
				myStackTemp.push(String.valueOf(myStack.pop()));		
			}
		}
		myPattyType = pattyType;
		makeRecipe();
		retrieveMyStack();
	}

	public void removeCategory(String type) {
		switch (type) {
		case "cheese": 
			for(String ingredient : myCheeseCategory.split(",")) {
				removeIngredient(ingredient);
			}
			break;
		case "veggies": 
			for(String ingredient : myVeggiesCategory.split(",")) {
				removeIngredient(ingredient);
			}
			break;
		case "sauce": 
			for(String ingredient : mySauceCategory.split(",")) {
				removeIngredient(ingredient);
			}
			break;
		}
	}

	public void addCategory(String type) {
		switch (type) {
		case "cheese": 
			for(String ingredient : myCheeseCategory.split(",")) {
				addIngredient(ingredient);
			}
			break;
		case "veggies": 
			for(String ingredient : myVeggiesCategory.split(",")) {
				addIngredient(ingredient);
			}
			break;
		case "sauce": 
			for(String ingredient : mySauceCategory.split(",")) {
				addIngredient(ingredient);
			}
			break;
		}
	}

	public void addIngredient(String type) {
		boolean ingredientAdded = false;
		while(myStack.size() != 0 && !ingredientAdded) {
			for (int i = myBaronRecipe.split(",").length - 1; i >= 0 && !ingredientAdded; i--) {
				if (type.equals(myBaronRecipe.split(",")[i].toString())) {
					for(String cheese : myCheeseCategory.split(",")) {
						if(type.equals(cheese)) {
							int myPattyremained = myPattyCount;
							while(myPattyremained > 1) {
								myStackTemp.push(String.valueOf(myStack.pop()));
								myPattyremained--;
							}
						}
					}

					myStack.push(type);
					ingredientAdded = true;
				}  else if(String.valueOf(myStack.peek()).equals(myBaronRecipe.split(",")[i])){
					if(String.valueOf(myStack.peek()).equals(myPattyType)) {
						removePattyAndCheese();
					} else {
						myStackTemp.push(String.valueOf(myStack.pop()));
					}
				}
			}
		}
		retrieveMyStack();
	}

	public void removePattyAndCheese() {
		int pattyInList = myPattyCount;
		while (pattyInList > 0) {
			if(String.valueOf(myStack.peek()).equals(myPattyType)) {
				pattyInList--;
			}
			myStackTemp.push(String.valueOf(myStack.pop()));
		}
	}

	public void removeIngredient(String type) {
		boolean ingredientRemoved = false;
		while(myStack.size() != 0 && !ingredientRemoved) {
			if (type.equals(String.valueOf(myStack.peek()))) {
				myStack.pop();
				ingredientRemoved = true;
			} else {
				myStackTemp.push(String.valueOf(myStack.pop()));		
			}
		}
		retrieveMyStack();
	}

	public String toString() {
		String output = "";

		/**
		for test purposes only. prints out a list of ingredients based on the Baron Burger recipe
		
		for (int i = myBaronRecipe.split(",").length - 1; i >= 0; i--) {
			output += myBaronRecipe.split(",")[i] + ", ";
		}
		output = "";
		*/

		while(myStack.size() != 0) {
			String localStr = "";
			localStr = String.valueOf(myStack.pop());
			myStackTemp.push(localStr);
			for(String ingredientPart : localStr.split("-")) {
				output += ingredientPart.substring(0, 1).toUpperCase();
				output += ingredientPart.substring(1).toLowerCase();
				output += "-";
			}
			output = output.substring(0, output.length() - 1);

			if (myStack.size() != 0) {
				output += ", ";
			}
		}

		if(!"".equals(output.trim())) {
			output = "[" + output + "]";
		}

		retrieveMyStack();
		return output;
	}	
}


/**
Burger (boolean theWorks) a constructor that initializes a Burger with only a bun and patty if theWorks is false and a Baron Burger if theWorks is true.
void changePatties(String pattyType) this method changes all patties in the Burger to the pattyType.
void addPatty() this method adds one patty to the Burger up to the maximum of 3.
void removePatty() this method removes one patty from the Burger down to the minimum of 1.
void addCategory(String type) this method adds all items of the type to the Burger in the proper locations.
void removeCategory(String type) this method removes all items of the type from the Burger.
void addIngredient(String type) this method adds the ingredient type to the Burger in the proper location.
void removeIngredient(String type) this method removes the ingredient type from the Burger.
String toString() this method converts the Burger to a String for display.
 */
