package API;
import java.util.concurrent.TimeUnit;

import Book.OrderBook;
import Chart.ClientStatusChangedAction;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClientBuilder;
import ws.wamp.jawampa.transport.netty.NettyWampClientConnectorProvider;

public class WampHandler {
	private static final String POLONIEX_API_URL = "wss://api.poloniex.com";
	private static final String REALM = "realm1";
	private static final int RECONNECT_INTERVAL_SEC = 5;

	private final WampClient client;

	private final OrderBook orderBookAsk;
	private final OrderBook orderBookBid;

	public WampHandler() throws Exception
	{
		NettyWampClientConnectorProvider connectorProvider = new NettyWampClientConnectorProvider();

		WampClientBuilder builder = new WampClientBuilder();

		builder.withConnectorProvider(connectorProvider);
		builder.withUri(POLONIEX_API_URL);
		builder.withRealm(REALM);
		builder.withInfiniteReconnects();
		builder.withReconnectInterval(RECONNECT_INTERVAL_SEC, TimeUnit.SECONDS);

		client = builder.build();
		orderBookAsk = new OrderBook();
		orderBookBid = new OrderBook();

		client.statusChanged().subscribe(new ClientStatusChangedAction(client, orderBookAsk, orderBookBid));
	}

	public void open()
	{
		client.open();
	}

	public void close()
	{
		client.close().toBlocking().last();
	}

}
