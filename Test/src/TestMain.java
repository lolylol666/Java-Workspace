
import java.math.BigDecimal;

import javax.swing.Timer;

public class TestMain
{
	private int	i		= 0;
	BigDecimal	value	= new BigDecimal(1.0);

	public static void main(String[] args)
	{
		double d = 4.23;
		double e = d % 1;
		System.out.println(e);
		String spaces = new String(new char[10]).replace("\0", "ok");
		System.out.println(spaces);
		int x = 50, y = 20, i = 6;
		float z = 5 / 10;

		System.out.println(z * (x * 100 / y));
		TestInterface max = (a, b) ->
		{
			return (a > b) ? a : b;
		};
		TestInterface min = new TestInterface()
		{
			public int op(int a, int b)
			{
				return (a <= b) ? a : b;
			}
		};
		System.out.println(max);
		System.out.println(max.op(x, y));
		System.out.println(min);
		System.out.println(min.op(x, y));
		System.out.println(x / y);
		Timer timer = new Timer(1000, null);
		timer.setCoalesce(true);
	}

	/*
	 * public int max(int a, int b) { // TODO Auto-generated method stub return
	 * (a>b)?a:b; }
	 */

	static int caca(int a, int b, TestInterface face)
	{
		return face.op(a, b);
	}

	public void run()
	{
		TestClass1<Integer> caca = new TestClass1<Integer>();
		caca.caca2(3);
		value = value.setScale(5);
		System.out.println(i + value.toString());
		i++;
	}
}
