import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class Wallet {
	
	private Map<String, BigDecimal> cxCoins;

	public Wallet(JsonArray balances) {
		cxCoins = new TreeMap<>();
		updateWallet(balances);
	}
	
	public void updateWallet(JsonArray balances)
	{
		for(Entry<String, JsonElement> entry : balances.get(0).getAsJsonObject().get("exchange").getAsJsonObject().entrySet())
		{
			cxCoins.put(entry.getKey(), entry.getValue().getAsBigDecimal());
		}
	}
	
	public void displayBalances()
	{
		System.out.println(cxCoins.entrySet());
	}
}
