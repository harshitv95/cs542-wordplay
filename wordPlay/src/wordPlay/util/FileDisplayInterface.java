package wordPlay.util;

import java.io.Writer;

public interface FileDisplayInterface {
	public void printToFile(String s);
	public Writer getFileWriter(String filename);
}
