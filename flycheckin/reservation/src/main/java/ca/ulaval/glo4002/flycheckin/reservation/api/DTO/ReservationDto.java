package ca.ulaval.glo4002.flycheckin.reservation.api.DTO;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

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
}
