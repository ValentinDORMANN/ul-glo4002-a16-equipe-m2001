package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/seat-assignations")
public class SeatAssignationResource {

	@POST
	public Response createBooking(JSONObject jsonRequest) {
		return Response.status(400).entity(jsonRequest).build();
	}

}
