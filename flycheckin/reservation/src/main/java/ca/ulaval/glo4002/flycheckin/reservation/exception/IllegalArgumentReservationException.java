package ca.ulaval.glo4002.flycheckin.reservation.exception;

public class IllegalArgumentReservationException extends RuntimeException implements ReservationException {

	private static final long serialVersionUID = 1L;

	public IllegalArgumentReservationException() {
	}

	public IllegalArgumentReservationException(String message) {
		super(message);
	}
}