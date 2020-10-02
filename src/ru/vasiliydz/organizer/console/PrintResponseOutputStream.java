package ru.vasiliydz.organizer.console;

import ru.vasiliydz.organizer.clientbase.BaseResponse;
import ru.vasiliydz.organizer.clientbase.Client;
import ru.vasiliydz.organizer.clientbase.ClientListResponse;
import ru.vasiliydz.organizer.clientbase.ResponseOutputStream;

import java.io.PrintStream;
import java.util.List;

/**
 * Console implementation of {@link ResponseOutputStream ResponseOutputStream}
 */
public class PrintResponseOutputStream implements ResponseOutputStream {
	private final PrintStream printStream;

	/**
	 * Constructor
	 * @param printStream stream to write responses to
	 */
	public PrintResponseOutputStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void putResponse(BaseResponse response) {
		if (response instanceof ClientListResponse) {
			printSpecial((ClientListResponse)response);
		} else {
			printDefault(response);
		}
	}

	private void printDefault(BaseResponse response) {
		printStream.println(response.toString());
	}

	private void printSpecial(ClientListResponse clientListResponse) {
		List<String> fieldsToPrint = clientListResponse.getFieldsToShow();
		printStream.println("List of clients:");
		for (Client client : clientListResponse.getClientList()) {
			printClient(client, fieldsToPrint);
			printStream.println();
		}
		printStream.println("Printed "
				+ clientListResponse.getClientList().size() + " clients");

	}

	private void printClient(Client client, List<String> fieldsToPrint) {
		printStream.println("ClientId: " + client.getId());
		for (String fieldName : fieldsToPrint) {
			System.out.println(fieldName + ": " + client.getField(fieldName).getStringValue());
		}
	}
}
