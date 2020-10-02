package ru.vasiliydz.organizer.clientbase;

public class OkClientCreatedResponse extends OkBaseResponse {
	private final long clientId;

	public OkClientCreatedResponse(long clientId) {
		super("Client " + clientId + " is successfully inserted");
		this.clientId = clientId;
	}

	public long getClientId() {
		return clientId;
	}
}
