import java.awt.EventQueue;
import java.math.BigDecimal;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class MyTimer extends ScheduledThreadPoolExecutor implements Runnable
{
	private Window				window;
	private BigDecimal			second;
	private int					minute, hour;
	private ScheduledFuture<?>	currentTask;
								
	public MyTimer(BigDecimal second, int minute, int hour, Window window)
	{
		super(1);
		this.second = second;
		this.minute = minute;
		this.hour = hour;
		this.window = window;
	}
	
	public BigDecimal getSecond()
	{
		return second;
	}
	
	public void setSecond(BigDecimal second)
	{
		this.second = second;
	}
	
	public int getMinute()
	{
		return minute;
	}
	
	public void run()
	{
		if (second.compareTo(new BigDecimal(59.999)) >= 0)
		{
			second = new BigDecimal(0);
			addMinute();
		}
		
		EventQueue.invokeLater(() ->
		{
			second = second.add(new BigDecimal(0.001));
			second = second.setScale(3, BigDecimal.ROUND_DOWN);
			window.updateTimerText(hour + ":" + minute + ":" + second.toString());
		});
	}
	
	private void addMinute()
	{
		if (minute == 59)
		{
			hour++;
			minute = 0;
		}
		minute++;
	}
	
	public void start(int initialDelay, int period)
	{
		currentTask = this.scheduleAtFixedRate(this, initialDelay, period, TimeUnit.MILLISECONDS);
	}
	
	public void stop()
	{
		currentTask.cancel(false);
	}
}