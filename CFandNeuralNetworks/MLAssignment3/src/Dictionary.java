public class Dictionary {
	private String token;
	private int inSpamOccurence; // number of times the word has occured in spam
									// mails
	private int inHamOccurence; // number of times the word has occured in ham
								// mails

	public Dictionary(String token) {
		this.token = token;
		this.inHamOccurence = 0;
		this.inSpamOccurence = 0;
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

}
