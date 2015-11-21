import java.util.Scanner;

public class Lab4 {

	public static void main(String[] args) 
	{
		String strContainer;
		try(Scanner input = new Scanner(System.in);)
		{
			do
			{
				System.out.println("* The good news is you now know about looping in Java *\n\ndo you want to hear the good news again (yes or no)?");
				strContainer = input.nextLine();
				strContainer = strContainer.toLowerCase();
				strContainer = strContainer.trim();
				while(!strContainer.equals("yes")&&!strContainer.equals("no"))
				{
					System.out.println("bad answer, enter yes or no, case insensitive.\n\ndo you want to hear the good news again (yes or no)");
					strContainer = input.nextLine();
					strContainer = strContainer.toLowerCase();
					strContainer = strContainer.trim();
				}
			}while(strContainer.equals("yes"));
		}
	}

}
