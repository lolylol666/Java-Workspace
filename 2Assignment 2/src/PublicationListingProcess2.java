import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PublicationListingProcess2
{
	private static Publication	PublicationArray[];
	private static int			recordCount	= 0;
	private static List<String>	list;
								
	private enum PublicationTypes
	{
		PUBLICATIONCODE, PUBLICATIONNAME, PUBLICATIONYEAR, PUBLICATIONAUTHORNAME, PUBLICATIONCOST, PUBLICATIONNBPAGES
	};
	
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Welcome to Publication Listing Process 2.");
			System.out.println("Let's append some records.");
			System.out.println("Enter -1 as publication code number to stop.");
			PrintWriter writer = new PrintWriter(new FileWriter("PublicationData_Ouput.txt", true));
			insertRowsToFile(writer);
			writer.close();
			BufferedReader reader = new BufferedReader(new FileReader("PublicationData_Ouput.txt"));
			printFileItems(reader);
			reader.close();
			
			PublicationArray = new Publication[recordCount];
			
			for (int i = 0; i < PublicationArray.length; i++)
			{
				Scanner listReader = new Scanner(list.get(i));
				
				PublicationArray[i] = new Publication(listReader.nextLong(), listReader.next(), listReader.nextInt(),
						listReader.next(), listReader.nextDouble(), listReader.nextInt());
				listReader.close();
			}
			
			System.out.println("Now enter pub code to search:");
			Scanner input = new Scanner(System.in);
			long pubCode = input.nextLong();
			
			System.out.println("Searching using binary search:");
			int index1 = binaryPublicationSearch(PublicationArray, 0, PublicationArray.length, pubCode);
			System.out.println((index1 == -1) ? "The search found nothing."
					: ("The binary search found the code at index: " + index1));
					
			System.out.println("Searching using sequential search:");
			int index2 = sequentialPublicationSearch(PublicationArray, 0, PublicationArray.length, pubCode);
			System.out.println((index2 == -1) ? "The search found nothing."
					: ("The sequential search found the code at index: " + index2));
					
			System.out.println("Writing the array to Publications.dat...");
			
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Publications.dat"));
			
			output.writeObject(PublicationArray);
			
			input.close();
			output.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static int binaryPublicationSearch(Publication array[], int start, int end, long pubCode)
	{
		int cursor = (start + end) / 2;
		int index, count = 0;
		
		while (count <= (end - start))
		{
			++count;
			index = cursor;
			System.out.println(cursor + " " + array.length);
			if (array[cursor].getPublication_code() == pubCode)
			{
				System.out.println("Number of iterations: " + count);
				return index;
			} else if (array[cursor].getPublication_code() < pubCode)
				cursor = (cursor + 1 + end) / 2;
			else
				cursor = (start + cursor - 1) / 2;
		}
		return -1;
	}
	
	public static int sequentialPublicationSearch(Publication array[], int start, int end, long pubCode)
	{
		int index, count = 0;
		for (int i = start; i < end; i++)
		{
			index = i;
			++count;
			if (array[i].getPublication_code() == pubCode)
			{
				System.out.println("Number of iterations: " + count);
				return index;
			}
		}
		return -1;
	}
	
	public static void insertRowsToFile(PrintWriter writer)
	{
		Scanner input = new Scanner(System.in);
		while (true)
		{
			System.out.println("Enter the record [pub code][pub name][pub year][author name][pub cost][nb. pages]:");
			long pubCode = input.nextLong();
			if (pubCode == -1)
				return;
			writer.print(pubCode + " ");
			writer.print(input.next() + " ");
			writer.print(input.nextInt() + " ");
			writer.print(input.next() + " ");
			writer.print(input.nextDouble() + " ");
			writer.print(input.nextInt());
			writer.println();
		}
		
	}
	
	public static void printFileItems(BufferedReader reader)
	{
		try
		{
			String line;
			list = new ArrayList<String>();
			while ((line = reader.readLine()) != null && line.length() != 0)
			{
				System.out.println(line);
				list.add(line);
				recordCount++;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
