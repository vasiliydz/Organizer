package ru.vasiliydz.organizer.fields;

import ru.vasiliydz.organizer.clientbase.Matcher;

public class SimplePhoneValidator implements Matcher<String> {

	@Override
	public boolean matches(String s) {
		return s.matches("[0-9]+");
	}
}
