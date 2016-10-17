package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.reservation.infrastructure.BookingRepository;
import javassist.NotFoundException;

public class Services {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private BookingRepository bookingRepository;

	public Services() {
		this.bookingRepository = new BookingRepository();
	}

	/* Set Reservation */

	public int createReservation(JSONObject json) {
		try {
			Booking booking = JsonToBooking(json);
			BookingPassengers bookingPassengers = JsonToBookingPassenger(json);
			return this.bookingRepository.saveNewBooking(booking, bookingPassengers);
		} catch (Exception e) {
			return 0;
		}
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

			String hash = buffer.getString("passport_number") + ":" + json.getInt("reservation_number");

			Passenger passenger = new Passenger(buffer.getString("first_name"), buffer.getString("last_name"),
					buffer.getInt("age"), buffer.getString("passport_number"), buffer.getString("seat_class"), hash);
			list_passengers.add(passenger);

		}
		return list_passengers;
	}

	/* Get Reservation */

	public JSONObject getReservation(int numberReservation) throws NotFoundException {
		JSONObject json = new JSONObject();
		BookingPassengers bookingPassengers = this.bookingRepository.getBookingInfos(numberReservation);
		json = bookingPassengers.toJSON();
		json.put("reservation_number", numberReservation);
		return json;
	}

	/* Get PassengerInfo for Boarding */

	public JSONObject getPassengerInfoFromHash(String passengerHash) throws NotFoundException {
		int bookingNumber = Integer.parseInt(passengerHash.split(":")[1]);
		BookingPassengers bookingPassengers = this.bookingRepository.getBookingInfos(bookingNumber);
		Passenger passenger = bookingPassengers.getPassengerInfos(passengerHash);
		JSONObject json = passenger.toJSONForBoardingResource();
		json.put("date", bookingPassengers.flightDate());
		return json;
	}

	/* Post Seat Assignation */
	public int setSeatAssignation(String passengerHash, String mode)
			throws NotFoundException, ClientProtocolException, IOException {
		int bookingNumber = Integer.parseInt(passengerHash.split(":")[1]);
		BookingPassengers bookingPassengers = this.bookingRepository.getBookingInfos(bookingNumber);
		// see if passenger has id
		// bookingPassengers.getPassengerInfos(passengerHash).getSeatAssignationId();
		// throw exception if has seat
		String modelId = getModelIdFromFlightNumber(bookingPassengers.getFlight());
		// Call service for list
		JSONObject planeModel = getPlaneModel(modelId);
		// send to seatAssignation list and hash_passenger
		return bookingNumber;
	}

	public String getModelIdFromFlightNumber(String flightNumber) {
		switch (flightNumber) {
		case "QK-918":
			return "dash-8";
		case "NK-750":
			return "a320";
		case "QK-432":
			return "boeing-777-300";
		}
		return "dash-8";
	}

	public JSONObject getPlaneModel(String model_id) throws ClientProtocolException, IOException {
		String url = "http://glo3000.ift.ulaval.ca/plane-blueprint/planes/" + model_id + "/seats.json";
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = httpClient.execute(request);
		HttpEntity entityAnswer = response.getEntity();
		String response_string = EntityUtils.toString(entityAnswer, "UTF-8");
		JSONObject planeJson = new JSONObject(response_string);
		return planeJson;
	}

}
