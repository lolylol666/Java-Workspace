
public class ShapesTest
{
	public static void main(String[] args)
	{
		Rectangles Rect = new Rectangles(2, 4);
		Rectangles Rect1 = new Rectangles(2, 4);
		Rectangles Rect2 = new Rectangles(10, 12.5);
		Shapes s2 = new Rectangles(2, 4);
		Rect.displayObject(Rect2);
		s2.displayObject(Rect);
		System.out.println();
		System.out.println("Equality result between Rect and itself : " + Rect.equal(Rect));
		System.out.println("Equality result between Rect1 and Rect : " + Rect1.equal(Rect));
		System.out.println("Equality result between Rect2 and Rect : " + Rect2.equal(Rect));
	}// End main() method
}
