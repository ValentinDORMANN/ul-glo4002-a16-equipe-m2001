package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Services;

@Path("/reservations/{reservation_number}")
public class BookingPassengerResource {

	@GET
	public Response getNumberResevation(@Context HttpServletRequest request) {
		String urlArrayWhichContainsNumberReservation[] = request.getRequestURL().toString().split("/");
		Services service = new Services();
		try {
			JSONObject jsonObject = service.getReservation(Integer.parseInt(
					urlArrayWhichContainsNumberReservation[urlArrayWhichContainsNumberReservation.length - 1]));
			return Response.ok(jsonObject.toString()).build();
		} catch (Exception e) {
			return Response.status(404).build();
		}
	}

}