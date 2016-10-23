package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.*;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.*;

public class ReservationServiceTest {
  private ReservationDto reservationDto = new ReservationDto();
  private Reservation mockreservation = mock(Reservation.class);
  private ReservationService service;

  @Before
  public void initialize() {
    service = new ReservationService();
  }

  @Test
  public void givenDTOReservationWhenCreatingReservationThenVerySave() {
    willReturn(mockreservation).given(service).createReservation(reservationDto);

    mockreservation = service.createReservation(reservationDto);

    verify(mockreservation, times(1)).save();
  }
}