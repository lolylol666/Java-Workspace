package API;




import Book.OrderType;
import Book.PubOrderBookUpdateAction;
import Book.PublicOrderBook;
import Chart.PrintTickerDataAction;
import Chart.TradeHistoryUpdateAction;
import rx.functions.Action1;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClient.ConnectedState;
import ws.wamp.jawampa.WampClient.ConnectingState;
import ws.wamp.jawampa.WampClient.DisconnectedState;
import ws.wamp.jawampa.WampClient.State;

public class ClientStatusChangedAction implements Action1<State>
{
	private final WampClient client;
	private final PublicOrderBook orderBookAsk;
	private final PublicOrderBook orderBookBid;

	public ClientStatusChangedAction(WampClient client, PublicOrderBook orderBookAsk, PublicOrderBook orderBookBid)
	{
		this.client = client;
		this.orderBookAsk = orderBookAsk;
		this.orderBookBid = orderBookBid;
	}

	@Override
	public void call(State status)
	{
		System.err.println("Status: " + status);

		if (status instanceof ConnectedState) {
			subscribeForUpdates();

		} else if (status instanceof ConnectingState) {

		} else if (status instanceof DisconnectedState) {

		} else {
			System.err.println("Invalid client state!");
		}
	}

	private void subscribeForUpdates(String... currency)
	{
		client.makeSubscription("ticker").subscribe(new PrintTickerDataAction());
		for(int x=0;x<currency.length;++x)
		{
		client.makeSubscription(currency[x])
				.subscribe(new PubOrderBookUpdateAction(orderBookAsk, OrderType.ASK));
		client.makeSubscription(currency[x])
				.subscribe(new PubOrderBookUpdateAction(orderBookBid, OrderType.BID));
		client.makeSubscription(currency[x]).subscribe(new TradeHistoryUpdateAction());
		}
	}
}