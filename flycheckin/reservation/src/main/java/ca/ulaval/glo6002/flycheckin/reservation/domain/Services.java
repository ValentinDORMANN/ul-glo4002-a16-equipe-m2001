package ca.ulaval.glo6002.flycheckin.reservation.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo6002.flycheckin.reservation.infrastructure.BookingRepository;

public class Services {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private BookingRepository bookingRepository;

	public Services(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public int createReservation(JSONObject json) throws JSONException, ParseException {
		Booking booking = JsonToBooking(json);
		BookingPassengers bookingPassengers = JsonToBookingPassenger(json);
		return this.bookingRepository.saveNewBooking(booking, bookingPassengers);
	}

	public Booking JsonToBooking(JSONObject json) throws JSONException {

		Booking booking = new Booking(json.getInt("reservation_number"), json.getString("reservation_date"),
				json.getString("reservation_confirmation"), json.getString("payment_location"));
		return booking;

	}

	public BookingPassengers JsonToBookingPassenger(JSONObject json) throws JSONException, ParseException {
		List<Passenger> passengers = JsonToPassengers(json);
		BookingPassengers bookingPassengers = new BookingPassengers(passengers, json.getString("flight_number"),
				json.getString("flight_date"));
		return bookingPassengers;
	}

	public List<Passenger> JsonToPassengers(JSONObject json) throws JSONException, ParseException {
		List<Passenger> list_passengers = new ArrayList<Passenger>();
		Date date = formatter.parse(json.getString("flight_date"));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		JSONArray json_passengers = json.getJSONArray("passengers");
		for (int i = 0; i < json_passengers.length(); i++) {
			JSONObject buffer = json_passengers.getJSONObject(i);

			String hash = buffer.getString("passport_number") + ":" + buffer.getString("flight_number") + ":"
					+ Integer.toString(cal.get(Calendar.MONTH)) + Integer.toString(cal.get(Calendar.DATE));

			Passenger passenger = new Passenger(buffer.getString("first_name"), buffer.getString("last_name"),
					buffer.getInt("age"), buffer.getString("passport_number"), buffer.getString("seat_class"), hash);
			list_passengers.add(passenger);

		}
		return list_passengers;
	}
}
