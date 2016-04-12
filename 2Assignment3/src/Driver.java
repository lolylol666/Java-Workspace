
public class Driver
{
	
	public static void main(String[] args)
	{
		OrderBook list = new OrderBook();
		
		list.addBOrder("dfasf", 1133, 3000);
		list.addBOrder("dfasf", 1233, 3000);
		list.addBOrder("dfasf", 1133, 3000);
		
		list.addOOrder("fdssf", 1333, 2700);
		list.addOOrder("fdssf", 1633, 400);
		list.addOOrder("fdssf", 1733, 400);
		list.addOOrder("fdssf", 1833, 400);
		list.addOOrder("fdssf", 1333, 2000);
		
		list.addBOrder("dfasf", 1333, 3000);
		list.addBOrder("dfasf", 1834, 2900);
		list.outputBook();
		list.outputBBO();
	}
	
}
