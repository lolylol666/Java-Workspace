// -------------------------------------------------------
// Assignment #3
// Written by: Shiqi Lu (40003741)
// For COMP 248 Section Q – Fall 2015
// --------------------------------------------------------

import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		try(Scanner input = new Scanner(System.in))
		{
			System.out.println("Hello, and welcome to Shiqi Lu's assignment 3's main menu.\nEnter '1' to play tic-tac-toe, or '2' to order some pizza, and 'quit' to exit:");
			while(true)
			{
				String option = input.nextLine();
				option = option.toLowerCase();
				option = option.trim();
				switch(option)						//Choose the correct procedure according to user's choice
				{
				case "1":
					TicTacToe.tttStart();			//TicTacToe
					break;
				case "2":
					orderPizza(input);				//Pizza
					break;
				case "quit":
					System.exit(0);					//Exit
					break;
				default: System.out.println("Please enter a valid choice('1' for Tic-Tac-Toe, '2' for pizza, or quit)");
				}
			}
		}
	}

	private static void orderPizza(Scanner input) 
	{
		Pizza pizzaDefault = new Pizza();										//Initializes Pizza objects
		Pizza pizza1 = new Pizza("",0,0,0);
		Pizza pizza2 = new Pizza("",0,0,0);
		
		System.out.println("Welcome to Shiqi's delicioso pizzas mamamia~~");
		System.out.println("Please place your order for the 1st pizza.");
		System.out.println("Size? Small/Medium/Large:");						//Assign user inputs to pizza1's fields
		pizza1.setSpec("size", input.next());
		System.out.println("Indicate the number of each of the following toppings: cheese, pepperroni and mushroom");
		pizza1.setSpec("cheese", input.next());
		pizza1.setSpec("pepper", input.next());
		pizza1.setSpec("mush", input.next());
		
		System.out.println("Please place your order for the 2nd pizza.");
		System.out.println("Size? Small/Medium/Large:");						//Assign user inputs to pizza2's fields
		pizza2.setSpec("size", input.next());
		System.out.println("Indicate the number of each of the following toppings: cheese, pepperroni and mushroom");
		pizza2.setSpec("cheese", input.next());
		pizza2.setSpec("pepper", input.next());
		pizza2.setSpec("mush", input.next());
		
		System.out.println("\nHere are the three pizzas:\n");					//Displays the pizzas
		System.out.println("A "+pizzaDefault.toString()+" costs $"+pizzaDefault.calcCost());
		System.out.println("A "+pizza1.toString()+" costs $"+pizza1.calcCost());
		System.out.println("A "+pizza2.toString()+" costs $"+pizza2.calcCost()+"\n");
		
		System.out.println("\nResult of the comparison:");
		boolean result[] = {false, false, false};								//Compares the equality of the pizzas
		if(pizzaDefault.equals(pizza1)) result[0] = result[1] = true;
		if(pizzaDefault.equals(pizza2)) result[0] = result[2] = true;
		if(pizza1.equals(pizza2)) result[1] = result[2] = true;
		
		if(result[0]&&result[1]&&result[2]) System.out.println("All three pizzas are same!");
		else if(result[0]||result[1]||result[2])
		{
			if(result[0]&&result[1]) System.out.println("First and third pizza are the same!");
			else if(result[0]&&result[2]) System.out.println("First and second pizza are the same!");
			else if(result[1]&&result[2]) System.out.println("Second and third pizza are the same!");
		}
		else System.out.println("None of the pizzas are same!");
		
		pizzaDefault.setSpec("size", pizza1.getSize());											//Changing first pizza to match pizza1
		pizzaDefault.setSpec("cheese", String.valueOf(pizza1.getSpec("cheese")));
		pizzaDefault.setSpec("pepper", String.valueOf(pizza1.getSpec("pepper")));
		pizzaDefault.setSpec("mush", String.valueOf(pizza1.getSpec("mush")));
		
		System.out.println("\nResult of the comparison after changing the first pizza:");
		result = new boolean[]{false, false, false};											//Comparing after the changes
		result[0] = result[1] = true;
		if(pizzaDefault.equals(pizza2)) result[2] = true;
		
		if(result[0]&&result[1]&&result[2]) System.out.println("All three pizzas are same!");	
		else System.out.println("First and second pizza are the same!");
		
		System.out.println("\nA "+pizzaDefault.toString()+" costs $"+pizzaDefault.calcCost());	//Displays the pizzas
		System.out.println("A "+pizza2.toString()+" costs $"+pizza2.calcCost()+"\n");
		
		System.out.println("\nBon appetit and hasta la vista~\n\n");
	}
}
