package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class ReservationInMemoryTest {
  private DateFormat flightDateFormat;
  private DateFormat reservationDateFormat;
  private static final int RESERVATION_NUMBER = 55555;
  private static final int WRONG_RESERVATION_NUMBER = 44444;
  private static final String FLIGHT_NUMBER = "AF415";
  private static final String FLIGHT_DATE = "2016-01-25";
  private static final String PAYMENT_LOCATION = "/payments/da39a3ee5e6b4b0d3255bfef95601890afd80709";
  private static final String RESERVATION_CONFIRM = "A3833";
  private static final String RESERVATION_DATE = "2016-10-30T00:00:00Z";
  private Reservation storedReservation;
  private Reservation reservation;

  private ReservationInMemory reservationInMemory = new ReservationInMemory();

  @Before
  public void initiateTest() throws ParseException {
    flightDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    reservationDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    storedReservation = new Reservation();
    storedReservation.setFlightNumber(FLIGHT_NUMBER);
    storedReservation.setFlightDate(flightDateFormat.parse(FLIGHT_DATE));
    storedReservation.setPaymentLocation(PAYMENT_LOCATION);
    storedReservation.setReservationConfirmation(RESERVATION_CONFIRM);
    storedReservation.setReservationDate(reservationDateFormat.parse(RESERVATION_DATE));
    storedReservation.setReservationNumber(RESERVATION_NUMBER);
  }

  @Test(expected = NotFoundReservationException.class)
  public void WhenGetReservationWithWrongReservationNumberThenReturnExecption() {
    reservation = reservationInMemory.getReservationByNumber(WRONG_RESERVATION_NUMBER);
  }

  @Test
  public void givenNotEmptyReservationListWhenGetStoredReversationThenReturnReservation() throws ParseException {
    reservationInMemory.saveNewReservation(storedReservation);

    reservation = reservationInMemory.getReservationByNumber(RESERVATION_NUMBER);

    assertEquals(storedReservation, reservation);
  }
}
