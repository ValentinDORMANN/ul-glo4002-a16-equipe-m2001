package ca.ulaval.glo4002.flycheckin.boarding.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededCheckedLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.interne.LuggageCheckinService;

@Path("")
public class LuggageCheckinResource {

  private static final boolean ALLOWED = true;
  private static final boolean NOT_ALLOWED = false;
  private LuggageCheckinService luggageCheckinService;

  public LuggageCheckinResource() {
    luggageCheckinService = new LuggageCheckinService();
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
    } catch (ExcededCheckedLuggageException ex) {
      return Response.status(Status.OK).entity(createNotAllowedLuggageDto(ex.getMessage())).build();
    }
  }

  private URI createUrlLocation(UriInfo uriInfo, String passengerHash, String luggageHash) throws URISyntaxException {
    String url = uriInfo.getBaseUri().toString() + "passengers/" + passengerHash + "/baggages/" + luggageHash;
    return new URI(url);
  }

  private LuggageDto createAllowedLuggageDto() {
    LuggageDto luggageDto = new LuggageDto();
    luggageDto.allowed = ALLOWED;
    return luggageDto;
  }

  private LuggageDto createNotAllowedLuggageDto(String message) {
    LuggageDto luggageDto = new LuggageDto();
    luggageDto.allowed = NOT_ALLOWED;
    luggageDto.refusation_reason = message;
    return luggageDto;
  }
}
