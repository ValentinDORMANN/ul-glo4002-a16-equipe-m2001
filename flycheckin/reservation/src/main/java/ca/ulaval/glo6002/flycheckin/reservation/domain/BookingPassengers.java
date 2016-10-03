package ca.ulaval.glo6002.flycheckin.reservation.domain;


public class BookingPassengers {
	private Booking booking;
	//private Passenger passenger;
	//private Seat seat;

	public BookingPassengers(Booking booking){  // Passenger passenger, Seat seat) {
		this.booking = booking;
	//	this.passenger = passenger;
		// this.generateHash();
		//this.seat = seat;

	}

	public String generateHash() {
		//this.passenger.generateHash();
		return "";//this.passenger.getHash();
	}

}
