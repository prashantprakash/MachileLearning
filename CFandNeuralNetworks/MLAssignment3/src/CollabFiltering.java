import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.text.StyleContext.SmallAttributeSet;

public class CollabFiltering {
	public static int numberofTestExample;
	
	public static void main(String[] args) {
		//String trainFilePath = "E:\\MLAssignments\\netflix\\TrainingRatings.txt";
		// File trainFile = new File(trainFilePath);
		String trainFilePath = args[0];
		numberofTestExample = Integer.parseInt(args[2]);
		FileDataSource fd = new FileDataSource(trainFilePath);
		try {
			fd.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String content = fd.getContent();
		String[] row = content.split("\n");
		HashMap<String, User> userRating = new HashMap<String, User>();
		HashMap<String, ArrayList<String>> itemMap = new HashMap<String, ArrayList<String>>();
		for (int r = 0; r < row.length; r++) {
			String[] col = row[r].split(",");
			if (userRating.containsKey(col[0])) {
				userRating.get(col[0]).getRating().put(col[1], Double.parseDouble(col[2]));
				userRating.get(col[0]).incrementSumRating(Double.parseDouble(col[2]));
				userRating.get(col[0]).incrementItemCount();
			} else {
				User user = new User(col[0]);
				user.getRating().put(col[1], Double.parseDouble(col[2]));
				user.incrementSumRating(Double.parseDouble(col[2]));
				user.incrementItemCount();
				userRating.put(col[0], user);
			}

			if (itemMap.containsKey(col[1])) {
				itemMap.get(col[1]).add(col[0]);
			} else {
				ArrayList<String> userids = new ArrayList<String>();
				userids.add(col[0]);
				itemMap.put(col[1], userids);
			}
		}
		

		HashMap<Similarity, Double> similarity = new HashMap<Similarity, Double>();
		//String testFilePath = "E:\\MLAssignments\\netflix\\TestingRatings1.txt";
		String testFilePath = args[1];
		fd = new FileDataSource(testFilePath);

		try {
			fd.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String testContent = fd.getContent();
		String[] rowTest = testContent.split("\n");
		double RMSE =0.0;
		double MAE =0.0;
		int step=1;
		if(numberofTestExample==0){
			numberofTestExample = rowTest.length;
		}
		for (int r = 0; r < numberofTestExample; r++) {
			String[] col = rowTest[r].split(",");
			String userId = col[0];
			String itemId = col[1];
		
			double prating = 0.0;
			ArrayList<String> userIteminter = itemMap.get(itemId);
			double K=0.0;
			for (int j = 0; j < userIteminter.size(); j++) {

				Similarity sim = new Similarity(userRating.get(userId).getUid(), userIteminter.get(j));
				if (similarity.containsKey(sim)) {
					continue;
				} else {
					HashMap<String, Double> userARatings = userRating.get(userId).getRating();
					HashMap<String, Double> userBRatings = userRating.get(userIteminter.get(j)).getRating();
					double numerator = 0.0;
					double denomOne = 0.0;
					double denomTwo = 0.0;
					for (Entry<String, Double> entry : userARatings.entrySet()) {
						if (userBRatings.containsKey(entry.getKey())) {
							numerator += (entry.getValue() - userRating.get(userId).getAverageRating())
									* (userBRatings.get(entry.getKey()) - userRating.get(userIteminter.get(j))
											.getAverageRating());
							denomOne += Math.pow(entry.getValue() - userRating.get(userId).getAverageRating(), 2);
							denomTwo += Math.pow(
									(userBRatings.get(entry.getKey()) - userRating.get(userIteminter.get(j))
											.getAverageRating()), 2);

						}
					}

					if(denomOne!=0.0 && denomTwo !=0.0) {
					similarity.put(sim, (numerator / Math.sqrt(denomTwo * denomOne)));
					prating += (numerator / Math.sqrt(denomTwo * denomOne))
							* (userRating.get(userIteminter.get(j)).getRating().get(itemId) - userRating.get(
									userIteminter.get(j)).getAverageRating());
					K+= (numerator / Math.sqrt(denomTwo * denomOne));
					}

				}

			}// end of calculating similarity
			if(K!=0.0){
				
				if(prating<0.0) {
					prating =0.0;
				}
				if(prating >5.0) {
					prating =5.0;
				}
			prating = userRating.get(userId).getAverageRating() + (1/K) * prating;
			//System.out.println("prating is" + prating + " for user id: " + userId + " for item ID : " + itemId
					//+ " actual rating is: " + col[2]);
			RMSE += Math.pow((prating-Double.parseDouble(col[2])), 2);
			MAE += Math.abs(prating-Double.parseDouble(col[2]));
			}
			
			if(r== 50*step) {
				printResults(RMSE, MAE, r);
				step++;
			}
			
		}
		
		System.out.println("Final RMSE is: " + Math.sqrt(RMSE/numberofTestExample));
		System.out.println("Final MAE is: " + MAE/numberofTestExample);

	}
	
	public static void printResults(double RMSE , double MAE , int denominator ) {
		System.out.println("Processed " + denominator + " number of rows");
		System.out.println("RMSE is: " + Math.sqrt(RMSE/denominator));
		System.out.println("MAE is: " + MAE/denominator);
	}
}
