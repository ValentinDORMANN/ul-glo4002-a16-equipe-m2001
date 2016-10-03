package ca.ulaval.glo6002.flycheckin.reservation.api;


import java.sql.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;


@Path("/events/reservation-created")
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {
	private JSONObject json;
	
	public BookingResource(JSONObject json){
		this.json = json;
		
	}

	@POST
    public void createBooking(String message) {
    	
    }

	public boolean validateReservationDate(String reservationDate) {
		//String date =json.getString("reservation_date");
		if (reservationDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")){
			return true;
		}
		return false;
		
	}

	public String extractFlightDate(String flight_date) throws RuntimeException {
		String date = flight_date.substring(0, 9);
		if(validateReservationDate(date)){
			return flight_date.substring(0, 9);
		}
		else{
			throw new RuntimeException();
		}
	}
}
