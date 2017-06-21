import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class CommandCenter {

	private static String poloKey = "IPK7IHVC-NP8FYE48-YJZN4NM1-RFHCS8M0";
	private static String poloSecret = "e36864198a437d1ac55476696870ccae7a943e0dceac507958747b1de6fa691664c0dce6cea97ac80220593f3d82466d838a44c00d69c2db13eb75007502a899";
	static final String HMAC_SHA512 = "HmacSHA512";

	public static void main(String[] args) throws Exception {
		Exchanges.Poloniex poloSession = new Exchanges.Poloniex(poloKey, poloSecret);
		API.Response response;
		
		response = poloSession.returnAvailableAccountBalances();
		
		Wallet poloWallet = new Wallet(response.responseMsg);
		poloWallet.displayBalances();
		
		System.out.println(response.responseCode);
		System.out.println(response.responseMsg.get(0).getAsJsonObject().get("exchange"));
		
		
		Thread.sleep(1000);
		response = poloSession.returnChartData("BTC_XMR","14400","1495331302","1498009702");
		System.out.println(response.responseCode);
		System.out.println(response.responseMsg.get(0).getAsJsonObject().get("date"));
		
		return;
	}

	public static String calculateHMAC(String data, String secret, final String HMAC) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), HMAC);
		Mac mac = Mac.getInstance(HMAC);
		mac.init(secretKeySpec);
		return toHexString(mac.doFinal(data.getBytes()));
	}

	private static String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		String signature = formatter.toString();
		formatter.close();
		return signature;
	}

	static String nonce() throws Exception {
		String nonce = String.valueOf(System.currentTimeMillis());
		Thread.sleep(1);
		return nonce;
	}

	public static JsonArray parseJSON(String jResponse) {
		Gson gson = new Gson();
//		Type type = new TypeToken<List<JsonObject>>(){}.getType();
		
		if(!jResponse.startsWith("[")) jResponse="["+jResponse+"]";
	    return gson.fromJson(jResponse, JsonArray.class);     
	}

}
