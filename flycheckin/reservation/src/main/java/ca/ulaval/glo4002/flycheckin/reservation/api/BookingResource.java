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
	private boolean validatedJson = true;

	@POST
	public Response createBooking(@Context UriInfo uriInfo, String bookingRequest)
			throws JSONException, ParseException {
		jsonRequest = new JSONObject(bookingRequest);
		int reservationNumber = 0;
		service = new Services();
		reservationNumber = service.createReservation(jsonRequest);
		if (reservationNumber != 0) {
			return Response.status(201).entity(uriInfo.getBaseUri().toString() + "reservations/" + reservationNumber)
					.build();
		} else {
			return Response.status(400).build();
		}
	}

	private boolean validateReservationDate(String reservationDate) {
		if (!reservationDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
			validatedJson = false;
		}
		return validatedJson;

	}

	private boolean validateflightDate(String flightDate) {
		boolean validateDate = true;
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ");
			@SuppressWarnings("unused")
			java.util.Date dateformatter = date.parse(flightDate);
		} catch (Exception e) {
			validateDate = false;
		}
		return validateDate;

	}

	private boolean validateBookingNumber(String bookingNumber) {
		if (!bookingNumber.matches("^[0-9]+$")) {
			validatedJson = false;
		}
		return validatedJson;

	}

	private boolean validateFirstName(String firstname) {
		if (!firstname.matches("^[a-zA-Z]+$")) {
			validatedJson = false;
		}
		return validatedJson;

	}

	private boolean validateLastName(String name) {
		if (!name.matches("^[a-zA-Z]+$")) {
			validatedJson = false;
		}
		return validatedJson;

	}

	private boolean validatePassport(String passport) {
		if (passport.isEmpty()) {
			validatedJson = false;
		}
		return validatedJson;

	}

	private boolean validateReservationJson() {
		return validatedJson;

	}
}