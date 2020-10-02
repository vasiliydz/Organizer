package ru.vasiliydz.organizer.json;

import com.owlike.genson.Genson;
import ru.vasiliydz.organizer.clientbase.ClientBase;

import java.io.FileReader;
import java.io.IOException;

public class ClientBaseJsonFileLoader {
	private final String filename;
	private final Genson genson;

	public ClientBaseJsonFileLoader(String filename) {
		this.filename = filename;
		genson = GensonConfiguration.getInstance().getGenson();
	}

	public ClientBase readClientBase() throws IOException {
		try (FileReader reader = new FileReader(filename)) {
			return genson.deserialize(reader, ClientBase.class);
		}
	}
}
