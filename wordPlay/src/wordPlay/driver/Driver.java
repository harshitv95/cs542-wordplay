package wordPlay.driver;

import java.util.Map;

import wordPlay.helpers.ValidationHelper;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.metrics.helper.MetricCalculator;
import wordPlay.metrics.helper.Stat;

/**
 * @author John Doe
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}"))
				|| (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");

		ValidationHelper validation = new ValidationHelper();

		validation.critical().validateFile(args[0]); // Validating Input File
		validation.critical().validateFile(args[1]); // Validating Output File
		validation.critical().validateFile(args[2]); // Validating Metrics Output File

		MetricsCalculator metrics = new MetricsCalculator();
		metrics.registerCalculator(new MetricCalculator<String>() {
			@Override
			public String calculate(Map<String, Double> stats) {
				double wordCount = stats.get(Stat.WORD_COUNT.toString()),
						sentenceCount = stats.get(Stat.SENTENCE_COUNT.toString());
				return "AVG_NUM_WORDS_PER_SENTENCE = " + String.format("%.02f", (wordCount / sentenceCount));
			}
		});
		metrics.registerCalculator(new MetricCalculator<String>() {
			@Override
			public String calculate(Map<String, Double> stats) {
				double wordCount = stats.get(Stat.WORD_COUNT.toString()),
						charCount = stats.get(Stat.CHAR_COUNT.toString());
				return "AVG_WORD_LENGTH = " + String.format("%.02f", (charCount / wordCount));
			}
		});

	}
}
