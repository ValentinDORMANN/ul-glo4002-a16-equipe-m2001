package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.domain.ReservationRegistry;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class HibernateReservationTest {

	private static final int RESERVATION_NUMBER = 55555;
  private static final int WRONG_RESERVATION_NUMBER = 44444;
  private ReservationRegistry reservationRegistry = new HibernateReservation();
  private Reservation mockReservation;

  @Before
  public void initiateTest() throws ParseException {
    mockReservation = mock(Reservation.class);
    willReturn(RESERVATION_NUMBER).given(mockReservation).getReservationNumber();
  }

  @Test(expected = NotFoundReservationException.class)
  public void withWrongReservationNumberWhenGetReservationThenReturnException() {
    reservationRegistry.getReservationByNumber(WRONG_RESERVATION_NUMBER);
  }

  /*@Test
  public void givenNotEmptyReservationListWhenGetStoredReversationThenReturnReservation() {
    reservationRegistry.saveNewReservation(mockReservation);

    Reservation reservation = reservationRegistry.getReservationByNumber(RESERVATION_NUMBER);

    assertEquals(mockReservation, reservation);
  }

  @Test(expected = IllegalArgumentReservationException.class)
  public void givenNotEmptyReservationListWhenSaveReversationAlreadyStoredThenReturnException() {
    reservationRegistry.saveNewReservation(mockReservation);
  }*/
}
