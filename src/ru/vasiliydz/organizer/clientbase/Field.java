package ru.vasiliydz.organizer.clientbase;

/**
 * This is an abstract element describing the structure of {@link Client Client} object. Object of this type
 * should be created by instance implementing {@link FieldBuilder FieldBuilder} interface.
 * Updates of field should be performed by {@link FieldSetter FieldSetter}.
 */
public interface Field {
	/**
	 * Returns {@link String String} identifier of this field
	 * @return {@link String String} identifier of this field
	 */
	String getFieldName();

	/**
	 * Returns the {@link String String} representation of this field's value
	 * @return {@link String String} representation of this field's value
	 */
	String getStringValue();
}
