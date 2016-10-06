package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookingPassengers {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private String flightNumber;
	private Date flightDate;
	private List<Passenger> passengers;

	public BookingPassengers(List<Passenger> passengers, String flightNumber, String flightDate) throws ParseException {

		this.flightNumber = flightNumber;
		this.flightDate = formatter.parse(flightDate);
		this.passengers = passengers;
	}
}
