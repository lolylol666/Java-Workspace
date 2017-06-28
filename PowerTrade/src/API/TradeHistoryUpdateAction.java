package API;

import com.fasterxml.jackson.databind.JsonNode;

import Chart.Trade;
import Chart.TradeHistory;
import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;

public class TradeHistoryUpdateAction implements Action1<PubSubData> {
	private static final String ID_DATA = "data";
	private static final String ID_UPDATE_TYPE = "type";
	private static final String ID_NEW_TRADE = "newTrade";

	private TradeHistory history;

	public TradeHistoryUpdateAction(TradeHistory history) {
		this.history = history;
	}

	@Override
	public void call(PubSubData data) {
		try {
			for (JsonNode node : data.arguments()) {

				String updateType = node.get(ID_UPDATE_TYPE).asText();

				// skip updates that do not concern trade history
				if (!updateType.equals(ID_NEW_TRADE)) {
					continue;
				}

				JsonNode tradeData = node.get(ID_DATA);

				Trade trade = new Trade(tradeData);
				System.out.println(trade);

				history.add(trade);
				history.addChange();
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}
}