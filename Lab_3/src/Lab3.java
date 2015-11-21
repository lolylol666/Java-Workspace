import java.util.Scanner;

public class Lab3 {

	public static void main(String[] args) 
	{
		try(Scanner scan = new Scanner(System.in);)
		{
			String input = scan.nextLine();
			for(int index = input.length()-1; index>=0 ; --index)
			{
				System.out.print(input.charAt(index));
			}
		}

	}

}
