package Nexus;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class Wallet {
	
	private final Map<String, Double> cxCoins;

	public Wallet(JsonArray balances) {
		cxCoins = new ConcurrentHashMap<>();
		updateWallet(balances);
	}
	
	public void updateWallet(JsonArray balances)
	{
		for(Entry<String, JsonElement> entry : balances.get(0).getAsJsonObject().get("exchange").getAsJsonObject().entrySet())
		{
			cxCoins.put(entry.getKey(), entry.getValue().getAsDouble());
		}
	}
	
	public void displayBalances()
	{
		System.out.println(cxCoins.entrySet());
	}
}
