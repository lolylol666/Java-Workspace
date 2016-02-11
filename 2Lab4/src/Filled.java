
public class Filled extends ChocolatePlus
{
	private String filledWith;

	public Filled()
	{
		super();
		filledWith = "";
	}

	public Filled(String name, int cal, boolean hasNuts, String filledWith)
	{
		super(name, cal, hasNuts);
		this.filledWith = filledWith;
	}

	@Override
	public void info()
	{
		String nuts;
		if (this.hasNuts != true)
			nuts = ("This candy bar has no nuts\n");
		else
			nuts = ("This candy bar has nuts\n");
		System.out.println(super.toString() + nuts + toString());
	}

	public String toString()
	{
		return ("Yummy!! This bar is filled with " + filledWith + "\n");
	}

}
