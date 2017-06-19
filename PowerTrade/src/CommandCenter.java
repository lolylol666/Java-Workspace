import java.net.URL;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CommandCenter {

	private static String poloKey="IPK7IHVC-NP8FYE48-YJZN4NM1-RFHCS8M0";
	private static String poloSecret="e36864198a437d1ac55476696870ccae7a943e0dceac507958747b1de6fa691664c0dce6cea97ac80220593f3d82466d838a44c00d69c2db13eb75007502a899";
	private static final String HMAC_SHA512 = "HmacSHA512";

	public static void main(String[] args) throws Exception 
	{
		Poloniex poloSession = new Poloniex(poloKey, poloSecret);
		return;
	}
	
	private static String calculateHMAC(String data, String secret)
		    throws Exception
	{
		    SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), HMAC_SHA512);
		    Mac mac = Mac.getInstance(HMAC_SHA512);
		    mac.init(secretKeySpec);
		    return toHexString(mac.doFinal(data.getBytes()));
	}

	private static String toHexString(byte[] bytes) 
	{
		    Formatter formatter = new Formatter();
		    for (byte b : bytes) 
		    {
		        formatter.format("%02x", b);
		    }
		    String signature = formatter.toString();
		    formatter.close();
		    return signature;
	}
	
	private void parseJSON()
	{
		
	}
	
	static private class Poloniex
	{
		 private String APIkey;
		 private String APIsecret;
		 private String tURL="https://poloniex.com/tradingApi";
		 private String pURL="https://poloniex.com/public";
		 private API tradeAPI;
		 
			public Poloniex(String key, String secret) throws Exception {
				URL tradeURL = new URL(tURL);
				tradeAPI = new API(tradeURL);
				this.APIkey=key;
				this.APIsecret=secret;
				iniPolo();
			}

			private void iniPolo() throws Exception {
				tradeAPI.openConn();
				tradeAPI.conn.setRequestProperty("Key", APIkey);
			}
			
			private void sign(String sCommand) throws Exception
			{
				String sign = calculateHMAC(sCommand, APIsecret);
				tradeAPI.conn.setRequestProperty("Sign", sign);
			}
			
			
			
	}

}
