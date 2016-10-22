package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import ca.ulaval.glo4002.flycheckin.reservation.domain.*;

@Path("/heartbeat")
@Consumes(MediaType.APPLICATION_JSON)
public class HeartbeatResource {

  @GET
  public Heartbeat beat(@QueryParam("token") String token) {
    return new Heartbeat(token);
  }
}