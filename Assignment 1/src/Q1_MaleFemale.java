import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Q1_MaleFemale 
{
	//Variables that will contain the values. Type is double to ensure compatibility in arithmetic operations
	private static double males, females;
	
	//Starting method and announcement of choice
	public static void maleFemale() throws InterruptedException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{			
		System.out.println("Welcome to question #1: Male/Female\n"); 
		Thread.sleep(1000);
		getMales();
	}
	
	//Get males input
	private static void getMales() throws InterruptedException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{


		try(Scanner input = new Scanner(System.in);)
		{
			//Sequence of messages and check if input is integer
			System.out.println("Please enter the number of males in the class in whole numbers.");
			if(input.hasNextInt()) 
			{
				males = input.nextInt();
				getFemales();
			}
			else 
			{
				System.out.println("Please enter an INTEGER.");
				getMales();
			}
		}
	}
	
	//Get females input
	private static void getFemales() throws InterruptedException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		try(Scanner input = new Scanner(System.in);)
		{
			//Sequence of messages and check if input is integer
			System.out.println("Please enter the number of females in the class in whole numbers.");
			if(input.hasNextInt()) 
			{
				females = input.nextInt();
				output();
			}
			else 
			{
				System.err.println("Please enter an INTEGER!");
				getFemales();
			}
		}
	}
	
	//Outputs gathered in one method
	private static void output() throws InterruptedException //Exception for finish()
	, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		//Sequence of outputs, where variables of type double are converted to int for readability
		System.out.println("Good. There are "+(int)males+" males and "+(int)females+" females, a total of "+(int)(males+females)+" students in this class.");
		double ratioMales = Math.round(1000d*(males/(males+females)))/1000d;
		double ratioFemales = Math.round(1000d*(females/(males+females)))/1000d;
		
		//The percentages and ratios
		System.out.println("Percentages:\nMales ---> "+ratioMales*100+"% ~ "+ratioMales);
		System.out.println("Females ---> "+ratioFemales*100+"% ~ "+ratioFemales);
		
		//Finishing procedure
		Main.finish("Q1_MaleFemale", "maleFemale");
	}


}



