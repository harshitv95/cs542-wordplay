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
 * @author harshitv
 *
 */
public enum Stat {

	SENTENCE_COUNT(null),

	WORD_COUNT(null),

	CHAR_COUNT(null),;

	private StatCalculator<Double> calc;

	private Stat(StatCalculator<Double> calc) {
		this.calc = calc;
	}

	public Double calculate(Map<String, Double> map, String word) {
		return this.calc.calculate(map, word, this.toString());
	}

	public void initialize(Map<String, Double> map) {
		if (!map.containsKey(this.toString()))
			map.put(this.toString(), 0.0);
	}

	@Override
	public String toString() {
		return this.name();
	}

}