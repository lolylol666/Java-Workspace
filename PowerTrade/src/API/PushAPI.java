package API;

import java.util.concurrent.TimeUnit;

import Book.OrderType;
import Book.PublicOrderBook;
import Chart.TickerRecord;
import Chart.TradeHistory;
import Exchanges.Poloniex;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClientBuilder;
import ws.wamp.jawampa.transport.netty.NettyWampClientConnectorProvider;

public class PushAPI {

	private final WampClient client;
	private final Poloniex sessionHandler;

	public PushAPI(Poloniex sessionHandler,String POLONIEX_API_URL, String REALM, int RECONNECT_INTERVAL_SEC) throws Exception {
		NettyWampClientConnectorProvider connectorProvider = new NettyWampClientConnectorProvider();

		WampClientBuilder builder = new WampClientBuilder();
		this.sessionHandler=sessionHandler;
		
		
		builder.withConnectorProvider(connectorProvider);
		builder.withUri(POLONIEX_API_URL);
		builder.withRealm(REALM);
		builder.withInfiniteReconnects();
		builder.withReconnectInterval(RECONNECT_INTERVAL_SEC, TimeUnit.SECONDS);

		client = builder.build();
		
		client.statusChanged().subscribe(new ClientStatusAction(client, sessionHandler));
	}

	public void open() {
		client.open();
	}

	public void close() {
		client.close().toBlocking().last();
	}

	public void subscribeToTicker(TickerRecord record) {
		client.makeSubscription("ticker").subscribe(new TickerUpdateAction(record));
	}

	public void subscribeToCoin(String currency, PublicOrderBook buyBook, PublicOrderBook sellBook,
			TradeHistory history) {
		client.makeSubscription(currency).subscribe(new PubOrderBookUpdateAction(sellBook, OrderType.ASK));
		client.makeSubscription(currency).subscribe(new PubOrderBookUpdateAction(buyBook, OrderType.BID));
		client.makeSubscription(currency).subscribe(new TradeHistoryUpdateAction(history));
	}

}
