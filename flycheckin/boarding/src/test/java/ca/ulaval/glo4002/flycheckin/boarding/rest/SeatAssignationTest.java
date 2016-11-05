package ca.ulaval.glo4002.flycheckin.boarding.rest;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatAssignationDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.HttpServices;

public class SeatAssignationTest {

  private static final String PASSENGER_HASH = "HASH";
  private static final String WRONG_PASSENGER_HASH = "FAKE";
  private static final int STATUS_OK = 200;
  private static final int STATUS_NOT_FOUND = 404;
  private HttpServices httpServices;
  private ReservationDto reservationDto;
  private SeatAssignationDto seatAssignationDto;
  private SeatAssignationResource seatAssignationResource;
  private Response response;

  @Before
  public void initiateTest() throws Exception {
    httpServices = mock(HttpServices.class);
    reservationDto = mock(ReservationDto.class);
    seatAssignationDto = mock(SeatAssignationDto.class);
    seatAssignationResource = new SeatAssignationResource(httpServices);
    willReturn(reservationDto).given(httpServices).getReservationDtoFromReservation(PASSENGER_HASH);
    seatAssignationDto.passenger_hash = PASSENGER_HASH;
  }

  @Test
  public void givenWrongPassengerHashWhenGetReservationThenReturnStatusNotFound() throws Exception {
    willThrow(NotFoundPassengerException.class).given(httpServices)
        .getReservationDtoFromReservation(WRONG_PASSENGER_HASH);
    seatAssignationDto.passenger_hash = WRONG_PASSENGER_HASH;
    response = seatAssignationResource.assignSeatToPassenger(seatAssignationDto);
    assertEquals(STATUS_NOT_FOUND, response.getStatus());
  }

  @Test
  public void givenPassengerHashWhenGetReservationThenReturnStatusOk() throws Exception {
    response = seatAssignationResource.assignSeatToPassenger(seatAssignationDto);
    assertEquals(STATUS_OK, response.getStatus());
  }
}
