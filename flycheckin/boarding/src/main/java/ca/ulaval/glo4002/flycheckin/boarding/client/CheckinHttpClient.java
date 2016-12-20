package ca.ulaval.glo4002.flycheckin.boarding.client;

import javax.ws.rs.core.Response;

public class CheckinHttpClient extends HttpClient {

  private static final String LOCALHOST = "http://localhost:";
  private static final String CHECKINS_HASH = "/checkins/hash/";
  private static int reservationServer = 0;
  private static final int HTTP_STATUS_NOT_FOUND = 404;
  private static final String MESSAGE_NOT_CHECKEDIN = "Passenger is not checkedin";

  public CheckinHttpClient() {
    reservationServer = Integer.valueOf(System.getProperty("reservation.port"));
  }

  public void verifyCheckinFromReservation(String passengerHash) {
    String url = LOCALHOST + reservationServer + CHECKINS_HASH + passengerHash;
    Response response = callUrlWithGetMethod(url);
    if (response.getStatus() == HTTP_STATUS_NOT_FOUND)
      throw new NotCheckedinException(MESSAGE_NOT_CHECKEDIN);
  }
}
