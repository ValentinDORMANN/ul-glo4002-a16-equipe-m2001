package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.FlyCheckinApplicationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

@Path("")
public class ReservationResource {

  private static final String GET_RESERVATION_PATH = "/reservations/{reservation_number}";
  private static final String GET_RESERVATION_BY_HASH_PATH = "/reservations/hash/{passenger_hash}";
  private static final String POST_RESERVATION_PATH = "/events/reservation-created";

  @POST
  @Path(POST_RESERVATION_PATH)
  @Consumes(MediaType.APPLICATION_JSON)
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
  @Path(GET_RESERVATION_PATH)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getReserversation(@PathParam("reservation_number") int reservationNumber) {
    Reservation reservation = new Reservation();
    try {
      reservation = reservation.readReservationByNumber(reservationNumber);
    } catch (NotFoundReservationException ex) {
      return Response.status(Status.NOT_FOUND).build();
    }
    ReservationDto reservationDto = new ReservationDto(reservation);
    return Response.status(Status.OK).entity(reservationDto).build();
  }

  @GET
  @Path(GET_RESERVATION_BY_HASH_PATH)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getReservationByHash(@PathParam("passenger_hash") String passenger_hash) {
    Reservation reservation = new Reservation();
    try {
      reservation = reservation.searchReservationByPassengerHash(passenger_hash);
    } catch (NotFoundPassengerException ex) {
      return Response.status(Status.NOT_FOUND).build();
    }
    ReservationDto reservationDto = new ReservationDto(reservation, passenger_hash);
    return Response.ok(reservationDto).build();
  }
}
