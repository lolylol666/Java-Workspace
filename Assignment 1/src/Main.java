// -------------------------------------------------------
// Assignment 1
// Written by: Shiqi Lu #40003741
// For COMP 248 Section Q – Fall 2015
// --------------------------------------------------------

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) throws InterruptedException //Method 'main' throws sleep interruption exception
, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		try(Scanner input = new Scanner(System.in); ) //Declaration of Scanner class object
		{
			//Greetings
			System.out.println("Welcome to Shiqi Lu's assignment #1"); 

			//String type input container 
			String choice;

			//Introduction of choices offered
			System.out.println("Please enter the number or the title (case sensitive) of the question you want to view:");
			System.out.println("1. Male/female ratio \n2. Magic Date \n3. Internet Bill \nEnter \"exit\" to quit this program.");

			//Acquiring the input and announcing it.
			choice = input.nextLine();
			System.out.println("You chose: " + choice);

			//Analyzing the input and calling the appropriate method.
			switch(choice)
			{
			case "1":
			case "Male/female ratio":
				Q1_MaleFemale.maleFemale();
				break;

			case "2":
			case "Magic Date":
				Q2_MagicDate.magicDate();
				break;

			case "3":
			case "Internet Bill":
				Q3_InternetBill.internetBill();
				break;

			case "exit":
				System.out.println("The program will terminate in 3 seconds. Bye~");
				Thread.sleep(3000);
				System.exit(0);
				break;

			default: //If input not recognized, warns the user and try again
				System.out.println("Your input is not computable. Please choose from the list, or enter \"exit\".");
				Main.main(args);
				break;

			}

		}

	}
	
	//Finishing procedure, with java reflection integration for retry
	public static void finish(String className, String methodName) throws InterruptedException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		try(Scanner input = new Scanner(System.in);)
		{
			System.out.println("Type \"exit\" to quit, \"retry\" to try this question again, or anything else to return to main program.");
			String choice = input.nextLine();
			switch(choice)
			{
			case "exit":
				System.out.println("The program will terminate in 3 seconds. Bye~"); //Quits the program after 3 seconds sleep
				Thread.sleep(3000);
				System.exit(0);
				break;
			case "retry":
				Class.forName(className).getMethod(methodName).invoke(null);		 //Calls the appropriate starting method if user wishes to try same question again
				break;
			default:			
				Main.main(null);													 //Return to main program.
				break;
			}
		}
	}
}


