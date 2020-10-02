package ru.vasiliydz.organizer.clientbase;

/**
 * Some boolean expression of some argument
 */
public interface Matcher<T> {

	/**
	 * Checks that object matches some rule
	 * @param t {@link T T} object to match
	 * @return true if t matches rule, else false
	 */
	boolean matches(T t);
}
