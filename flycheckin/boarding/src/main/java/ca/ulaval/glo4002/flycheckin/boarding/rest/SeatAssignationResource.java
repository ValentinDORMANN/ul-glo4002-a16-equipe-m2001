package ca.ulaval.glo4002.flycheckin.boarding.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatAssignationDTO;
import ca.ulaval.glo4002.flycheckin.boarding.services.HttpServices;

@Path("")
public class SeatAssignationResource {

  private static final String SEAT_ASSIGNATIONS = "/seat-assignations";
  private HttpServices httpService;

  public SeatAssignationResource() {
    this.httpService = new HttpServices();
  }

  public SeatAssignationResource(HttpServices httpService) {
    this.httpService = httpService;
  }

  @POST
  @Path(SEAT_ASSIGNATIONS)
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response assignSeatToPassenger(SeatAssignationDTO seatAssignationDto) throws Exception {
    try {
      ReservationDto reservationDto;
      reservationDto = httpService.getReservationDtoFromReservation(seatAssignationDto.passenger_hash);
      return Response.status(Status.OK).entity(reservationDto).build();
    } catch (Exception ex) {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

}
