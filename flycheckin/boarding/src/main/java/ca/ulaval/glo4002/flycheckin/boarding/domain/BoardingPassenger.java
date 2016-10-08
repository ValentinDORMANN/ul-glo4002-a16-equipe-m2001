package ca.ulaval.glo4002.flycheckin.boarding.domain;

public class BoardingPassenger {
	private String fullname;
	private String passportNumber;
	private String hash;
	private int checkinId;
	static private int nbInstance = 0;

	public BoardingPassenger(String fullname, String passportNumber, String hash) {
		this.fullname = fullname;
		this.passportNumber = passportNumber;
		this.hash = hash;
		++BoardingPassenger.nbInstance;
		this.checkinId = BoardingPassenger.nbInstance;
	}

	public String getHash() {
		return this.hash;
	}

	public int getCheckinId() {
		return this.checkinId;
	}
}