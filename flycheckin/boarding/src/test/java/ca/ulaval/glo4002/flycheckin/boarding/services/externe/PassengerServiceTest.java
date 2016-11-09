package ca.ulaval.glo4002.flycheckin.boarding.services.externe;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.client.ReservationHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.InMemoryPassenger;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class PassengerServiceTest {

  private static final String HASH = "HASH";
  private ReservationHttpClient mockReservationHttpClient;
  private InMemoryPassenger mockInMemoryPassenger;
  private ReservationDto mockReservationDto;
  private PassengerDto mockPassengerDto;
  private PassengerService passengerService;

  @Before
  public void initiateTest() {
    mockReservationHttpClient = mock(ReservationHttpClient.class);
    mockInMemoryPassenger = mock(InMemoryPassenger.class);
    passengerService = new PassengerService(mockReservationHttpClient, mockInMemoryPassenger);
    mockReservationDto = mock(ReservationDto.class);
    mockPassengerDto = mock(PassengerDto.class);
  }

  @Test
  public void givenPassengerHashWhenSeatAssignationThenReturnPassenger() {
    mockPassengerDto.passenger_hash = HASH;
    PassengerDto[] passengers = { mockPassengerDto };
    mockReservationDto.passengers = passengers;
    willReturn(mockReservationDto).given(mockReservationHttpClient).getReservationDtoFromReservation(HASH);

    Passenger passenger = passengerService.getPassengerByHashInReservation(HASH);

    assertEquals(HASH, passenger.getPassengerHash());
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenWrongPassengerHashWhenSeatAssignationThenThrowException() {
    willThrow(NotFoundPassengerException.class).given(mockReservationHttpClient).getReservationDtoFromReservation(HASH);

    passengerService.getPassengerByHashInReservation(HASH);
  }
}
