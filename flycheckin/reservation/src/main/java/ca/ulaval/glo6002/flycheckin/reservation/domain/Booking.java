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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

	public int getReservation_number() {
		return reservation_number;
	}

	public void setReservation_number(int reservation_number) {
		this.reservation_number = reservation_number;
	}

	public Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}

	public String getReservation_confirmation() {
		return reservation_confirmation;
	}

	public void setReservation_confirmation(String reservation_confirmation) {
		this.reservation_confirmation = reservation_confirmation;
	}

	public String getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}

	public Date getFlight_date() {
		return flight_date;
	}

	public void setFlight_date(Date flight_date) {
		this.flight_date = flight_date;
	}

	public String getPayment_location() {
		return payment_location;
	}

	public void setPayment_location(String payment_location) {
		this.payment_location = payment_location;
	}

}
