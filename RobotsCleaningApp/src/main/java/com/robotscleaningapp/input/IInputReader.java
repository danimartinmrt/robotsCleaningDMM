package com.robotscleaningapp.input;

public interface IInputReader {
	
	/**
	 * Reads the next line of input.
	 * 
	 * @return The next line of input as a String.
	 */
	String nextLine();
	
	/**
	 * Closes the input reader.
	 */
	void close();
}

