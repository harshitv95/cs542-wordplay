package wordPlay.handler;

public class WordRotator {

	/**
	 * Rotates the given {@code word} by {@code idx} places. If {@code escapePeriod}
	 * is set to {@code true}, period (".") will be handled, i.e. skipped from
	 * rotation, else period will be considered as part of the word for rotating.
	 * 
	 * @param word         {@code String}
	 * @param idx          {@code int}
	 * @param escapePeriod {@code boolean}
	 * @return {@code String} The rotated word
	 */
	public String rotate(String word, int idx, boolean escapePeriod) {
		boolean appendPeriod = word.endsWith(".");
		if (escapePeriod && appendPeriod)
			word = word.substring(0, word.length() - 1);
		int len = word.length();
		word = word.substring(len - (idx % len)) + word.substring(0, len - (idx % len));
		return word + (escapePeriod && appendPeriod ? "." : "");
	}

	/**
	 * Rotates the given {@code word} by {@code idx} places. Also handles period
	 * ("."), i.e. does not involve period while rotating. Call the overloaded
	 * method {@link #rotate(String, int, boolean)} to override this behavior
	 * 
	 * @param word {@code String}
	 * @param idx  {@code int}
	 * @return {@code String} The rotated word
	 */
	public String rotate(String word, int idx) {
		return this.rotate(word, idx, true);
	}

}
