package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Luggage;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class LuggageDto {
  private static final String LINEAR_DIMENSION_UNIT = "cm";

  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  public int linear_dimension;
  public String linear_dimension_unit;
  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  public int weight;
  public String weight_unit;
  public String type;
  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  public double price;

  public LuggageDto() {
  }

  public LuggageDto(Luggage luggage) {
    linear_dimension = luggage.getDimensionInCm();
    weight = luggage.getWeightInKg();
    price = luggage.getPrice();
  }
}
