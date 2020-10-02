package ru.vasiliydz.organizer.clientbase;

/**
 * This is a {@link Query Query} on updating a {@link Field Field} of some {@link Client Client} entry
 * from {@link ClientBase ClientBase}
 */
public class UpdateClientQuery implements Query {
	private final long clientId;
	private final String fieldName;
	private final FieldSetterSource fieldSetterSource;

	/**
	 * Constructor
	 * @param clientId id of {@link Client Client} to update
	 * @param fieldName {@link Field Field} to update
	 * @param fieldSetterSource {@link FieldSetterSource FieldSetterSource} that should provide a
	 * new {@link Field Field} value represented by {@link FieldSetter FieldSetter} object
	 */
	public UpdateClientQuery(long clientId,
							 String fieldName, FieldSetterSource fieldSetterSource) {
		this.clientId = clientId;
		this.fieldName = fieldName;
		this.fieldSetterSource = fieldSetterSource;
	}

	@Override
	public OkBaseResponse applyToBase(ClientBase base) {
		Client client = base.getClient(clientId);
		Field field = client.getField(fieldName);
		FieldSetter setter = fieldSetterSource.askForFieldSetter(field);
		setter.setFieldValue(field);
		return new OkBaseResponse("Client " + client.getId() + " is successfully updated");
	}
}
