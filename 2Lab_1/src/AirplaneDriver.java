import java.util.Scanner;

//-----------------------------
//COMP 249/Winter 2016
//Date: Thursday January 21, 2016
//Lab: 1
//Lab Section: 
//Your name:
//Student ID:
//-----------------------------
public class AirplaneDriver {
	public static void main(String[] args) {

		final int MAX_FLEET_SIZE = 100;

		Scanner keyIn = new Scanner(System.in);

		
		// b) Declare an array of objects Airplane which can hold up to MAX_FLEET_SIZE Airplane objects
		
		Airplane[] airpArray = new Airplane[MAX_FLEET_SIZE];

		
		// c) Create a 1st entry in the array using the default constructor
		// which you store in the array created in step b)
		
		airpArray[0]=new Airplane();
		

		
		// d) Prompt the user for the information to create 2 other objects of type Airplane and store them in the
		// same array
		
		System.out.println("Please enter Crew Size and Destination for plane 2: ");
		int c = keyIn.nextInt();
		String d = keyIn.nextLine();
		airpArray[1]= new Airplane(c,d); 
		
		System.out.println("Please enter Crew Size and Destination for plane 3: ");
		c = keyIn.nextInt();
		d = keyIn.nextLine();
		airpArray[2]= new Airplane(c,d); 

		
		// e) Increase the crew size of the 2nd Airplane by 2   
		airpArray[1].setCrewSize(airpArray[1].getCrewSize()+2);
		


		// f) Create a 4th object which has the crew size of the 3rd Airplane in Fleet and the 
		// 1st Airplane's destination
		airpArray[3]=new Airplane(airpArray[2].getCrewSize(),airpArray[0].getDestination());
		


		// g) Create a new Airplane with exactly the same information as the Airplane in index location 2
		
		airpArray[4]=new Airplane(airpArray[2]);


		
		// h) Display a message saying how many airplanes there are as well as
		// the information of all airplanes. Make use of the static attribute numOfPlanes
		// for the number of Airplanes.
		
		System.out.println("The total number of planes: "+Airplane.getNumPlanes());
		for(int i=0;i<5;i++)
		{
			System.out.println("Airplane at index: "+i);
			System.out.println(airpArray[i].toString());
		}

		
		// i) Compare the Airplane in location 2 to the remaining Airplane objects in the array and 	
		// display a message of the format
		// airplane 2 (is/is not) the same as airplane i
		// for the remaining airplanes, using the equals method
		for(int i=0;i<5;i++)
		{
			String r = "is not";
			if(airpArray[2]==airpArray[i]) r = "is";
			System.out.println("airplane 2 "+r+" the same as airplane "+i);
		}




		
		
		keyIn.close();
	}// end of main

}
