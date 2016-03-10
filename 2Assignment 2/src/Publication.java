
public class Publication
{
	private long	publication_code;
	private String	publication_name;
	private int		publication_year;
	private String	publication_authorname;
	private double	publication_cost;
	private int		publication_nbpages;
					
	public Publication(long publication_code, String publication_name, int publication_year,
			String publication_authorname, double publication_cost, int publication_nbpages)
	{
		super();
		this.publication_code = publication_code;
		this.publication_name = publication_name;
		this.publication_year = publication_year;
		this.publication_authorname = publication_authorname;
		this.publication_cost = publication_cost;
		this.publication_nbpages = publication_nbpages;
	}
	
	public long getPublication_code()
	{
		return publication_code;
	}
	
	public void setPublication_code(long publication_code)
	{
		this.publication_code = publication_code;
	}
	
	public String getPublication_name()
	{
		return publication_name;
	}
	
	public void setPublication_name(String publication_name)
	{
		this.publication_name = publication_name;
	}
	
	public int getPublication_year()
	{
		return publication_year;
	}
	
	public void setPublication_year(int publication_year)
	{
		this.publication_year = publication_year;
	}
	
	public String getPublication_authorname()
	{
		return publication_authorname;
	}
	
	public void setPublication_authorname(String publication_authorname)
	{
		this.publication_authorname = publication_authorname;
	}
	
	public double getPublication_cost()
	{
		return publication_cost;
	}
	
	public void setPublication_cost(double publication_cost)
	{
		this.publication_cost = publication_cost;
	}
	
	public int getPublication_nbpages()
	{
		return publication_nbpages;
	}
	
	public void setPublication_nbpages(int publication_nbpages)
	{
		this.publication_nbpages = publication_nbpages;
	}
	
}
