package wordPlay.helpers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class ValidationHelper {

	public ValidationHelper() {
		this(false);
	}

	public ValidationHelper(boolean critical) {
		this.critical = critical;
	}

	private final boolean critical;

	public ValidationHelper critical() {
		return new ValidationHelper(true);
	}

	public boolean validateOnlyAlphaNumeric(String str) {
		return this.validateOnlyAlphaNumeric(str, "[" + str + "] contains non-alphanumeric characters");
	}

	public boolean validateOnlyAlphaNumeric(String str, String message) {
		if (str.matches(".*[^a-zA-Z0-9].*")) {
			System.err.println(message);
			if (critical)
				throw new RuntimeException(message);
			return false;
		}
		return true;
	}

	public boolean validateFile(String filename) {
		boolean retVal = validateOnlyAlphaNumeric(filename, "File name (" + filename
				+ ") should not contain special characters, it should only contain alpha-numeric characters");
		if (!retVal)
			return retVal;

		if (!Files.exists(Paths.get(filename))) {
			System.err.println("File [" + filename + "] does not exist.");
			if (critical)
				throw new RuntimeException("File [" + filename + "] does not exist.");
			return false;
		}
		return true;
	}

}
