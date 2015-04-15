import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpamHamImpl {
	public static int noOfIter;
	public static double eta;
	public static double lambda;
	public static void main(String[] args) {
		//String spamTrainFolder = "E:\\MLAssignments\\hw2_train\\train\\spam\\";
		//String hamTrainFolder = "E:\\MLAssignments\\hw2_train\\train\\ham\\";
		//String spamTestFolder = "E:\\MLAssignments\\hw2_test\\test\\spam\\";
		//String hamTestFolder = "E:\\MLAssignments\\hw2_test\\test\\ham\\";
		String spamTrainFolder = args[0];
		String hamTrainFolder = args[1];
		String spamTestFolder = args[2];
		String hamTestFolder = args[3];
		SpamHamImpl.noOfIter = Integer.parseInt(args[4]);
		SpamHamImpl.eta = Double.parseDouble(args[5]);
		SpamHamImpl.lambda = Double.parseDouble(args[6]);
		
		HashMap<String, Dictionary> words = new HashMap<String, Dictionary>();
		// HashMap<String, Dictionary> wordswStop = new HashMap<String,
		// Dictionary>();
		// HashMap<String , Dictionary> wordsFSelection = new HashMap<String,
		// Dictionary>();
		File folderSpam = new File(spamTrainFolder);
		File folderHam = new File(hamTrainFolder);
		File[] listSpamFiles = folderSpam.listFiles();
		File[] listHamFiles = folderHam.listFiles();

		File folderTestSpam = new File(spamTestFolder);
		File folderTestHam = new File(hamTestFolder);
		File[] listSpamTestFiles = folderTestSpam.listFiles();
		File[] listHamTestFiles = folderTestHam.listFiles();
		
		System.out.println("********************WITH STOP WORDS************************");
		
		System.out.println("---------------------Naive Bayes----------------------------");
		runNaiveBayes(words, listSpamFiles, listHamFiles, listSpamTestFiles, listHamTestFiles, spamTrainFolder,
				hamTrainFolder, spamTestFolder, hamTestFolder, "no", "no");
		System.out.println("------------------------------------------------------------");
		System.out.println("-----------------Logistic Regression------------------------");
		runLogisticRegression(words, listSpamFiles, listHamFiles, listSpamTestFiles, listHamTestFiles, spamTrainFolder,
				hamTrainFolder, spamTestFolder, hamTestFolder, "no");
		System.out.println("-------------------------------------------------------------");
		System.out.println("*************************************************************");
		// Removal of STOP Words , modify the dictionary
		// printTokens(words);
		// printWords(words);
		//System.out.println(words.size());
		//System.out.println("after Removal of Stop Words");
		
		System.out.println("******************WITHOUT STOP WORDS**************************");
		words = new HashMap<String, Dictionary>();
		System.out.println("---------------------Naive Bayes----------------------------");
		runNaiveBayes(words, listSpamFiles, listHamFiles, listSpamTestFiles, listHamTestFiles, spamTrainFolder,
				hamTrainFolder, spamTestFolder, hamTestFolder, "yes", "no");
		//System.out.println(words.size());
		System.out.println("------------------------------------------------------------");
		System.out.println("-----------------Logistic Regression------------------------");
		runLogisticRegression(words, listSpamFiles, listHamFiles, listSpamTestFiles, listHamTestFiles, spamTrainFolder,
				hamTrainFolder, spamTestFolder, hamTestFolder, "no");
		System.out.println("-------------------------------------------------------------");
		System.out.println("***************************************************************");

		words = new HashMap<String, Dictionary>();
		
		System.out.println("******************STEMMING AND TF *****************************");
		System.out.println("---------------------Naive Bayes----------------------------");
		runNaiveBayes(words, listSpamFiles, listHamFiles, listSpamTestFiles, listHamTestFiles, spamTrainFolder,
				hamTrainFolder, spamTestFolder, hamTestFolder, "yes", "yes");

		//System.out.println(words.size());
		System.out.println("------------------------------------------------------------");
		System.out.println("-----------------Logistic Regression------------------------");
		runLogisticRegression(words, listSpamFiles, listHamFiles, listSpamTestFiles, listHamTestFiles, spamTrainFolder,
				hamTrainFolder, spamTestFolder, hamTestFolder, "yes");
		
		System.out.println("-------------------------------------------------------------");
		System.out.println("***************************************************************");

	}

	public static void removeStopWords(HashMap<String, Dictionary> words) {
		StopWords st = new StopWords();
		for (String token : st.getStopWords()) {
			words.remove(token);
		}
	}

	public static void printTokens(HashMap<String, Dictionary> words) {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.addAll(words.keySet());
		for (String string : tokens) {
			System.out.println(string);
		}
	}

	public static void printWords(HashMap<String, Dictionary> words) {
		for (Map.Entry<String, Dictionary> entry : words.entrySet()) {
			System.out.println("word is:" + entry.getKey() + "in spam : " + entry.getValue().getInSpamOccurence()
					+ " in Ham : " + entry.getValue().getInHamOccurence());
		}
	}

	public static void runNaiveBayes(HashMap<String, Dictionary> words, File[] listSpamFiles, File[] listHamFiles,
			File[] listSpamTestFiles, File[] listHamTestFiles, String spamTrainFolder, String hamTrainFolder,
			String spamTestFolder, String hamTestFolder, String stopWords, String fs) {

		Prior prior = new Prior(listSpamFiles.length, listHamFiles.length);
		int totalSpamWordCount = buildDictionary(listSpamFiles, spamTrainFolder, words, "spam", stopWords, fs);
		int totalHamWordCount = buildDictionary(listHamFiles, hamTrainFolder, words, "ham", stopWords, fs);

		/* calculating probability of all words */
		for (Map.Entry<String, Dictionary> entry : words.entrySet()) {
			entry.getValue().calculateProbabilty(totalSpamWordCount, totalHamWordCount, words.size());
		}

		double priorSpam = Math.log((double) prior.getSpamCount() / (prior.getSpamCount() + prior.getHamCount()));
		double priorHam = Math.log((double) prior.getHamCount() / (prior.getSpamCount() + prior.getHamCount()));
		double spamAccuracy = getNaiveAccuracy(listSpamTestFiles, priorSpam, priorHam, words, spamTestFolder, "spam");
		System.out.println("Spam Accuracy is :    " + spamAccuracy);
		double hamAccuracy = getNaiveAccuracy(listHamTestFiles, priorSpam, priorHam, words, hamTestFolder, "ham");
		System.out.println("Ham Accuracy is:      " + hamAccuracy);

	}

	public static void runLogisticRegression(HashMap<String, Dictionary> words, File[] listSpamFiles,
			File[] listHamFiles, File[] listSpamTestFiles, File[] listHamTestFiles, String spamTrainFolder,
			String hamTrainFolder, String spamTestFolder, String hamTestFolder, String fs) {

		ArrayList<String> tokens = new ArrayList<String>();
		tokens.addAll(words.keySet());
		int M = listSpamFiles.length + listHamTestFiles.length;
		int N = tokens.size();
		// the last column of vector contains the output value
		int[][] vector = new int[M][N + 2];
		// build vector for spam files
		buildVector(vector, M, N + 2, 0, listSpamFiles, "spam", tokens, spamTrainFolder);
		buildVector(vector, M, N + 2, listSpamFiles.length, listHamFiles, "ham", tokens, hamTrainFolder);
		int count = 0;
		/*
		 * for(int i =0 ; i<M;i++){ System.out.println(vector[i][N+1]); count
		 * ++; }
		 */
		// System.out.println(count);
		double[] wt = new double[N + 1];
		// initialize weight with some intial values
		// Arrays.fill(wt, 1);
		// Arrays.fill(wt, 0, wt.length / 4, 0.75);
		// Arrays.fill(wt, wt.length / 4, wt.length / 2, -0.35);
		// Arrays.fill(wt, wt.length / 2 + 1, 3 * wt.length / 4, 0.75);
		// Arrays.fill(wt, 3 * wt.length / 4 + 1, wt.length - 1, -0.35);l
		Arrays.fill(wt, 0.15);
		double[] pr = new double[M];
		// Arrays.fill(pr, 1 / M);
		int noOfIter = SpamHamImpl.noOfIter;
		double eta = SpamHamImpl.eta;
		double lambda = SpamHamImpl.lambda;
		modifyWeights(vector, wt, noOfIter, M, N + 1, pr, eta, lambda);
		double spamAccuracy = getLogisticAccuracy(listSpamTestFiles, spamTestFolder, N + 2, tokens, wt, "spam", fs);
		System.out.println("spam Accuracy:       " + spamAccuracy);
		double hamAccuracy = getLogisticAccuracy(listHamTestFiles, hamTestFolder, N + 2, tokens, wt, "ham", fs);
		System.out.println("ham Accuracy:        " + hamAccuracy);

	}

	public static double getLogisticAccuracy(File[] lists, String path, int COLUMN, ArrayList<String> words,
			double[] wt, String choice, String fs) {
		int count = 0;
		Porter porterObj = new Porter();
		for (File file : lists) {

			if (file.isFile()) {
				FileDataSource fd = new FileDataSource(file, path);
				try {
					fd.readFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String content = fd.getContent();
				String[] tokens = content.split("\\s+");
				double sampleData[] = new double[COLUMN];
				sampleData[0] = 1;
				for (int t = 0; t < tokens.length; t++) {
					String word = tokens[t].toLowerCase();
					if ("yes".equals(fs)) {
						word = porterObj.stripAffixes(word);
					}
					sampleData[words.indexOf(word) + 1] = sampleData[words.indexOf(word) + 1] + 1;

				}
				double wx = wt[0];

				for (int a = 1; a < COLUMN - 1; a++) {
					wx = wx + wt[a] * sampleData[a];
					if (sampleData[a] != 0.0) {
						// System.out.println("wt" + wt[a] + " sample" +
						// sampleData[a]);
					}
				}
				// System.out.println("wx is: "+ wx);
				if (wx > 0.0)
					count++;

			}

		}
		if ("spam".equals(choice)) {
			return ((double) count / lists.length) * 100;
		} else {
			return (((double) lists.length - count) / lists.length) * 100;
		}

	}

	public static void modifyWeights(int[][] vector, double[] wt, int noOfIter, int ROW, int COLUMN, double[] pr,
			double eta, double lambda) {
		for (int iter = 0; iter < noOfIter; iter++) {

			for (int row = 0; row < ROW; row++) {
				double p = wt[0];
				// System.out.println("for row number" + row);
				for (int column = 1; column < COLUMN; column++) {
					p = p + wt[column] * vector[row][column];
					// if (iter == 1) {
					// System.out.println("wt column" + wt[column] + "vecotr" +
					// vector[row][column]);
					// }
				}
				if (p <= 0) {
					// System.out.println("p is" + p);
				}

				// System.out.println("p is" + p);
				// pr[row] = p > 0 ? 1 : -1;
				// pr[row] =p;

				pr[row] = customSigmoid(p);
				if (row == 149 && iter == 0) {
					// System.out.println("p is" + p);
					// System.out.println("exp is" + pr[row]);
				}
				// System.out.println(pr[row]);
				if (iter == 0) {
					// System.out.println("pr row" + pr[row]);
				}
			}
			double dw[] = new double[COLUMN];
			for (int k = 1; k < COLUMN; k++) {
				for (int j = 0; j < ROW; j++) {
					dw[k] = dw[k] + vector[j][k] * (vector[j][COLUMN] - pr[j]);
					if (iter == 0) {
						// System.out.println("dw[k]" + dw[k] + "vector[j][k]" +
						// vector[j][k] + "vector[j][column]"
						// + vector[j][COLUMN] + " pr[j]" + pr[j]);
						// System.out.println("dw for :" + k + "and j values: "
						// + j+ " is :" +dw[k]);
					}
					// if(dw[k] < 0.1)
					// System.out.println("asdasd");

				}
				// if(iter==0)
				// System.out.println("dw[k]  " + dw[k] + "   end");

			}
			for (int k = 1; k < COLUMN; k++) {

				wt[k] = wt[k] + eta * (dw[k]) - (eta * lambda * wt[k]);
				// /wt[k] = wt[k] + eta * (dw[k]);
				// if(iter==0)
				// System.out.println("wt [k]" + wt[k] + "dw[k]" + dw[k]);

			}

			/*
			 * System.out.println("after :" + iter + "weight vector is");
			 * for(int i=0;i< COLUMN ;i++) { System.out.print(wt[i] + " ");
			 * 
			 * } System.out.println();
			 */
		}

		/*
		 * for(int i=0;i< COLUMN ;i++) {
		 * 
		 * System.out.println(wt[i]); }
		 */

	}

	public static double customSigmoid(double p) {
		if (p > 100)
			return 1.0;
		else if (p < -100)
			return 0.0;
		else
			return 1 / (1 + Math.exp(-p));
		// return 1 / (1 + Math.exp(-p));
	}

	public static void buildVector(int[][] vector, int m, int n, int index, File[] list, String choice,
			ArrayList<String> words, String path) {
		for (File file : list) {

			FileDataSource fd = new FileDataSource(file, path);
			try {
				fd.readFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String content = fd.getContent();
			String[] tokens = content.split("\\s+");
			vector[index][0] = 1;
			if ("spam".equalsIgnoreCase(choice)) {
				vector[index][n - 1] = 1;
			} else {
				vector[index][n - 1] = 0;
			}
			for (int i = 0; i < tokens.length; i++) {
				String word = tokens[i].toLowerCase();
				vector[index][words.indexOf(word) + 1] = vector[index][words.indexOf(word) + 1] + 1;

			}

			index++;

		}

	}

	/*
	 * This function tokenize and builds dictionary (bag of words model)
	 */
	public static int buildDictionary(File[] list, String path, HashMap<String, Dictionary> words, String choice,
			String stopWordChoice, String fs) {
		int count = 0;
		StopWords st = new StopWords();
		Porter porterObj = new Porter();
		// String regexNumber = "\\d+";
		List<String> stopWords = st.getStopWords();
		for (File file : list) {
			if (file.isFile()) {
				FileDataSource fd = new FileDataSource(file, path);
				try {
					fd.readFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String content = fd.getContent();
				String[] tokens = content.split("\\s+");
				for (int i = 0; i < tokens.length; i++) {
					String word = tokens[i].toLowerCase();
					if ("yes".equalsIgnoreCase(stopWordChoice) && stopWords.contains(word))
						continue;
					// if(word.matches(regexNumber)) continue;
					if (!word.matches("[a-zA-Z.? ]*"))
						continue;

					if ("yes".equals(fs)) {
						word = porterObj.stripAffixes(word);
					}
					if (words.containsKey(word)) {
						Dictionary token = (Dictionary) words.get(word);
						if ("spam".equalsIgnoreCase(choice)) {
							token.incrementInSpamOccurence();
						} else {
							token.incrementInHamOccurence();
						}
					} else {
						Dictionary token = new Dictionary(word);
						if ("spam".equalsIgnoreCase(choice)) {
							token.incrementInSpamOccurence();
						} else {
							token.incrementInHamOccurence();
						}
						words.put(word, token);
					}
					count++;
				}

			}

		}

		// TF implementation Considering Terms which occur most in a document
		// has high relevance
		
		
		
		if ("yes".equalsIgnoreCase(fs)) {
			int c=0;
			HashMap<String, Dictionary> tempWords = new HashMap<String, Dictionary>();
			for (Map.Entry<String, Dictionary> entry : words.entrySet()) {
				tempWords.put(entry.getKey(), entry.getValue());
			}
			
			for (Map.Entry<String, Dictionary> entry : tempWords.entrySet()) {
				if ("spam".equalsIgnoreCase(choice) && entry.getValue().getInSpamOccurence() == 1)
					words.remove(entry.getKey());
				else if ("ham".equalsIgnoreCase(choice) && entry.getValue().getInHamOccurence() == 1) {
					words.remove(entry.getKey());
				}else if("spam".equalsIgnoreCase(choice)){
					c += entry.getValue().getInSpamOccurence();
				}else if("ham".equalsIgnoreCase(choice)) {
					c+= entry.getValue().getInHamOccurence();
				}

			}
			return c;
		}

		return count;
	}

	/*
	 * This function calculates accuracy
	 */
	public static double getNaiveAccuracy(File[] list, double priorSpam, double priorHam,
			HashMap<String, Dictionary> words, String path, String choice) {
		double isSpam = 0.0;
		double isHam = 0.0;
		int count = 0;
		for (File file : list) {
			if (file.isFile()) {
				isSpam = priorSpam;
				isHam = priorHam;
				FileDataSource fd = new FileDataSource(file, path);
				try {
					fd.readFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String content = fd.getContent();
				String[] tokens = content.split("\\s+");
				for (int i = 0; i < tokens.length; i++) {
					String token = tokens[i].toLowerCase();
					if (words.containsKey(token)) {
						Dictionary dic = (Dictionary) words.get(token);
						isSpam += dic.getProbSpam();
						isHam += dic.getProbHam();
					}
				}

				if ("spam".equals(choice) && isSpam > isHam)
					count++;

				if ("ham".equals(choice) && isHam > isSpam)
					count++;

			}
		}
		//System.out.println("count" + count);
		//System.out.println("total files" + list.length);
		return (100 * (double) (count) / list.length);
	}

}
