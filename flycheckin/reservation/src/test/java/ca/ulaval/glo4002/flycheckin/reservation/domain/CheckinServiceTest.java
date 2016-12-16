package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotTimeToCheckinException;
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
  private CheckinInMemory mockCheckinInMemory;//go
  private HibernateCheckin hibernateCheckinMock;
  private ReservationInMemory mockReservationInMemory;//go
  private HibernateReservation hibernateReservationMock;
  private CheckinDto CheckinDto;
  private Reservation reservationMock;
  private CheckinService checkinService;

  @Before
  public void initiateTest() {
	  CheckinDto = new CheckinDto();
    mockCheckinInMemory = mock(CheckinInMemory.class);//go
    hibernateReservationMock = mock(HibernateReservation.class);//go
    reservationMock = mock(Reservation.class);
    CheckinDto.passenger_hash = PASSENGER_HASH;
    CheckinDto.by = AGENT;
    willReturn(CHECKIN_NUMBER).given(mockCheckinInMemory).doPassengerCheckin(PASSENGER_HASH);
    checkinService = new CheckinService(mockCheckinInMemory, hibernateReservationMock);
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenFakePassengerWhenAgentCheckinThenThrowException() {
	  willThrow(NotFoundPassengerException.class).given(hibernateReservationMock)
      .findReservationByPassengerHash(FAKE_PASSENGER_HASH);
	  CheckinDto.passenger_hash = FAKE_PASSENGER_HASH;

    checkinService.saveCheckin(CheckinDto);
  }

  @Test(expected = NotTimeToCheckinException.class)
  public void givenPassengerWhenSelfCheckinNotInTimeThenThrowException() {
	  willReturn(reservationMock).given(hibernateReservationMock).findReservationByPassengerHash(PASSENGER_HASH);
	  CheckinDto.by = SELF;
    willThrow(NotTimeToCheckinException.class).given(reservationMock).validateCheckinPeriod(CheckinDto.by);

    checkinService.saveCheckin(CheckinDto);
  }

  @Test(expected = ReservationModuleException.class)
  public void givenWrongPassengerInformationWhenCheckinThenThrowException() {
	  willReturn(reservationMock).given(hibernateReservationMock).findReservationByPassengerHash(PASSENGER_HASH);
    willReturn(IS_NOT_VALID).given(reservationMock).isPassengerInfosValid(PASSENGER_HASH);

    checkinService.saveCheckin(CheckinDto);
  }

  @Test
  public void givenValidPassengerWhenDoCheckinThenReturnCheckinNumber() {
    willReturn(IS_VALID).given(reservationMock).isPassengerInfosValid(PASSENGER_HASH);

    int checkinNumber = checkinService.saveCheckin(CheckinDto);

    assertEquals(CHECKIN_NUMBER, checkinNumber);
  }
}