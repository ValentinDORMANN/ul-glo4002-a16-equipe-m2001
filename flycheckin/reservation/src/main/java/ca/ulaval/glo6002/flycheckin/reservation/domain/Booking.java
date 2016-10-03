package ca.ulaval.glo6002.flycheckin.reservation.domain;

public class Booking /* implements BookingInterface */ {

	private int reservationNumber;
	private String reservationDate;
	private String reservationConfirmation;
	private String paymentLocation;

	public Booking(int reservationNumber, String reservationDate, String reservationConfirmation,
			String paymentLocation) {
		this.reservationNumber = reservationNumber;
		this.reservationDate = reservationDate;
		this.reservationConfirmation = reservationConfirmation;
		this.paymentLocation = paymentLocation;
	}

	public int getReservationNumber() {
		return reservationNumber;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public String getReservationConfirmation() {
		return reservationConfirmation;
	}

	public String getPaymentLocation() {
		return paymentLocation;
	}

}
