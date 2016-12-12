package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.domain.ReservationRegistry;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class ReservationInMemoryTest {

  private static final int RESERVATION_NUMBER = 55555;
  private static final int WRONG_RESERVATION_NUMBER = 44444;
  private static final String PASSENGER_HASH = "ABCD";
  private ReservationRegistry reservationRegistry = new ReservationInMemory();
  private Reservation reservationMock;

  @Before
  public void initiateTest() throws ParseException {
    reservationMock = mock(Reservation.class);
    willReturn(RESERVATION_NUMBER).given(reservationMock).getReservationNumber();
  }

  @Test(expected = NotFoundReservationException.class)
  public void withWrongReservationNumberWhenGetReservationThenReturnException() {
    reservationRegistry.getReservationByNumber(WRONG_RESERVATION_NUMBER);
  }

  @Test
  public void givenNotEmptyReservationListWhenGetStoredReversationThenReturnReservation() {
    reservationRegistry.saveNewReservation(reservationMock);

    Reservation reservation = reservationRegistry.getReservationByNumber(RESERVATION_NUMBER);

    assertEquals(reservationMock, reservation);
  }

  @Test(expected = IllegalArgumentReservationException.class)
  public void givenNotEmptyReservationListWhenSaveReversationAlreadyStoredThenReturnException() {
    reservationRegistry.saveNewReservation(reservationMock);
  }
  
  @Test(expected = NotFoundPassengerException.class)
  public void givenNotEmptyReservationListContainingPassengerWhenGetReservationByHashThenThrowException() {
    given(reservationMock.isThisHashInReservation(PASSENGER_HASH)).willThrow(new NotFoundPassengerException()); 
    
    reservationRegistry.getReservationByPassengerHash(PASSENGER_HASH);
  }
}
