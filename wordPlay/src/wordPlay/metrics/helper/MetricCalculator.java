package wordPlay.metrics.helper;

import java.util.Map;

import wordPlay.metrics.MetricsCalculator;

/**
 * An single-function interface declaring a method to calculate a specific
 * Metric, for {@link MetricsCalculator}
 * 
 * @author Harshit Vadodaria
 *
 * @param <Output>
 */
public interface MetricCalculator<Output> {
	public Output calculate(Map<String, Double> stats);
}
