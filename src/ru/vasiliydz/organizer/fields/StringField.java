package ru.vasiliydz.organizer.fields;

import ru.vasiliydz.organizer.clientbase.*;

public class StringField implements ComparableField {
	private final String fieldName;
	private String fieldValue;
	private final Matcher<String> validator;

	StringField(String fieldName, Matcher<String> validator) {
		this.fieldName = fieldName;
		this.validator = validator;
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public String getStringValue() {
		return fieldValue;
	}

	private void setFieldValue(String newValue) {
		if (!validator.matches(newValue)) {
			throw new ClientBaseException("String " + newValue + " is not valid for " + getFieldName() + " field");
		}
		fieldValue = newValue;
	}

	@Override
	public int compareTo(ComparableField comparableField) {
		if (!(comparableField instanceof StringField)) {
			throw new RuntimeException("Comparing of different types of fields");
		}
		String anotherFieldValue = ((StringField) comparableField).fieldValue;
		return fieldValue.compareTo(anotherFieldValue);
	}

	public static class Builder implements ComparableFieldBuilder {
		private final String fieldName;
		private final Matcher<String> validator;

		public Builder(String fieldName, Matcher<String> validator) {
			this.fieldName = fieldName;
			this.validator = validator;
		}

		public Builder(String fieldName) {
			this(fieldName, new TrueStringValidator());
		}


		@Override
		public String getFieldName() {
			return fieldName;
		}

		@Override
		public ComparableField createField() {
			return new StringField(fieldName, validator);
		}
	}

	public static class Setter implements FieldSetter {

		private final String value;

		public Setter(String value) {
			if (value == null) {
				throw new IllegalArgumentException("Null is not suitable value for this setter");
			}
			this.value = value;
		}

		@Override
		public void setFieldValue(Field field) {
			if (!(field instanceof StringField)) {
				throw new ClientBaseException("Given field is not instance of StringField");
			}
			((StringField) field).setFieldValue(value);
		}

	}
}
