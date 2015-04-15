/*
 * Author: Prashant Prakash
 * Created Date: 14th Feb 2015 
 * Decription:  This class keeps information about the prior
 * 
 */

public class Prior {
	private int spamCount;
	private int hamCount;

	public Prior(int spamCount, int hamCount) {
		this.spamCount = spamCount;
		this.hamCount = hamCount;
	}

	public int getSpamCount() {
		return spamCount;
	}

	public void setSpamCount(int spamCount) {
		this.spamCount = spamCount;
	}

	public int getHamCount() {
		return hamCount;
	}

	public void setHamCount(int hamCount) {
		this.hamCount = hamCount;
	}

}
