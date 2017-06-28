package API;

import com.fasterxml.jackson.databind.JsonNode;

import Book.OrderType;
import Book.PublicOrderBook;
import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;

public class PubOrderBookUpdateAction implements Action1<PubSubData>
{
	private static final String ID_DATA = "data";
	private static final String ID_UPDATE_TYPE = "type";
	private static final String ID_ORDER_TYPE = "type";
	private static final String ID_ORDER_BOOK_MODIFY = "orderBookModify";
	private static final String ID_ORDER_BOOK_REMOVE = "orderBookRemove";
	private static final String ID_ORDER_RATE = "rate";
	private static final String ID_ORDER_AMOUNT = "amount";

	private final PublicOrderBook orderBook;
	private final OrderType acceptedOrderType;

	public PubOrderBookUpdateAction(PublicOrderBook orderBook, OrderType acceptedOrderType)
	{
		this.orderBook = orderBook;
		this.acceptedOrderType = acceptedOrderType;
	}

	@Override
	public void call(PubSubData data)
	{
		try {
			for (JsonNode node : data.arguments()) {

				String updateType = node.get(ID_UPDATE_TYPE).asText();

				// skip updates that do not concern orderbook
				if (!updateType.equals(ID_ORDER_BOOK_MODIFY) && !updateType.equals(ID_ORDER_BOOK_REMOVE)) {
					continue;
				}

				JsonNode order = node.get(ID_DATA);
				String orderType = order.get(ID_ORDER_TYPE).asText();

				if (orderType.equals(acceptedOrderType.getCode())) {

					if (updateType.equals(ID_ORDER_BOOK_MODIFY)) {
						double rate = order.get(ID_ORDER_RATE).asDouble();
						double amount = order.get(ID_ORDER_AMOUNT).asDouble();
						orderBook.put(rate, amount);
						orderBook.addChange();

					} else if (updateType.equals(ID_ORDER_BOOK_REMOVE)) {
						double rate = order.get(ID_ORDER_RATE).asDouble();
						orderBook.remove(rate);
						orderBook.addChange();
					}

					System.out.println(acceptedOrderType + ": " + orderBook);
				}
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}
}