
public abstract class ChocolatePlus extends CandyBar
{
	protected boolean hasNuts;

	public ChocolatePlus()
	{
		super();
		hasNuts = false;
	}

	public ChocolatePlus(String name, int cal, boolean hasNuts)
	{
		super(name, cal);
		this.hasNuts = hasNuts;
	}

	public String toString()
	{
		return super.toString();
	}
}
