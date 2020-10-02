package ru.vasiliydz.organizer.clientbase;

class IdGenerator {
	private long lastId;

	IdGenerator() {
		lastId = 0;
	}

	long generateId() {
		lastId++;
		return lastId;
	}
}
