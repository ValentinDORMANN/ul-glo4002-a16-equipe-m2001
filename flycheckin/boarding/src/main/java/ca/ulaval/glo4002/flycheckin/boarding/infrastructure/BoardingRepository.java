package ca.ulaval.glo4002.flycheckin.boarding.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.BoardingPassenger;

public class BoardingRepository {

	public static Map<Integer, BoardingPassenger> boardingPassengersList = new HashMap<Integer, BoardingPassenger>();

	public int saveNewBoarding(BoardingPassenger boardingPassenger) {
		int checkinId = boardingPassenger.getCheckinId();
		boardingPassengersList.put(checkinId, boardingPassenger);
		return checkinId;
	}
}
