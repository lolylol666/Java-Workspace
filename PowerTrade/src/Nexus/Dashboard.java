package Nexus;

import API.API;
import Book.OrderBook;
import Book.PublicOrderBook;
import Chart.CandleStickChart;
import Chart.TickerRecord;
import Chart.TradeHistory;
import Exchanges.Poloniex;

public class Dashboard {
	
	private final String			currency;
	private final Poloniex		sessionHandler;
	private final PublicOrderBook	publicBuyBook, publicSellBook;
	private OrderBook		myBuyBook, mySellBook;
	
	private CandleStickChart			chart;
	private TradeHistory	tradeHistory;
	
	private API.Response response;
	
	public Dashboard(String currency, Poloniex session) throws Exception {
		this.currency = currency;
		this.sessionHandler = session;
		publicBuyBook = new PublicOrderBook();
		publicSellBook = new PublicOrderBook();
		tradeHistory = new TradeHistory();
	}
	
	public void init() throws Exception {
		
		subscribeCoinPushData();
		
		updateMyBook();
		updateChart();
	}
	
	public void updateAll() {
		updateMyBook();
		updateChart();
	}
	
	private void subscribeCoinPushData() throws Exception {
		sessionHandler.subscribeCoin(currency, publicBuyBook, publicSellBook, tradeHistory);
	}

	public void updateMyBook() {
		
	}
	
	public void updateChart() {
		
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public Poloniex getSessionHandler() {
		return sessionHandler;
	}
	
	public PublicOrderBook getPublicBuyBook() {
		return publicBuyBook;
	}
	
	public PublicOrderBook getPublicSellBook() {
		return publicSellBook;
	}
	
	public OrderBook getMyBuyBook() {
		return myBuyBook;
	}
	
	public OrderBook getMySellBook() {
		return mySellBook;
	}
	
	public CandleStickChart getChart() {
		return chart;
	}
	
	public TradeHistory getTradeHistory() {
		return tradeHistory;
	}
	
	public class TickerPanel {
		
		
		private TickerRecord		ticker;
//		private List<TickerRecord>	tickersList;
		private Wallet wallet;
		private API.Response response;
		Poloniex sessionHandler;
		
		public TickerPanel(Poloniex sessionHandler) throws Exception {
			this.sessionHandler=sessionHandler;
			subscribeTicker();
			updateWallet();
		}
		
		public void subscribeTicker() throws Exception {
			sessionHandler.subscribeTicker(ticker);
		}
		
		public void updateWallet() throws Exception {
			response = sessionHandler.returnAvailableAccountBalances();
			wallet=new Wallet(response.getResponseMsg());
		}
		
		public TickerRecord getTicker() {
			return ticker;
		}
		
		public Wallet getWallet()
		{
			return wallet;
		}
	}
}
