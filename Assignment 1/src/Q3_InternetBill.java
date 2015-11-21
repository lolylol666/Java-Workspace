import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Q3_InternetBill 
{
	//Static fields declaration, where input and rates will be stored
	private static String combo;
	private static double hours;
	public static double[] packA = {9.95, 10, 2}; 
	public static double[] packB = {13.95, 20, 1}; 
	public static double[] packC = {19.95, 0, 0};

	//Initial greeting message
	public static void internetBill() throws InterruptedException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		System.out.println("Welcome to question #3: Internet Bill");
		Thread.sleep(1000);
		getCombo();
	}

	//Get the chosen combo input from user
	private static void getCombo() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException, NoSuchFieldException
	{
		try(Scanner input = new Scanner(System.in);)
		{
			//Introduction of different packages offered
			System.out.println("Please enter the letter of your internet combo from the following:");
			System.out.println("Package A: For $9.95/month 10 hours of access are provided. Additional hours are $2.00 per hour.\nPackage B: For $13.95/month 20 hours of access are provided. Additional hours are $1.00 per hour.\nPackage C: For $19.95/month unlimited access is provided.");

			//Acquire the input and turn it to upper case for readability
			combo = input.nextLine();
			combo = combo.toUpperCase();

			//Check validity of choice using regular expression
			if(combo.matches("[ABC]"))
			{
				getHours();
			}
			else
			{
				//Warns the user to enter a valid choice
				System.out.println("Choice not recognized. Please enter a choice from the list!");
				getCombo();
			}
		}
	}

	//Get the usage in hours input from the user 
	private static void getHours() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException, NoSuchFieldException
	{
		try(Scanner input = new Scanner(System.in);)
		{
			//Instructions
			System.out.println("Now, please enter the number of hours used (decimals accepted): ");
			if(input.hasNextDouble()) hours = input.nextDouble(); 	//Check if there is a valid input of type double
			else 
			{
				System.err.println("Please enter a valid number!"); //Warns the user of invalid input
				getHours();											//Try again
			}		
			output(); 												//If the inputs are accepted, proceed to the output method
		}
	}

	private static void output() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException, NoSuchFieldException
	{
		//Remind the user of his inputs
		System.out.println("You chose Package \""+combo+"\" with "+hours+" hours of usage.\n");

		//Get the rate of the chosen package with reflection and assign the values to an array of type double
		double[] pack = (double[]) Q3_InternetBill.class.getField("pack"+combo).get(null);
		double chosenPrice = (pack[0]+((hours-pack[1])>0?(hours-pack[1]):0)*pack[2]); //Calculate the monthly fees of chosen combo
		String[] evalPrice = evaluate(chosenPrice);									  //Get the rates of other choices by calling "evaluate" method

		//Announce the chosen price
		System.out.println("Your monthly bill with Package "+combo+" is "+chosenPrice+"$");

		//Check if the choice is the cheapest and send outputs appropriately
		if(evalPrice[2]=="noSavings") System.out.println("Congratulations! Your combo has the best price!");
		else System.out.println("Your bill could have been "+evalPrice[1]+"$/month by choosing Package "+evalPrice[0]+" instead, a saving of "+(chosenPrice-Double.valueOf(evalPrice[1]))+"$/month!");

		//Finishing procedures
		Main.finish("Q3_InternetBill", "internetBill");
	}

	//Method for evaluation all the choices and determining which is best
	private static String[] evaluate(double chosenPrice) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		String[]result = new String[3]; 								//Initialize a String array to contain the results
		HashMap<String, Double> price = new HashMap<String, Double>();  //Initialize a HashMap to contain all packages' rates
		
		//Use a loop to calculate the prices of all packages and assign the values to the HashMap
		for(char i='A'; i<='C'; ++i)
		{
			double[] pack = (double[]) Q3_InternetBill.class.getField("pack"+i).get(null);
			price.put(Character.toString(i), pack[0]+((hours-pack[1])>0?(hours-pack[1]):0)*pack[2]);
		}
		
		//Determine which is the cheapest
		result[1]=Double.toString(Math.min(price.get("A"), price.get("B")));
		result[1]=Double.toString(Math.min(Double.valueOf(result[1]), price.get("C")));
		
		//Compare the cheapest with the chosen combo to see if the user already chose the best one
		if(chosenPrice==Double.valueOf(result[1])) 
		{
			result[2] = "noSavings";
		} 
		else 
		{
			result[2] = "";
		}
		
		//Using a augmented for loop, determine which package had the best price
		for(Entry<String, Double> entry : price.entrySet())
		{
			if(entry.getValue().equals(Double.valueOf(result[1]))) result[0]=entry.getKey();
		}
		
		//Return the best price, the package of the best price, and whether the user has already chosen the cheapest option
		return result;
	}
}