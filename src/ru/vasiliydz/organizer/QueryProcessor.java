package ru.vasiliydz.organizer;

import ru.vasiliydz.organizer.clientbase.*;
import ru.vasiliydz.organizer.console.QueryStreamException;

public class QueryProcessor {
	private final ClientBase clientBase;
	private final ResponseOutputStream responseOutputStream;

	public QueryProcessor(ClientBase clientBase, ResponseOutputStream responseOutputStream) {
		this.clientBase = clientBase;
		this.responseOutputStream = responseOutputStream;
	}

	public void processQueryStream(QueryInputStream queryStream) {
		Query query = getQuerySafely(queryStream);
		while (query != null) {
			try {
				BaseResponse response = query.applyToBase(clientBase);
				responseOutputStream.putResponse(response);
			} catch (ClientBaseException e) {
				responseOutputStream.putResponse(new StringResponse(e.getMessage()));
			}
			query = getQuerySafely(queryStream);
		}

	}

	private Query getQuerySafely(QueryInputStream queryStream) {
		while (true) {
			try {
				return queryStream.getNextQuery();
			} catch (QueryStreamException e) {
				responseOutputStream.putResponse(new StringResponse(e.getMessage()));
			}
		}
	}
}
