package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class LuggageDto {

  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  public int linear_dimension;
  public String linear_dimension_unit;
  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  public int weight;
  public String weight_unit;
  public String type;
  public boolean allowed;
  public String refusation_reason;

}
