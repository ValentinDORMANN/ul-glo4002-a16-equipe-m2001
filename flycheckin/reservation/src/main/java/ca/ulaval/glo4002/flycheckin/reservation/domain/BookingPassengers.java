package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javassist.NotFoundException;

public class BookingPassengers {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private String flightNumber;
	private Date flightDate;
	private List<Passenger> passengers;

	public BookingPassengers(List<Passenger> passengers, String flightNumber, String flightDate) throws ParseException {

		this.flightNumber = flightNumber;
		this.flightDate = formatter.parse(flightDate);
		this.passengers = passengers;
	}

	public String getFlight() {
		return flightNumber;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		JSONArray json_passengers = new JSONArray();
		for (Passenger passenger : this.passengers) {
			json_passengers.put(passenger.toJSON());
		}
		json.put("passengers", json_passengers);
		json.put("flight_number", this.flightNumber);
		json.put("flight_date", this.flightDate);
		return json;
	}

	public int getPassengerInfosIndex(String hashCode) {
		int index = -1;
		List<Passenger> passengers = this.passengers;
		for (int i = 0; i < passengers.size(); i++) {
			if (passengers.get(i).getHashCode() == hashCode)
				index = i;
		}
		return index;
	}

	public Passenger getPassengerInfos(String hashCode) throws NotFoundException {
		Passenger passenger;
		int index = getPassengerInfosIndex(hashCode);
		passenger = this.passengers.get(index);
		if (isNull(passenger))
			throw new NotFoundException("PASSENGER NOT FOUND");
		return passenger;
	}

	private boolean isNull(Object obj) {
		return obj == null;
	}

}
