package ca.ulaval.glo6002.flycheckin.reservation.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking /* implements BookingInterface */ {

	private int reservationNumber;
	private Date reservationDate;
	private String reservationConfirmation;
	private String flightNumber;
	private Date flightDate;
	private String paymentLocation;

	public Booking(int reservationNumber, String reservationDate, String reservationConfirmation, String flightNumber,
			String flightDate, String paymentLocation) throws ParseException {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.reservationNumber = reservationNumber;
			this.reservationDate = sdf.parse(reservationDate);
			this.reservationConfirmation = reservationConfirmation;
			this.flightNumber = flightNumber;
			this.flightDate = sdf.parse(flightDate);
			this.paymentLocation = paymentLocation;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public int getReservationNumber() {
		return reservationNumber;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public String getReservationConfirmation() {
		return reservationConfirmation;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public String getPaymentLocation() {
		return paymentLocation;
	}

}
