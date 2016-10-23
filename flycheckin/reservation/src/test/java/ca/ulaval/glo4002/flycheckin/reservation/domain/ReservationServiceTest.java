package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.*;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.*;

public class ReservationServiceTest {

  @Mock
  private ReservationDto reservationDto;
  private Reservation reservation;
  private ReservationService service;

  @Before
  public void initialize() {
    reservationDto = new ReservationDto();
    PassengerDto[] passengers = {};
    reservationDto.passengers = passengers;
    service = new ReservationService();
  }

  @Test
  public void givenDTOReservationWhenCreatingReservationThenVerySave() {

    this.reservation = service.createReservation(reservationDto);

    assertFalse(this.reservation == null);
  }

}
