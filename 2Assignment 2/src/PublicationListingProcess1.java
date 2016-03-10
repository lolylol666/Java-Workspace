
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
		File outFile;
		
		while (true)
		{
			Scanner input = new Scanner(System.in);
			String fileName = input.nextLine();
			outFile = new File(fileName);
			input.close();
			if (!outFile.exists() || !outFile.isFile())
				break;
			long size = outFile.length();
			System.out.println("This file [" + size + " bytes] already exists. Please enter another:");
		}
		
		try
		{
			Scanner fileInput;
			PrintWriter fileOutput;
			Scanner finalFile;
			fileInput = new Scanner(new FileReader("PublicationData_Input.txt"));
			fileOutput = new PrintWriter(outFile);
			
			correctListOfItems(fileInput, fileOutput);
			printFileItems(fileInput);
			
			finalFile = new Scanner(new FileReader(outFile));
			printFileItems(finalFile);
			
			fileInput.close();
			fileOutput.close();
			
		} catch (FileNotFoundException e)
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
			lines++;
			list.add(input.nextLine());
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
			int index = dupeCheck(i, pubCodeList);
			if (index >= 0)
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
							codeIn.close();
							throw new CopyCodeException();
						} catch (CopyCodeException e)
						{
							System.out.println("You entered another duplicate pub code. Please try again.");
						}
					} else
					{
						codeIn.close();
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
				if (pubCodeList.get(index) == pubCodeList.get(i))
					return i;
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
