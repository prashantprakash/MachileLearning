
public class RunCollab {
	
	public static void main(String [] args) {
		// run it for 10 different datasets
		int base =1;
		String input[] = new String[2];
		input[0] = "E:\\MLAssignments\\netflix\\TrainingRatings.txt";
		input[1] ="E:\\MLAssignments\\netflix\\TestingRatings.txt";
		for(int i=1;i<=10;i++) {
			
			CollabFiltering.numberofTestExample = 1000*base; 
			CollabFiltering.main(input);
			base++;
		}
	}

}
