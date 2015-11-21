import java.util.Scanner;

// -------------------------------------------------------
// Assignment #2
// Written by: Shiqi Lu (40003741)
// For COMP 248 Section Q – Fall 2015
// --------------------------------------------------------

public class DrawHouse 
{
	//Fields that are needed during runtime
	static int width, height, drawCount = 0, failCount = 0;
	static String strContainer;
	static String name;
	
	//Main method, includes greeting and asks the name of the user
	public static void main(String[] args) throws InterruptedException 
	{
		try(Scanner input = new Scanner(System.in);) 
		{
			System.out.println("Greetings, and welcome to Shiqi's Draw-a-house 3000!\n");
			System.out.println("May I have your name?");
			name = input.nextLine();
			name = name.trim();

			System.out.println("Well then "+name+", I hope you will thoroughly enjoy the experience of Draw-a-house 3000. Yay!");
			ask(input);
		} 
	}
	
	//Asks the user if he wants a house to be drawn, and calls appropriate methods
	private static void ask(Scanner input) throws InterruptedException 
	{
		System.out.println("By the way, do you actually want a house to be drawn (yes/no)?");
		strContainer = input.nextLine();		
		strContainer = strContainer.trim();
		strContainer = strContainer.toLowerCase();

		switch(strContainer)
		{
		case "yes":
			startDraw(input);
			break;
		case "no":
			finish();
			break;
		default: 
			System.out.println("Hum what? I don't understand. Oh well...");
			ask(input);
		}
	}
	
	//Draws the house by calling different methods successively
	private static void startDraw(Scanner input) throws InterruptedException 
	{
		System.out.println("Okay! Let's get started!");
		System.out.println("So "+name+", enter the WIDTH and the HEIGHT of the house to be drawn [ww hh]. Beware ---> EVEN numbers and in the right order please :3");

		getWidth(input);
		if(failCount>0) System.out.println("Now please enter the height:");
		failCount = 0;
		
		getHeight(input);
		failCount = 0;
		input.nextLine(); //"Refreshes" the input stream
		
		System.out.println("Perfect! Hey you know what "+name+"? You are pretty good with numbers! Yay!");
		Thread.sleep(1000);
		System.out.println("Now let's draw a house of "+width+" of width and "+height+" of height!");
		Thread.sleep(1000);

		drawRoof();
		drawBase();
		drawCount++;

		System.out.println("So "+name+", would you like me to draw you another house? Enter yes to continue or anything else to leave.");
		
		strContainer = input.nextLine();
		strContainer = strContainer.trim();
		strContainer = strContainer.toLowerCase();

		if(strContainer.equals("yes")) startDraw(input); //Asks the users if he wants to retry
		else finish();
	}
	
	//Obtains the width and checks if it's valid (integer, even, positive). Quits if more than 3 chances
	private static void getWidth(Scanner input)
	{			
		boolean check;
		for(check=!(input.hasNextInt()), strContainer=input.next(); check||Integer.valueOf(strContainer)%2!=0||Integer.valueOf(strContainer)<=0; ++failCount,check=!(input.hasNextInt()),strContainer=input.next()) 
		{		
			if(failCount==3) 
			{
				System.out.println("Oh dear "+name+", it seems this program is too complicated for you. Please google the definition of even numbers and try again.");
				finish();
			}
			System.out.println("Hey "+name+", EVEN NUMBERS please!");
			System.out.println("Please enter the desired width:");
			input.nextLine();
		}
		width = Integer.valueOf(strContainer);
	}

	//Obtains the height and checks if it's valid (integer, even, positive). Quits if more than 3 chances
	private static void getHeight(Scanner input)
	{
		boolean check;
		for(check=!(input.hasNextInt()), strContainer=input.next(); check||Integer.valueOf(strContainer)%2!=0||Integer.valueOf(strContainer)<=0; ++failCount,check=!(input.hasNextInt()),strContainer=input.next()) 
		{		
			if(failCount==3) 
			{
				System.out.println("Oh dear "+name+", it seems this program is too complicated for you. Please google the definition of even numbers and try again.");
				finish();
			}
			System.out.println("Hey "+name+", EVEN NUMBERS please!");
			System.out.println("Please enter the desired height:");
		}
		height = Integer.valueOf(strContainer);
	}

	//Draws the roof according to width
	private static void drawRoof()
	{
		for(int i=1; i<=width/2;++i)
		{
			String spaces = new String(new char[width/2-i]).replace("\0", " ");			//Obtains the number of spaces

			if(i==1) System.out.println(spaces+"**");
			else 
			{
				String interSpaces = new String(new char[(i-1)*2]).replace("\0", " ");	//Obtains the number of spaces between slashes
				System.out.println(spaces+"/"+interSpaces+"\\");
			}
		}
	}

	//Draws the base according to width and height
	private static void drawBase()
	{
		String lines = new String(new char[width]).replace("\0", "-");				//Obtains number of lines to be printed according to width
		System.out.println(lines);
		for(int i=1; i<=height;++i)
		{
			String interSpaces = new String(new char[width-2]).replace("\0", " ");	//Obtains the number of spaces between bars
			System.out.println("|"+interSpaces+"|");
		}
		System.out.println(lines);
	}

	//Program exiting procedure and tells user how many houses were drawn
	private static void finish() 
	{
		if(drawCount>0) System.out.println("You drew "+drawCount+" house(s).");
		else System.out.println("You did not draw any house :( Why "+name+" why!");
		System.out.println("Alright then "+name+", I bid you farewell!");
		System.exit(0);
	}	
}