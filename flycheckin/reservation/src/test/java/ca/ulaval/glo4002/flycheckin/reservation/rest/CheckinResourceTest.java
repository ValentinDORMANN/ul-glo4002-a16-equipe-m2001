package ca.ulaval.glo4002.flycheckin.reservation.rest;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.domain.CheckIn;
import ca.ulaval.glo4002.flycheckin.reservation.domain.CheckinService;
import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

public class CheckinResourceTest {

  private CheckinService mockCheckinService;
  private UriInfo uriInfoMock;
  private CheckinDto checkinDto;
  private static final String AGENT="AGENT";
  private static final String PASSENGER_HASH="HASH";
  private static final boolean ISVIP=true;
  private CheckinResource checkinResource;

  @Before
  public void initiateTest() {
	  checkinDto=new CheckinDto();
	checkinDto.by= AGENT; 
	checkinDto.by= PASSENGER_HASH;
	checkinDto.vip= ISVIP;
    mockCheckinService = mock(CheckinService.class);
    checkinResource = new CheckinResource(mockCheckinService);
    mock(CheckIn.class);
  }

  @Test
  public void givenPassengerHashNotInMemoryWhenCheckinThenReturnStatusNotFound() {
    willThrow(NotFoundPassengerException.class).given(mockCheckinService).saveCheckin(checkinDto);

    Response response = checkinResource.createCheckin(uriInfoMock, checkinDto);

    assertEquals(Status.NOT_FOUND, response.getStatusInfo());
  }

  @Test
  public void givenIncompletePassengerWhenCheckinThenReturnStatusBadRequest() {
    willThrow(ReservationModuleException.class).given(mockCheckinService).saveCheckin(checkinDto);

    Response response = checkinResource.createCheckin(uriInfoMock, checkinDto);

    assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
  }
}
