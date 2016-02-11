//----------------------------------------
// Nothing to add and is not to be changed
//----------------------------------------

public abstract class CandyBar {
	private String name;
	private int calories;
	
	public CandyBar()
	{
		name="";
	}
	
	public CandyBar(String name, int cal)
	{
		this.name = name;
		calories = cal;
	}
		
	public String toString()
	{
		return "A(n) " + name + " has " + calories + " calories.\n";	}
	
	public abstract void info();

}