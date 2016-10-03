package ca.ulaval.glo6002.flycheckin.reservation.domain;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Services {

	public Services() {

	}

	public void saveBooking(JSONObject json) {

	}

	public Booking JsonToBooking(JSONObject json) throws JSONException, ParseException {

		Booking booking = new Booking(json.getInt("reservation_number"), json.getString("reservation_date"),
				json.getString("reservation_confirmation"), json.getString("paymentLocation"));
		return booking;

	}

	public List<BookingPassengers> JsonToBookingPassengers(JSONObject json) {
		List<BookingPassengers> list_passengers = new ArrayList<BookingPassengers>();
		for (int i = 0; i < json.getJSONArray("passengers").length(); i++) {
			JSONObject buffer = json.getJSONArray("passengers").getJSONObject(i);
			BookingPassengers passenger = new BookingPassengers(buffer.getString("first_name"),
					buffer.getString("last_name"), buffer.getInt("age"), buffer.getString("passport_number"),
					buffer.getString("seat_class"), json.getString("flight_number"), json.getString("flight_date"));
			list_passengers.add(passenger);
		}
		return list_passengers;
	}

	public void getBooking() {

	}

	public void changeFlightDate() {

	}

	public void changeFlightNumber() {

	}
}
