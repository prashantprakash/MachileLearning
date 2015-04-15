import java.util.HashMap;

public class User {
	private String uid;
	private HashMap<String, Double> rating;
	private double sumRating;
	private int noOfitemsRated;

	public User(String uid) {
		this.uid = uid;
		rating = new HashMap<String, Double>();
		sumRating = 0.0;
		noOfitemsRated = 0;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public HashMap<String, Double> getRating() {
		return rating;
	}

	public void setRating(HashMap<String, Double> rating) {
		this.rating = rating;
	}

	public double getAverageRating() {
		return this.sumRating / this.noOfitemsRated;
	}

	public void incrementSumRating(double rating) {
		this.sumRating += rating;
	}

	public void incrementItemCount() {
		this.noOfitemsRated++;
	}

}
