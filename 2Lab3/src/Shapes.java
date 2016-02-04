import java.util.Date;

public class Shapes
{
	private String	color;
	private boolean	filled;
	private Date	dateCreated;

	public Shapes()
	{
		dateCreated = new Date();
		color = "white";
		filled = false;
	}

	public Shapes(String color, boolean filled)
	{
		dateCreated = new Date();
		this.color = color;
		this.filled = filled;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public boolean isFilled()
	{
		return filled;
	}

	public void setFilled(boolean filled)
	{
		this.filled = filled;
	}

	public Date getDateCreated()
	{
		return dateCreated;
	}

	public double getArea()
	{
		return 0.0;
	}

	public double getPerimeter()
	{
		return 0.0;
	}

	public boolean equal(Shapes shape)
	{
		if (shape.getColor().equals(this.color) && shape.isFilled() == this.filled)
			return true;
		else
			return false;
	}

	public static void displayObject(Rectangles rectangle)
	{
		System.out.println("Displaying objects of a rectangle :");
		System.out.println("-----------------------------------------");
		System.out.println("Create Date : " + rectangle.getDateCreated().toString());
		System.out.println("Color : " + rectangle.getColor());
		System.out.println("Filled : " + rectangle.isFilled());
		System.out.println("Width : " + rectangle.getWidth());
		System.out.println("Height : " + rectangle.getHeight());
		System.out.println("Area : " + rectangle.getArea());
		System.out.println("Perimeter : " + rectangle.getPerimeter());
	}

}
