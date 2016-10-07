package ca.ulaval.glo4002.flycheckin.boarding.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.BoardingPassenger;

public class BoardingRepository {

	public static Map<String, BoardingPassenger> boardingPassengersList = new HashMap<String, BoardingPassenger>();

	public String saveNewBoarding(BoardingPassenger boardingPassenger) {
		String hash = boardingPassenger.getHash();
		boardingPassengersList.put(hash, boardingPassenger);
		return hash;
	}

	public String passengerAlreadyChecked(BoardingPassenger boardingPassenger) {
		String passengerFoundHash = "0";
		String hash = boardingPassenger.getHash();
		for (int i = 0; i < boardingPassengersList.size(); i++) {
			if (boardingPassengersList.get(i).getHash().equals(hash)) {
				passengerFoundHash = hash;
				break;
			}
		}
		return passengerFoundHash;
	}
}
