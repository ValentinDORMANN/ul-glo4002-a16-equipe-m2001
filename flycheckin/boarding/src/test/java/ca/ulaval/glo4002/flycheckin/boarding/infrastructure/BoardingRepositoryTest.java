package ca.ulaval.glo4002.flycheckin.boarding.infrastructure;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.BoardingPassenger;
import javassist.NotFoundException;

public class BoardingRepositoryTest {
	private final int CHECKIN_DONE_RETURN = 0;
	BoardingPassenger boardingPassengerTest;
	private BoardingRepository boardingRepository;

	@Before
	public void initiateTest() throws ParseException, NotFoundException {
		boardingRepository = new BoardingRepository();
		boardingPassengerTest = new BoardingPassenger("JOE PATER", "B2F1J4L5", "HACH_PASSENGER");
		boardingRepository.saveNewBoarding(boardingPassengerTest);
	}

	@Test
	public void WhenSavedNewBoardingThenReturnCheckinId() {
		// Then
		assertTrue(BoardingRepository.boardingPassengersList.containsValue(boardingPassengerTest));
	}

	@Test
	public void WhenSavedNewBoardingTwoTimesThenReturnNull() {
		// When
		int checkinDone = boardingRepository.saveNewBoarding(boardingPassengerTest);
		assertEquals(checkinDone, 0);
	}

}