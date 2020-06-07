package wordPlay.handler;

public class WordRotator {

	public String rotate(String word, int idx) {
		int len = word.length();
		return word.substring(idx % len) + word.substring(0, idx % len);
	}

}
