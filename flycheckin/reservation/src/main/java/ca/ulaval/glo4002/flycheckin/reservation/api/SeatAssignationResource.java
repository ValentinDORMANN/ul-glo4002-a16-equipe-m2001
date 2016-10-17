package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Services;
import javassist.NotFoundException;

@Path("/seat-assignations")
public class SeatAssignationResource {

	private Services service = new Services();

	@POST
	public Response setSeatAssignation(String stringJsonRequest) {
		JSONObject jsonRequest = new JSONObject(stringJsonRequest);
		int number_assignation;
		try {
			number_assignation = this.service.setSeatAssignation(jsonRequest.getString("passenger_hash"),
					jsonRequest.getString("mode"));
			return Response.status(201).entity(number_assignation).build();
		} catch (NotFoundException e) {
			return Response.status(404).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}

	}

}
