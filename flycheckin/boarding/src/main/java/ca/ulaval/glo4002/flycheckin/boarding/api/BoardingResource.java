package ca.ulaval.glo4002.flycheckin.boarding.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

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

	public boolean validatePassengerHash(String passengerHash) {
		return passengerHash.matches("^[A-Za-z0-9]+/[A-Za-z0-9]+$");
	}

	public boolean validateAgentId(String agentId) {
		return agentId.matches("^[0-9]+$");
	}
}