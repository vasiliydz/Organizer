package ru.vasiliydz.organizer.clientbase;

/**
 * Object of this type performs the setting of some value to given {@link Field Field}. It is using to initialize
 * or to update field value
 */
public interface FieldSetter extends FieldSetterSource {

	/**
	 * Performs the setting of some value to given field
	 * @param field {@link Field Field} to set value to
	 * @throws ClassCastException Throws if this setter doesn't match to given field
	 */
	void setFieldValue(Field field);

	/**
	 * This is implementation of the constant {@link FieldSetterSource FieldSetterSource} that returns
	 * instance of this {@link FieldSetter FieldSetter}
	 * @param field {@link Field Field} to generate value to
	 * @return this
	 */
	default FieldSetter askForFieldSetter(Field field) {
		return this;
	}
}
