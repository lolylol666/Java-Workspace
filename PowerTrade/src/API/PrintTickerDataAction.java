package API;

import Chart.TickerRecord;
import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;

public class PrintTickerDataAction implements Action1<PubSubData>
{
	private TickerRecord record;
	
	public PrintTickerDataAction(TickerRecord record) {
		super();
		this.record = record;
	}



	@Override
	public void call(PubSubData data)
	{
		try {
			int x = record.getChange();
			record = new TickerRecord(data);
			record.addChange(x);
			
			System.out.println(record);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}