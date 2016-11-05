package ca.ulaval.glo4002.flycheckin.reservation.api;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.UriInfo;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.CheckinDto;
import ca.ulaval.glo4002.flycheckin.reservation.domain.CheckinService;

public class CheckinResourceTest {

  private static final int CHECKIN_ID = 42;
  private static final int CREATED_STATUS = 201;
  private static final int BAD_REQUEST_STATUS = 400;
  private static final int NOT_FOUND_STATUS = 404;
  private static final String LOCATION = "blabla/";

  private UriInfo uriInfo;
  private CheckinService checkinService;
  private CheckinResource checkinResource;
  private CheckinDto checkinDto;

  @Before
  public void initiateTest() {
    uriInfo = mock(UriInfo.class);
    checkinDto = mock(CheckinDto.class);
    checkinService = mock(CheckinService.class);
    checkinResource = new CheckinResource(checkinService);

    given(uriInfo.getBaseUri().toString()).willReturn(LOCATION);
  }

  /*TODO with hibernate lab 5/6 
   * Correct CHeckstyle if acollades*/

  /*
  @Test
  public void given_when_then() {
    willReturn(CHECKIN_ID).given(checkinService).saveCheckin(checkinDto);
    Response response = checkinResource.createCheckin(uriInfo, checkinDto);
    assertEquals(CREATED_STATUS, response.getStatus());
  }*/

}
