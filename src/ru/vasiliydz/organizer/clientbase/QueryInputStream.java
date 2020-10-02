package ru.vasiliydz.organizer.clientbase;

/**
 * Object of this type represents the stream that generates {@link Query Query} objects
 */
public interface QueryInputStream {

	/**
	 * Returns next {@link Query Query} from the stream, or null if stream has reached end
	 * @return Next {@link Query Query} from the stream, or null if stream has reached end
	 */
	Query getNextQuery();
}
