package wordPlay.metrics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import wordPlay.metrics.helper.MetricCalculator;
import wordPlay.metrics.helper.Stat;

/**
 * Processes each word to gether stats, and uses these stats to help calculate
 * the stats registered through the
 * {@link #registerCalculator(MetricCalculator)} method.
 * 
 * @author harshitv
 *
 * @param <Output> To type-restrict the MetricCalculator instances that can be
 *                 registered
 */
public class MetricsCalculator<Output> {

	/**
	 * Linked List of registered metric calculators, to maintain order
	 */
	protected List<MetricCalculator<Output>> metrics = new LinkedList<>();

	/**
	 * Stores stats calculated after arrival of each word, used to calculate metrics
	 */
	protected Map<String, Double> stats = new HashMap<>();

	/**
	 * Method to get a Stat like WORD_COUNT etc. Returns a double value represent
	 * the required stat, or null if the stat does not exist
	 * 
	 * @param statName String name of the required statistic
	 */
	public double getStat(String statName) {
		return this.stats.get(statName);
	}

	/**
	 * Calculate stats configured in the {@code enum} {@link Stat}
	 * 
	 */
	protected Stat[] statCalculators = Stat.values();

	public MetricsCalculator() {
		for (Stat calc : statCalculators)
			calc.initialize(stats);
	}

	/**
	 * Register an instance of {@link MetricCalculator}, to calculate the metrics
	 * after all words have been processed
	 */
	public void registerCalculator(MetricCalculator<Output> calc) {
		if (calc != null)
			metrics.add(calc);
	}

	/**
	 * Call after arrival of each word. This method will calculate stats after each
	 * word arrives.
	 * 
	 * @param word {@code String} The word to gather stats off
	 */
	public void processWord(String word) {
		for (Stat calc : statCalculators)
			calc.calculate(stats, word);
	}

	/**
	 * Call at the end to calculate all metrics, when no more words remain to
	 * process.
	 * 
	 * @return {@code String} : Value for all metrics, one in each line, in the
	 *         order they were registered
	 */
	public String calculate() {
		StringBuilder sb = new StringBuilder();
		for (MetricCalculator<Output> calc : metrics)
			sb.append(calc.calculate(stats)).append(System.lineSeparator());
		return sb.toString();
	}

	@Override
	public String toString() {
		return this.calculate();
	}

}
