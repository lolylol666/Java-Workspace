
public class University {

	private String UniversityName;
	private String[] professors = new String[9];
	private int numberOfProfessors;
	
	public University(String un) {UniversityName = un;}
	public University(String[] p) {professors = p;}
	
	public void addProfessor(String professor)
	{
		professors[numberOfProfessors] = professor;
		numberOfProfessors++;
	}
	
	public String[] getProfessor() {return professors;}
	
	public int getNumberOfProfessors() {return numberOfProfessors;}
	
	public String getUniversityName() {return UniversityName;}
	
	public String toString() 
	{
		return ("University name: "+getUniversityName()+" Professors[] = "+getProfessor()+"number of professors: "+getNumberOfProfessors());
	}
}
