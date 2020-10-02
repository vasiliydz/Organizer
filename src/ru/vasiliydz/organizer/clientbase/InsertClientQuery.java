package ru.vasiliydz.organizer.clientbase;

/**
 * This is a {@link Query Query} on creating a new {@link Client Client} entry and adding it to the
 * {@link ClientBase ClientBase}
 */
public class InsertClientQuery implements Query {

	private final FieldSetterSource fieldSetterSource;

	/**
	 * Constructor
	 * @param fieldSetterSource {@link FieldSetterSource FieldSetterSource} that should provide a
	 * {@link Field Field} values (represented by {@link FieldSetter FieldSetters}) of a new
	 * {@link Client Client} entry
	 */
	public InsertClientQuery(FieldSetterSource fieldSetterSource) {
		this.fieldSetterSource = fieldSetterSource;
	}

	@Override
	public OkClientCreatedResponse applyToBase(ClientBase base) {
		long clientId = base.addClient(fieldSetterSource);
		return new OkClientCreatedResponse(clientId);
	}
}
