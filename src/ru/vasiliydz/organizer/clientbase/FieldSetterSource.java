package ru.vasiliydz.organizer.clientbase;

/**
 * Objects of this type can generate {@link FieldSetter FieldSetters} for {@link Field Fields} that contains
 * some value
 */
public interface FieldSetterSource {

	/**
	 * Generates some value for {@link Field Field}
	 * @param field {@link Field Field} to generate value to
	 * @return {@link FieldSetter FieldSetter} of generated value
	 */
	FieldSetter askForFieldSetter(Field field);
}
