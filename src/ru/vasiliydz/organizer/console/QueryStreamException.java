package ru.vasiliydz.organizer.console;

/**
 * Main exception that is throwed when something goes wrong with input stream
 */
public class QueryStreamException extends RuntimeException {

	/**
	 * Constructor
	 * @param s what goes wrong
	 */
	public QueryStreamException(String s) {
		super(s);
	}
}
