package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;

public class ReservationInMemoryTest {
  private static final int RESERVATION_NUMBER = 55555;
  private ReservationInMemory reservationInMemory = new ReservationInMemory();
  @Mock
  private Reservation mockReservation;

  @Before
  public void initiateTest() throws ParseException {
    mockReservation = mock(Reservation.class);
    willReturn(RESERVATION_NUMBER).given(mockReservation).getReservationNumber();
  }

  /*  @Test(expected = NotFoundReservationException.class)
  public void WhenGetReservationWithWrongReservationNumberThenReturnExecption() {
    reservation = reservationInMemory.getReservationByNumber(WRONG_RESERVATION_NUMBER);
  }*/

  @Test
  public void givenNotEmptyReservationListWhenGetStoredReversationThenReturnReservation() throws ParseException {
    reservationInMemory.saveNewReservation(mockReservation);

    Reservation reservation = reservationInMemory.getReservationByNumber(RESERVATION_NUMBER);

    assertEquals(mockReservation, reservation);
  }
}
