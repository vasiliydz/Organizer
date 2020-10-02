package ru.vasiliydz.organizer.clientbase;

/**
 * This type represents the query that can be applied to {@link ClientBase ClientBase}
 */
public interface Query {
	/**
	 * Apply this query to {@link ClientBase ClientBase}
	 * @param base {@link ClientBase ClientBase} to apply query to
	 * @return {@link BaseResponse BaseResponse} of base to this query
	 */
	BaseResponse applyToBase(ClientBase base);
}
