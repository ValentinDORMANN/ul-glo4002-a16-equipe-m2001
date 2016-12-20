package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;

@JsonInclude(Include.NON_NULL)
public class LuggageDto {

  private static final int GRAMME_CONVERSION = 1000;
  private static final int MM_CONVERSION = 10;
  
  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  public int linear_dimension;
  public String linear_dimension_unit;
  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  public int weight;
  public String weight_unit;
  public String type;
  public double price;

  public LuggageDto() {
  }

  public LuggageDto(Luggage luggage) {
    linear_dimension = (int) luggage.getDimensionInCm() * MM_CONVERSION;
    weight = (int) luggage.getWeightInKg() * GRAMME_CONVERSION;
    price = luggage.getPrice();
  }
}
