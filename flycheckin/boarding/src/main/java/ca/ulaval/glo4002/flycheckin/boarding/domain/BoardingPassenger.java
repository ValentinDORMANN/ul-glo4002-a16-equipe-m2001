package ca.ulaval.glo4002.flycheckin.boarding.domain;

public class BoardingPassenger {
	private String fullname;
	private String passportNumber;
	private String hash;

	public BoardingPassenger(String fullname, String passportNumber, String hash) {
		this.fullname = fullname;
		this.passportNumber = passportNumber;
		this.hash = hash;
	}

}