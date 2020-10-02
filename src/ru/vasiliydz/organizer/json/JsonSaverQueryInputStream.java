package ru.vasiliydz.organizer.json;

import ru.vasiliydz.organizer.clientbase.Query;
import ru.vasiliydz.organizer.clientbase.QueryInputStream;

public class JsonSaverQueryInputStream implements QueryInputStream {
	private final String filename;
	private final QueryInputStream originalStream;
	private boolean timeToSave;

	public JsonSaverQueryInputStream(String filename, QueryInputStream originalStream) {
		this.filename = filename;
		this.originalStream = originalStream;
		timeToSave = false;
	}


	@Override
	public Query getNextQuery() {
		if (timeToSave) {
			timeToSave = false;
			return new SaveToJsonFileQuery(filename);
		}
		timeToSave = true;
		return originalStream.getNextQuery();
	}
}
