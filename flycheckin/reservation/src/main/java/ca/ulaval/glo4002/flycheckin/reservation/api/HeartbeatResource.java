package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;



import ca.ulaval.glo4002.flycheckin.reservation.domain.Heartbeat;

@Path("/heartbeat")
@Produces(MediaType.APPLICATION_JSON)
public class HeartbeatResource {

	@GET
	public Heartbeat beat(@QueryParam("token") String token) {
		return new Heartbeat(token);
	}
}