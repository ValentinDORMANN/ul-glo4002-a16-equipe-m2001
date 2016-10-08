package ca.ulaval.glo4002.flycheckin.boarding.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Services;

@Path("/checkins")
@Produces(MediaType.APPLICATION_JSON)
public class BoardingResource {

	private static final String RESERVATION_SERVER = "localhost:8888";
	private Services services = new Services();
	private JSONObject json;

	// TODO
	@POST
	public Response getBoarding(String boardingRequest) {
		this.json = new JSONObject(boardingRequest);
		if (!validateJsonBoarding(json)) {
			return Response.status(400).build();
		} else {
			String passengerHash = json.getString("passenger_hash");
			String url = RESERVATION_SERVER + "/reservation/passengerInfo/" + passengerHash;
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);

			// HttpResponse response = httpClient.execute(request);
			return Response.ok(json.getString("passenger_hash").toString()).build();
		}
	}

	/*
	 * @POST public Response getReservationResponse(@Context UriInfo uriInfo,
	 * String reservationResponse) { this.json = new
	 * JSONObject(reservationResponse); if
	 * (!validatePassengerHash(json.getString("passenger_hash"))) { return
	 * Response.status(404).build(); } else if (!validateJsonPassenger(json)) {
	 * return Response.status(400).build(); } else { int checkinId =
	 * this.services.createBoarding(json); return
	 * Response.ok(uriInfo.getBaseUri().toString() + "checkins/" +
	 * checkinId).build(); } }
	 */

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
		return validateFullname(fullname) && validatePassportNumber(passportNumber) && validatePassengerHash(hash);
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
		return fullname.matches("^[A-Z][a-z]+([-. ][A-Z][a-z]+)* [A-Z]+$");
	}

}