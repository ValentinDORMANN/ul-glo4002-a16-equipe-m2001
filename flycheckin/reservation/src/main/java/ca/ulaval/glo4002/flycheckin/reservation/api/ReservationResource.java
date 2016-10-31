package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.FlyCheckinApplicationException;

@Path("")
public class ReservationResource {

  @POST
  @Path("/events/reservation-created")
  @Consumes("application/json")
  public Response createReserversation(@Context UriInfo uriInfo, ReservationDto reservationDto) {
    try {
      Reservation reservation = new Reservation(reservationDto);
      String location = createUrlforGetReservation(uriInfo, reservation);
      return Response.status(Status.CREATED).entity(location).build();
    } catch (FlyCheckinApplicationException ex) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  private String createUrlforGetReservation(UriInfo uriInfo, Reservation reservation) {
    return uriInfo.getBaseUri().toString() + "reservations/" + reservation.getReservationNumber();
  }

  @GET
  @Path("/reservations/{reservation_number}")
  @Produces("application/json")
  public Response getReserversation(@PathParam("reservation_number") int reservationNumber) {
    Reservation reservation = new Reservation();
    reservation = reservation.readReservationByNumber(reservationNumber);
    return Response.status(200).entity(reservation).build();
  }
}
