package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.exception.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotTimeToCheckinException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateCheckin;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

public class CheckinServiceTest {

  private static final String PASSENGER_HASH = "HASH";
  private static final String FAKE_PASSENGER_HASH = "FAKE_HASH";
  private static final String AGENT = "AGENT";
  private static final String SELF = "SELF";
  private static final int CHECKIN_NUMBER = 1;
  private static final boolean IS_VALID = true;
  private static final boolean IS_NOT_VALID = false;
 // private CheckinInMemory checkinInMemoryMock;
  private HibernateCheckin hibernateCheckinMock;
  private ReservationInMemory reservationInMemoryMock;
  private ReservationRegistry hibernateReservationMock;
  private CheckinDto checkinDto;
  private Reservation reservationMock;
  private CheckinService checkinService;

  @Before
  public void initiateTest() {
   // checkinInMemoryMock = mock(CheckinInMemory.class);
    hibernateCheckinMock = mock(HibernateCheckin.class);
    hibernateReservationMock = mock(ReservationRegistry.class);
    //reservationInMemoryMock = mock(ReservationInMemory.class);
    reservationMock = mock(Reservation.class);

    checkinDto = new CheckinDto();
    checkinService = new CheckinService(hibernateCheckinMock, hibernateReservationMock,reservationMock);

  willReturn(reservationMock).given(hibernateReservationMock).getReservationByPassengerHash(PASSENGER_HASH);
    willThrow(NotCheckedinException.class).given(hibernateCheckinMock).findCheckinByPassengerHash(FAKE_PASSENGER_HASH);

    checkinDto.passenger_hash = PASSENGER_HASH;
    checkinDto.by = AGENT;
  }

  @Test(expected = NotCheckedinException.class)
  public void givenFakePassengerWhenAgentCheckinThenThrowException() {
	 // willReturn(reservationMock).given(hibernateReservationMock).getReservationByPassengerHash(PASSENGER_HASH);
    checkinDto.passenger_hash = FAKE_PASSENGER_HASH;

    checkinService.saveCheckin(checkinDto);
  }

/*  @Test(expected = NotTimeToCheckinException.class)
  public void givenPassengerWhenSelfCheckinNotInTimeThenThrowException() {
    checkinDto.by = SELF;
    willThrow(NotTimeToCheckinException.class).given(reservationMock).validateCheckinPeriod(checkinDto.by);

    checkinService.saveCheckin(checkinDto);
  }*/

  /*@Test(expected = ReservationModuleException.class)
  public void givenWrongPassengerInformationWhenCheckinThenThrowException() {
    willReturn(IS_NOT_VALID).given(reservationMock).isPassengerInfosValid(PASSENGER_HASH);

    checkinService.saveCheckin(checkinDto);
  }*/

  /*@Test
  public void givenValidPassengerWhenDoCheckinThenReturnCheckinNumber() {
    willReturn(IS_VALID).given(reservationMock).isPassengerInfosValid(PASSENGER_HASH);

    int checkinNumber = checkinService.saveCheckin(checkinDto);

    assertEquals(CHECKIN_NUMBER, checkinNumber);
  }*/

  /*@Test
  public void givenPassengerNotCheckedWhenIsCheckinDoneThenVerifyCheckinServiceCalled() {
    checkinService.isCheckInPassengerDone(PASSENGER_HASH);

    verify(hibernateCheckinMock).findCheckinByPassengerHash(PASSENGER_HASH);
  }*/

  /*@Test(expected = NotCheckedinException.class)
  public void givenPassengerNotCheckedWhenIsCheckinDoneThenThrowException() {
    willThrow(NotCheckedinException.class).given(hibernateCheckinMock).findCheckinByPassengerHash(PASSENGER_HASH);

    checkinService.isCheckInPassengerDone(PASSENGER_HASH);
  }*/
}
