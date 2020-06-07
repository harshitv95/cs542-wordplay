package wordPlay.metrics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import wordPlay.metrics.helper.MetricCalculator;
import wordPlay.metrics.helper.Stat;

public class MetricsCalculator {

	/**
	 * Linked List of registered metric calculators, to maintain order
	 */
	protected List<MetricCalculator<?>> metrics = new LinkedList<>();

	/**
	 * Stores stats calculated after arrival of each word, used to calculate metrics
	 */
	protected Map<String, Double> stats = new HashMap<>();

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
	public <Output> void registerCalculator(MetricCalculator<Output> calc) {
		if (calc != null)
			metrics.add(calc);
	}

	/**
	 * Call after arrival of each word. This method will calculate stats after each
	 * word arrives.
	 * 
	 * @param word
	 */
	public void processWord(String word) {
		for (Stat calc : statCalculators)
			calc.calculate(stats, word);
	}

	/**
	 * Call at the end to calculate all metrics, when no more words remain to
	 * process.
	 * 
	 * @return {@code String} : Value for all metrics, one in each line, in the order they
	 *         were registered
	 */
	public String calculate() {
		StringBuilder sb = new StringBuilder();
		for (MetricCalculator<?> calc : metrics)
			sb.append(calc.calculate(stats)).append('\n');
		return sb.toString();
	}

	@Override
	public String toString() {
		return this.calculate();
	}

}
