import java.util.Scanner;

public class Practical2 {

	static int nbOfRows;
	static int nbOfCols;
	public static void main(String[] args) 
	{
		try(Scanner input = new Scanner(System.in))
		{
			System.out.println("Enter the number of rows and columns of the array:");
			nbOfRows = input.nextInt();
			nbOfCols = input.nextInt();
			double[][] array = new double[nbOfRows][nbOfCols];

			System.out.println("Enter the array:");
			for(int i=0; i<nbOfRows;i++)
			{
				for(int j=0; j<nbOfCols;j++)
				{
					array[i][j] = input.nextDouble();
				}
			}
			locateLargest(array);
		}

	}

	static void locateLargest(double array[][])
	{
		double value=array[0][0];
		int[] location=new int[2];
		for(int i=0; i<nbOfRows;i++)
		{
			for(int j=0; j<nbOfCols;j++)
			{
				if(array[i][j]>value)
				{
					location[0]=i;
					location[1]=j;
					value = array[i][j];
				}
			}
		}
		System.out.println("The largest value is at ["+location[0]+", "+location[1]+"], "+array[location[0]][location[1]]);
	}

}
