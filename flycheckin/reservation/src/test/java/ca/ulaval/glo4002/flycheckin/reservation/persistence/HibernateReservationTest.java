package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;

public class HibernateReservationTest {

  private static final int RESERVATION_NUMBER = 55555;
  private static final int WRONG_RESERVATION_NUMBER = 44444;
  private HibernateReservation hibernateReservation = new HibernateReservation();
  private Reservation mockReservation;

  @Before
  public void initiateTest() {
    mockReservation = mock(Reservation.class);
    willReturn(RESERVATION_NUMBER).given(mockReservation).getReservationNumber();
  }

  /*@Test(expected = NotFoundReservationException.class)
  public void withWrongReservationNumberWhenGetReservationThenReturnException() {
  hibernateReservation.findReservationByNumber(WRONG_RESERVATION_NUMBER);
  }
  */
  @Test
  public void givenNotEmptyReservationWhenGetStoredReversationThenReturnReservation() {
    hibernateReservation.insertNewReservation(mockReservation);

    Reservation reservation = hibernateReservation.findReservationByNumber(RESERVATION_NUMBER);

    assertEquals(mockReservation, reservation);
  }
  /*
  @Test(expected = IllegalArgumentReservationException.class)
  public void givenNotEmptyReservationListWhenSaveReversationAlreadyStoredThenReturnException() {
  hibernateReservation.insertNewReservation(mockReservation);
  }*/

}
