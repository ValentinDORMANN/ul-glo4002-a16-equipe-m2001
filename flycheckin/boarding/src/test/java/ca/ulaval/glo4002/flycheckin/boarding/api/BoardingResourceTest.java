package ca.ulaval.glo4002.flycheckin.boarding.api;

import static org.junit.Assert.*;

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
	public final String RESERVATION_DATE = "2016-01-31";

	@Before
	public void initialization() {
		boardingResource = new BoardingResource();
		jsonBoarding.put("passenger_hash", PASSENGER_HASH);
		jsonBoarding.put("by", Integer.toString(AGENT_ID));

		jsonPassenger.put("fullname", PASSENGER_FULLNAME);
		jsonPassenger.put("passport_number", PASSENGER_PASSPORT);
		jsonPassenger.put("passenger_hash", PASSENGER_HASH);
		// TODO date
	}

	@Test(expected = Exception.class)
	public void givenPostWithMissingPassengerInfoThenThrowException() {
		// Given
		jsonBoarding.remove("passenger_hash");

		// When
		boardingResource.validateJsonBoarding(jsonBoarding);
	}

	@Test(expected = Exception.class)
	public void givenPostWithMissingAgentIdThenThrowException() {
		// Given
		jsonBoarding.remove("by");

		// When
		boardingResource.validateJsonBoarding(jsonBoarding);
	}

	@Test
	public void givenValidBoardingMessageWhenValidThenReturnTrue() throws RuntimeException {
		assertTrue(boardingResource.validateJsonBoarding(jsonBoarding));
	}

	@Test(expected = Exception.class)
	public void givenJsonWithEmptyFullNameWhenValidateThenReturnException() {
		// Given
		jsonPassenger.remove("fullname");

		// When
		boardingResource.validateJsonPassenger(jsonPassenger);
	}

	@Test(expected = Exception.class)
	public void givenJsonWithEmptyPassportNumberWhenValidateThenReturnException() {
		// Given
		jsonPassenger.remove("passport_number");

		// When
		boardingResource.validateJsonPassenger(jsonPassenger);
	}

	@Test(expected = Exception.class)
	public void givenJsonWithEmptyPassengerHashWhenValidateThenReturnException() {
		// Given
		jsonPassenger.remove("passenger_hash");

		// When
		boardingResource.validateJsonPassenger(jsonPassenger);
	}

	// TODO date

	@Test
	public void givenValidAnswerThenValidationShouldReturnTrue() throws RuntimeException {
		assertTrue(boardingResource.validateJsonPassenger(jsonPassenger));
	}
}