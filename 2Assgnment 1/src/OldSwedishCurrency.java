// -------------------------------------------------------
// Assignment 1
// Written by: Shiqi Lu 40003741
// For COMP 249 Section PP – Winter 2016
// --------------------------------------------------------

/**
 * In 1777, the Swedes used a monetary system based on riksdaler, skilling, and
 * runstycken. This system was rather complicated because it was not decimal: 1
 * riksdaler was divided into 48 skillings and 1 skilling was divided into 16
 * runstyckens.
 * 
 * @author Shiqi Lu
 *
 */
public class OldSwedishCurrency
{
	private int riksdaler, skillings, runstyckens;

	/**
	 * Default constructor, which initializes all fields to 0;
	 */
	public OldSwedishCurrency()
	{
		this(0, 0, 0);
	}

	/**
	 * This is the constructor with parameters for each unit. This sets the
	 * fields of the {@link OldSwedishCurrency} according to parameters. Then it
	 * normalizes the fields, such that all fields respect the scale of this
	 * currency.
	 * 
	 * @param riksdaler
	 *            This is the number of Riksdaler, a Riksdaler is equivalent to
	 *            48 Skilling
	 * @param skillings
	 *            This is the number of Skilling, a Skilling is equivalent to 16
	 *            Runstyken
	 * @param runstyckens
	 *            This is the number of Runstyken
	 */
	public OldSwedishCurrency(int riksdaler, int skillings, int runstyckens)
	{
		if (riksdaler < 0 || skillings < 0 || runstyckens < 0)
			riksdaler = skillings = runstyckens = 0;
		this.riksdaler = riksdaler;
		this.skillings = skillings;
		this.runstyckens = runstyckens;
		normalize();
	}

	/**
	 * The copy constructor. Initializes a new {@link OldSwedishCurrency} object
	 * with the parameters of the passed {@link OldSwedishCurrency} object.
	 * 
	 * @param oldSC
	 */
	public OldSwedishCurrency(OldSwedishCurrency oldSC)
	{
		riksdaler = oldSC.getRiksdaler();
		skillings = oldSC.getSkillings();
		runstyckens = oldSC.getRunstyckens();
	}

	/**
	 * The method used to normalized newly passed values to the scale of
	 * {@link OldSwedishCurrency}. 1 riksdaler = 48 skillings. 1 skilling = 16
	 * runstyckens.
	 */
	private void normalize()
	{
		int ri, sk;

		sk = (runstyckens / 16);
		runstyckens -= sk * 16;
		skillings += sk;

		ri = (skillings / 48);
		skillings -= ri * 48;
		riksdaler += ri;
	}

	/**
	 * Function that checks if the calling {@link OldSwedishCurrency} and the
	 * passed {@link OldSwedishCurrency} have the same value.
	 * 
	 * @param oldSC
	 *            The passed {@link OldSwedishCurrency}
	 * @return True if equal, false otherwise.
	 */
	public boolean equals(OldSwedishCurrency oldSC)
	{
		if (riksdaler == oldSC.getRiksdaler() && skillings == oldSC.getSkillings()
				&& runstyckens == oldSC.getRunstyckens())
			return true;
		else
			return false;
	}

	/**
	 * Function that compares two {@link OldSwedishCurrency} objects.
	 * 
	 * @param oldSC
	 *            The {@link OldSwedishCurrency} to be compared to.
	 * @return 1 if calling {@link OldSwedishCurrency} is greater, 0 if equal,
	 *         and -1 otherwise.
	 */
	public int compareTo(OldSwedishCurrency oldSC)
	{
		if (this.equals(oldSC))
			return 0;
		else if (this.convertToRunstycken() > oldSC.convertToRunstycken())
			return 1;
		else
			return -1;
	}

	/**
	 * Function returning the calling {@link OldSwedishCurrency} in the format:
	 * riksdaler + " rikdaler, " + skillings + " skillings, " + runstyckens +
	 * " runstyckens"
	 */
	public String toString()
	{
		return (riksdaler + " rikdaler, " + skillings + " skillings, " + runstyckens + " runstyckens");
	}

	/**
	 * Function used to add a {@link OldSwedishCurrency} with another.
	 * 
	 * @param oldSC
	 *            {@link OldSwedishCurrency} object to be added to.
	 * @return The result of the addition in a form of a new
	 *         {@link OldSwedishCurrency} object.
	 */
	public OldSwedishCurrency add(OldSwedishCurrency oldSC)
	{
		return new OldSwedishCurrency(riksdaler + oldSC.getRiksdaler(), skillings + oldSC.getSkillings(),
				runstyckens + oldSC.getRunstyckens());
	}

	/**
	 * Function used to subtract a {@link OldSwedishCurrency} with another.
	 * 
	 * @param oldSC
	 *            {@link OldSwedishCurrency} object to be subtracted.
	 * @return The result of the subtraction in a form of a new
	 *         {@link OldSwedishCurrency} object.
	 */
	public OldSwedishCurrency substract(OldSwedishCurrency oldSC)
	{
		if ((this.convertToRunstycken() - oldSC.convertToRunstycken()) < 0)
			return new OldSwedishCurrency(0, 0, 0);
		else
			return new OldSwedishCurrency(riksdaler - oldSC.getRiksdaler(), skillings - oldSC.getSkillings(),
					runstyckens - oldSC.getRunstyckens());
	}

	/**
	 * Converts a value in runstyckens to its equivalent in
	 * {@link OldSwedishCurrency}.
	 * 
	 * @param runstyckens
	 *            Value to be converted to {@link OldSwedishCurrency}.
	 * @return the result in a {@link OldSwedishCurrency} object.
	 */
	public static OldSwedishCurrency convertFromRunstycken(int runstyckens)
	{
		return new OldSwedishCurrency(0, 0, runstyckens);
	}

	/**
	 * Converts a {@link OldSwedishCurrency} to its equivalent in runstyckens.
	 * 
	 * @return Its value in runstyckens.
	 */
	public int convertToRunstycken()
	{
		return (runstyckens + skillings * 16 + riksdaler * 768);
	}

	public int getRiksdaler()
	{
		return riksdaler;
	}

	public void setRiksdaler(int riksdaler)
	{
		this.riksdaler = riksdaler;
		normalize();
	}

	public int getSkillings()
	{
		return skillings;
	}

	public void setSkillings(int skillings)
	{
		this.skillings = skillings;
		normalize();
	}

	public int getRunstyckens()
	{
		return runstyckens;
	}

	public void setRunstyckens(int runstyckens)
	{
		this.runstyckens = runstyckens;
		normalize();
	}

}
