package ca.ulaval.glo4002.flycheckin.boarding.domain;

public class BoardingPassenger {

	private String firstName;
	private String lastName;
	private String passportNumber;
	private String hash;

	public BoardingPassenger(String firstName, String lastName, String passportNumber, String hash) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.passportNumber = passportNumber;
		this.hash = hash;
	}

	public String getHash() {
		return this.hash;
	}
}
