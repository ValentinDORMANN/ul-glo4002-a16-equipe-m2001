package ca.ulaval.glo4002.flycheckin.reservation.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ReservationDto {

  private static final String DATE_PATTERN = "yyyy-MM-dd";
  private static final String DATE_GMT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  public int reservation_number;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
  public Date reservation_date;
  public String reservation_confirmation;
  public String flight_number;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_GMT_PATTERN)
  public Date flight_date;
  public String payment_location;
  public List<PassengerDto> passengers;

  public ReservationDto() {
  }

  public ReservationDto(Reservation reservation) {
    this.reservation_number = reservation.getReservationNumber();
    this.flight_number = reservation.getFlightNumber();
    this.flight_date = reservation.getFlightDate();
    this.passengers = new ArrayList<PassengerDto>();
    for (int i = 0; i < reservation.getPassengers().size(); i++) {
      this.passengers.add(new PassengerDto(reservation.getPassengers().get(i)));
    }
  }

  public ReservationDto(Reservation reservation, String hash) {
    this.flight_number = reservation.getFlightNumber();
    this.flight_date = reservation.getFlightDate();
    this.passengers = new ArrayList<PassengerDto>();
    this.passengers.add(new PassengerDto(reservation.getPassengerFromHash(hash)));
  }
}
