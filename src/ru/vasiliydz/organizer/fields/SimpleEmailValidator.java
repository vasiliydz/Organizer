package ru.vasiliydz.organizer.fields;

import ru.vasiliydz.organizer.clientbase.Matcher;

public class SimpleEmailValidator implements Matcher<String> {

	@Override
	public boolean matches(String s) {
		return s.matches("[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+");
	}
}
