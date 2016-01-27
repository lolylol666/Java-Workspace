import java.util.Scanner;

public class Main
{
	private final static String		PASSWORD	= "comp249";
	private static Scanner			keyIn;

	private static HouseholdGoods[]	goodsArray;
	private static int				index		= 0;

	public static void main(String[] args)
	{
		keyIn = new Scanner(System.in);
		goodsArray = new HouseholdGoods[100];
		String input;

		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Welcome to Shiqi Lu's marvelous Household Goods sold in swedish currency store!");
		System.out.println("-------------------------------------------------------------------------------");

		while (true)
		{
			System.out.println("What would you like to do?");
			System.out.println("1. Enter a new item in inventory (password required)");
			System.out.println("2. Change information of an item in inventory (password required)");
			System.out.println("3. Display all items of a specific type");
			System.out.println("4. Display all items under a certain price");
			System.out.println("5. Statistics on your inventory");
			System.out.println("6. Quit");
			System.out.println("Please enter your choice --> ");

			input = keyIn.nextLine();
			input = input.trim();
			switch (input)
			{
				case "1":
				case "2":
				case "3":
				case "4":
				case "5":
					break;
				case "6":
					keyIn.close();
					System.exit(0);
					break;
			}
		}

	}

	private static void addItem()
	{
		for (int i = 1; !checkPass(); i++)
		{
			if (i == 4)
				return;
			System.out.println("You have entered a wrong password (attempt " + i + " of 3). Please try again.");
		}

		if (index < 100)
		{
			String type, description;
			OldSwedishCurrency price;

			System.out.println("Please enter the type: ");
			type = keyIn.nextLine();

			System.out.println("Please enter the description: ");
			description = keyIn.nextLine();

			System.out.println("Please enter a price in riksdaler, skillings and runstyckens: ");
			price = new OldSwedishCurrency(keyIn.nextInt(), keyIn.nextInt(), keyIn.nextInt());

			goodsArray[index] = new HouseholdGoods(type, description, price);

			index++;
		}
	}

	private static void changeItem()
	{
		for (int i = 1; !checkPass(); i++)
		{
			if (i == 4)
				return;
			System.out.println("You have entered a wrong password (attempt " + i + " of 3). Please try again.");
		}

		int itemNumber;

		System.out.println("Please enter the item number you wish to update: ");
		itemNumber = keyIn.nextInt();

		while (itemNumber >= index || itemNumber < 0)
		{
			System.out.println("There is no item at that index. Enter another or enter -1 to quit to main menu: ");
			if ((itemNumber = keyIn.nextInt()) == -1)
				return;
		}

	}

	private static void displayAllType()
	{

	}

	private static void displayUnderPrice()
	{

	}

	private static void stats()
	{

	}

	private static HouseholdGoods lowestPrice()
	{
		return new HouseholdGoods();
	}

	private static HouseholdGoods highestPrice()
	{
		return new HouseholdGoods();
	}

	private static boolean checkPass()
	{
		System.out.println("Please enter password: ");
		String pass = keyIn.nextLine();
		if (pass.equals(PASSWORD))
			return true;
		else
			return false;
	}
}
