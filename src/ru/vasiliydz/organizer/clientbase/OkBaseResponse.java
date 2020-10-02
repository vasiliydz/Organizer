package ru.vasiliydz.organizer.clientbase;

/**
 * {@link BaseResponse BaseResponse} that means that last {@link Query Query} to {@link ClientBase
 * ClientBase} completed successfully, but there is no expected response from base (e.g. query to update
 * base)
 */
public class OkBaseResponse implements BaseResponse {
	private final String message;

	/**
	 * Constructor
	 * @param message some message from base
	 */
	public OkBaseResponse(String message) {
		this.message = message;
	}

	public OkBaseResponse() {
		this("");
	}

	@Override
	public String toString() {
		return "OK " + message;
	}
}
