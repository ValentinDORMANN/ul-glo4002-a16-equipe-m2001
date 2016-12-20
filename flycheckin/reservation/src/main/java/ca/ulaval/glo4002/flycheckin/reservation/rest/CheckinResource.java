package ca.ulaval.glo4002.flycheckin.reservation.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.glo4002.flycheckin.reservation.domain.CheckinService;
import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

@Path("/checkins")
public class CheckinResource {

  private static CheckinInMemory checkinInMemory = new CheckinInMemory();
  private static HibernateReservation hibernateReservation;
  private static CheckinService checkinService;

  public CheckinResource() {
    hibernateReservation = new HibernateReservation();
    checkinService = new CheckinService(checkinInMemory, hibernateReservation);
  }

  public CheckinResource(CheckinService checkinService) {
    CheckinResource.checkinService = checkinService;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createCheckin(@Context UriInfo uriInfo, CheckinDto checkinDto) {
    try {
      int checkinId = checkinService.saveCheckin(checkinDto);
      URI url = createUrlToGetCheckin(uriInfo, checkinId);
      return Response.status(Status.CREATED).location(url).build();
    } catch (NotFoundPassengerException ex) {
      return Response.status(Status.NOT_FOUND).build();
    } catch (ReservationModuleException | URISyntaxException ex) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  private URI createUrlToGetCheckin(UriInfo uriInfo, int checkinId) throws URISyntaxException {
    String location = uriInfo.getBaseUri().toString() + "checkins/" + Integer.toString(checkinId);
    return new URI(location);
  }
}