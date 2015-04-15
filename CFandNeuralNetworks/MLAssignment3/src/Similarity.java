public class Similarity {

	private String userA;
	private String userB;
	private double value;

	public Similarity(String userA, String userB) {
		this.userA = userA;
		this.userB = userB;
	}

	public String getUserA() {
		return userA;
	}

	public void setUserA(String userA) {
		this.userA = userA;
	}

	public String getUserB() {
		return userB;
	}

	public void setUserB(String userB) {
		this.userB = userB;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object O) {
		if ((O instanceof Similarity)
				&& ((((Similarity) O).getUserA().equals(this.userA) && ((Similarity) O).getUserB().equals(this.userB)) || (((Similarity) O)
						.getUserA().equals(this.userB) && ((Similarity) O).getUserB().equals(this.userA)))) {
			return true;
		} else {
			return false;
		}
	}
}
