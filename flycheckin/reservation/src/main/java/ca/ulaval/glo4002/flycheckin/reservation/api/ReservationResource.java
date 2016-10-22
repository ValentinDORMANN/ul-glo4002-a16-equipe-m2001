package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.*;
import ca.ulaval.glo4002.flycheckin.reservation.domain.*;

@Path("/events/reservation-created")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

  private ReservationService reservationService;

  public ReservationResource() {
    this.reservationService = new ReservationService();
  }

  public ReservationResource(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @POST
  public Response createReserversation(ReservationDto reservationDto) {
    return Response.status(201).build();
  }
}
