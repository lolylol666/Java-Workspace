import java.util.ArrayList;
import java.util.List;
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
		int input;

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

			if (keyIn.hasNextInt())
			{
				input = keyIn.nextInt();
				keyIn.nextLine();
				switch (input)
				{
					case 1:
						addItem();
						break;
					case 2:
						changeItem();
						break;
					case 3:
						displayAllType();
						break;
					case 4:
						displayUnderPrice();
						break;
					case 5:
						stats();
						break;
					case 6:
						System.out.println("Goodbye!");
						keyIn.close();
						System.exit(0);
						break;
				}
			} else
				keyIn.nextLine();
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
		} else
			System.out.println("Array is full. Cannot add more than 100 items!");
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

		displayItem(itemNumber);

		while (true)
		{
			int input;

			while (true)
			{
				System.out.println(
						"What would you like to change?\n1. Type\n2. Description\n3. Price\n4. Quit\nEnter choice -->");
				if (keyIn.hasNextInt())
				{
					input = keyIn.nextInt();
					if (input >= 1 && input <= 4)
						break;
				} else
					keyIn.nextLine();
			}

			switch (input)
			{
				case 1:
					System.out.println("Enter a new type: ");
					goodsArray[itemNumber].setType(keyIn.nextLine());
					displayItem(itemNumber);
					break;
				case 2:
					System.out.println("Enter a new description: ");
					goodsArray[itemNumber].setDescription(keyIn.nextLine());
					displayItem(itemNumber);
					break;
				case 3:

					while (true)
					{
						System.out.println("Enter a new price in format riksdaler, skillings and runstyckens: ");
						int ri, sk, ru;
						if (keyIn.hasNextInt())
						{
							ri = keyIn.nextInt();
							if (keyIn.hasNextInt())
							{
								sk = keyIn.nextInt();
								if (keyIn.hasNextInt())
								{
									ru = keyIn.nextInt();
									goodsArray[itemNumber].setPrice(new OldSwedishCurrency(ri, sk, ru));
									break;
								}
							}
						}
						keyIn.nextLine();
					}
					displayItem(itemNumber);
					break;
				case 4:
					return;
			}
		}

	}

	private static void displayItem(int itemNumber)
	{
		System.out.println("Item #: " + itemNumber);
		System.out.println("Type: " + goodsArray[itemNumber].getType());
		System.out.println("Description: " + goodsArray[itemNumber].getDescription());
		System.out.println("Price: " + goodsArray[itemNumber].getPrice().toString());
	}

	private static void displayAllType()
	{
		System.out.println("Enter the type you are searching for: ");
		String input = keyIn.nextLine();
		input = input.trim();
		int count = 0;
		for (int i = 0; i < index; i++)
		{
			if (goodsArray[i].getType().equals(input))
			{
				displayItem(i);
				count++;
			}
		}
		if (count == 0)
			System.out.println("Nothing found.");
	}

	private static void displayUnderPrice()
	{
		int count = 0;
		OldSwedishCurrency price;

		while (true)
		{
			System.out.println(
					"Enter the price under which you are searching for, in riksdaler, skillings and runstyckens: ");

			int ri, sk, ru;
			if (keyIn.hasNextInt())
			{
				ri = keyIn.nextInt();
				if (keyIn.hasNextInt())
				{
					sk = keyIn.nextInt();
					if (keyIn.hasNextInt())
					{
						ru = keyIn.nextInt();
						price = new OldSwedishCurrency(ri, sk, ru);
						break;
					}
				}
			}
			keyIn.nextLine();
		}

		for (int i = 0; i < index; i++)
		{
			if (price.compareTo(goodsArray[i].getPrice()) > 0)
			{
				displayItem(i);
				count++;
			}
		}

		if (count == 0)
			System.out.println("Nothing found.");
	}

	private static void stats()
	{
		while (true)
		{
			System.out.println(
					"What information would you like?\n1. Cost and details of cheapest item\n2. Cost and details of most costly item\n3. Number of items of a each type\n4. Average cost of items in inventory\n5. Quit\nEnter your choice >");
			if (keyIn.hasNextInt())
			{
				int input = keyIn.nextInt();
				keyIn.nextLine();
				if (input > 0 && input < 6)
				{
					switch (input)
					{
						case 1:
							for (int i = 0; i < index; i++)
							{
								if (lowestPrice() == goodsArray[i].getPrice().convertToRunstycken())
									displayItem(i);
							}
							break;
						case 2:
							for (int i = 0; i < index; i++)
							{
								if (highestPrice() == goodsArray[i].getPrice().convertToRunstycken())
									displayItem(i);
							}
							break;
						case 3:
							List<String> goodsList = new ArrayList<String>();
							for (int i = 0; i < index; i++)
							{
								if (!goodsList.contains(goodsArray[i].getType()))
								{
									goodsList.add(goodsArray[i].getType());
								}
							}
							for (String itemType : goodsList)
							{
								int typeNumber = 0;
								for (int i = 0; i < index; i++)
								{
									if (goodsArray[i].getType().equals(itemType))
										typeNumber++;
								}
								System.out.println("Type: " + itemType);
								System.out.println("Number: " + typeNumber + "\n");
							}
							break;
						case 4:
							int sum = 0;
							for (int i = 0; i < index; i++)
							{
								sum += goodsArray[i].getPrice().convertToRunstycken();
							}
							double average = (double) (sum / (index - 1));
							double decimal = (average % 1);
							OldSwedishCurrency avPrice = new OldSwedishCurrency(0, 0, (int) average);
							System.out.println(
									"The average price is: " + avPrice.toString() + " and" + decimal + "runstyckens.");
							break;
						case 5:
							return;
					}
				}
			} else
				keyIn.nextLine();
		}
	}

	private static int lowestPrice()
	{
		int result = 0;
		for (int i = 0, comp = goodsArray[0].getPrice().convertToRunstycken(); i < index; i++)
		{
			if (comp > goodsArray[i].getPrice().convertToRunstycken())
			{
				comp = goodsArray[i].getPrice().convertToRunstycken();
			}
			result = comp;
		}
		return result;
	}

	private static int highestPrice()
	{
		int result = 0;
		for (int i = 0, comp = goodsArray[0].getPrice().convertToRunstycken(); i < index; i++)
		{
			if (comp < goodsArray[i].getPrice().convertToRunstycken())
			{
				comp = goodsArray[i].getPrice().convertToRunstycken();
			}
			result = comp;
		}
		return result;
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
