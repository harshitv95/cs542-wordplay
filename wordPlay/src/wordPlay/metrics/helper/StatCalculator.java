package wordPlay.metrics.helper;

import java.util.Map;

import wordPlay.metrics.MetricsCalculator;

/**
 * An single-function interface declaring a method to calculate a specific
 * Statistic, for {@link MetricsCalculator}
 * 
 * @author Harshit Vadodaria
 *
 * @param <Output>
 */
public interface StatCalculator<Output> {
	/**
	 * Calculate the required Stat (word count etc.),
	 * which could be used to calculate certain metrics
	 * @param map Map to get other stats, and to store the output of the current stat
	 * @param word String word
	 * @param statKey String Name of the Statistic
	 * @return Output Generic return type
	 */
	public Output calculate(Map<String, Double> map, String word, String statKey);
}
