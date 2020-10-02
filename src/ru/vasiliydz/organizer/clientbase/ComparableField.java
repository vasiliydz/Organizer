package ru.vasiliydz.organizer.clientbase;

/**
 * This is {@link Field Field} type, which objects can be compared. It might be used to sort
 * {@link Client Client} list by this field. Comparable fields should be produced by
 * {@link ComparableFieldBuilder ComparableFieldBuilder}
 */
public interface ComparableField extends Field, Comparable<ComparableField> {
}
