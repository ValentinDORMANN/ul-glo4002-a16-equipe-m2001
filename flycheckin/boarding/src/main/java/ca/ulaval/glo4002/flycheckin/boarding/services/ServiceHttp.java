package ca.ulaval.glo4002.flycheckin.boarding.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class ServiceHttp {

  private static final String LOCALHOST = "http://localhost:";
  private static final String RESERVATION_HASH = "/reservations/hash/";
  private static final int HTTP_STATUS_NOT_FOUND = 404;
  private static final String MSG_NOT_FOUND = "Passenger not found";
  private static int reservationServer;

  public ServiceHttp() {
    reservationServer = Integer.valueOf(System.getProperty("reservation.port"));
  }

  public ReservationDto getReservationDtoFromReservation(String passenger_hash) throws Exception {
    String url = LOCALHOST + reservationServer + RESERVATION_HASH + passenger_hash;
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url);
    Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
    if (response.getStatus() == HTTP_STATUS_NOT_FOUND)
      throw new NotFoundPassengerException(MSG_NOT_FOUND);
    ReservationDto reservationDto = response.readEntity(ReservationDto.class);
    response.close();
    return reservationDto;
  }
}
