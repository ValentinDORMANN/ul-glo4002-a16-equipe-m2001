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

	public String receptionHashPassenger(JSONObject json) throws JSONException {
		return json.getString("passenger_hash").trim();
	}

	public BoardingPassenger receptionBoardingPassenger(JSONObject json) throws JSONException {
		String fullname = json.getString("fullname").trim();
		String passportNumber = json.getString("passeport_number").trim();
		String hash = json.getString("passenger_hash").trim();
		return new BoardingPassenger(fullname, passportNumber, hash);
	}

	public int createBoarding(JSONObject json) throws NotFoundException {
		try {
			BoardingPassenger boardingPassenger = receptionBoardingPassenger(json);
			return this.boardingRepository.saveNewBoarding(boardingPassenger);
		} catch (Exception e) {
			throw new NotFoundException("404 NOT FOUND");// return 0;
		}
	}
}
