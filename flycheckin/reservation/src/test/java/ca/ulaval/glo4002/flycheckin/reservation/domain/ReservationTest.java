package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.mockito.Mockito.*;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.reservation.api.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;

public class ReservationTest {
  private static final int RESERVATION_NUMBER = 55555;

  private ReservationInMemory mockReservationInMemory;
  private ReservationDto mockReservationDto;
  private Reservation reservation;

  @Before
  public void initiateTest() {
    mockReservationInMemory = mock(ReservationInMemory.class);
    mockReservationDto = mock(ReservationDto.class);
    mockReservationDto.reservation_number = RESERVATION_NUMBER;
    PassengerDto[] passengers = {};
    mockReservationDto.passengers = passengers;

  }

  /*  @Test
  public void whenCreateReservationThenVerifyReservationInMemorySaveNewReservation() {
    reservation = new Reservation(mockReservationInMemory, mockReservationDto);
  
    verify(mockReservationInMemory).saveNewReservation(reservation);
  }*/

  /*  @Test
  public void whenReadReservationByNumberThenVerifyReservationInMemoryGetReservation() {
    reservation = new Reservation(mockReservationInMemory, mockReservationDto);
  
    reservation.readReservationByNumber(RESERVATION_NUMBER);
  
    verify(mockReservationInMemory).getReservationByNumber(RESERVATION_NUMBER);
  }*/
}