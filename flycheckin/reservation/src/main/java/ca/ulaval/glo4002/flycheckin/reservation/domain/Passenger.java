package ca.ulaval.glo4002.flycheckin.reservation.domain;

public class Passenger {

	private String firstName;
	private String lastName;
	private int age;
	private String passportNumber;
	private String seatClass;
	private String hash;

	public Passenger(String firstName, String lastName, int age, String passportNumber, String seatClass, String hash) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.passportNumber = passportNumber;
		this.seatClass = seatClass;
		this.hash = hash;
	}
}
