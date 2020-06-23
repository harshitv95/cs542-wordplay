package wordPlay.metrics.helper;

import java.util.Map;

/**
 * Contains calculators for various stats used to calculate metrics. Each Stat
 * (Statistic) is configured with:
 * <ol>
 * <li>A key (name of the statistic)</li>
 * <li>A function that calculates the stat and stores it in a {@link Map},
 * provided as parameter</li>
 * </ol>
 * 
 * @author Harshit Vadodaria
 *
 */
public enum Stat {

	SENTENCE_COUNT(new StatCalculator<Double>() {
		@Override
		public Double calculate(Map<String, Double> map, String word, String statKey) {
			double val = map.getOrDefault(statKey, 0.0);
			if (word.trim().endsWith("."))
				map.put(statKey, val += 1);
			return val;
		}
	}),

	WORD_COUNT(new StatCalculator<Double>() {
		@Override
		public Double calculate(Map<String, Double> map, String word, String statKey) {
			double val = map.getOrDefault(statKey, 0.0);
			map.put(statKey, val += 1);
			return val;
		}
	}),

	CHAR_COUNT(new StatCalculator<Double>() {
		@Override
		public Double calculate(Map<String, Double> map, String word, String statKey) {
			double val = map.getOrDefault(statKey, 0.0);
			map.put(statKey, val += word.replace(".", "").length());
			return val;
		}
	}),
	;

	private StatCalculator<Double> calc;

	private Stat(StatCalculator<Double> calc) {
		this.calc = calc;
	}

	/**
	 * Calculate the {@code Stat} and store the result in {@code map}, and return the same
	 * @param map
	 * @param word
	 * @return double value representing the value of the calculated Stat
	 */
	public Double calculate(Map<String, Double> map, String word) {
		return this.calc.calculate(map, word, this.toString());
	}

	/**
	 * Initializes all stats to 0.0
	 * @param map
	 */
	public void initialize(Map<String, Double> map) {
		if (!map.containsKey(this.toString()))
			map.put(this.toString(), 0.0);
	}

	@Override
	public String toString() {
		return this.name();
	}

}