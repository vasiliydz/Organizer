package ru.vasiliydz.organizer.console;

import ru.vasiliydz.organizer.clientbase.Field;
import ru.vasiliydz.organizer.clientbase.FieldSetter;
import ru.vasiliydz.organizer.clientbase.FieldSetterSource;
import ru.vasiliydz.organizer.fields.ListStringField;
import ru.vasiliydz.organizer.fields.StringField;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.List;

/**
 * Console implementation of {@link FieldSetterSource FieldSetterSource}
 */
public class StreamFieldSetterSource implements FieldSetterSource {
	private final Scanner scanner;
	private final PrintStream printStream;

	/**
	 * Constructor
	 * @param printStream where it should print requests
	 * @param inputStream where it should get responses from
	 */
	public StreamFieldSetterSource(PrintStream printStream, InputStream inputStream) {
		this.scanner = new Scanner(inputStream);
		this.printStream = printStream;
	}

	@Override
	public FieldSetter askForFieldSetter(Field field) {
		if (field instanceof StringField) {
			printStream.print("Insert value for " + field.getFieldName() + ": ");
			return getStringFieldSetter();
		} else if (field instanceof ListStringField) {
			printStream.print("List values for " + field.getFieldName() + " list: ");
			return getListStringFieldSetter();
		}
		throw new RuntimeException("FieldSetter does not know this type of field");
	}

	private StringField.Setter getStringFieldSetter() {
		String value = scanner.nextLine();
		return new StringField.Setter(value);
	}

	private ListStringField.Setter getListStringFieldSetter() {
		List<String> value = new ArrayList<>();
		String values = scanner.nextLine();
		StringTokenizer tokenizer = new StringTokenizer(values);
		while (tokenizer.hasMoreTokens()) {
			value.add(tokenizer.nextToken());
		}
		return new ListStringField.Setter(value);
	}

}
