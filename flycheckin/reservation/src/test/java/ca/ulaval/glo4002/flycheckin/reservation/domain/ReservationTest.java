package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.reservation.api.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;

public class ReservationTest {
  private static final int RESERVATION_NUMBER = 55555;

  private ReservationInMemory mockReservationInMemory;
  private ReservationDto mockReservationDto;
  private PassengerDto mockPassengerDto;
  private Reservation reservation;

  @Before
  public void initiateTest() {
    mockReservationInMemory = mock(ReservationInMemory.class);
    mockReservationDto = mock(ReservationDto.class);
    mockPassengerDto = mock(PassengerDto.class);
    mockReservationDto.reservation_number = RESERVATION_NUMBER;
    mockReservationDto.passengers = new ArrayList<PassengerDto>();
    mockReservationDto.passengers.add(mockPassengerDto);
    reservation = new Reservation(mockReservationInMemory, mockReservationDto);
  }

  /*
  @Test
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