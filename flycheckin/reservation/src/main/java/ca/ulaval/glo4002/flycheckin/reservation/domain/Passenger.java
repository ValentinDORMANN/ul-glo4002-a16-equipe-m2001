package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.PassengerDto;

@Entity
public class Passenger {

	private static final String EMPTY = "";
	private static final int CHILD_AGE = 21;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String passengerHash;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "age")
	private int age;
	@Column(name = "passeportNumber")
	private String passportNumber;
	@Column(name = "seatClass")
	private String seatClass;
	@Column(name = "isvip")
	private boolean isVip;

	public Passenger() {
	}

	public Passenger(PassengerDto passengerDto) throws IllegalArgumentException {
		this.firstName = passengerDto.first_name;
		this.lastName = passengerDto.last_name;
		this.age = passengerDto.age;
		this.passportNumber = passengerDto.passport_number;
		this.seatClass = passengerDto.seat_class;
		this.passengerHash = UUID.randomUUID().toString();
	}

	public boolean isValid() {
		return !(firstName.trim().equals(EMPTY) || lastName.trim().equals(EMPTY)
				|| passportNumber.trim().equals(EMPTY));
	}

	public boolean hasThisHash(String passengerHash) {
		return this.passengerHash.equals(passengerHash);
	}

	public boolean isChild() {
		return age < CHILD_AGE;
	}

	public void changeVipStatus(boolean isVip) {
		this.isVip = isVip;
	}

	public int getAge() {
		return age;
	}

	public String getPassengerHash() {
		return passengerHash;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public boolean getIsVip() {
		return isVip;
	}
}