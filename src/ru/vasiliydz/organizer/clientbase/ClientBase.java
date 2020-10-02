package ru.vasiliydz.organizer.clientbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Main class that represents client database object. It can store {@link Client Client} entries,
 * create new ones using the {@link ClientBuilder ClientBuilder} instance, update and delete them.
 * But access to stored data should not be got directly, {@link Query Query} objects should be used
 * instead of this
 */
public class ClientBase {
	private final ClientBuilder clientBuilder;
	private Map<Long, Client> clients;

	/**
	 * ClientBase constructor
	 * @param clientBuilder builder used to create {@link Client Client} entries
	 */
	public ClientBase(ClientBuilder clientBuilder) {
		this.clientBuilder = clientBuilder;
		clients = new HashMap<>();
	}

	/**
	 * Returns list of available {@link Field Field} names in {@link ClientBuilder ClientBuilder}
	 * @return list of available {@link Field Field} names
	 */
	public List<String> getFieldList() {
		return clientBuilder.getFieldList();
	}

	long addClient(FieldSetterSource fieldSetterSource) {
		Client client = clientBuilder.createClient(fieldSetterSource);
		clients.put(client.getId(), client);
		return client.getId();
	}

	List<Client> getClientList() {
		return new ArrayList<>(clients.values());
	}

	Client getClient(long clientId) {
		Client client = clients.get(clientId);
		if (client == null) {
			throw new ClientBaseException("There is no client with id " + clientId);
		}
		return client;
	}

	void removeClient(long clientId) {
		Client removedClient = clients.remove(clientId);
		if (removedClient == null) {
			throw new ClientBaseException("There is no client with id " + clientId);
		}
	}
}
