package ru.vasiliydz.organizer.json;

import com.owlike.genson.Genson;
import ru.vasiliydz.organizer.clientbase.ClientBase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientBaseJsonFileSaver {
	private final String filename;
	private final Genson genson;

	public ClientBaseJsonFileSaver(String filename) {
		this.filename = filename;
		this.genson = GensonConfiguration.getInstance().getGenson();
	}

	public void saveClientBase(ClientBase base) throws IOException {
		Path path = Paths.get(filename);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			genson.serialize(base, writer);
		}
	}

}
