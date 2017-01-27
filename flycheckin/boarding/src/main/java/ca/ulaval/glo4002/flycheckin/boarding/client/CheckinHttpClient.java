package ca.ulaval.glo4002.flycheckin.boarding.client;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CheckinHttpClient extends HttpClient {

  private static final String LOCALHOST = "http://localhost:";
  private static final String CHECKINS_HASH = "/checkins/hash/";
  private static final String MESSAGE_NOT_CHECKEDIN = "Passenger is not checkedin";

  private static int reservationServer = 0;

  public CheckinHttpClient() {
    reservationServer = Integer.valueOf(System.getProperty("reservation.port"));
  }

  public void verifyCheckinFromReservation(String passengerHash) {
    String url = LOCALHOST + reservationServer + CHECKINS_HASH + passengerHash;
    Response response = callUrlWithGetMethod(url);
    if (response.getStatus() == Status.NOT_FOUND.getStatusCode())
      throw new NotCheckedinException(MESSAGE_NOT_CHECKEDIN);
  }
}
