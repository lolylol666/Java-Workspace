
public class Order
{
	private String	ID;
	private double	price;
	private int		volume;
					
	public Order(String ID, double price, int volume)
	{
		this.ID = ID;
		this.price = price;
		this.volume = volume;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public void setID(String iD)
	{
		ID = iD;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public int getVolume()
	{
		return volume;
	}
	
	public void setVolume(int volume)
	{
		this.volume = volume;
	}
	
	public String toString()
	{
		return "";
	}
}
