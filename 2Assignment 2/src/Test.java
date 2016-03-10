import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Test
{
	public static void main(String[] args)
	{
		try
		{
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("Publications.dat"));
			
			Publication[] array = (Publication[]) input.readObject();
			
			System.out.println(array.length);
			
			for (int i = 0; i < array.length; i++)
			{
				System.out.println(array[i].getPublication_code() + " " + array[i].getPublication_name() + " "
						+ array[i].getPublication_year() + " " + array[i].getPublication_authorname() + " "
						+ array[i].getPublication_cost() + " " + array[i].getPublication_nbpages());
			}
			input.close();
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
