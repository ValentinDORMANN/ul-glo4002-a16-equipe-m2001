package ca.ulaval.glo4002.flycheckin.boarding.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.flycheckin.boarding.ams.ServiceAms;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PlaneModelDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class ServiceHttp {

  private static final String LOCALHOST = "http://localhost:";
  private static final String RESERVATION_HASH = "/reservations/hash/";
  private static final String PATH_FOR_PLANE_MODEL = "http://glo3000.ift.ulaval.ca/plane-blueprint/planes/";
  private static final String SEATS_EXTENSION = "/seats.json";
  private static final int HTTP_STATUS_NOT_FOUND = 404;
  private static final String MSG_NOT_FOUND = "Passenger not found";
  private static int reservationServer;
  private static ServiceAms serviceAms;

  public ServiceHttp() {
    reservationServer = Integer.valueOf(System.getProperty("reservation.port"));
    serviceAms = new ServiceAms();
  }

  @Consumes(MediaType.APPLICATION_JSON)
  public ReservationDto getReservationDtoFromReservation(String passengerHash) throws Exception {
    String url = LOCALHOST + reservationServer + RESERVATION_HASH + passengerHash;
    Response response = callUrlWithGetMethod(url);
    if (response.getStatus() == HTTP_STATUS_NOT_FOUND)
      throw new NotFoundPassengerException(MSG_NOT_FOUND);
    ReservationDto reservationDto = response.readEntity(ReservationDto.class);
    response.close();
    return reservationDto;
  }

  @Consumes(MediaType.APPLICATION_JSON)
  public PlaneModelDto getPlaneModelDtoAccordingFlightNumber(String flightNumber) {
    String planeModel = serviceAms.getPlaneModelByFlightNumber(flightNumber);
    String url = PATH_FOR_PLANE_MODEL + planeModel + SEATS_EXTENSION;
    Response response = callUrlWithGetMethod(url);
    PlaneModelDto planeModelDto = response.readEntity(PlaneModelDto.class);
    response.close();
    return planeModelDto;
  }

  private Response callUrlWithGetMethod(String url) {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url);
    return target.request(MediaType.APPLICATION_JSON_TYPE).get();
  }
}
