import java.io.File;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String directory;

		System.out.println("Enter a directory or file:");
		directory = input.nextLine();

		System.out.println(getSize(new File(directory)) + " bytes");
		input.close();
	}

	public static long getSize(File file)
	{
		long size = 0;
		File[] files;

		if (file.exists())
		{
			if (file.isDirectory())
			{
				files = file.listFiles();
				for (int i = 0; i < files.length; i++)
				{
					size += getSize(files[i]);
				}
			} else
			{
				return file.length();
			}
		} else
			System.out.println("File does not exist.");
		return size;
	}

}
