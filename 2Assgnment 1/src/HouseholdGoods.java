// -------------------------------------------------------
// Assignment 1
// Written by: Shiqi Lu 40003741
// For COMP 249 Section PP – Winter 2016
// --------------------------------------------------------

/**
 * {@link HouseholdGoods} regroups many caracteristics of retail goods.
 * 
 * @author Shiqi Lu
 *
 */
public class HouseholdGoods
{
	private String				type, description;
	private OldSwedishCurrency	price;

	private static int			numberOfGoods;

	/**
	 * Default constructor. Initializes the fields with null string and create a
	 * new {@link OldSwedishCurrency} object with a value of 0. Increments the
	 * static field numberOfGoods.
	 */
	public HouseholdGoods()
	{
		type = description = null;
		price = new OldSwedishCurrency();
		numberOfGoods++;
	}

	/**
	 * Constructor with a set of parameters. Set the new {@link HouseholdGoods}
	 * object's fields appropriately.
	 * 
	 * @param type
	 *            The type of the good.
	 * @param description
	 *            The description of the good.
	 * @param price
	 *            The price in {@link OldSwedishCurrency}.
	 */
	public HouseholdGoods(String type, String description, OldSwedishCurrency price)
	{
		this.type = type;
		this.description = description;
		this.price = new OldSwedishCurrency(price);
		numberOfGoods++;
	}

	/**
	 * The copy constructor. Initializes a new {@link OldSwedishCurrency} with
	 * the passed one's parameters.
	 * 
	 * @param goods
	 *            {@link OldSwedishCurrency} to be copied.
	 * @return A new {@link OldSwedishCurrency} with the same caracteristics.
	 */
	public boolean equals(HouseholdGoods goods)
	{
		if (this.type.equals(goods.getType()) && this.description.equals(goods.getDescription())
				&& this.price.equals(goods.getPrice()))
			return true;
		else
			return false;
	}

	/**
	 * Returns a formatted String representing the {@link OldSwedishCurrency}.
	 */
	public String toString()
	{
		return ("Type: " + type + "\nDescription: " + description + "\nPrice: " + price.toString() + "\n");
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public OldSwedishCurrency getPrice()
	{
		return price;
	}

	public void setPrice(OldSwedishCurrency price)
	{
		this.price = price;
	}

	public static int getNumberOfGoods()
	{
		return numberOfGoods;
	}
}
