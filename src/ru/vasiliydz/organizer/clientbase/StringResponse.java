package ru.vasiliydz.organizer.clientbase;

/**
 * This class wraps a string response returned by {@link ClientBase ClientBase} on some
 * {@link Query Query}
 */
public class StringResponse implements BaseResponse {
	private final String stringResponse;

	/**
	 * Constructor
	 * @param stringResponse returned string response
	 */
	public StringResponse(String stringResponse) {
		this.stringResponse = stringResponse;
	}

	@Override
	public String toString() {
		return stringResponse;
	}
}
