package ru.vasiliydz.organizer.json;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.reflect.VisibilityFilter;

public class GensonConfiguration {
	private static final GensonConfiguration instance = new GensonConfiguration();
	private final Genson genson;

	private GensonConfiguration() {
		genson = new GensonBuilder().useRuntimeType(true).useClassMetadata(true)
				.useConstructorWithArguments(true).setFieldFilter(VisibilityFilter.ALL).create();
	}

	public static GensonConfiguration getInstance() {
		return instance;
	}

	public Genson getGenson() {
		return genson;
	}
}
