public class RunScript {

	public static void main(String[] args) {
		int[] k = { 2, 5, 10, 15, 20 };
		int p = 2, r = 10;
		int q = k.length;
		String inputfileName = "";
		String outputfileName = "";
		for (int i = 1; i <= p; i++) {
			if (i == 1) {
				inputfileName = "E:\\MLAssignments\\Koala.jpg";
			} else {
				inputfileName = "E:\\MLAssignments\\Penguins.jpg";
			}
			for (int j = 0; j < q; j++) {
				for (int l = 1; l <= r; l++) {
					if (i == 1)
						outputfileName = "D:\\CodingContest\\HackerRank\\MLAssignment4\\output\\Koala=K";
					else
						outputfileName = "D:\\CodingContest\\HackerRank\\MLAssignment4\\output\\Penguins=K";

					outputfileName += k[j] + "out" + l + ".jpg";
					String[] arr = new String[3];
					arr[0] = inputfileName;
					arr[1] = String.valueOf(k[j]);
					arr[2] = outputfileName;
					KMeans.main(arr);

				}
			}
		}
	}

}
