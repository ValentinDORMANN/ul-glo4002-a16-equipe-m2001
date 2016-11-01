package ca.ulaval.glo4002.flycheckin.reservation.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ReservationDto {

  public int reservation_number;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  public Date reservation_date;
  public String reservation_confirmation;
  public String flight_number;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
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
}
