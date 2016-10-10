package ca.ulaval.glo4002.flycheckin.boarding.infrastructure;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import javassist.NotFoundException;

public class BoardingRepositoryTest {
	private final int CHECKIN_DONE_RETURN = 0;
	private String passengerHash;
	private BoardingRepository boardingRepository;

	@Before
	public void initiateTest() throws ParseException, NotFoundException {
		boardingRepository = new BoardingRepository();
		passengerHash = "HACH_PASSENGER";
		boardingRepository.saveNewBoarding(passengerHash);
	}

	@Test
	public void WhenSavedNewBoardingThenReturnCheckinId() {
		// Then
		assertTrue(BoardingRepository.boardingPassengersList.containsValue(passengerHash));
	}

	@Test
	public void WhenSavedNewBoardingTwoTimesThenReturnNull() {
		// When
		int checkinDone = boardingRepository.saveNewBoarding(passengerHash);
		assertEquals(CHECKIN_DONE_RETURN, checkinDone);
	}

}