
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PublicationListingProcess1
{
	private static Publication PublicationArray[];
	
	private enum PublicationTypes
	{
		PUBLICATIONCODE, PUBLICATIONNAME, PUBLICATIONYEAR, PUBLICATIONAUTHORNAME, PUBLICATIONCOST, PUBLICATIONNBPAGES
	};
	
	public static void main(String args[])
	{
		System.out.println("Welcome to Publication Listing Process 1.");
		System.out.println("Please enter the name of the output file:");
		String fileName;
		Scanner input = new Scanner(System.in);
		
		do
		{
			
			fileName = input.nextLine();
			File outFile = new File(fileName);
			
			if (!outFile.exists() || !outFile.isFile())
				break;
				
			long size = outFile.length();
			System.out.println("This file [" + size + " bytes] already exists. Please enter another:");
		} while (true);
		
		try
		{
			Scanner fileInput;
			PrintWriter fileOutput;
			fileInput = new Scanner(new FileReader("PublicationData_Input.txt"));
			fileOutput = new PrintWriter(fileName);
			
			correctListOfItems(fileInput, fileOutput);
			fileInput.close();
			fileOutput.close();
			
			System.out.println("PublicationData_Input.txt:");
			printFileItems(new Scanner(new FileReader("PublicationData_Input.txt")));
			
			System.out.println(fileName + ":");
			printFileItems(new Scanner(new FileReader(fileName)));
			
			input.close();
			
		} catch (
		
		FileNotFoundException e)
		
		{
			e.printStackTrace();
		}
		
	}
	
	public static void correctListOfItems(Scanner input, PrintWriter output)
	{
		int lines = 0;
		List<String> list = new ArrayList<String>();
		
		while (input.hasNextLine())
		{
			String line = input.nextLine();
			if (line.length() != 0)
			{
				lines++;
				list.add(line);
			}
		}
		
		if (lines <= 1)
		{
			System.out.println("Nothing to do.");
			return;
		}
		
		PublicationArray = new Publication[lines];
		List<Long> pubCodeList = new ArrayList<Long>();
		
		for (int i = 0; i < PublicationArray.length; i++)
		{
			Scanner listReader = new Scanner(list.get(i));
			
			long pubCode;
			PublicationArray[i] = new Publication(pubCode = listReader.nextLong(), listReader.next(),
					listReader.nextInt(), listReader.next(), listReader.nextDouble(), listReader.nextInt());
			listReader.close();
			pubCodeList.add(pubCode);
		}
		
		for (int i = 0; i < pubCodeList.size(); i++)
		{
			int index;
			while ((index = dupeCheck(i, pubCodeList)) >= 0)
			{
				System.out.println("There is a duplicate publication code [" + pubCodeList.get(index) + "] for entry "
						+ index + ".");
				System.out.println("Please enter another one");
				while (true)
				{
					Scanner codeIn = new Scanner(System.in);
					long newCode = codeIn.nextLong();
					pubCodeList.set(index, newCode);
					if (dupeCheck(index, pubCodeList) >= 0)
					{
						try
						{
							throw new CopyCodeException();
						} catch (CopyCodeException e)
						{
							System.out.println("You entered another duplicate pub code. Please try again.");
						}
					} else
					{
						break;
					}
				}
			}
		}
		
		for (int i = 0; i < PublicationArray.length; i++)
		{
			PublicationArray[i].setPublication_code(pubCodeList.get(i));
			output.println(PublicationArray[i].getPublication_code() + " " + PublicationArray[i].getPublication_name()
					+ " " + PublicationArray[i].getPublication_year() + " "
					+ PublicationArray[i].getPublication_authorname() + " " + PublicationArray[i].getPublication_cost()
					+ " " + PublicationArray[i].getPublication_nbpages());
		}
	}
	
	private static int dupeCheck(int index, List<Long> pubCodeList)
	{
		for (int i = 0; i < pubCodeList.size(); i++)
		{
			if (i != index)
			{
				if (pubCodeList.get(index).equals(pubCodeList.get(i)))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	public static void printFileItems(Scanner input)
	{
		while (input.hasNextLine())
		{
			System.out.println(input.nextLine());
		}
	}
	
}
