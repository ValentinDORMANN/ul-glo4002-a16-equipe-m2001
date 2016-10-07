package ca.ulaval.glo4002.flycheckin.boarding.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.boarding.domain.BoardingPassenger;

@Path("/checkins")
@Produces(MediaType.APPLICATION_JSON)
public class BoardingResource {

	private JSONObject json;

	public BoardingResource(JSONObject json) {
		this.json = json;
	}

	@POST
	public void createBooking(String message) {
	}

	public boolean validateJsonBoarding(JSONObject json) {
		String passengerHash = json.getString("passenger_hash").trim();
		String agentId = json.getString("by").trim();
		boolean isOK = validatePassengerHash(passengerHash) && validateAgentId(agentId);
		return isOK;
	}

	public boolean validateJsonPassenger(JSONObject json) {
		String fullname = json.getString("fullname").trim();
		String passportNumber = json.getString("passeport_number").trim();
		String hash = json.getString("passenger_number").trim();
		return (fullname.split(":").length == 2) && validateFullname(fullname) && validatePassportNumber(passportNumber)
				&& validatePassengerHash(hash);
	}

	public boolean validatePassengerHash(String passengerHash) {
		return passengerHash.matches("^[A-Za-z0-9]+:[A-Za-z0-9]+$");// passport:reservationNumber
	}

	public boolean validateAgentId(String agentId) {
		return agentId.matches("^[0-9]+$");
	}

	public boolean validatePassportNumber(String passportNumber) {
		return passportNumber.matches("^[A-Z0-9]{8,9}$");
	}

	public boolean validateFullname(String fullname) {
		return fullname.matches("^[A-Z][a-z]+([-. ][A-Z][a-z]+)*:[A-Z]+$");
	}

	// passengerHash format "passportNumber:reservationNumber"
	public JSONObject queryBookingPassenger(String passengerHash) {
		JSONObject jsonQuery = new JSONObject();
		jsonQuery.put("passenger_hash", passengerHash);
		return jsonQuery;
	}

	public BoardingPassenger receptionBookingPassenger(JSONObject json) {
		String fullname = json.getString("fullname").trim();
		String[] fullnameSplited = fullname.split(":");
		String firstname = fullnameSplited[0];
		String lastname = fullnameSplited[1];
		String passportNumber = json.getString("passeport_number").trim();
		String hash = json.getString("passenger_hash").trim();
		return new BoardingPassenger(firstname, lastname, passportNumber, hash);
	}

}