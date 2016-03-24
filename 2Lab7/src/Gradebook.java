
public class Gradebook<E>
{
	private E[] grades;

	public Gradebook(E[] array)
	{
		this.grades = array;
	}

	public E[] getGrades()
	{
		return grades;
	}

	public void setGrades(E[] array)
	{
		this.grades = array;
	}

	public void printGrades()
	{
		for (int i = 0; i < grades.length; i++)
		{
			System.out.print(grades[i] + " ");
		}
		System.out.print("\n");
	}

	public int numGrades(E grade)
	{
		int count = 0;
		for (int i = 0; i < grades.length; i++)
		{
			if (grades[i].equals(grade))
				count++;
		}
		return count;
	}
}
