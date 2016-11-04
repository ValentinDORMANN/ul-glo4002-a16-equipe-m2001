package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationDto {

  private static final String DATE_GMT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  public String flight_number;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_GMT_PATTERN)
  public Date flight_date;
  public PassengerDto[] passengers;

  public String getFlight_number() {
    return flight_number;
  }

  public Date getFlight_date() {
    return flight_date;
  }

  public PassengerDto[] getPassengers() {
    return passengers;
  }

  public void setFlight_number(String flight_number) {
    this.flight_number = flight_number;
  }

  public void setFlight_date(Date flight_date) {
    this.flight_date = flight_date;
  }

  public void setPassengers(PassengerDto[] passengers) {
    this.passengers = passengers;
  }
}
