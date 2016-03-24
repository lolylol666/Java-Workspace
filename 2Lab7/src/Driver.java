/*
Write a Gradebook generic class, which:
> Has an instance variable for an array of generic objects
> Has appropriate constructor, accessor and mutator
> Has a printGrade() method
> Has a method numGrades that takes a grade as a parameter and returns the 
number of grades in the Gradebook that match it exactly
> Sample output is given at the end
*/


class Driver {

   public static void main(String args[]) 
   {
      String[] letter = { "A+","A+", "B+", "B-", "F", "D","B","B-","A+"};
      Gradebook<String> g = new Gradebook<String>(letter);
      g.printGrades();
      System.out.println( "Number of A+:" );
      System.out.println(g.numGrades("A+"));


      Integer[] percent = { 81, 50, 72, 83, 54, 69, 83 };
	  Gradebook<Integer> g2 = new Gradebook<Integer>(percent);
	  g2.printGrades();
	  System.out.println("Number of 83:");
	  System.out.println(g2.numGrades(83));
	  
	  Double[] gpa = { 4.0, 4.0, 2.3, 3.0, 3.7 };
	  Gradebook<Double> g3 = new Gradebook<Double>(gpa);
	  g3.printGrades();
	  System.out.println("Number of 4.0:");
	  System.out.println(g3.numGrades(4.0));
   }
}

/*
Sample Output:
A+ A+ B+ B- F D B B- A+
Number of A+:
3
81 50 72 83 54 69 83
Number of 83:
2
4.0 4.0 2.3 3.0 3.7
Number of 4.0:
2
*/