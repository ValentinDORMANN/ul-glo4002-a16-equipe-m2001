package ca.ulaval.glo4002.flycheckin.boarding.domain;

import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.boarding.infrastructure.BoardingRepository;

public class Services {

	private BoardingRepository boardingRepository;

	public Services() {
		this.boardingRepository = new BoardingRepository();
	}

	public String receptionHashPassenger(JSONObject json) {
		return json.getString("passenger_hash").trim();
	}

	public BoardingPassenger receptionBoardingPassenger(JSONObject json) {
		String fullname = json.getString("fullname").trim();
		String passportNumber = json.getString("passeport_number").trim();
		String hash = json.getString("passenger_hash").trim();
		return new BoardingPassenger(fullname, passportNumber, hash);
	}

	public int createBoarding(JSONObject json) {
		BoardingPassenger boardingPassenger = receptionBoardingPassenger(json);
		return this.boardingRepository.saveNewBoarding(boardingPassenger);
	}
}
