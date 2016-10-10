package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.infrastructure.BoardingRepository;
import javassist.NotFoundException;

public class ServicesTest {
	private final String PASSENGER_FULL_NAME = "John DOE";
	private final String PASSENGER_HASH = "B0074584:44444";
	private final String PASSEPORT_NUMBER = "B0074584";
	private final int CHECKIN_NUMBER = 1200;
	private JSONObject json;
	private BoardingRepository mockBoardingRepository;
	private Services service;

	@Before
	public void initiateTest() {
		json = new JSONObject();
		json.put("fullname", PASSENGER_FULL_NAME);
		json.put("passenger_hash", PASSENGER_HASH);
		json.put("passeport_number", PASSEPORT_NUMBER);
		mockBoardingRepository = mock(BoardingRepository.class);
		service = new Services(mockBoardingRepository);
		when(mockBoardingRepository.saveNewBoarding(PASSENGER_HASH)).thenReturn(CHECKIN_NUMBER);
	}

	@Test
	public void WhenGetHashPassengerFromJsonThenReturnHashPassenger() {
		// When
		String passengerHash = service.receptionHashPassenger(json);

		// Then
		assertEquals(PASSENGER_HASH, passengerHash);
	}

	@Test
	public void WhenCreatingBoardingThenVerifySave() throws NotFoundException {
		// When
		service.createBoarding(json);

		// Then
		verify(mockBoardingRepository).saveNewBoarding(PASSENGER_HASH);
	}

	@Test
	public void WhenCreateBoardingThenReturnCheckinNumber() throws NotFoundException {
		// When
		int checkin = service.createBoarding(json);

		// Then
		assertEquals(CHECKIN_NUMBER, checkin);
	}
}
