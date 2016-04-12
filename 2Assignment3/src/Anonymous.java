
public interface Anonymous
{
	/**
	 * Do not print names from this method. To print full details, use
	 * {@link #printFulldetails()} method.
	 * 
	 * @return A string containing the order's price, type and volume.
	 */
	public String toString();
	
	/**
	 * Used to print all information on the order.
	 * 
	 * @return String.
	 */
	public String printFullDetails();
}
