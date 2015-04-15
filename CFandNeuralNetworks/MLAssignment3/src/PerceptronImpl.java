import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PerceptronImpl {

	public static void main(String[] args) {

		Input input = new Input(args[0], args[1], args[2], args[3], Double.parseDouble(args[4]),
				Integer.parseInt(args[5]), args[6]);
		HashMap<String, Dictionary> words = new HashMap<String, Dictionary>();
		// build the dictionary first
		buildDictionary(input, words);
		File folderSpamtrain = new File(input.getSpamTrainFolder());
		File[] listOfSpamFiles = folderSpamtrain.listFiles();
		File folderHamtrain = new File(input.getHamTrainFolder());
		File[] listOfHamFiles = folderHamtrain.listFiles();
		int M = listOfSpamFiles.length + listOfHamFiles.length;
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.addAll(words.keySet());
		int N = tokens.size() + 2;
		int[][] vector = new int[M][N];
		// build vector
		buildVector(vector, 0, listOfSpamFiles, input.getSpamTrainFolder(), tokens, "spam", N);
		buildVector(vector, listOfSpamFiles.length, listOfHamFiles, input.getHamTrainFolder(), tokens, "ham", N);
		double pr[] = new double[M];
		Arrays.fill(pr, (1 / M));
		double wt[] = new double[N - 1];
		Arrays.fill(wt, 0.15);
		modifyWeights(vector, wt, M, N, pr, input);
		File folderSpamtest = new File(input.getSpamTestFolder());
		File[] listOfSpamTestFiles = folderSpamtest.listFiles();
		File folderHamTest = new File(input.getHamTestFolder());
		File[] listOfHamTestFiles = folderHamTest.listFiles();
		double spamAccuracy = getPerceptronAccuracy(listOfSpamTestFiles, input.getSpamTestFolder(), N, tokens, wt,
				"spam");
		System.out.println("spam Accuracy:       " + spamAccuracy);
		double hamAccuracy = getPerceptronAccuracy(listOfHamTestFiles, input.getHamTestFolder(), N, tokens, wt, "ham");
		System.out.println("ham Accuracy:        " + hamAccuracy);

	}

	public static double getPerceptronAccuracy(File[] lists, String path, int COLUMN, ArrayList<String> words,
			double[] wt, String choice) {
		int count = 0;
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
				double samp[]= new double[COLUMN];
		    	samp[0]=1;
		    	for(int t = 0; t < tokens.length; t++) {
		    		  String word = tokens[t].toLowerCase();
		    		  //logic get ind word update x
		    		  //int pos = ;
		    		  samp[words.indexOf(word)+1]=samp[words.indexOf(word)+1]+1;
		    		  

		    	}
		    	double wx=wt[0];
		    	
					for(int c=1;c<COLUMN-1;c++){
					wx= wx+wt[c]*samp[c];
					}
			//System.out.println(wx);
		    	if(wx>0)
		    	count++;
		    	
			}
		}
		
		if ("spam".equals(choice)) {
			return ((double) count / lists.length) * 100;
		} else {
			return (((double) lists.length - count) / lists.length) * 100;
		}

	}

	public static void modifyWeights(int[][] vector, double[] wt, int ROW, int COLUMN, double[] pr, Input input) {
		for (int i = 0; i < input.getNoOfIter(); i++) {
			for (int j = 0; j < ROW; j++) {

				double pdash = wt[0];
				for (int c = 1; c < COLUMN - 1; c++) {
					pdash = pdash + wt[c] * vector[j][c];
				}
				pr[j] = pdash > 0 ? 1 : -1;

				double dw[] = new double[COLUMN - 1];
				for (int k = 0; k < COLUMN - 1; k++) {

					dw[k] = dw[k] + vector[j][k] * (vector[j][COLUMN - 1] - pr[j]);

				}

				for (int k = 0; k < COLUMN - 1; k++) {
					wt[k] = wt[k] + input.getEta() * (dw[k]);
				}
			}

		}
	}

	public static void buildVector(int[][] vector, int index, File[] list, String path, ArrayList<String> words,
			String choice, int n) {
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
				vector[index][0] = 1;
				if ("spam".equals(choice)) {
					vector[index][n - 1] = 1;
				} else if ("ham".equals(choice)) {
					vector[index][n - 1] = -1;
				}
				for (int t = 0; t < tokens.length; t++) {
					String word = tokens[t].toLowerCase();
					vector[index][words.indexOf(word) + 1] = vector[index][words.indexOf(word) + 1] + 1;

				}

			}
			index++;

		}
	}

	public static void buildDictionary(Input input, HashMap<String, Dictionary> words) {
		// build with spam folder
		buildDictionaryUtil(input.getSpamTrainFolder(), words, "spam", input.getStopWords());
		// build with ham folder
		buildDictionaryUtil(input.getHamTrainFolder(), words, "ham", input.getStopWords());

	}

	public static void buildDictionaryUtil(String path, HashMap<String, Dictionary> words, String choice,
			String isStopWords) {
		File foldertrain = new File(path);
		File[] listOfFiles = foldertrain.listFiles();
		List<String> stopWords = new ArrayList<String>();
		if ("yes".equals(isStopWords)) {
			StopWords st = new StopWords();
			stopWords = st.getStopWords();

		}
		for (File file : listOfFiles) {
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
					if ("yes".equalsIgnoreCase(isStopWords) && stopWords.contains(word))
						continue;
					// if(word.matches(regexNumber)) continue;
					//if (!word.matches("[a-zA-Z.? ]*"))
						//continue;

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
				}

			}

		}
	}

	// end of buildDictionaryUtilFunction
}
