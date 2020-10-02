package ru.vasiliydz.organizer.clientbase;

/**
 * An abstract {@link Field Field} builder. All fields should be created by objects of this type. Different
 * implementations of this interface produces different types of fields.
 */
public interface FieldBuilder {
	/**
	 * Returns identifier of suitable field
	 * @return identifier (name) of suitable field
	 */
	String getFieldName();

	/**
	 * Creates field
	 * @return Created field
	 */
	Field createField();
}
