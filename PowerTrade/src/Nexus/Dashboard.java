package Nexus;

import java.util.List;

import Book.OrderBook;
import Book.PublicOrderBook;
import Chart.ChartFrame;
import Chart.TickerRecord;
import Chart.TradeHistory;
import Exchanges.Poloniex;

public class Dashboard {
	
	private String			currency;
	private Poloniex		sessionHandler;
	private PublicOrderBook	publicBuyBook, publicSellBook;
	private OrderBook		myBuyBook, mySellBook;
	
	private ChartFrame			chart;
	private TradeHistory	tradeHistory;
	
	public Dashboard(String currency, Poloniex session) throws Exception {
		this.currency = currency;
		this.sessionHandler = session;
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
	
	public ChartFrame getChart() {
		return chart;
	}
	
	public TradeHistory getTradeHistory() {
		return tradeHistory;
	}
	
	public class TickerPanel {
		private TickerRecord		ticker;
		private List<TickerRecord>	tickersList;
		
		public TickerPanel() {
			
		}
		
		public void subTicker() {
			
		}
		
		public TickerRecord getTicker() {
			return ticker;
		}
	}
}
