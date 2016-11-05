package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationDto {

  private static final String DATE_GMT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  public String flight_number;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_GMT_PATTERN)
  public Date flight_date;
  public PassengerDto[] passengers;
}
