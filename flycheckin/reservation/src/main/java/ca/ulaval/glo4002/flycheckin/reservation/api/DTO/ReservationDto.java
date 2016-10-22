package ca.ulaval.glo4002.flycheckin.reservation.api.DTO;

import java.util.Date;
import java.util.List;

public class ReservationDto {

	public int reservation_number;
	public Date reservation_date;
	public String reservation_confirmation;
	public String flight_number;
	public Date flight_date;
	public String payment_location;
	public List<PassengerDto> passengers;
}
