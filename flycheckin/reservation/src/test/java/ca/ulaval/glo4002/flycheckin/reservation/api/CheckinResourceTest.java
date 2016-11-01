package ca.ulaval.glo4002.flycheckin.reservation.api;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.CheckinDto;
import ca.ulaval.glo4002.flycheckin.reservation.domain.CheckinService;

public class CheckinResourceTest {

  private CheckinService checkinService;
  private CheckinResource checkinResource;
  private CheckinDto checkinDto;

  @Before
  public void setUp() {
    checkinDto = mock(CheckinDto.class);
    checkinService = mock(CheckinService.class);
    checkinResource = new CheckinResource(checkinService);
  }

  @Test
  public void given_when_then() {
    // fail("Not yet implemented");
  }

}
