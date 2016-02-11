
public class JustChocolate extends CandyBar
{
	private int cacaoPercentage, weight;

	public JustChocolate()
	{
		super();
		cacaoPercentage = weight = 0;
	}

	public JustChocolate(String name, int cal, int cacaoPercentage, int weight)
	{
		super(name, cal);
		this.cacaoPercentage = cacaoPercentage;
		this.weight = weight;
	}

	public void info()
	{
		System.out.println(super.toString() + "\n" + toString());
	}

	public String toString()
	{
		return "This candy bar is made with " + cacaoPercentage + "% cacao and weighs " + weight + " grams\n";
	}

}
