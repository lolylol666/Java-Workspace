package Chart;

public class CandleStickChart {

	
	private class CandleStick
	{
		private final long date;
		private final double high, low, open, close, volume, quotedVol, weightedAv;
		
		public CandleStick(long date, double high, double low, double open, double close, double volume,
				double quotedVol, double weightedAv) {
			this.date = date;
			this.high = high;
			this.low = low;
			this.open = open;
			this.close = close;
			this.volume = volume;
			this.quotedVol = quotedVol;
			this.weightedAv = weightedAv;
		}
		
		
	}
}
