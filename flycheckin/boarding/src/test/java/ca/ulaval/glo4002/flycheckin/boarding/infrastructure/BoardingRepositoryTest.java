package ca.ulaval.glo4002.flycheckin.boarding.infrastructure;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.BoardingPassenger;

public class BoardingRepositoryTest {
	private final int CHECKIN_DONE_RETURN = 0;
	BoardingPassenger boardingPassengerTst;
	private BoardingRepository boardingRepo;

	@Before
	public void initiateTest() throws ParseException {
		boardingRepo = new BoardingRepository();
		boardingPassengerTst = new BoardingPassenger("JOE PATER", "B2F1J4L5", "HACH_PASSENGER");
		boardingRepo.saveNewBoarding(boardingPassengerTst);
	}

	@Test
	public void WhenSavedNewBoardingThenReturnCheckinId() {
		// Then
		assertTrue(BoardingRepository.boardingPassengersList.containsValue(boardingPassengerTst));
	}

	@Test
	public void WhenSavedNewBoardingTwoTimesThenReturnNull() {
		// When
		int checkinDone = boardingRepo.saveNewBoarding(boardingPassengerTst);

		// Then
		assertEquals(CHECKIN_DONE_RETURN, checkinDone);
	}

}