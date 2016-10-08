package ca.ulaval.glo4002.flycheckin.boarding.api;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class BoardingResourceTest {
	private BoardingResource boardingResource;
	private JSONObject mockJson = mock(JSONObject.class);
	private JSONObject mockJsonWrongDate = mock(JSONObject.class);

	@Before
	public void initialization() {
		boardingResource = new BoardingResource();
	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidatePassengerHash() {
		willThrow(Exception.class).given(boardingResource)
				.validatePassengerHash(mockJsonWrongDate.getString("passengerHash"));
		boardingResource.validatePassengerHash(mockJsonWrongDate.getString("passengerHash"));
	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidateAgentId() {
		willThrow(Exception.class).given(boardingResource).validateAgentId(mockJsonWrongDate.getString("agentId"));
		boardingResource.validateAgentId(mockJsonWrongDate.getString("agentId"));

	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidatePassPortNumber() {
		willThrow(Exception.class).given(boardingResource)
				.validatePassportNumber(mockJsonWrongDate.getString("passportNumber"));
		boardingResource.validatePassportNumber(mockJsonWrongDate.getString("passportNumber"));

	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidateFullName() {
		willThrow(Exception.class).given(boardingResource).validateFullname(mockJsonWrongDate.getString("fullname"));
		boardingResource.validateFullname(mockJsonWrongDate.getString("fullname"));
	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidateJsonPassenger() {
		willThrow(Exception.class).given(boardingResource).validateJsonPassenger(mockJson);
		boardingResource.validateJsonPassenger(mockJson);

	}

}