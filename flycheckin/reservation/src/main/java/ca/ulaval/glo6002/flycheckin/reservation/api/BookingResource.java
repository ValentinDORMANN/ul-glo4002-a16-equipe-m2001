package ca.ulaval.glo6002.flycheckin.reservation.api;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/events/reservation-created")
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {

	@POST
    public void createBooking(String message) {
    	
    }
}
