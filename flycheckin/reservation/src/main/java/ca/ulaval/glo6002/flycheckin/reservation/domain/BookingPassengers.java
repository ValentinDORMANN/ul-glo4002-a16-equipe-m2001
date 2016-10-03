package ca.ulaval.glo6002.flycheckin.reservation.domain;

public class BookingPassengers {

	private String first_name;
	private String last_name;
	private int age;
	private String passport_number;
	private String seat_class;
	private String flight_number;

	public BookingPassengers(String first_name, String last_name, int age, String passport_number, String seat_class,
			String flight_number) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.passport_number = passport_number;
		this.seat_class = seat_class;
		this.flight_number = flight_number;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public int getAge() {
		return age;
	}

	public String getPassport_number() {
		return passport_number;
	}

	public String getSeat_class() {
		return seat_class;
	}

	public String getFlight_number() {
		return this.flight_number;
	}

	public String generateHash() {
		return this.passport_number + ":" + this.flight_number;
	}

}
