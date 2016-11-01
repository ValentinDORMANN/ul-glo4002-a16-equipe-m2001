package ca.ulaval.glo4002.flycheckin.reservation.api;

import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.reservation.api.dto.ReservationDto;

public class ReservationResourceTest {

  private static final int RESERVATION_NUMBER = 123456;
  private static final int RESERVATION_NUMBER2 = 1234567;
  private ReservationResource reservationResource;
  private Response createdResponse;
  private Response badRequestResponse;
  @Mock
  private ReservationDto reservationDto;
  @Mock
  private UriInfo urinfo;

  @Before
  public void setUp() {
    this.reservationResource = new ReservationResource();

    this.reservationDto = mock(ReservationDto.class);
    this.createdResponse = Response.status(201).build();
    this.badRequestResponse = Response.status(400).build();

    this.reservationDto.reservation_number = RESERVATION_NUMBER;
    PassengerDto[] passengers = {};
    // this.reservationDto.passengers = passengers;
  }

  @Test
  public void givenReservationDto_whenCreateReservation_thenBackReservation() {
    // Reservation reservation = new Reservation(reservationDto);
    // assertEquals(RESERVATION_NUMBER, reservation.getReservationNumber());
  }

}
