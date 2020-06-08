package wordPlay.util;

import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class Results implements FileDisplayInterface, StdoutDisplayInterface, Closeable {

	final Writer stdOut, fileOut;
	final String filename;

	public Results(String filename) {
		this.stdOut = this.getStdOutWriter();
		this.fileOut = this.getFileWriter(filename);
		this.filename = filename;
	}

	protected void write(Writer out, String str) throws IOException {
		out.write(str);
		out.flush();
	}
	
	protected void writeLn(Writer out) throws IOException {
		out.flush();
	}

	@Override
	public void printToStdOut(String s) {
		try {
			write(stdOut, s);
		} catch (IOException e) {
			throw new RuntimeException("Failed to write to stdout", e);
		}
	}

	@Override
	public void printToFile(String s) {
		try {
			write(fileOut, s);
		} catch (IOException e) {
			throw new RuntimeException("Failed to write to File", e);
		}
	}

	@Override
	public Writer getStdOutWriter() {
		return new PrintWriter(System.out);
	}

	@Override
	public Writer getFileWriter(String filename) {
		try {
			return new FileWriter(filename);
		} catch (IOException e) {
			throw new RuntimeException("Failed to open file [" + filename + "] for writing", e);
		}
	}

	@Override
	public void close() {
		try {
			stdOut.flush();
			stdOut.close();
			fileOut.flush();
			fileOut.close();
		} catch (IOException e) {
			throw new RuntimeException("Failed to close results", e);
		}
	}

	@Override
	public String toString() {
		return "{stdOut: " + stdOut + ", fileOut: " + fileOut + "}";
	}

}
