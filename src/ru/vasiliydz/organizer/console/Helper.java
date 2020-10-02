package ru.vasiliydz.organizer.console;

import java.util.TreeMap;
import java.util.Map;

/**
 * Object that answers on help commands
 */
public class Helper {

	private final String generalHelp;

	private final Map<String, String> commandHelp;

	public Helper() {
		commandHelp = new TreeMap<>();
		commandHelp.put("help", "");
		commandHelp.put("insert",
				"Usage: insert\nInserts a new client to database"
		);
		commandHelp.put("update",
				"Usage: update <clientId> <fieldName>\nUpdates the field of the given client"
		);
		commandHelp.put("delete",
				"Usage: delete <clientId>\nRemoves the client with the given id"
		);
		commandHelp.put("list",
				"Usage1: list\nPrints the full list of clients in the base\n"
				+ "Usage2: list <field1> <field2>...\nPrints list of clients sorted by given fields"
		);
		commandHelp.put("find",
				"Usage: find <phoneNumber>\nPrints list of clients having given phone number"
		);
		commandHelp.put("exit",
				"Usage: exit\nTerminates this application");
		StringBuilder generalHelpBuilder = new StringBuilder();
		generalHelpBuilder.append("Print \"help <command>\" to get command's usage\n" +
				"Possible commands:");
		for (String command : commandHelp.keySet()) {
			generalHelpBuilder.append(" ").append(command);
		}
		generalHelp = generalHelpBuilder.toString();
		commandHelp.put("help", generalHelp);
	}

	/**
	 * Returns general help
	 * @return general help string
	 */
	public String getHelp() {
		return generalHelp;
	}

	/**
	 * Returns specific help about given command
	 * @param command command to get help about
	 * @return specific help string or general help if helper does not know this command or command is
	 * "help"
	 */
	public String getHelp(String command) {
		String ret = commandHelp.get(command);
		if (ret == null) {
			return generalHelp;
		}
		return ret;
	}
}
