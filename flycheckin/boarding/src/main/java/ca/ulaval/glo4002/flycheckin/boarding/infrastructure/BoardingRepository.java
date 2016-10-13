package ca.ulaval.glo4002.flycheckin.boarding.infrastructure;

import java.util.HashMap;
import java.util.Map;

public class BoardingRepository {

	public static Map<Integer, String> boardingPassengersList = new HashMap<Integer, String>();
	private static int nbInstance = 1000;
	private static final int BOARDING_NOT_FOUND = 0;

	public int saveNewBoarding(String passengerHash) {
		boolean checkinDone = boardingPassengersList.containsValue(passengerHash);
		if (!checkinDone) {
			boardingPassengersList.put(nbInstance, passengerHash);
			nbInstance += 1;
			return nbInstance;
		} else
			return BOARDING_NOT_FOUND;
	}
}
