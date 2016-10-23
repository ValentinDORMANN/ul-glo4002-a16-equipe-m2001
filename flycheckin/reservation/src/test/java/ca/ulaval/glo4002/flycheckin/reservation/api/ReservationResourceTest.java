package ca.ulaval.glo4002.flycheckin.reservation.api;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.mockito.Mock;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.domain.ReservationService;

public class ReservationResourceTest {

  private ReservationResource reservationResource;
  private Response createdResponse;
  private Response badRequestResponse;
  @Mock
  private ReservationService reservationService;
  @Mock
  private ReservationDto reservationDto;

  @Before
  public void setUp() {
    this.reservationResource = new ReservationResource(this.reservationService);
    this.createdResponse = Response.status(201).build();
    this.badRequestResponse = Response.status(400).build();
  }

  /*  @Test
  public void givenReservationDto_whenCreateReserversation_thenBackCreatedResponse() {
    Response response = this.reservationResource.createReserversation(this.reservationDto);
    assertEquals(this.createdResponse.getStatus(), response.getStatus());
  }*/

}
