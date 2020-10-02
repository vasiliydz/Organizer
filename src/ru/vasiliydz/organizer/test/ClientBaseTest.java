package ru.vasiliydz.organizer.test;

import org.junit.Assert;
import org.junit.Test;
import ru.vasiliydz.organizer.clientbase.*;
import ru.vasiliydz.organizer.fields.StringField;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClientBaseTest {

	@Test(expected = ClientBaseException.class)
	public void updatingClientInEmptyBase() {
		// given
		String fieldName = "field";
		ClientBase emptyBase = createSimpleEmptyBase(fieldName);
		Query updateClientQuery = new UpdateClientQuery(1, fieldName,
				new StringField.Setter("newValue"));
		// when
		updateClientQuery.applyToBase(emptyBase);
		// then throws ClientBaseException
	}

	@Test(expected = ClientBaseException.class)
	public void deletingClientInEmptyBase() {
		// given
		ClientBase emptyBase = createSimpleEmptyBase("field");
		Query deleteClientQuery = new DeleteClientQuery(1);
		// when
		deleteClientQuery.applyToBase(emptyBase);
		// then throws ClientBaseException
	}

	@Test
	public void takingClientFieldWhenHeIsAddedToBase() {
		// given
		String fieldName = "field";
		ClientBase base = createSimpleEmptyBase(fieldName);
		String fieldValue = "someValue";
		new InsertClientQuery(new StringField.Setter(fieldValue)).applyToBase(base);
		// when
		String valueFromBase = new GetClientListQuery().applyToBase(base).getClientList().get(0)
				.getField(fieldName).getStringValue();
		// then
		Assert.assertEquals(fieldValue, valueFromBase);
	}

	@Test
	public void takingClientFieldWhenItHasBeenUpdated() {
		// given
		String fieldName = "field";
		ClientBase base = createSimpleEmptyBase(fieldName);
		OkClientCreatedResponse response =
				new InsertClientQuery(new StringField.Setter("someValue")).applyToBase(base);
		long clientId = response.getClientId();
		String newFieldValue = "newValue";
		new UpdateClientQuery(clientId, fieldName, new StringField.Setter(newFieldValue)).applyToBase(base);
		// when
		String valueFromBase = new GetClientListQuery().applyToBase(base).getClientList().get(0)
				.getField(fieldName).getStringValue();
		// then
		Assert.assertEquals(newFieldValue, valueFromBase);
	}

	@Test
	public void differentClientsAreReceivingDifferentIds() {
		// given
		ClientBase base = createSimpleEmptyBase("field");
		int numOfClients = 20;
		Set<Long> clientIds = new HashSet<>();
		// when
		for (int i = 0; i < numOfClients; i++) {
			OkClientCreatedResponse response =
					new InsertClientQuery(new StringField.Setter("someValue")).applyToBase(base);
			clientIds.add(response.getClientId());
		}
		// then
		Assert.assertEquals(numOfClients, clientIds.size());
	}

	@Test(expected = ClientBaseException.class)
	public void tryingToAccessClientAfterDeleting() {
		// given
		String fieldName = "field";
		ClientBase base = createSimpleEmptyBase(fieldName);
		OkClientCreatedResponse response =
				new InsertClientQuery(new StringField.Setter("someValue")).applyToBase(base);
		long clientId = response.getClientId();
		new DeleteClientQuery(clientId).applyToBase(base);
		// when
		new UpdateClientQuery(clientId, fieldName, new StringField.Setter("newValue"))
				.applyToBase(base);
		// then throws ClientBaseException
	}



	private ClientBase createSimpleEmptyBase(String fieldName) {
		return new ClientBase(new ClientBuilder(Collections.singletonList(
				new StringField.Builder(fieldName)
		)));
	}

}