package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotTimeToCheckinException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;

public class ReservationTest {
  private static final int RESERVATION_NUMBER = 55555;
  private static final String PASSENGER_HASH = "HASH";
  private static final String FAKE_PASSENGER_HASH = "FAKE_HASH";

  private static final Calendar CALENDAR = Calendar.getInstance();
  private static final Date TODAY = CALENDAR.getTime();
  private static final Date TWO_DAYS_BEFORE = initiateDateByHour(-48, false);
  private static final Date SIX_HOURS_BEFORE = initiateDateByHour(-6, true);

  private ReservationInMemory mockReservationInMemory;
  private ReservationDto mockReservationDto;
  private Passenger mockPassenger;
  private List<Passenger> passengers;
  private Reservation reservation;

  private static Date initiateDateByHour(int changeHour, boolean sens){
    Calendar calendar = CALENDAR;
    calendar.add(Calendar.HOUR, changeHour);
    calendar.add(Calendar.MILLISECOND, (sens) ? 1 : -1);
    return calendar.getTime();
  }

  
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
  public void givenFakePassengerWhenVerifyIfPassengerInReservationThenReturnFalse() {
    willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
    
    assertFalse(reservation.getPassengerHashListInReservation().contains(FAKE_PASSENGER_HASH));
  }

  @Test
  public void givenValidPassengerWhenVerifyIfPassengerInReservationThenReturnTrue() {
    willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
    
    assertTrue(reservation.getPassengerHashListInReservation().contains(PASSENGER_HASH));
  }

  @Test
  public void givenValidPassengerHashWhenGetPassengerByHashThenReturnPassenger() {
    willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
    
    assertEquals(mockPassenger, reservation.getPassengerFromHash(PASSENGER_HASH));
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenFakePassengerHashWhenGetPassengerByHashThenThrowException() {
    willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
    
    reservation.getPassengerFromHash(FAKE_PASSENGER_HASH);
  }
  // TODO
  /*
  @Test
  public void whenCreateReservationThenVerifyReservationInMemorySaveNewReservation() {
    verify(mockReservationInMemory).saveNewReservation(reservation);
  }
  
  @Test
  public void whenReadReservationByNumberThenVerifyReservationInMemoryGetReservation() {
    reservation.readReservationByNumber(RESERVATION_NUMBER);
  
    verify(mockReservationInMemory).getReservationByNumber(RESERVATION_NUMBER);
  }
  @Test(expected = NotTimeToCheckinException.class)
  public void WhenSelfCheckinOutOfvalidePeriodThenThrowException(){
    reservation.setFlightDate(TWO_DAYS_BEFORE);
    
    doThrow(new NotTimeToCheckinException("")).when(reservation).validateSelfCheckinPeriod();
  }*/

}