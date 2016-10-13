package ca.ulaval.glo4002.flycheckin.reservation.domain;

import org.json.JSONObject;

public class Passenger {

	private static final int AGE_CHILD = 14;

	private String firstName;
	private String lastName;
	private int age;
	private String passportNumber;
	private String seatClass;
	private String hashCode;

	public Passenger(String firstName, String lastName, int age, String passportNumber, String seatClass,
			String hashCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.passportNumber = passportNumber;
		this.seatClass = seatClass;
		this.hashCode = hashCode;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("passenger_hash", this.hashCode);
		json.put("first_name", this.firstName);
		json.put("last_name", this.lastName);
		json.put("child", (this.age < AGE_CHILD));
		json.put("passport_number", this.passportNumber);
		json.put("seat_class", this.seatClass);
		return json;
	}

	public JSONObject toJSONForBoardingResource() {
		JSONObject json = new JSONObject();
		json.put("passenger_hash", this.hashCode);
		json.put("fullname", this.firstName + " " + this.lastName);
		json.put("passport_number", this.passportNumber);
		return json;
	}

	public String getHashCode() {
		return this.hashCode;
	}
}
