package wordPlay.metrics.helper;

import java.util.Map;

import wordPlay.metrics.MetricsCalculator;

/**
 * An single-function interface declaring a method to calculate a specific
 * Statistic, for {@link MetricsCalculator}
 * 
 * @author harshitv
 *
 * @param <Output>
 */
public interface StatCalculator<Output> {
	public Output calculate(Map<String, Double> map, String word, String statKey);
}
