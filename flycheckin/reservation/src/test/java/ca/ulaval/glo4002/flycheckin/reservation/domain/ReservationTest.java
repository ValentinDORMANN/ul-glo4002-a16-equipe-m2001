package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;

public class ReservationTest {
  private static final int RESERVATION_NUMBER = 55555;
  private static final String PASSENGER_HASH = "HASH";
  private static final String FAKE_PASSENGER_HASH = "FAKE_HASH";

  private ReservationInMemory mockReservationInMemory;
  private ReservationDto mockReservationDto;
  private Passenger mockPassenger;
  private List<Passenger> passengers;
  private Reservation reservation;

  @Before
  public void initiateTest() {
    mockReservationInMemory = mock(ReservationInMemory.class);
    mockReservationDto = mock(ReservationDto.class);
    mockPassenger = mock(Passenger.class);
    mockReservationDto.reservation_number = RESERVATION_NUMBER;
    passengers = new ArrayList<Passenger>();
    passengers.add(mockPassenger);
    reservation = new Reservation(mockReservationInMemory, mockReservationDto, passengers);
  }

  @Test
  public void givenFakePassengerHashwhenGetPassengerHashListInReservation_thenVerifyIfHashExist() {
    willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
    assertFalse(reservation.getPassengerHashListInReservation().contains(FAKE_PASSENGER_HASH));
  }

  @Test
  public void givenPassengerHash_whenGetPassengerHashListInReservation_thenVerifyIfHashExist() {
    willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
    assertTrue(reservation.getPassengerHashListInReservation().contains(PASSENGER_HASH));
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