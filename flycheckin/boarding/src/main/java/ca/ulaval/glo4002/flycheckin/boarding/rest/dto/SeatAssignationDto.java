package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SeatAssignationDto {

  public String passenger_hash;
  public String mode;
  public String seat;
}
