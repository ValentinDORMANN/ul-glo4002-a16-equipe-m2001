package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotTimeToCheckinException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

public class CheckinServiceTest {

  private static final String PASSENGER_HASH = "HASH";
  private static final String FAKE_PASSENGER_HASH = "FAKE_HASH";
  private static final String AGENT = "AGENT";
  private static final String SELF = "SELF";
  private static final int CHECKIN_NUMBER = 1;
  private static final boolean IS_VALID = true;
  private static final boolean IS_NOT_VALID = false;

  private CheckinInMemory mockCheckinInMemory;// go
  private HibernateReservation hibernateReservationMock;
  private CheckinDto checkinDto;
  private Reservation reservationMock;
  private CheckinService checkinService;

  @Before
  public void initiateTest() {
    checkinDto = new CheckinDto();
    mockCheckinInMemory = mock(CheckinInMemory.class);// go
    hibernateReservationMock = mock(HibernateReservation.class);
    reservationMock = mock(Reservation.class);
    assignElementToDto(checkinDto);
    driveMockObject(mockCheckinInMemory, hibernateReservationMock, reservationMock);

    checkinService = new CheckinService(mockCheckinInMemory, hibernateReservationMock);
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenFakePassengerWhenAgentCheckinThenThrowException() {
    willThrow(NotFoundPassengerException.class).given(hibernateReservationMock)
        .findReservationByPassengerHash(FAKE_PASSENGER_HASH);
    checkinDto.passenger_hash = FAKE_PASSENGER_HASH;

    checkinService.saveCheckin(checkinDto);
  }

  @Test(expected = NotTimeToCheckinException.class)
  public void givenPassengerWhenSelfCheckinNotInTimeThenThrowException() {
    checkinDto.by = SELF;
    willThrow(NotTimeToCheckinException.class).given(reservationMock).validateCheckinPeriod(checkinDto.by);

    checkinService.saveCheckin(checkinDto);
  }

  @Test(expected = ReservationModuleException.class)
  public void givenWrongPassengerInformationWhenCheckinThenThrowException() {

    willReturn(IS_NOT_VALID).given(reservationMock).isPassengerInfosValid(PASSENGER_HASH);

    checkinService.saveCheckin(checkinDto);
  }

  @Test
  public void givenValidPassengerWhenDoCheckinThenReturnCheckinNumber() {

    int checkinNumber = checkinService.saveCheckin(checkinDto);

    assertEquals(CHECKIN_NUMBER, checkinNumber);
  }

  private void driveMockObject(CheckinInMemory mockCheckinInMemory, HibernateReservation hibernateReservationMock,
      Reservation reservationMock) {
    willReturn(reservationMock).given(hibernateReservationMock).findReservationByPassengerHash(PASSENGER_HASH);
    willReturn(IS_VALID).given(reservationMock).isPassengerInfosValid(PASSENGER_HASH);
    willReturn(CHECKIN_NUMBER).given(mockCheckinInMemory).doPassengerCheckin(PASSENGER_HASH);
  }

  private void assignElementToDto(CheckinDto checkinDto) {
    checkinDto.passenger_hash = PASSENGER_HASH;
    checkinDto.by = AGENT;
  }
}