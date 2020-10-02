package ru.vasiliydz.organizer.clientbase;

/**
 * Main exception that is throwed when something goes wrong with client database
 */
public class ClientBaseException extends RuntimeException {

	/**
	 * Constructor
	 * @param s what goes wrong
	 */
	public ClientBaseException(String s) {
		super(s);
	}
}
