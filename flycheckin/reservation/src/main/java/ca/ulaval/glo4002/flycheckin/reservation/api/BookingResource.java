package ca.ulaval.glo4002.flycheckin.reservation.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Services;

@Path("/events/reservation-created")
public class BookingResource {
	private Services service;
	private JSONObject jsonRequest;

	@POST
	public Response createBooking(@Context UriInfo uriInfo, String bookingRequest)
			throws JSONException, ParseException {
		jsonRequest = new JSONObject(bookingRequest);
		if (validateJson(jsonRequest)) {
			int reservationNumber = 0;
			service = new Services();
			reservationNumber = service.createReservation(jsonRequest);
			if (reservationNumber != 0) {
				return Response.status(201)
						.entity(uriInfo.getBaseUri().toString() + "reservations/" + reservationNumber).build();
			} else {
				return Response.status(400).build();
			}
		} else {
			return Response.status(400).build();
		}
	}

	private boolean validateFlightDate(JSONObject bookingRequest) {
		boolean validateDate = true;
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			java.util.Date date = dateformat.parse(bookingRequest.getString("flight_date"));
		} catch (Exception e) {
			validateDate = false;
		}
		return validateDate;
	}

	private boolean validateReservationDate(JSONObject bookingRequest) {
		return ((String) bookingRequest.get("reservation_date")).matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
	}

	private boolean validateBookingNumber(JSONObject bookingRequest) {
		return (bookingRequest.getInt("reservation_number") > 0);
	}

	private boolean validateFlightNumber(JSONObject bookingRequest) {
		return !bookingRequest.getString("flight_number").isEmpty();
	}

	public boolean validateJson(JSONObject bookingRequest) {
		return validateFlightDate(bookingRequest) && validateReservationDate(bookingRequest)
				&& validateBookingNumber(bookingRequest) && validateFlightNumber(bookingRequest);
	}
}