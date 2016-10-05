import java.util.Arrays;
import java.util.List;

import sun.security.util.Length;

public class NGram implements Comparable<NGram> {

	private String[] contents;
	private String separator;

	public NGram(List<String> source, String sep) {
		separator = sep;
		contents = new String[source.size()];
		contents = Arrays.copyOf(source.toArray(new String[source.size()]), source.size());
	}

	public int compareTo(NGram other) {
		String[] otherArray = other.contents;
		String[] thisArray = this.contents;
		int min = Math.min(otherArray.length, thisArray.length);
		for (int i = 0; i < min; i++) {
			if (thisArray[i].compareToIgnoreCase(otherArray[i]) != 0) {
				return thisArray[i].compareToIgnoreCase(otherArray[i]);//if any two items in the array are not equal
			}
		}

		return thisArray.length - otherArray.length;
	}

	public boolean equals(Object o) {
		if (o instanceof NGram) {
			o = (NGram) o;
			if (((NGram) o).contents.length == this.contents.length) {
				for (int i = 0; i < contents.length; i++) {
					if (!contents[i].equals(((NGram) o).contents[i])) {
						return false;
					}
				}

			} else {
				return false;
			}

		} else {
			return false;
		}
		return true;
	}

	public int hashCode() {
		int sum = 0;
		int factor = 95;
		for (int i = 0; i < contents.length; i++) {
			sum += contents[i].hashCode() * (contents[i].length()) * factor * i;
			factor += 13;
		}

		return sum;
	}

	public String toString() {

		return this.contents[contents.length - 1] + separator;
	}
}