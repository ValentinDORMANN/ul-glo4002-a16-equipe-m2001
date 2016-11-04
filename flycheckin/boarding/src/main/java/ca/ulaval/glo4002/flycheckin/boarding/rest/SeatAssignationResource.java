package ca.ulaval.glo4002.flycheckin.boarding.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatAssignationDTO;

@Path("")
public class SeatAssignationResource {

  private static final String LOCALHOST = "http://localhost:";
  private static final int RESERVATION_SERVER = Integer.valueOf(System.getProperty("reservation.port"));
  private static final String SEAT_ASSIGNATIONS = "/seat-assignations";

  @POST
  @Path(SEAT_ASSIGNATIONS)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response assignSeatToPassenger(SeatAssignationDTO seatAssignationDto) {
    ReservationDto reservationDto;
    try {
      reservationDto = getReservationDtoFromReservation(seatAssignationDto.passenger_hash);
      return Response.status(Status.OK).entity(reservationDto).build();
    } catch (Exception e) {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @Consumes(MediaType.APPLICATION_JSON)
  private ReservationDto getReservationDtoFromReservation(String passenger_hash) throws Exception {
    String url = LOCALHOST + RESERVATION_SERVER + "/reservations/hash/" + passenger_hash;
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target(url);
    Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
    if (response.getStatus() == 404)
      throw new Exception();
    return response.readEntity(ReservationDto.class);
  }
}
