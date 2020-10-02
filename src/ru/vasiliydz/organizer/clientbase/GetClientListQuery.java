package ru.vasiliydz.organizer.clientbase;

/**
 * This is a {@link Query Query} that requests the full list of {@link Client Clients} from
 * {@link ClientBase ClientBase}
 */
public class GetClientListQuery implements Query {

	@Override
	public ClientListResponse applyToBase(ClientBase base) {
		return new ClientListResponse(base.getClientList(), base.getFieldList());
	}
}
