import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Q2_MagicDate 
{
	private static int day, month, year;
	
	//Starting method
	public static void magicDate() throws InterruptedException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{		
		System.out.println("Welcome to question #2: Magic Date");
		Thread.sleep(1000);
		getDate();
	}
	
	//Get date input
	private static void getDate() throws InterruptedException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		try(Scanner input = new Scanner(System.in);)
		{

			System.out.println("Please enter a date in the [mm dd yyyy] format:");
			
			//Check if input is integer
			if(input.hasNextInt()) 			month = input.nextInt();
			else fail();
			if(input.hasNextInt()) 			day = input.nextInt();
			else fail();
			if(input.hasNextInt()) 			year = input.nextInt();
			else fail();
			
			//Check if date is valid and in appropriate format, otherwise warn and restart
			if((month <= 12)&&(day <= 31)&&(year <= 9999))
			{
				//Check if date is magic using ternary operator, and outputs the answer.
				System.out.println("The date you entered, "+month+"/"+day+"/"+year+(((month*day)==((year%10)+(year/10%10)*10))?" IS ":" is NOT ")+"a magic date!");
			}
			else 
			{
				System.out.println("Please enter a valid date in the right format! Try again...");
				getDate();
			}
			Main.finish("Q2_MagicDate", "magicDate");
		}
	}
	
	//Method that warns the user of an invalid input and tries to get input again
	private static void fail() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException, NoSuchFieldException
	{
		System.err.println("Please enter an INTEGER!");
		getDate();
	}

}
