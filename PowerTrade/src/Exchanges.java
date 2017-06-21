import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;


public class Exchanges {
	static class Poloniex {
		private String APIkey;
		private String APIsecret;
		private String tURL = "https://poloniex.com/tradingApi";
		private String pURL = "https://poloniex.com/public";
		private API tradeAPI;

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

			URL tradeURL = new URL(tURL);
		
			API.Response response = tradeAPI.sendPostRequest(tradeURL, postHeaders, sCommand);
			
			return response;
		}

		private API.Response sendGetCommand(String[] _commands) throws Exception
		{
			Map<String,String> command = prepCommand(_commands);
			String sCommand = tradeAPI.formatCommand(command);
			
			URL gCommand = new URL(pURL+"?"+sCommand);
			
			API.Response response = tradeAPI.sendGetRequest(gCommand);
			
			return response;
		}

		API.Response returnChartData(String... params) throws Exception 
		{
			String[] _commands = {"command","returnChartData",
								"currencyPair",params[0],
								"period",params[1],
								"start",params[2],
								"end",params[3]};
			return sendGetCommand(_commands);
		}
		
		API.Response returnAvailableAccountBalances() throws Exception
		{
			String[] _commands = { "command", "returnAvailableAccountBalances" };
			return sendPostCommand(_commands);		
		}

		API.Response returnBalances() throws Exception {
			String[] _commands = { "command", "returnBalances" };
			return sendPostCommand(_commands);
		}
	}
}
