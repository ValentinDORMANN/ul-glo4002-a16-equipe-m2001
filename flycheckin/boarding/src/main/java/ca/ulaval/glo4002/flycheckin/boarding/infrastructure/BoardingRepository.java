package ca.ulaval.glo4002.flycheckin.boarding.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.BoardingPassenger;

public class BoardingRepository {

	public static Map<Integer, BoardingPassenger> boardingPassengersList = new HashMap<Integer, BoardingPassenger>();
	private static int nbInstance = 1000;
	private static final int BOARDING_NOT_FOUND = 0;

	public int saveNewBoarding(BoardingPassenger boardingPassenger) {
		boolean checkinDone = boardingPassengersList.containsValue(boardingPassenger);
		if (!checkinDone) {
			boardingPassengersList.put(nbInstance, boardingPassenger);
			nbInstance += 1;
			return nbInstance;
		} else
			return BOARDING_NOT_FOUND;
	}
}
