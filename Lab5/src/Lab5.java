import java.util.Scanner;

public class Lab5 
{

	static String strContainer;
	static char[] newName;
	public static void main(String[] args) 
	{
		try(Scanner input = new Scanner(System.in);)
		{
			strContainer = input.nextLine();
			strContainer = strContainer.trim();
			char charArray[] = strContainer.toCharArray();
			for(int i = 1;i <= charArray.length; i++, charArray = newName)
			{
				rotateChars(charArray);
			}
		}


	}

	public static void rotateChars(char[] name)
	{
		newName = new char[name.length];
		for(int i = 0; i < name.length-1; i++)
		{
			newName[i+1] = name[i];
		}
		newName[0] = name[name.length-1];
		String output = new String(newName);
		System.out.println(output);
	}

}
