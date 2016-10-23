package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class ReservationInMemoryTest {
  private static final int RESERVATION_NUMBER = 55555;
  private static final int WRONG_RESERVATION_NUMBER = 44444;
  private ReservationInMemory reservationInMemory = new ReservationInMemory();
  @Mock
  private Reservation mockReservation;

  @Before
  public void initiateTest() throws ParseException {
    mockReservation = mock(Reservation.class);
    willReturn(RESERVATION_NUMBER).given(mockReservation).getReservationNumber();
  }

  @Test(expected = NotFoundReservationException.class)
  public void whenGetReservationWithWrongReservationNumberThenReturnExecption() {
    reservationInMemory.getReservationByNumber(WRONG_RESERVATION_NUMBER);
  }

  @Test
  public void givenNotEmptyReservationListWhenGetStoredReversationThenReturnReservation() {
    reservationInMemory.saveNewReservation(mockReservation);

    Reservation reservation = reservationInMemory.getReservationByNumber(RESERVATION_NUMBER);

    assertEquals(mockReservation, reservation);
  }

  @Test(expected = IllegalArgumentReservationException.class)
  public void givenNotEmptyReservationListWhenSaveReversationAlreadyStoredThenReturnException() {
    reservationInMemory.saveNewReservation(mockReservation);
  }
}
