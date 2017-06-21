package Chart;

import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;

public class PrintTickerDataAction implements Action1<PubSubData>
{
	@Override
	public void call(PubSubData data)
	{
		try {
			TickerRecord record = new TickerRecord(data);
			System.out.println(record);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}