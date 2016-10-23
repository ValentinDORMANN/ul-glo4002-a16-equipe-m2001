package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.*;
import ca.ulaval.glo4002.flycheckin.reservation.domain.*;
import ca.ulaval.glo4002.flycheckin.reservation.exception.*;

@Path("")
public class ReservationResource {

  @POST
  @Path("/events/reservation-created")
  @Consumes("application/json")
  public Response createReserversation(@Context UriInfo uriInfo, ReservationDto reservationDto) {
    try {
      Reservation reservation = new Reservation(reservationDto);
      String location = createURLforGetReservation(uriInfo, reservation);
      return Response.status(Status.CREATED).entity(location).build();
    } catch (ReservationException ex) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  private String createURLforGetReservation(UriInfo uriInfo, Reservation reservation) {
    return uriInfo.getBaseUri().toString() + "reservations/" + reservation.getReservationNumber();
  }

  @GET
  @Path("/reservations/{reservation_number}")
  @Produces("application/json")
  public Response getReserversation(@PathParam("reservation_number") int reservation_number) {
    Reservation reservation = new Reservation();
    return Response.status(200).entity(reservation.readReservationByNumber(reservation_number)).build();
  }
}
