package ca.ulaval.glo4002.flycheckin.boarding.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.flycheckin.boarding.persistence.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class ReservationHttpClient extends HttpClient {

  private static final String LOCALHOST = "http://localhost:";
  private static final String RESERVATION_HASH = "/reservations/hash/";
  private static int reservationServer = 0;
  private static final int HTTP_STATUS_NOT_FOUND = 404;
  private static final String MESSAGE_NOT_FOUND = "Passenger not found";

  public ReservationHttpClient() {
    reservationServer = Integer.valueOf(System.getProperty("reservation.port"));
  }

  @Consumes(MediaType.APPLICATION_JSON)
  public ReservationDto getReservationDtoFromReservation(String passengerHash) {
    String url = LOCALHOST + reservationServer + RESERVATION_HASH + passengerHash;
    Response response = callUrlWithGetMethod(url);
    if (response.getStatus() == HTTP_STATUS_NOT_FOUND)
      throw new NotFoundPassengerException(MESSAGE_NOT_FOUND);
    ReservationDto reservationDto = response.readEntity(ReservationDto.class);
    response.close();
    return reservationDto;
  }
}
