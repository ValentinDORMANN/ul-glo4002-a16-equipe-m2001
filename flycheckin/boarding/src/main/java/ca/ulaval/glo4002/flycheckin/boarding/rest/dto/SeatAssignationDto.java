package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SeatAssignationDto {

  public String passenger_hash;
  public String mode;
  public String seat;
}
