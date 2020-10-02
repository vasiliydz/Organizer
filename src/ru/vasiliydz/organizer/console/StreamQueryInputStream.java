package ru.vasiliydz.organizer.console;

import ru.vasiliydz.organizer.clientbase.*;
import ru.vasiliydz.organizer.fields.ListStringField;

import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

/**
 * Console implementation of {@link QueryInputStream QueryInputStream}
 */
public class StreamQueryInputStream implements QueryInputStream {
	private final Scanner scanner;
	private final FieldSetterSource fieldSetterSource;
	private final Helper helper;

	/**
	 * Constructor
	 * @param inputStream where it should get queries from
	 * @param fieldSetterSource {@link FieldSetterSource FieldSetterSource} which should fill {@link Field
	 * Fields}
	 */
	public StreamQueryInputStream(InputStream inputStream, FieldSetterSource fieldSetterSource) {
		this.scanner = new Scanner(inputStream);
		this.fieldSetterSource = fieldSetterSource;
		helper = new Helper();
	}

	@Override
	public Query getNextQuery() {
		String strQuery = scanner.nextLine();
		StringTokenizer tokenizer = new StringTokenizer(strQuery);
		switch (tokenizer.nextToken()) {
			case "exit":
				return null;
			case "help":
				return getHelpQuery(tokenizer);
			case "insert":
				return new InsertClientQuery(fieldSetterSource);
			case "update":
				return getUpdateClientQuery(tokenizer, fieldSetterSource);
			case "delete":
				return getDeleteClientQuery(tokenizer);
			case "list":
				return getListQuery(tokenizer);
			case "find":
				return getFindByStringListQuery(tokenizer, "phoneList");

		}
		throw new QueryStreamException("This command is unknown");
	}

	private Query getHelpQuery(StringTokenizer tokenizer) {
		if (!tokenizer.hasMoreTokens()) {
			return getPrintStringQuery(helper.getHelp());
		}
		String command = tokenizer.nextToken();
		return getPrintStringQuery(helper.getHelp(command));
	}

	private UpdateClientQuery getUpdateClientQuery(StringTokenizer tokenizer,
			FieldSetterSource fieldSetterSource) {
		long clientId = getClientIdSafely(tokenizer);
		String fieldName = getStringSafely(tokenizer);
		return new UpdateClientQuery(clientId, fieldName, fieldSetterSource);
	}

	private DeleteClientQuery getDeleteClientQuery(StringTokenizer tokenizer) {
		long clientId = getClientIdSafely(tokenizer);
		return new DeleteClientQuery(clientId);
	}

	private Query getListQuery(StringTokenizer tokenizer) {
		// если токенов нету, то возвращаем обычный список клиентов
		if (!tokenizer.hasMoreTokens()) {
			return new GetClientListQuery();
		}
		List<String> fields = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			fields.add(tokenizer.nextToken());
		}
		return new GetSortedClientListQuery(fields);
	}

	private GetConditionalClientListQuery getFindByStringListQuery(StringTokenizer tokenizer,
																   String listFieldName) {
		String phone = getStringSafely(tokenizer);
		Matcher<Client> matcher = client -> {
			Field field = client.getField(listFieldName);
			if (!(field instanceof ListStringField)) {
				throw new RuntimeException("Field " + listFieldName + " is not ListStringField");
			}
			return ((ListStringField) field).hasValue(phone);
		};
		return new GetConditionalClientListQuery(matcher);
	}

	private long getClientIdSafely(StringTokenizer tokenizer) {
		try {
			return Long.parseLong(tokenizer.nextToken());
		} catch (Exception e) {
			throw new QueryStreamException("Wrong command usage");
		}
	}

	private String getStringSafely(StringTokenizer tokenizer) {
		try {
			return tokenizer.nextToken();
		} catch (Exception e) {
			throw new QueryStreamException("Wrong command usage");
		}
	}

	private Query getPrintStringQuery(String s) {
		return base -> new StringResponse(s);
	}
}
