
public class OldSwedishCurrency
{
	private int riksdaler, skillings, runstyckens;

	public OldSwedishCurrency()
	{
		riksdaler = 0;
		skillings = 0;
		runstyckens = 0;
	}

	public OldSwedishCurrency(int riksdaler, int skillings, int runstyckens)
	{
		if (riksdaler < 0 || skillings < 0 || runstyckens < 0)
			riksdaler = skillings = runstyckens = 0;
		this.riksdaler = riksdaler;
		this.skillings = skillings;
		this.runstyckens = runstyckens;
		normalize();
	}

	public OldSwedishCurrency(OldSwedishCurrency oldSC)
	{
		riksdaler = oldSC.getRiksdaler();
		skillings = oldSC.getSkillings();
		runstyckens = oldSC.getRunstyckens();
	}

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

	public boolean equals(OldSwedishCurrency oldSC)
	{
		if (riksdaler == oldSC.getRiksdaler() && skillings == oldSC.getSkillings()
				&& runstyckens == oldSC.getRunstyckens())
			return true;
		else
			return false;
	}

	public int compareTo(OldSwedishCurrency oldSC)
	{
		if (this.equals(oldSC))
			return 0;
		else if (this.convertToRunstycken() > oldSC.convertToRunstycken())
			return 1;
		else
			return -1;
	}

	public String toString()
	{
		return (riksdaler + " rikdaler, " + skillings + " skillings, " + runstyckens + " runstyckens");
	}

	public OldSwedishCurrency add(OldSwedishCurrency oldSC)
	{
		return new OldSwedishCurrency(riksdaler + oldSC.getRiksdaler(), skillings + oldSC.getSkillings(),
				runstyckens + oldSC.getRunstyckens());
	}

	public OldSwedishCurrency substract(OldSwedishCurrency oldSC)
	{
		if ((this.convertToRunstycken() - oldSC.convertToRunstycken()) < 0)
			return new OldSwedishCurrency(0, 0, 0);
		else
			return new OldSwedishCurrency(riksdaler - oldSC.getRiksdaler(), skillings - oldSC.getSkillings(),
					runstyckens - oldSC.getRunstyckens());
	}

	public static OldSwedishCurrency convertFromRunstycken(int runstyckens)
	{
		return new OldSwedishCurrency(0, 0, runstyckens);
	}

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
