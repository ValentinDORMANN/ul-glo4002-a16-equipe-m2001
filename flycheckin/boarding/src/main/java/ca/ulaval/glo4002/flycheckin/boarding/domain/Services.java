package ca.ulaval.glo4002.flycheckin.boarding.domain;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.boarding.infrastructure.BoardingRepository;
import javassist.NotFoundException;

public class Services {

	private BoardingRepository boardingRepository;

	public Services() {
		this.boardingRepository = new BoardingRepository();
	}

	public Services(BoardingRepository boardingRepository) {
		this.boardingRepository = boardingRepository;
	}

	public String receptionHashPassenger(JSONObject json) throws JSONException {
		return json.getString("passenger_hash").trim();
	}

	public int createBoarding(JSONObject json) throws NotFoundException {
		String passengerHash = receptionHashPassenger(json);
		return this.boardingRepository.saveNewBoarding(passengerHash);
	}
}
