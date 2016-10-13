
package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Services;

@Path("/reservations/passengerInfo/{passengerHash}")
@Produces(MediaType.APPLICATION_JSON)
public class PassengerRessource {

	@GET
	public Response getPassengerInfoFromHash(@Context HttpServletRequest request) {
		try {
			String urlArrayWhichContainsPassengerHash[] = request.getRequestURL().toString().split("/");
			Services service = new Services();
			JSONObject jsonObject = service.getPassengerInfoFromHash(
					urlArrayWhichContainsPassengerHash[urlArrayWhichContainsPassengerHash.length - 1]);
			return Response.ok(jsonObject.toString()).build();
		} catch (Exception e) {
			return Response.status(404).build();
		}
	}
}