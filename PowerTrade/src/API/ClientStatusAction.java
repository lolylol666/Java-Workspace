package API;




import Exchanges.Poloniex;
import rx.functions.Action1;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClient.ConnectedState;
import ws.wamp.jawampa.WampClient.ConnectingState;
import ws.wamp.jawampa.WampClient.DisconnectedState;
import ws.wamp.jawampa.WampClient.State;

public class ClientStatusAction implements Action1<State>
{
	private final WampClient clientHandler;
	private final Poloniex sessionHandler;

	public ClientStatusAction(WampClient clientHandler, Poloniex sessionHandler)
	{
		this.clientHandler = clientHandler;
		this.sessionHandler=sessionHandler;
	}

	@Override
	public void call(State status)
	{
		System.err.println("Status: " + status);

		if (status instanceof ConnectedState) {
			sessionHandler.setPushState(true);
			//subscribeForUpdates();

		} else if (status instanceof ConnectingState) {
			sessionHandler.setPushState(false);
		} else if (status instanceof DisconnectedState) {
			sessionHandler.setPushState(false);
		} else {
			sessionHandler.setPushState(false);
			System.err.println("Invalid client state!");
		}
	}

//	private void subscribeForUpdates(String... currency)
//	{
//		client.makeSubscription("ticker").subscribe(new PrintTickerDataAction());
//		for(int x=0;x<currency.length;++x)
//		{
//		client.makeSubscription(currency[x])
//				.subscribe(new PubOrderBookUpdateAction(orderBookAsk, OrderType.ASK));
//		client.makeSubscription(currency[x])
//				.subscribe(new PubOrderBookUpdateAction(orderBookBid, OrderType.BID));
//		client.makeSubscription(currency[x]).subscribe(new TradeHistoryUpdateAction());
//		}
//	}
}