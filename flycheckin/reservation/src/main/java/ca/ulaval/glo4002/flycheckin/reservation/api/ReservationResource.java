package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.*;
import ca.ulaval.glo4002.flycheckin.reservation.domain.*;

@Path("/events/reservation-created")
public class ReservationResource {

  private ReservationService reservationService;

  public ReservationResource() {
    this.reservationService = new ReservationService();
  }

  public ReservationResource(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @POST
  @Consumes("application/json")
  public Response createReserversation(@Context UriInfo uriInfo, ReservationDto reservationDto) {
    try {
      Reservation reservation = this.reservationService.createReservation(reservationDto);
      String location = createURLforGetReservation(uriInfo, reservation);
      return Response.status(Status.CREATED).entity(location).build();
    } catch (Exception ex) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  private String createURLforGetReservation(UriInfo uriInfo, Reservation reservation) {
    return uriInfo.getBaseUri().toString() + "/reservations/" + reservation.getReservationNumber();
  }
}
