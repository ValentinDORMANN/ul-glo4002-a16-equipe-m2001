package ca.ulaval.glo4002.flycheckin.reservation.domain;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.ReservationDto;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;


public class ReservationServiceTest {
	private ReservationDto reservationDto = new ReservationDto();
	private Reservation mockreservation=mock(Reservation.class);
	private ReservationService service;
	@Before
	public void initialize(){
		service = new ReservationService();
	}

	@Test
	public void givenDTOReservationWhenCreatingReservationThenVerySave(){
		willReturn(mockreservation).given(service).createReservation(reservationDto);
		
		mockreservation=service.createReservation(reservationDto);
		
		verify(mockreservation,times(1)).save();
	}
}
