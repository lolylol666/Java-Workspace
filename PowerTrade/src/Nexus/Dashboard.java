package Nexus;

import Book.OrderBook;
import Book.PublicOrderBook;
import Chart.Chart;
import Chart.TickerRecord;
import Chart.TradeHistory;
import Exchanges.Poloniex;

public class Dashboard {
	
	private String			currency;
	private Poloniex		sessionHandler;
	private PublicOrderBook	publicBuyBook, publicSellBook;
	private OrderBook		myBuyBook, mySellBook;
	
	private Chart			chart;
	private TradeHistory	tradeHistory;
	
	public Dashboard(String currency, Poloniex session) throws Exception {
		this.currency = currency;
		this.sessionHandler = session;
	}
	
	public void init() {
		subPublicBook();
		updateMyBook();
		
		updateChart();
		subHistory();
	}
	
	public void updateAll() {
		updateMyBook();
		updateChart();
	}
	
	public void subPublicBook() {
		
	}
	
	public void updateMyBook() {
		
	}
	
	public void updateChart() {
		
	}
	
	public void subHistory() {
		
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
	
	public Chart getChart() {
		return chart;
	}
	
	public TradeHistory getTradeHistory() {
		return tradeHistory;
	}
	
	public class Panel {
		private TickerRecord ticker;
		
		public Panel() {
			
		}
		
		public void subTicker() {
			
		}
		
		public TickerRecord getTicker() {
			return ticker;
		}
	}
}
