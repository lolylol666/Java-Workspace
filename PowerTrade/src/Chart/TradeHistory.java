/**
 * 
 */
package Chart;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author xkrajnan
 *
 */
public class TradeHistory
{

	private final ConcurrentLinkedQueue<Trade> trades;
	private int change=0;

	public TradeHistory()
	{
		this.trades = new ConcurrentLinkedQueue<>();
	}

	public boolean add(Trade trade)
	{
		return trades.add(trade);
	}

	public Trade remove()
	{
		return trades.remove();
	}
	
	public void addChange() {
		++change;
	}

	public int getChange() {
		return change;
	}

	public void resetChange() {
		change = 0;
	}

	@Override
	public String toString()
	{
		return trades.toString();
	}

}
