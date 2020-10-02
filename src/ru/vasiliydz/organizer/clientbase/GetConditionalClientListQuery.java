package ru.vasiliydz.organizer.clientbase;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a {@link Query Query} that requests a list of {@link Client Clients} from {@link ClientBase
 * ClientBase} by some condition
 */
public class GetConditionalClientListQuery implements Query {
	private final Matcher<Client> matcher;

	/**
	 * Constructor
	 * @param matcher object that represents condition to choose {@link Client Clients}
	 */
	public GetConditionalClientListQuery(Matcher<Client> matcher) {
		this.matcher = matcher;
	}

	@Override
	public ClientListResponse applyToBase(ClientBase base) {
		List<Client> clientList = new ArrayList<>();
		for (Client client : base.getClientList()) {
			if (matcher.matches(client)) {
				clientList.add(client);
			}
		}
		return new ClientListResponse(clientList, base.getFieldList());
	}
}
