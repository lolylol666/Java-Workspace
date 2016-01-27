
public class HouseholdGoods
{
	private String				type, description;
	private OldSwedishCurrency	price;

	private static int			numberOfGoods;

	public HouseholdGoods()
	{
		type = description = null;
		price = new OldSwedishCurrency();
		numberOfGoods++;
	}

	public HouseholdGoods(String type, String description, OldSwedishCurrency price)
	{
		this.type = type;
		this.description = description;
		this.price = new OldSwedishCurrency(price);
		numberOfGoods++;
	}

	public boolean equals(HouseholdGoods goods)
	{
		if (this.type.equals(goods.getType()) && this.description.equals(goods.getDescription())
				&& this.price.equals(goods.getPrice()))
			return true;
		else
			return false;
	}

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
