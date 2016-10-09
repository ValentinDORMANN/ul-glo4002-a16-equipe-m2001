package ca.ulaval.glo4002.flycheckin.boarding.api;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class BoardingResourceTest {

	private BoardingResource boardingResource;
	private JSONObject mockJson = mock(JSONObject.class);

	@Before
	public void initialization() {
		boardingResource = new BoardingResource();
	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidateJsonPassenger() {
		willThrow(Exception.class).given(boardingResource).validateJsonPassenger(mockJson);
		boardingResource.validateJsonPassenger(mockJson);
	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidateJsonBoarding() {
		willThrow(Exception.class).given(boardingResource).validateJsonBoarding(mockJson);
		boardingResource.validateJsonBoarding(mockJson);
	}

}