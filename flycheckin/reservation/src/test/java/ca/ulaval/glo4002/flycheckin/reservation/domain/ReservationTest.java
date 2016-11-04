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
  private static final Date SELF_CHECKIN_START_TIME = initiateDateByHour(-48, false);
  private static final Date SELF_CHECKIN_END_TIME = initiateDateByHour(-6, true);

  private ReservationInMemory mockReservationInMemory;
  private ReservationDto mockReservationDto;
  private Passenger mockPassenger;
  private List<Passenger> passengers;
  private Reservation reservation;

  private static Date initiateDateByHour(int changeHour, boolean sens) {
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
    willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
  }

  @Test
  public void givenFakePassengerWhenVerifyIfPassengerIsInListPassengerInReservationThenReturnFalse() {
    assertFalse(reservation.getPassengerHashListInReservation().contains(FAKE_PASSENGER_HASH));
  }

  @Test
  public void givenValidPassengerWhenVerifyIfPassengerInReservationThenReturnTrue() {
    assertTrue(reservation.getPassengerHashListInReservation().contains(PASSENGER_HASH));
  }

  @Test
  public void givenValidPassengerHashWhenGetPassengerByHashThenReturnPassenger() {
    assertEquals(mockPassenger, reservation.getPassengerByHash(PASSENGER_HASH));
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenFakePassengerHashWhenGetPassengerByHashThenThrowException() {
    reservation.getPassengerByHash(FAKE_PASSENGER_HASH);
  }
  
  @Test
  public void whenReadReservationByNumberThenVerifyReservationInMemoryGetReservation() {
    reservation.readReservationByNumber(RESERVATION_NUMBER);
  
    verify(mockReservationInMemory).getReservationByNumber(RESERVATION_NUMBER);
  }
  @Test(expected = NotTimeToCheckinException.class)
  public void whenSelfCheckinBeforeStartTimeThenThrowException() {
    reservation.setFlightDate(SELF_CHECKIN_START_TIME);
    
    reservation.validateCheckinPeriod("SELF");
  }
  
  @Test(expected = NotTimeToCheckinException.class)
  public void whenSelfCheckinEndTimeThenThrowException() {
    reservation.setFlightDate(SELF_CHECKIN_END_TIME);
    
    reservation.validateCheckinPeriod("SELF");
  }
}