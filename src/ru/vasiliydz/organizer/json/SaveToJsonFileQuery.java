package ru.vasiliydz.organizer.json;

import ru.vasiliydz.organizer.clientbase.BaseResponse;
import ru.vasiliydz.organizer.clientbase.ClientBase;
import ru.vasiliydz.organizer.clientbase.Query;
import ru.vasiliydz.organizer.clientbase.StringResponse;

import java.io.IOException;

public class SaveToJsonFileQuery implements Query {
	private final ClientBaseJsonFileSaver baseSaver;

	public SaveToJsonFileQuery(String filename) {
		baseSaver = new ClientBaseJsonFileSaver(filename);
	}

	@Override
	public BaseResponse applyToBase(ClientBase base) {
		try {
			baseSaver.saveClientBase(base);
		} catch (IOException e) {
			return new StringResponse("Failed to save base to file");
		}
		return new StringResponse("");
	}
}
