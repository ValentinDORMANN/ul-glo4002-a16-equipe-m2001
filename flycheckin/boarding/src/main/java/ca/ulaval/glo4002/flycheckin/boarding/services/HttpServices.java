package ca.ulaval.glo4002.flycheckin.boarding.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class HttpServices {

  private static final String LOCALHOST = "http://localhost:";
  private static final String RESERVATION_HASH = "/reservations/hash/";
  private static final int RESERVATION_SERVER = Integer.valueOf(System.getProperty("reservation.port"));

  public ReservationDto getReservationDtoFromReservation(String passenger_hash) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String url = LOCALHOST + RESERVATION_SERVER + RESERVATION_HASH + passenger_hash;
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url);
    Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
    ReservationDto reservationDto = response.readEntity(ReservationDto.class);
    return reservationDto;
  }
}
