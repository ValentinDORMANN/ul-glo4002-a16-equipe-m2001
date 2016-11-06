package ca.ulaval.glo4002.flycheckin.boarding.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatAssignationDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.externe.PlaneModelService;

@Path("")
public class ResourceSeatAssignation {

  private static final String SEAT_ASSIGNATIONS = "/seat-assignations";

  public ResourceSeatAssignation() {
  }

  @POST
  @Path(SEAT_ASSIGNATIONS)
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response assignSeatToPassenger(SeatAssignationDto seatAssignationDto) throws Exception {
    try {
      PlaneModelService planeModelService = new PlaneModelService();
      List<Seat> list = planeModelService.getSeatsAccordingPlaneModel("a320");
      return Response.status(Status.OK).entity(list).build();
    } catch (NotFoundPassengerException ex) {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

}
