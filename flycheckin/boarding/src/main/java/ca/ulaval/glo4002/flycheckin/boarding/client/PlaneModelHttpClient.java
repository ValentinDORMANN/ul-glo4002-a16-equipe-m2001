package ca.ulaval.glo4002.flycheckin.boarding.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PlaneModelDto;

public class PlaneModelHttpClient extends HttpClient {

  private static final String PATH_FOR_PLANE_MODEL = "http://glo3000.ift.ulaval.ca/plane-blueprint/planes/";
  private static final String SEATS_EXTENSION = "/seats.json";

  @Consumes(MediaType.APPLICATION_JSON)
  public PlaneModelDto getPlaneModelDtoAccordingPlaneModel(String planeModel) {
    String url = PATH_FOR_PLANE_MODEL + planeModel + SEATS_EXTENSION;
    Response response = callUrlWithGetMethod(url);
    PlaneModelDto planeModelDto = response.readEntity(PlaneModelDto.class);
    response.close();
    return planeModelDto;
  }
}
