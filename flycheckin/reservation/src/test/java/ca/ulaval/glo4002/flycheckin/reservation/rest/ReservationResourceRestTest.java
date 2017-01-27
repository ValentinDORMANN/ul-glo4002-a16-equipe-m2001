package ca.ulaval.glo4002.flycheckin.reservation.rest;

import org.junit.Test;

public class ReservationResourceRestTest extends RestTestBase {

  private static final int EXISTING_RESERVATION = 74563;

  @Test
  public void getReservationByReservationNumber() {

    givenBaseRequest().when()
        .get("/reservations/{reservation_number}", buildPathParams("reservation_number", EXISTING_RESERVATION)).then()
        .body("reservation_number", equalTo(EXISTING_RESERVATION)).and().statusCode(Status.OK.getStatusCode());
  }
}
