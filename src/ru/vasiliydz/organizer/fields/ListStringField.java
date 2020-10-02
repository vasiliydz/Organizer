package ru.vasiliydz.organizer.fields;

import ru.vasiliydz.organizer.clientbase.*;

import java.util.ArrayList;
import java.util.List;

public class ListStringField implements Field {
	private final String fieldName;
	private List<String> value;
	private final Matcher<String> validator;

	public ListStringField(String fieldName, List<String> value, Matcher<String> validator) {
		this.fieldName = fieldName;
		this.validator = validator;
		setFieldValue(value);
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public String getStringValue() {
		return value.toString();
	}

	public boolean hasValue(String strValue) {
		return value.contains(strValue);
	}

	private void setFieldValue(List<String> newValue) {
		List<String> verifiedValue = new ArrayList<>();
		for (String valueItem : newValue) {
			if (!validator.matches(valueItem)) {
				throw new ClientBaseException("String " + valueItem + " is not valid for " + getFieldName() + " field");
			}
			verifiedValue.add(valueItem);
		}
		value = verifiedValue;
	}

	public static class Builder implements FieldBuilder {
		private final String fieldName;
		private final Matcher<String> validator;


		public Builder(String fieldName) {
			this(fieldName, new TrueStringValidator());
		}

		public Builder(String fieldName, Matcher<String> validator) {
			this.fieldName = fieldName;
			this.validator = validator;
		}

		@Override
		public String getFieldName() {
			return fieldName;
		}

		@Override
		public Field createField() {
			return new ListStringField(fieldName, new ArrayList<>(), validator);
		}
	}

	public static class Setter implements FieldSetter {
		private final List<String> value;

		public Setter(List<String> value) {
			if (value == null) {
				this.value = new ArrayList<>();
			} else {
				this.value = value;
			}
		}

		@Override
		public void setFieldValue(Field field) {
			if (!(field instanceof ListStringField)) {
				throw new ClientBaseException("Given field is not instance of ListStringField");
			}
			((ListStringField) field).setFieldValue(value);
		}
	}
}
