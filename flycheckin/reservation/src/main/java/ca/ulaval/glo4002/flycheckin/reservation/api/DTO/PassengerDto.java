package ca.ulaval.glo4002.flycheckin.reservation.api.DTO;

import com.fasterxml.jackson.databind.annotation.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PassengerDto {

  public String first_name;
  public String last_name;
  public int age;
  public String passport_number;
  public String seat_class;
  public boolean child;
}
