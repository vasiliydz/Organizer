package ru.vasiliydz.organizer.clientbase;

/**
 * Produces {@link ComparableField ComparableFields}
 */
public interface ComparableFieldBuilder extends FieldBuilder {
	@Override
	ComparableField createField();
}
