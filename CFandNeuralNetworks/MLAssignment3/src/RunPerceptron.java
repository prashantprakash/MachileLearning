
public class RunPerceptron {
	public static void main(String []  args) {
		String input[]=new String[7];
		input[0]="E:\\MLAssignments\\hw2_train\\train\\spam\\";
		input[1]="E:\\MLAssignments\\hw2_train\\train\\ham\\";
		input[2] ="E:\\MLAssignments\\hw2_test\\test\\spam\\";
		input[3] = "E:\\MLAssignments\\hw2_test\\test\\ham\\";
		input[4] =".03";
		input[5] ="20";
		input[6] ="no";
		int count=1;
		System.out.println("Running with stop words");
		for(int i=1;i<=25;i++){
			System.out.println("Running for learning rate: " + input[4] + "  and number of iterations: " + input[5]);
			PerceptronImpl.main(input);
			input[5] = String.valueOf(Integer.parseInt(input[5]) + 20);
			if(count*5==i){
				count++;
				input[5]= "20";
				input[4] = String.valueOf(Double.parseDouble(input[4]) * 3);
			}
		}
		input[5] ="20";
		input[6] ="yes";
		input[4] = ".03";
		count=1;
		System.out.println("Running without stop words");
		for(int i=1;i<=25;i++){
			System.out.println("Running for learning rate: " + input[4] + " and number of iterations: " + input[5]);
			PerceptronImpl.main(input);
			input[5] = String.valueOf(Integer.parseInt(input[5]) + 20);
			if(count*5==i){
				count++;
				input[5]= "20";
				input[4] = String.valueOf(Double.parseDouble(input[4]) * 3);
			}
		}
	}
}
