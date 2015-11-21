
public class Pizza 
{
	private int  cheeseToppings, pepperToppings, mushToppings; 							//Declares the objects private fields
	private String size;
	public Pizza()																		//Default constructor
	{
		size = "";
		cheeseToppings = pepperToppings = mushToppings = 0;
	}

	public Pizza(String size, int cheeseToppings, int pepperToppings, int mushToppings)	//User assigned constructor
	{
		this.size = size;
		this.cheeseToppings = cheeseToppings;
		this.pepperToppings = pepperToppings;
		this.mushToppings = mushToppings;
	}

	String getSize()																	//Separate method to obtain size because of String return type
	{
		return size;
	}

	int getSpec(String spec)															//Method to get other specifications
	{
		if(spec.equals("cheese")) return cheeseToppings;
		if(spec.equals("pepper")) return pepperToppings;
		else return mushToppings;
	}

	void setSpec(String spec, String value)												//Method thats assigns all 4 specs
	{
		spec = spec.trim();
		if(spec.equals("size")) size = value.toUpperCase();
		else if(spec.equals("cheese")) cheeseToppings = Integer.parseInt(value);
		else if(spec.equals("pepper")) pepperToppings = Integer.parseInt(value);
		else mushToppings = Integer.parseInt(value);
	}

	public double calcCost()															//Method which calculates the cost
	{
		double sizeCost=0, toppingCost=0;
		switch(this.size)
		{
		case "SMALL":
			sizeCost=10;
			toppingCost=2;
			break;
		case "MEDIUM":
			sizeCost=12;
			toppingCost=2.25;
			break;
		case "LARGE":
			sizeCost=14;
			toppingCost=2.5;
		}
		return (sizeCost+toppingCost*(this.cheeseToppings+this.pepperToppings+this.mushToppings));
	}

	public String toString()															//Method that generates a string describing the pizza
	{
		return (size+" pizza with "+cheeseToppings+" cheese topping(s), "+pepperToppings+" pepperoni topping(s) and "+mushToppings+" mushroom topping(s)");
	}

	boolean equals(Pizza pizza)															//Method that tests the equality of the pizza, returning true if equal
	{
		if(this.size.equals(pizza.size)&&this.cheeseToppings==pizza.cheeseToppings&&this.pepperToppings==pizza.pepperToppings&&this.mushToppings==pizza.mushToppings)
			return true;
		else return false;	
	}

}
