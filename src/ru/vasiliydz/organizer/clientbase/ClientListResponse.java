package ru.vasiliydz.organizer.clientbase;

import java.util.List;

/**
 * This class wraps list of {@link Client Client} entries returned by {@link ClientBase ClientBase}
 * on some {@link Query Query}
 */
public class ClientListResponse implements BaseResponse {
	private final List<Client> clientList;
	private final List<String> fieldsToPrint;

	ClientListResponse(List<Client> clientList, List<String> fieldsToPrint) {
		this.clientList = clientList;
		this.fieldsToPrint = fieldsToPrint;
	}

	/**
	 * Returns list of {@link Client Client} entries wrapped into {@link BaseResponse BaseResponse} object
	 * @return list of {@link Client Client} entries
	 */
	public List<Client> getClientList() {
		return clientList;
	}

	/**
	 * Returns list of {@link Field Fields} by its names that should be viewed in this response
	 * @return list of {@link Field Field} names
	 */
	public List<String> getFieldsToShow() {
		return fieldsToPrint;
	}
}
