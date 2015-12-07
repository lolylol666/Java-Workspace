
public class UniversityTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		University u1 = new University("Concordia"), u2 = new University("McGill"), u3 = new University("UDM");

		String[] UniversityArray = new String[]{"Mohamed Taleb", 
												"Nancy Acemian", 
												"Emad Shibab", 
												"Pascal Hubert", 
												"Vahid Mirjalili", 
												"Lolei Khoun",
												"Houari Sahraoui",
												"Esma Aimeur",
												"Jacques Ferland"};
		for(int i=0; i<3;i++)
		{
			u1.addProfessor(UniversityArray[i]);
		}
		
		for(int i=3; i<6;i++)
		{
			u2.addProfessor(UniversityArray[i]);
		}
		
		for(int i=6; i<9;i++)
		{
			u3.addProfessor(UniversityArray[i]);
		}
		
		System.out.println(u1.getUniversityName());
		System.out.println(u1.getProfessor()[0]+"\n"+u1.getProfessor()[1]+"\n"+u1.getProfessor()[2]);
		System.out.println("Number of professors of the University u1: "+u1.getNumberOfProfessors()+"\n");
		
		System.out.println(u2.getUniversityName());
		System.out.println(u2.getProfessor()[0]+"\n"+u2.getProfessor()[1]+"\n"+u2.getProfessor()[2]);
		System.out.println("Number of professors of the University u2: "+u2.getNumberOfProfessors()+"\n");
		
		System.out.println(u3.getUniversityName());
		System.out.println(u3.getProfessor()[0]+"\n"+u3.getProfessor()[1]+"\n"+u3.getProfessor()[2]);
		System.out.println("Number of professors of the University u3: "+u3.getNumberOfProfessors()+"\n");
		
		System.out.println("Total of all professors u1+u2+u3: "+(u1.getNumberOfProfessors()+u2.getNumberOfProfessors()+u3.getNumberOfProfessors()));
	}

}
