package Exchanges;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import API.API;
import API.PushAPI;
import Book.PublicOrderBook;
import Chart.TickerRecord;
import Chart.TradeHistory;
import Nexus.CommandCenter;

public class Poloniex {
		private String APIkey;
		private String APIsecret;
		private API tradeAPI;
		private static final String T_URL = "https://poloniex.com/tradingApi";
		private static final String P_URL = "https://poloniex.com/public";
		
		private PushAPI pushAPI;
		private static final String POLONIEX_API_URL = "wss://api.poloniex.com";
		private static final String REALM = "realm1";
		private static final int RECONNECT_INTERVAL_SEC = 5;
		private static boolean pushIsConnected=false;


		public Poloniex(String key, String secret) throws Exception {
			
			tradeAPI = new API();
			this.APIkey = key;
			this.APIsecret = secret;
		}
		
		private String sign(String data) throws Exception {
			String sign = CommandCenter.calculateHMAC(data, APIsecret, CommandCenter.HMAC_SHA512);
			return sign;
		}

		private Map<String, String> prepCommand(String[] _commands) throws Exception
		{
			Map<String,String> command = new LinkedHashMap<>();
		
			for (int x = 0; x < _commands.length; x += 2) {
				command.put(_commands[x], _commands[x + 1]);
			}
		
			command.put("nonce", CommandCenter.nonce());
			return command;
		}

		private API.Response sendPostCommand(String[] _commands) throws Exception {
		
			Map<String,String> command = prepCommand(_commands);
			String sCommand = tradeAPI.formatCommand(command);
			
			String[] postHeaders={"Key",APIkey,
					"Sign",sign(sCommand)};

			URL tradeURL = new URL(T_URL);
		
			API.Response response = tradeAPI.sendPostRequest(tradeURL, postHeaders, sCommand);
			
			return response;
		}

		private API.Response sendGetCommand(String[] _commands) throws Exception
		{
			Map<String,String> command = prepCommand(_commands);
			String sCommand = tradeAPI.formatCommand(command);
			
			URL gCommand = new URL(P_URL+"?"+sCommand);
			
			API.Response response = tradeAPI.sendGetRequest(gCommand);
			
			return response;
		}

		public API.Response returnChartData(String... params) throws Exception 
		{
			String[] _commands = {"command","returnChartData",
								"currencyPair",params[0],
								"period",params[1],
								"start",params[2],
								"end",params[3]};
			return sendGetCommand(_commands);
		}
		
		public API.Response returnAvailableAccountBalances() throws Exception
		{
			String[] _commands = { "command", "returnAvailableAccountBalances" };
			return sendPostCommand(_commands);		
		}

		public API.Response returnBalances() throws Exception {
			String[] _commands = { "command", "returnBalances" };
			return sendPostCommand(_commands);
		}
		
		public void  startPushAPI() throws Exception
		{
			pushAPI = new PushAPI(this,POLONIEX_API_URL,REALM,RECONNECT_INTERVAL_SEC);
		}

		public void subscribeTicker(TickerRecord record) throws Exception {
			if(pushIsConnected) pushAPI.subscribeToTicker(record);
		}
		
		public void subscribeCoin(String currency, PublicOrderBook buyBook, PublicOrderBook sellBook,
				TradeHistory history) throws Exception {
			if(pushIsConnected) pushAPI.subscribeToCoin(currency, buyBook, sellBook, history);
		}
		
		public void setPushState(boolean state)
		{
			pushIsConnected=state;
		}
		
		public boolean getPushState()
		{
			return pushIsConnected; 
		}
}
