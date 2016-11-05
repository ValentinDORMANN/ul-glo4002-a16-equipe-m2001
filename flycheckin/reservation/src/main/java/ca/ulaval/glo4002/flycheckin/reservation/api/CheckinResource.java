package ca.ulaval.glo4002.flycheckin.reservation.api;

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

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.CheckinDto;
import ca.ulaval.glo4002.flycheckin.reservation.domain.CheckinService;
import ca.ulaval.glo4002.flycheckin.reservation.exception.FlyCheckinApplicationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;

@Path("/checkins")
public class CheckinResource {

  private CheckinService checkinService;

  public CheckinResource() {
    this.checkinService = new CheckinService();
  }

  public CheckinResource(CheckinService checkinService) {
    this.checkinService = checkinService;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createCheckin(@Context UriInfo uriInfo, CheckinDto checkinDto) {
    try {
      int checkinId = this.checkinService.saveCheckin(checkinDto);
      String location = createUrlToGetCheckin(uriInfo, checkinId);
      URI url = new URI(location);
      return Response.status(Status.CREATED).location(url).build();
    } catch (URISyntaxException e) {
      return Response.status(Status.CREATED).build();
    } catch (NotFoundPassengerException ex) {
      return Response.status(Status.NOT_FOUND).build();
    } catch (FlyCheckinApplicationException ex) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  private String createUrlToGetCheckin(UriInfo uriInfo, int checkinId) {
    return uriInfo.getBaseUri().toString() + "checkins/" + Integer.toString(checkinId);
  }

}
