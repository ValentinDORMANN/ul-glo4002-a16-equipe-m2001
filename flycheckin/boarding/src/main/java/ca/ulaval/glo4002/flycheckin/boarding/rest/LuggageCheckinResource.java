package ca.ulaval.glo4002.flycheckin.boarding.rest;

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

import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageInfoDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ResultLuggageCheckinDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.externe.PassengerService;
import ca.ulaval.glo4002.flycheckin.boarding.services.interne.LuggageCheckinService;

@Path("")
public class LuggageCheckinResource {

  private static final boolean ALLOWED = true;
  private static final boolean NOT_ALLOWED = false;
  private LuggageCheckinService luggageCheckinService;
  private PassengerService passengerService;

  public LuggageCheckinResource() {
    luggageCheckinService = new LuggageCheckinService();
    passengerService = new PassengerService();
  }

  @GET
  @Path("/passengers/{passenger_hash}/baggages")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLuggage(@Context UriInfo uriInfo, @PathParam("passenger_hash") String passengerHash) {
    try {
      Passenger passenger = passengerService.getPassenger(passengerHash);
      LuggageInfoDto luggageInfoDto = new LuggageInfoDto(passenger);
      return Response.status(Status.OK).entity(luggageInfoDto).build();
    } catch (NotFoundPassengerException ex) {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @POST
  @Path("/passengers/{passenger_hash}/baggages")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response checkinLuggage(@Context UriInfo uriInfo, @PathParam("passenger_hash") String passengerHash,
      LuggageDto luggageDto) throws URISyntaxException {
    try {
      String luggageHash = luggageCheckinService.assignLuggage(passengerHash, luggageDto);
      URI location = createUrlLocation(uriInfo, passengerHash, luggageHash);
      return Response.status(Status.CREATED).location(location).entity(createAllowedLuggageDto()).build();
    } catch (NotFoundPassengerException ex) {
      return Response.status(Status.NOT_FOUND).build();
    } catch (ExcededLuggageException ex) {
      return Response.status(Status.OK).entity(createNotAllowedLuggageDto(ex.getMessage())).build();
    }
  }

  private URI createUrlLocation(UriInfo uriInfo, String passengerHash, String luggageHash) throws URISyntaxException {
    String url = uriInfo.getBaseUri().toString() + "passengers/" + passengerHash + "/baggages/" + luggageHash;
    return new URI(url);
  }

  private ResultLuggageCheckinDto createAllowedLuggageDto() {
    ResultLuggageCheckinDto resultLuggageCheckinDto = new ResultLuggageCheckinDto();
    resultLuggageCheckinDto.allowed = ALLOWED;
    return resultLuggageCheckinDto;
  }

  private ResultLuggageCheckinDto createNotAllowedLuggageDto(String message) {
    ResultLuggageCheckinDto resultLuggageCheckinDto = new ResultLuggageCheckinDto();
    resultLuggageCheckinDto.allowed = NOT_ALLOWED;
    resultLuggageCheckinDto.refusation_reason = message;
    return resultLuggageCheckinDto;
  }
}
