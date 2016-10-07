package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import javassist.NotFoundException;

public class BoardingPassengerTest {
	private String fullname = "JOE";
	private String passportNumber = "B10044009";
	private String hash = "hashedpassenger";

	/*
	 * @Before public void InitializeBoardingPassenger() throws ParseException {
	 * BoardingPassenger boardingPassenger = new BoardingPassenger(fullname,
	 * passportNumber, hash); }
	 */
	@Test
	public void GivenValidPassengerHashWhenGetBoardingPassengerHashThenReturnCorrectHash() throws NotFoundException {

		// When
		BoardingPassenger boardingPassenger = new BoardingPassenger(fullname, passportNumber, hash);
		// Then
		assertEquals(hash, boardingPassenger.getHash());
	}

}