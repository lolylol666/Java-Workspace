
public class Rectangles extends Shapes
{
	private double width, height;

	public Rectangles(double width, double height)
	{
		super();
		this.width = width;
		this.height = height;
	}

	public Rectangles(double width, double height, String color, boolean filled)
	{
		super(color, filled);
		this.width = width;
		this.height = height;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getArea()
	{
		return width * height;
	}

	public double getPerimeter()
	{
		return 2 * (width + height);
	}

	public boolean equal(Rectangles rectangle)
	{
		if (rectangle.getColor().equals(this.getColor()) && rectangle.isFilled() == this.isFilled()
				&& rectangle.getWidth() == this.width && rectangle.getHeight() == this.height)
			return true;
		else
			return false;
	}
}
