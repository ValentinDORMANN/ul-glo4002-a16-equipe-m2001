package ca.ulaval.glo4002.flycheckin.boarding.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

@Path("")
public class LuggageCheckinResource {

  @POST
  @Path("/passengers/{passenger_hash}/baggages")
  public Response checkinLuggage(@PathParam("passneger_hash") String passengerHash, LuggageDto luggageDto) {
    return Response.ok().build();
  }
}
