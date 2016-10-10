package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.infrastructure.BoardingRepository;

public class ServicesTest {
	private final String PASSENGER_FULL_NAME = "John DOE";
	private final String PASSENGER_HASH = "B0074584:44444";
	private final String PASSEPORT_NUMBER = "B0074584";
	private final int CHECKIN_NUMBER = 1200;
	private JSONObject json;
	private BoardingRepository mockRepository = mock(BoardingRepository.class);
	private Services service;
	private BoardingPassenger boardingPassenger;

	@Before
	public void initiateTest() {
		json = new JSONObject();
		json.put("fullname", PASSENGER_FULL_NAME);
		json.put("passenger_hash", PASSENGER_HASH);
		json.put("passeport_number", PASSEPORT_NUMBER);
		service = new Services();
		boardingPassenger = service.receptionBoardingPassenger(json);
		when(mockRepository.saveNewBoarding(boardingPassenger)).thenReturn(CHECKIN_NUMBER);
	}

	@Test
	public void WhenCreatingBoardingThenVerifySave() {
		// When
		service.createBoarding(json);

		// Then
		verify(mockRepository).saveNewBoarding(boardingPassenger);
	}

	@Test
	public void WhenCreateBoardingThenReturnCheckinNumber() {
		// When
		int checkin = service.createBoarding(json);

		// Then
		assertEquals(CHECKIN_NUMBER, checkin);
	}
}
