package ca.ulaval.glo4002.flycheckin.boarding.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.BoardingPassenger;

public class BoardingRepository {

	public static Map<String, BoardingPassenger> boardingPassengersList = new HashMap<String, BoardingPassenger>();

	public String saveNewBooking(BoardingPassenger boardingPassenger) {
		String hash = boardingPassenger.getHash();
		boardingPassengersList.put(hash, boardingPassenger);
		return hash;
	}

}
