package ru.vasiliydz.organizer;

import ru.vasiliydz.organizer.clientbase.ClientBase;
import ru.vasiliydz.organizer.clientbase.ClientBuilder;
import ru.vasiliydz.organizer.clientbase.Matcher;
import ru.vasiliydz.organizer.fields.ListStringField;
import ru.vasiliydz.organizer.fields.SimpleEmailValidator;
import ru.vasiliydz.organizer.fields.SimplePhoneValidator;
import ru.vasiliydz.organizer.fields.StringField;
import ru.vasiliydz.organizer.json.ClientBaseJsonFileSaver;

import java.io.IOException;
import java.util.Arrays;

public class ClientBaseBuildApp {

	public static void main(String[] args) throws IOException {
		// Create validators for fields
		Matcher<String> phoneValidator = new SimplePhoneValidator();
		Matcher<String> eMailValidator = new SimpleEmailValidator();
		// Define the structure of client object
		ClientBuilder builder = new ClientBuilder(Arrays.asList(
				new StringField.Builder("firstName"),
				new StringField.Builder("lastName"),
				new StringField.Builder("patronymic"),
				new StringField.Builder("jobPosition"),
				new StringField.Builder("email", eMailValidator),
				new ListStringField.Builder("phoneList", phoneValidator)
		));
		// Create database
		ClientBase base = new ClientBase(builder);
		// Save it to JSON
		new ClientBaseJsonFileSaver("base.json").saveClientBase(base);
		System.out.println("ClientBase is successfully created");
	}
}
