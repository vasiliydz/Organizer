package ru.vasiliydz.organizer.clientbase;

import java.util.Map;

/**
 * An instance of this class contains the description of some client. Instances of this class should be created
 * by {@link ClientBuilder ClientBuilder}, because builder contains information about structure of client objects
 */
public class Client {
	private final long id;
	private Map<String, Field> fields;

	Client(long id, Map<String, Field> fields) {
		this.id = id;
		this.fields = fields;
	}

	/**
	 * Returns id of this client
	 * @return id of this client
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns a {@link Field Field} of this client by its name.
	 * @param fieldName name of the required {@link Field Field}
	 * @return requested {@link Field Field}
	 * @throws ClientBaseException if client does not have {@link Field Field} with given name
	 */
	public Field getField(String fieldName) throws ClientBaseException {
		Field retField = fields.get(fieldName);
		if (retField == null) {
			throw new ClientBaseException("Client has not field " + fieldName);
		}
		return retField;
	}

}
