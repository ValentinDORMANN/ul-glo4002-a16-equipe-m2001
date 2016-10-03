package ca.ulaval.glo6002.flycheckin.reservation.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking /* implements BookingInterface */ {

	private int reservation_number;
	private Date reservation_date;
	private String reservation_confirmation;
	private String flight_number;
	private Date flight_date;
	private String payment_location;

	public Booking(int reservation_number, String reservation_date, String reservation_confirmation,
			String flight_number, String flight_date, String payment_location) throws ParseException {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			this.reservation_number = reservation_number;
			this.reservation_date = sdf.parse(reservation_date);
			this.reservation_confirmation = reservation_confirmation;
			this.flight_number = flight_number;
			this.flight_date = sdf.parse(flight_date);
			this.payment_location = payment_location;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void recordBooking() {

	}

	public void recordPassenger() {

	}

	public void removeBooking() {

	}

	public void changeFlightDate() {

	}

	public void changeFlightNumber() {

	}

	public void getBooking() {

	}
}
