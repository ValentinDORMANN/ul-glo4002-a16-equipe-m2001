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
import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Services;

@Path("/checkins")
@Produces(MediaType.APPLICATION_JSON)
public class BoardingResource {
	private static final String RESERVATION_SERVER = "http://localhost:8888";
	private Services services = new Services();
	private JSONObject json;

	@POST
	public Response getBoarding(@Context UriInfo uriInfo, String boardingRequest)
			throws ClientProtocolException, IOException {
		this.json = new JSONObject(boardingRequest);
		if (!validateJsonBoarding(json)) { // post invalid
			return Response.status(400).build();
		} else {
			String passengerHash = json.getString("passenger_hash");
			String url = RESERVATION_SERVER + "/reservations/passengerInfo/" + passengerHash;
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			JSONObject passenger = new JSONObject(EntityUtils.toString(entity, "UTF-8"));
			if (!validatePassengerHash(json.getString("passenger_hash"))) {
				return Response.status(404).build();
			} else {
				if (!validateJsonPassenger(passenger) || this.services.createBoarding(passenger) == 0) {
					return Response.status(400).build();
				} else {
					int checkinId = this.services.createBoarding(passenger);
					return Response.ok(uriInfo.getBaseUri().toString() + "checkins/" + checkinId).build();
				}
			}
		}
	}

	public boolean validateJsonBoarding(JSONObject json) {
		String passengerHash = json.getString("passenger_hash").trim();
		String agentId = json.getString("by").trim();
		return validatePassengerHash(passengerHash) && validateAgentId(agentId);
	}

	public boolean validateJsonPassenger(JSONObject json) {
		String fullname = json.getString("fullname").trim();
		String passportNumber = json.getString("passeport_number").trim();
		String hash = json.getString("passenger_number").trim();
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
}