package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ResultLuggageCheckinDto {

  public boolean allowed;
  public String refusation_reason;
}
