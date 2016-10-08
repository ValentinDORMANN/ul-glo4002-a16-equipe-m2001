package ca.ulaval.glo4002.flycheckin.boarding.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.BoardingPassenger;

public class BoardingRepository {

	public static Map<Integer, BoardingPassenger> boardingPassengersList = new HashMap<Integer, BoardingPassenger>();
	static private int nbInstance = 1;

	public int saveNewBoarding(BoardingPassenger boardingPassenger) {
		boolean checkinDone = boardingPassengersList.containsValue(boardingPassenger);
		if (!checkinDone) {
			boardingPassengersList.put(nbInstance, boardingPassenger);
			nbInstance += 1;
			return nbInstance;
		} else
			return 0;
	}
}
