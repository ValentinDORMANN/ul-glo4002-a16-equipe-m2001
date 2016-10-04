package ca.ulaval.glo6002.flycheckin.reservation.domain;

import java.util.Date;
import java.util.List;

public class BookingPassengers {

	private String flightNumber;
	private Date flightDate;
	private List<Passenger> passengers;

	public BookingPassengers(List<Passenger> passengers, String flightNumber, Date flightDate) {

		this.flightNumber = flightNumber;
		this.flightDate = flightDate;
		this.passengers = passengers;
	}

}
