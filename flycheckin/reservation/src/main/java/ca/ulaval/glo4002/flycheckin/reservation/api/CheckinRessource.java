package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.CheckinDto;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

@Path("/checkins")
public class CheckinRessource {

  @POST
  @Consumes("application/json")
  public Response createReserversation(@Context UriInfo uriInfo, CheckinDto checkinDto) {
    try {
      int checkinId = 1;
      String location = createUrlforGetCheckin(uriInfo, checkinId);
      return Response.status(Status.CREATED).entity(location).build();
    } catch (NotFoundReservationException ex) {
      return Response.status(Status.NOT_FOUND).build();
    } catch (Exception ex) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  private String createUrlforGetCheckin(UriInfo uriInfo, int checkinId) {
    return uriInfo.getBaseUri().toString() + "checkins/" + Integer.toString(checkinId);
  }

}
