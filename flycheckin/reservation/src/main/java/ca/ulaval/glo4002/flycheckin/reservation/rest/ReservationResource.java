package ca.ulaval.glo4002.flycheckin.reservation.rest;

import java.net.URI;
import java.net.URISyntaxException;

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

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.ReservationDto;

@Path("")
public class ReservationResource {

  private static final String GET_RESERVATION_PATH = "/reservations/{reservation_number}";
  private static final String GET_RESERVATION_BY_HASH_PATH = "/reservations/hash/{passenger_hash}";
  private static final String POST_RESERVATION_PATH = "/events/reservation-created";

  @GET
  @Path(GET_RESERVATION_PATH)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getReserversation(@PathParam("reservation_number") int reservationNumber) {
    Reservation reservation = new Reservation();
    try {
      reservation = reservation.readReservationByNumber(reservationNumber);
      ReservationDto reservationDto = new ReservationDto(reservation);
      return Response.status(Status.OK).entity(reservationDto).build();
    } catch (NotFoundReservationException ex) {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @GET
  @Path(GET_RESERVATION_BY_HASH_PATH)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getReservationByHash(@PathParam("passenger_hash") String passengerHash) {
    Reservation reservation = new Reservation();
    try {
      reservation = reservation.searchReservationByPassengerHash(passengerHash);
      ReservationDto reservationDto = new ReservationDto(reservation, passengerHash);
      return Response.ok(reservationDto).build();
    } catch (NotFoundPassengerException ex) {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @POST
  @Path(POST_RESERVATION_PATH)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createReserversation(@Context UriInfo uriInfo, ReservationDto reservationDto)
      throws NotCheckedinException {
    try {
      Reservation reservation = new Reservation(reservationDto);
      URI url = createUrlforGetReservation(uriInfo, reservation);
      return Response.status(Status.CREATED).location(url).build();
    } catch (ReservationModuleException | URISyntaxException ex) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  private URI createUrlforGetReservation(UriInfo uriInfo, Reservation reservation) throws URISyntaxException {
    String location = uriInfo.getBaseUri().toString() + "reservations/" + reservation.getReservationNumber();
    return new URI(location);
  }
}
