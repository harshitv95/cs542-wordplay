package wordPlay.driver;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Map;

import wordPlay.handler.WordRotator;
import wordPlay.helpers.ValidationHelper;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.metrics.helper.MetricCalculator;
import wordPlay.metrics.helper.Stat;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

/**
 * @author Harshit Vadodaria
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

		ValidationHelper validation = new ValidationHelper();

		validation.critical().validateFile(args[0]); // Validating Input File
		validation.critical().validateFilename(args[1]); // Validating Output File
		validation.critical().validateFilename(args[2]); // Validating Metrics Output File

		FileProcessor fp;

		try {
			fp = new FileProcessor(args[0]);
		} catch (InvalidPathException | SecurityException | IOException e) {
			throw new RuntimeException("Failed to open input file", e);
		}
		WordRotator rotator = new WordRotator();
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
		
		try (Results outputRes = new Results(args[1]); Results metricRes = new Results(args[2]);) {

			String word;
			boolean appendPeriod;
			int idx = 0;
			while ((word = fp.poll()) != null) {
				appendPeriod = false;
				word = word.trim();
				metrics.processWord(word);
				if (word.endsWith(".")) {
					appendPeriod = true;
					word = word.substring(0, word.length() - 1);
				}
				word = rotator.rotate(word, ++idx) + (!appendPeriod ? "" : ".");
				outputRes.printToFile(word + " ");
				outputRes.printToStdOut(word + " ");
				if (appendPeriod) {
					idx = 0;
					outputRes.printToFile("\n");
					outputRes.printToStdOut("\n");
				}
			}
			String calculatedMetrics = metrics.calculate();
			metricRes.printToFile(calculatedMetrics);
			metricRes.printToStdOut(calculatedMetrics);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read word from file", e);
		}

	}
}
