package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.ReservationDto;

public class ReservationService {

	public Reservation createReservation(ReservationDto reservationDto) {
		
		return new Reservation(reservationDto.reservation_number,reservationDto.reservation_date,reservationDto.reservation_confirmation,reservationDto.flight_number,reservationDto.flight_date,reservationDto.payment_location,reservationDto.passengers);;
	}

}
