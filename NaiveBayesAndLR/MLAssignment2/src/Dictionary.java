/*
 * Author :Prashant Prakash
 * Created Date:  14th Feb 2015
 * Description : This class keeps information about the words 
 * 
 */
public class Dictionary {
	private String token;
	private int inSpamOccurence; // number of times the word has occured in spam
									// mails
	private int inHamOccurence; // number of times the word has occured in ham
								// mails
	private double probSpam;
	private double probHam;

	public Dictionary(String token) {
		this.token = token;
		this.inHamOccurence = 0;
		this.inSpamOccurence = 0;
		this.probHam = 0.0;
		this.probSpam = 0.0;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getInSpamOccurence() {
		return inSpamOccurence;
	}

	public void setInSpamOccurence(int inSpamOccurence) {
		this.inSpamOccurence = inSpamOccurence;
	}

	public int getInHamOccurence() {
		return inHamOccurence;
	}

	public void setInHamOccurence(int inHamOccurence) {
		this.inHamOccurence = inHamOccurence;
	}

	public void incrementInHamOccurence() {
		this.inHamOccurence++;
	}

	public void incrementInSpamOccurence() {
		this.inSpamOccurence++;
	}

	public double getProbSpam() {
		return probSpam;
	}

	public void setProbSpam(double probSpam) {
		this.probSpam = probSpam;
	}

	public double getProbHam() {
		return probHam;
	}

	public void setProbHam(double probHam) {
		this.probHam = probHam;
	}

	/*
	 * for calculating probabilty inclusion of laplace correction
	 */
	public void calculateProbabilty(int totalSpam, int totalHam , int dictSize) {
		this.probSpam = Math.log((double)(this.inSpamOccurence +1)/(totalSpam + dictSize));
		this.probHam = Math.log((double)(this.inHamOccurence +1)/(totalHam + dictSize));
	}

}
