package ru.vasiliydz.organizer.clientbase;

/**
 * This is a {@link Query Query} on removing {@link Client Client} entry from {@link ClientBase ClientBase}
 */
public class DeleteClientQuery implements Query {
	private final long clientId;

	/**
	 * Constructor
	 * @param clientId id of {@link Client Client} that should be removed
	 */
	public DeleteClientQuery(long clientId) {
		this.clientId = clientId;
	}

	@Override
	public OkBaseResponse applyToBase(ClientBase base) {
		base.removeClient(clientId);
		return new OkBaseResponse("Client " + clientId + " is successfully deleted");
	}
}
