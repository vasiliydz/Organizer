package ru.vasiliydz.organizer.fields;

import ru.vasiliydz.organizer.clientbase.Matcher;

public class TrueStringValidator implements Matcher<String> {

	@Override
	public boolean matches(String s) {
		return true;
	}
}
