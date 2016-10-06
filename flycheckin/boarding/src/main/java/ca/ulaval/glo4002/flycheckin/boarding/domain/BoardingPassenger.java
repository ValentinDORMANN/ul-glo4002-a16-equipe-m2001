package ca.ulaval.glo4002.flycheckin.boarding.domain;

public class BoardingPassenger {

	private String firstName;
	private String lastName;
	private int age;
	private String passportNumber;
	private String seatClass;
	private String hash;

	public BoardingPassenger(String firstName, String lastName, int age, String passportNumber, String seatClass,
			String hash) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.passportNumber = passportNumber;
		this.seatClass = seatClass;
		this.hash = hash;
	}

	public String getHash() {
		return this.hash;
	}
}
