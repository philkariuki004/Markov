import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class MapGenerator implements TextGenerator {
	TreeMap<NGram, ArrayList<NGram>> myMap = new TreeMap<NGram, ArrayList<NGram>>();
	Random rndm;
	TrainingText text;

	public MapGenerator() {
		rndm = new Random();
	}

	public MapGenerator(int randomSeed) {
		rndm = new Random(randomSeed);
	}

	public int train(Scanner source, String delimeter, int k) {
		text = new TrainingText(source, delimeter, k);
		myMap.clear();
		for (int i = 0; i < text.size() - 1; i++) {
			NGram keyGram = text.get(i);
			if (!myMap.containsKey(keyGram)) {
				ArrayList<NGram> toAppend = new ArrayList<NGram>();
				toAppend.add(text.get(i + 1));
				myMap.put(keyGram, new ArrayList<NGram>());
				myMap.get(keyGram).add(text.get(i + 1));

			} else {
				myMap.get(keyGram).add(text.get(i + 1));
			}
		}

		return myMap.keySet().size();

	}

	public String generateText(int length) {
		StringBuilder SB = new StringBuilder();
		int size = text.size();
		int num = rndm.nextInt(size);
		NGram seed = text.get(num);
		for (int i = 0; i < length; i++) {
			int rndnum = rndm.nextInt(myMap.get(seed).size());
			seed = myMap.get(seed).get(rndnum);
			SB.append(seed.toString());

		}

		return SB.toString();
	}
}
