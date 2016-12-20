package ca.ulaval.glo4002.flycheckin.boarding.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.glo4002.flycheckin.boarding.client.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignationRepository;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.SeatAssignationPersistence;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatAssignationDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.passenger.PassengerService;
import ca.ulaval.glo4002.flycheckin.boarding.services.seat.SeatAssignationService;

@Path("")
public class ResourceSeatAssignation {

  private static final String PATH_SEAT_ASSIGNATIONS = "/seat-assignations";
  private static final String SEAT_ASSIGNATIONS = "seat-assignations/";
  private static final String EMPTY_STRING = "";
  private SeatAssignationService seatAssignationService;
  private PassengerService passengerService;

  public ResourceSeatAssignation() {
    SeatAssignation seatAssignation = new SeatAssignation();
    SeatAssignationRepository seatAssignationRepository = new SeatAssignationPersistence();
    this.seatAssignationService = new SeatAssignationService(seatAssignation, seatAssignationRepository);
    this.passengerService = new PassengerService();
  }

  @POST
  @Path(PATH_SEAT_ASSIGNATIONS)
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response assignSeatToPassenger(@Context UriInfo uriInfo, SeatAssignationDto seatAssignationDto) throws URISyntaxException {
    try {
      Passenger passenger = passengerService.getPassengerByHash(seatAssignationDto.passenger_hash);
      SeatAssignation seatAssignation = seatAssignationService.assignSeatToPassenger(passenger, seatAssignationDto.mode);
      seatAssignationDto = transformSeatAssignationDto(seatAssignationDto, seatAssignation.getSeatNumber());
      URI url = createUrlforLocation(uriInfo, seatAssignation);
      return Response.status(Status.CREATED).location(url).entity(seatAssignationDto).build();
    } catch (NotFoundPassengerException ex) {
      return Response.status(Status.NOT_FOUND).build();
    } catch (BoardingModuleException ex) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  private SeatAssignationDto transformSeatAssignationDto(SeatAssignationDto seatAssignationDto, String seat) {
    seatAssignationDto.mode = EMPTY_STRING;
    seatAssignationDto.passenger_hash = EMPTY_STRING;
    seatAssignationDto.seat = seat;
    return seatAssignationDto;
  }

  private URI createUrlforLocation(UriInfo uriInfo, SeatAssignation seatAssignation) throws URISyntaxException {
    String url = uriInfo.getBaseUri().toString() + SEAT_ASSIGNATIONS + seatAssignation.getAssignationNumber();
    return new URI(url);
  }
}
