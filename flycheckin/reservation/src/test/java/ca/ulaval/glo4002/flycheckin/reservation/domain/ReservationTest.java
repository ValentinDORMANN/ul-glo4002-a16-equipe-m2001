package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.exception.NotTimeToCheckinException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.ReservationDto;

public class ReservationTest {

  private static final int RESERVATION_NUMBER = 55555;
  private static final String SELF = "SELF";
  private static final String PASSENGER_HASH = "HASH";
  private static final String FAKE_PASSENGER_HASH = "FAKE_HASH";
  private static final Calendar CALENDAR = Calendar.getInstance();
  private static final Date SELF_CHECKIN_START_TIME = addHoursToCalendar(-48, false);
  private static final Date SELF_CHECKIN_END_TIME = addHoursToCalendar(-6, true);

  private HibernateReservation hibernateReservationMock;
  private ReservationDto reservationDtoMock;
  private Passenger passengerMock;
  private List<Passenger> passengers;

  private Reservation reservation;

  @Before
  public void initiateTest() {
	  hibernateReservationMock = mock(HibernateReservation.class);
    reservationDtoMock = mock(ReservationDto.class);
    passengerMock = mock(Passenger.class);

    reservationDtoMock.reservation_number = RESERVATION_NUMBER;
    passengers = new ArrayList<Passenger>();
    passengers.add(passengerMock);

    reservation = new Reservation(hibernateReservationMock, reservationDtoMock, passengers);

    willReturn(true).given(passengerMock).hasThisHash(PASSENGER_HASH);
  }

  @Test
  public void givenValidPassengerHashWhenGetPassengerByHashThenReturnPassenger() {
    assertEquals(passengerMock, reservation.getPassengerByHash(PASSENGER_HASH));
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenFakePassengerHashWhenGetPassengerByHashThenThrowException() {
    reservation.getPassengerByHash(FAKE_PASSENGER_HASH);
  }

  @Test
  public void whenReadReservationByNumberThenVerifyReservationInMemoryGetReservation() {
    reservation.readReservationByNumber(RESERVATION_NUMBER);

    verify(hibernateReservationMock).findReservationByNumber(RESERVATION_NUMBER);
  }

  @Test(expected = NotTimeToCheckinException.class)
  public void whenSelfCheckinBeforeStartTimeThenThrowException() {
    reservation.setFlightDate(SELF_CHECKIN_START_TIME);

    reservation.validateCheckinPeriod(SELF);
  }

  @Test(expected = NotTimeToCheckinException.class)
  public void whenSelfCheckinEndTimeThenThrowException() {
    reservation.setFlightDate(SELF_CHECKIN_END_TIME);

    reservation.validateCheckinPeriod(SELF);
  }

  private static Date addHoursToCalendar(int changeHour, boolean mustAddMinute) {
    Calendar calendar = CALENDAR;
    calendar.add(Calendar.HOUR, changeHour);
    calendar.add(Calendar.MINUTE, (mustAddMinute) ? 1 : -1);
    return calendar.getTime();
  }
}