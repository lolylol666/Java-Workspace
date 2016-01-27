
public class OldSwedishCurrency 
{
	private int riksdaler, skillings, runstyckens;
	
	public OldSwedishCurrency()
	{
		riksdaler=0;
		skillings=0;
		runstyckens=0;
	}
	
	public OldSwedishCurrency(int ri, int sk, int ru)
	{
		riksdaler=ri;
		skillings=sk;
		runstyckens=ru;
		normalize();
	}
	
	private void normalize()
	{
		int ri,sk;
		
		sk=(runstyckens/16);
		runstyckens-=sk*16;
		skillings+=sk;
		
		ri=(skillings/48);
		skillings-=ri*48;
		riksdaler+=ri;
	}
}
