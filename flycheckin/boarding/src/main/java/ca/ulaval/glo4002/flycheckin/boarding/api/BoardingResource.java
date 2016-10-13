package ca.ulaval.glo4002.flycheckin.boarding.api;

import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Services;
import javassist.NotFoundException;

@Path("/checkins")
@Produces(MediaType.APPLICATION_JSON)
public class BoardingResource {
	private static final String RESERVATION_SERVER = "http://localhost:8888";
	private Services services = new Services();
	private JSONObject json;

	/*
	 * @POST public Response getBoarding(@Context UriInfo uriInfo, String
	 * boardingRequest) throws ClientProtocolException, IOException { this.json
	 * = new JSONObject(boardingRequest); if (!validateJsonBoarding(json)) {
	 * return Response.status(400).build(); } else { String passengerHash =
	 * json.getString("passenger_hash"); String url = RESERVATION_SERVER +
	 * "/reservations/passengerInfo/" + passengerHash; HttpClient httpClient =
	 * HttpClientBuilder.create().build(); HttpGet request = new HttpGet(url);
	 * HttpResponse response = httpClient.execute(request); HttpEntity entity =
	 * response.getEntity(); JSONObject passenger = new
	 * JSONObject(EntityUtils.toString(entity, "UTF-8")); if
	 * (!validatePassengerHash(json.getString("passenger_hash"))) { return
	 * Response.status(404).build(); } else { if
	 * (!validateJsonPassenger(passenger) ||
	 * this.services.createBoarding(passenger) == 0) { return
	 * Response.status(400).build(); } else { int checkinId =
	 * this.services.createBoarding(passenger); return
	 * Response.ok(uriInfo.getBaseUri().toString() + "checkins/" +
	 * checkinId).build(); } } } }
	 */

	@POST
	public Response getBoarding(@Context UriInfo uriInfo, String boardingRequest)
			throws ClientProtocolException, IOException {
		try {
			this.json = new JSONObject(boardingRequest);
			System.out.println("S01: Initial");
			System.out.println(json.getString("passenger_hash"));
			if (!validateJsonBoarding(json)) {
				System.out.println("E02: Invalid Post");
				throw new JSONException("Invalid Post");
			}
			System.out.println("S02: Invalid Post");
			String passengerHash = json.getString("passenger_hash");
			String url = RESERVATION_SERVER + "/reservations/passengerInfo/" + passengerHash;
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			JSONObject passenger = new JSONObject(EntityUtils.toString(entity, "UTF-8"));
			System.out.println(passenger.toString());
			if (!validatePassengerHash(passenger.getString("passenger_hash"))) {
				System.out.println("E03: Passenger with passengerHash not found");
				throw new NotFoundException("passenger with passengerHash not found");
			}
			System.out.println("S03: Passenger with passengerHash not found");
			if (!validateJsonPassenger(passenger)) {
				System.out.println("E04: Information missing");
				throw new JSONException("Information missing");
			}
			System.out.println("S04: Information missing");
			if (this.services.createBoarding(passenger) == 0) {
				System.out.println("E05: Boarding already done");
				throw new JSONException("Boarding already done");
			}
			System.out.println("S05: Boarding already done");
			int checkinId = this.services.createBoarding(passenger);
			System.out.println("S06: Done");
			return Response.ok(uriInfo.getBaseUri().toString() + "checkins/" + checkinId).build(); // save
		} catch (JSONException e) {
			// info missing | invalid POST | boarding already done
			return Response.status(400).build();
		} catch (NotFoundException e) {
			// passenger with passengerHash not found
			return Response.status(404).build();
		}
	}

	public boolean validateJsonBoarding(JSONObject json) {
		String passengerHash = json.getString("passenger_hash").trim();
		String agentId = json.getString("by").trim();
		return validatePassengerHash(passengerHash) && validateAgentId(agentId);
	}

	// TODO add date
	public boolean validateJsonPassenger(JSONObject json) {
		String fullname = json.getString("fullname").trim();
		String passportNumber = json.getString("passport_number").trim();
		String hash = json.getString("passenger_hash").trim();
		return validateFullname(fullname) && validatePassportNumber(passportNumber) && validatePassengerHash(hash);
	}

	private boolean validatePassengerHash(String passengerHash) {
		return passengerHash.matches("^[A-Za-z0-9]+:[A-Za-z0-9]+$");
	}

	private boolean validateAgentId(String agentId) {
		return agentId.matches("^[0-9]+$");
	}

	private boolean validatePassportNumber(String passportNumber) {
		return passportNumber.matches("^[A-Z0-9]{8,9}$");
	}

	private boolean validateFullname(String fullname) {
		return fullname.matches("^[A-Z][a-z]+([-. ][A-Z][a-z]+)* [A-Z]+$");
	}

	// TODO
	private boolean validateDate(String date) {
		return true;
	}
}