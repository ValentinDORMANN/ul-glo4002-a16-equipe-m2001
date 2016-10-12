package ca.ulaval.glo4002.flycheckin.boarding.api;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class BoardingResourceTest {

	private BoardingResource boardingResource;
	private JSONObject jsonBoarding = new JSONObject();
	private JSONObject jsonPassenger = new JSONObject();

	public final String PASSENGER_HASH = "7HTDEFRE:74563";
	public final int AGENT_ID = 10;
	public final String PASSENGER_FULLNAME = "Virgil VOILLOT";
	public final String PASSENGER_PASSPORT = "7HTDEFRE";
	public final String PASSENGER_NUMBER = "74563";
	public final String RESERVATION_DATE = "2016-01-31";

	@Before
	public void initialization() {
		boardingResource = new BoardingResource();
		jsonBoarding.put("passenger_hash", PASSENGER_HASH);
		jsonBoarding.put("by", Integer.toString(AGENT_ID));

		jsonPassenger.put("fullname", PASSENGER_FULLNAME);
		jsonPassenger.put("passeport_number", PASSENGER_PASSPORT);
		jsonPassenger.put("passenger_number", PASSENGER_NUMBER);
		// TODO date
	}

	@Test(expected = Exception.class)
	public void givenBoardingJsonMessageWhenReadThenValidatePassengerHash() {
		willThrow(Exception.class).given(boardingResource).validateJsonBoarding(jsonBoarding);
		jsonBoarding.remove("passenger_hash");

		boardingResource.validateJsonBoarding(jsonBoarding);
	}

	@Test(expected = Exception.class)
	public void givenBoardingJsonMessageWhenReadThenValidateAgentId() {
		willThrow(Exception.class).given(boardingResource).validateJsonBoarding(jsonBoarding);
		jsonBoarding.remove("by");

		boardingResource.validateJsonBoarding(jsonBoarding);
	}

	@Test
	public void givenInValidateBoardingMessageWhenHavingValideRequestThenVerify() throws RuntimeException {
		assertTrue(boardingResource.validateJsonBoarding(jsonBoarding));
	}

	@Test(expected = Exception.class)
	public void givenPassengerJsonMessageWhenReadThenValidateFullname() {
		willThrow(Exception.class).given(boardingResource).validateJsonPassenger(jsonPassenger);
		jsonPassenger.remove("fullname");

		boardingResource.validateJsonPassenger(jsonPassenger);
	}

	@Test(expected = Exception.class)
	public void givenPassengerJsonMessageWhenReadThenValidatePassportNumber() {
		willThrow(Exception.class).given(boardingResource).validateJsonPassenger(jsonPassenger);
		jsonPassenger.remove("passeport_number");

		boardingResource.validateJsonPassenger(jsonPassenger);
	}

	@Test(expected = Exception.class)
	public void givenPassengerJsonMessageWhenReadThenValidatePassengerNumber() {
		willThrow(Exception.class).given(boardingResource).validateJsonPassenger(jsonPassenger);
		jsonPassenger.remove("passenger_number");

		boardingResource.validateJsonPassenger(jsonPassenger);
	}

	// TODO date

	@Test
	public void givenInValidatePassengertMessageWhenHavingValideRequestThenVerify() throws RuntimeException {
		assertFalse(boardingResource.validateJsonPassenger(jsonPassenger));
	}
}