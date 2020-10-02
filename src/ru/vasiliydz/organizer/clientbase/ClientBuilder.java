package ru.vasiliydz.organizer.clientbase;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Object of this class produces the {@link Client Client} instances
 */
public class ClientBuilder {
	private final List<FieldBuilder> fieldBuilders;
	private final IdGenerator idGenerator;

	/**
	 * Creates ClientBuilder
	 * @param fieldBuilders List of {@link FieldBuilder FieldBuilders} that will be create
	 * {@link Field Fields} of each {@link Client Client} produced by this ClientBuilder
	 */
	public ClientBuilder(List<FieldBuilder> fieldBuilders) {
		verifyFieldBuilderList(fieldBuilders);
		this.fieldBuilders = new ArrayList<>(fieldBuilders);
		idGenerator = new IdGenerator();
	}

	public List<String> getFieldList() {
		List<String> fieldList = new ArrayList<>();
		for (FieldBuilder builder : fieldBuilders) {
			fieldList.add(builder.getFieldName());
		}
		return fieldList;
	}

	/**
	 * Creates {@link Client Client} according to {@link FieldBuilder FieldBuilder} list
	 * of this ClientBuilder with parameters got from the responser
	 * @param fieldSetterSource {@link FieldSetterSource FieldSetterSource} that generates values for
	 * {@link Field Fields}
	 * @return New {@link Client Client}
	 */
	public Client createClient(FieldSetterSource fieldSetterSource) {
		HashMap<String, Field> fields = new HashMap<>();
		for (FieldBuilder builder : fieldBuilders) {
			String fieldName = builder.getFieldName();
			Field field = builder.createField();
			FieldSetter setter = fieldSetterSource.askForFieldSetter(field);
			setter.setFieldValue(field);
			fields.put(fieldName, field);
		}
		return new Client(idGenerator.generateId(), fields);
	}

	/**
	 * Checks that {@link FieldBuilder FieldBuilder} list is correct, i.e. each field name appears only once
	 * @param fieldBuilders List of {@link FieldBuilder FieldBuilders} to check
	 * @throws IllegalArgumentException if fieldBuilders isn't correct
	 */
	private void verifyFieldBuilderList(List<FieldBuilder> fieldBuilders) throws IllegalArgumentException {
		Set<String> keys = new HashSet<>();
		for (FieldBuilder builder : fieldBuilders) {
			String key = builder.getFieldName();
			if (keys.contains(key)) {
				throw new IllegalArgumentException("FieldBuilder list is not correct");
			}
			keys.add(key);
		}
	}

}
