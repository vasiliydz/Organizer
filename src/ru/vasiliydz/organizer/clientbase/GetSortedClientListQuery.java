package ru.vasiliydz.organizer.clientbase;

import java.util.Comparator;
import java.util.List;

/**
 * This is a {@link Query Query} that requests a list of {@link Client Clients} from {@link ClientBase
 * ClientBase} sorted by list of {@link Field Field} names
 */
public class GetSortedClientListQuery implements Query {
	private final Comparator<Client> comparator;

	/**
	 * Constructor
	 * @param fieldsToSortBy list of {@link Field Field} names to sort by
	 */
	public GetSortedClientListQuery(List<String> fieldsToSortBy) {
		comparator = (client1, client2) -> {
			for (String fieldName : fieldsToSortBy) {
				Field field1 = client1.getField(fieldName);
				Field field2 = client2.getField(fieldName);
				if (!(field1 instanceof ComparableField && field2 instanceof ComparableField)) {
					throw new ClientBaseException("Field " + fieldName + " is not comparable");
				}
				ComparableField cField1 = (ComparableField) field1;
				ComparableField cField2 = (ComparableField) field2;
				int cmp = cField1.compareTo(cField2);
				if (cmp != 0) {
					return cmp;
				}
			}
			return 0;
		};
	}

	@Override
	public ClientListResponse applyToBase(ClientBase base) {
		List<Client> listOfClients = base.getClientList();
		listOfClients.sort(comparator);
		return new ClientListResponse(listOfClients, base.getFieldList());
	}
}
