import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BruteGenerator implements TextGenerator {

	TrainingText text;
	Random rndm;
	ArrayList<NGram> myArray = new ArrayList<NGram>();

	public BruteGenerator() {
		rndm = new Random();

	}

	public BruteGenerator(int randomSeed) {
		rndm = new Random(randomSeed);

	}

	public int train(Scanner source, String delimeter, int k) {

		text = new TrainingText(source, delimeter, k);
		return text.size();

	}

	public String generateText(int length) {

		StringBuilder SB = new StringBuilder();
		int size = text.size();
		int num = rndm.nextInt(size);
		NGram seed = text.get(num);

		for (int i = 0; i < length; i++) {
			myArray.clear();
			num = 0;
			while (num != text.size() + 1) {

				num = text.indexOf(seed, num) + 1;
				if (num < text.size()) {
					myArray.add(text.get(num));
				}
			}

			int rndnum = rndm.nextInt(myArray.size());
			seed = myArray.get(rndnum);
			SB.append(seed.toString());

		}
		return SB.toString();
	}

}
