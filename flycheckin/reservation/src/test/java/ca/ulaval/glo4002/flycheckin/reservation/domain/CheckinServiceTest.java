package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.CheckinDto;
import ca.ulaval.glo4002.flycheckin.reservation.exception.FlyCheckinApplicationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotTimeToCheckinException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;

public class CheckinServiceTest {

  private static final String PASSENGER_HASH = "HASH";
  private static final String FAKE_PASSENGER_HASH = "FAKE_HASH";
  private static final String AGENT = "AGENT";
  private static final String SELF = "SELF";
  private static final int CHECKIN_NUMBER = 1;
  private static final boolean IS_VALID = true;
  private static final boolean IS_NOT_VALID = false;
  private CheckinInMemory mockCheckinInMemory;
  private ReservationInMemory mockReservationInMemory;
  private CheckinDto mockCheckinDto;
  private Reservation mockReservation;
  private CheckinService checkinService;

  @Before
  public void initiateTest() {
    mockCheckinInMemory = mock(CheckinInMemory.class);
    mockReservationInMemory = mock(ReservationInMemory.class);
    mockCheckinDto = mock(CheckinDto.class);
    mockReservation = mock(Reservation.class);
    checkinService = new CheckinService(mockCheckinInMemory, mockReservationInMemory);
    willReturn(mockReservation).given(mockReservationInMemory).getReservationByPassengerHash(PASSENGER_HASH);
    willThrow(NotFoundPassengerException.class).given(mockReservationInMemory)
        .getReservationByPassengerHash(FAKE_PASSENGER_HASH);
    mockCheckinDto.passenger_hash = PASSENGER_HASH;
    mockCheckinDto.by = AGENT;
    willReturn(CHECKIN_NUMBER).given(mockCheckinInMemory).doPassengerCheckin(PASSENGER_HASH);
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenFakePassengerWhenAgentCheckinThenThrowException() {
    mockCheckinDto.passenger_hash = FAKE_PASSENGER_HASH;

    checkinService.saveCheckin(mockCheckinDto);
  }

  @Test(expected = NotTimeToCheckinException.class)
  public void givenPassengerWhenSelfCheckinNotInTimeThenThrowException() {
    mockCheckinDto.by = SELF;
    willThrow(NotTimeToCheckinException.class).given(mockReservation).validateCheckinPeriod(mockCheckinDto.by);

    checkinService.saveCheckin(mockCheckinDto);
  }

  @Test(expected = FlyCheckinApplicationException.class)
  public void givenWrongPassengerInformationWhenCheckinThenThrowException() {
    willReturn(IS_NOT_VALID).given(mockReservation).isPassengerInfosValid(PASSENGER_HASH);

    checkinService.saveCheckin(mockCheckinDto);
  }

  @Test
  public void givenValidPassengerWhenDoCheckinThenReturnCheckinNumber() {
    willReturn(IS_VALID).given(mockReservation).isPassengerInfosValid(PASSENGER_HASH);

    int checkinNumber = checkinService.saveCheckin(mockCheckinDto);

    assertEquals(CHECKIN_NUMBER, checkinNumber);
  }
}
