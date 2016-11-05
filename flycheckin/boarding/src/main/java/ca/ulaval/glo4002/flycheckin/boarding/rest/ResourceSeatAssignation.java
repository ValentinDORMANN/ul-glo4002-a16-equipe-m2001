package ca.ulaval.glo4002.flycheckin.boarding.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PlaneModelDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatAssignationDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.externe.ServiceHttp;

@Path("")
public class ResourceSeatAssignation {

  private static final String SEAT_ASSIGNATIONS = "/seat-assignations";
  private ServiceHttp httpServices;

  public ResourceSeatAssignation() {
    this.httpServices = new ServiceHttp();
  }

  public ResourceSeatAssignation(ServiceHttp httpServices) {
    this.httpServices = httpServices;
  }

  @POST
  @Path(SEAT_ASSIGNATIONS)
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response assignSeatToPassenger(SeatAssignationDto seatAssignationDto) throws Exception {
    try {
      ReservationDto reservationDto;
      reservationDto = httpServices.getReservationDtoFromReservation(seatAssignationDto.passenger_hash);
      PlaneModelDto plan = httpServices.getPlaneModelDtoAccordingFlightNumber(reservationDto.flight_number);
      return Response.status(Status.OK).entity(plan).build();
    } catch (NotFoundPassengerException ex) {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

}
