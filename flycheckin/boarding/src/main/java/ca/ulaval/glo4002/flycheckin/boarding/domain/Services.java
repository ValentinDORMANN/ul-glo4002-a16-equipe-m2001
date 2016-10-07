package ca.ulaval.glo4002.flycheckin.boarding.domain;

import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.boarding.infrastructure.BoardingRepository;

public class Services {

	private BoardingRepository boardingRepository;

	public Services() {
		this.boardingRepository = new BoardingRepository();
	}

	public BoardingPassenger receptionBookingPassenger(JSONObject json) {
		String fullname = json.getString("fullname").trim();
		String passportNumber = json.getString("passeport_number").trim();
		String hash = json.getString("passenger_hash").trim();
		return new BoardingPassenger(fullname, passportNumber, hash);
	}

	public String createBoarding(JSONObject json) {
		BoardingPassenger boardingPassenger = receptionBookingPassenger(json);
		return this.boardingRepository.saveNewBoarding(boardingPassenger);
	}
}
