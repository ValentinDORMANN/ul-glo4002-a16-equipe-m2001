package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class ReservationInMemoryTest {
  private static final int RESERVATION_NUMBER = 55555;
  private Reservation storedReservation;
  private Reservation reservation;

  private ReservationInMemory reservationInMemory = new ReservationInMemory();

  @Test(expected = NotFoundReservationException.class)
  public void givenEmptyReservationListWhenGetReservationThenReturnExecption() {
    reservation = reservationInMemory.getReservationByNumber(RESERVATION_NUMBER);
  }

  @Test
  public void givenNotEmptyReservationListWhenGetStoredReversationThenReturnReservation() {
    reservationInMemory.saveNewReservation(storedReservation);

    reservation = reservationInMemory.getReservationByNumber(RESERVATION_NUMBER);

    assertEquals(storedReservation, reservation);
  }
}
