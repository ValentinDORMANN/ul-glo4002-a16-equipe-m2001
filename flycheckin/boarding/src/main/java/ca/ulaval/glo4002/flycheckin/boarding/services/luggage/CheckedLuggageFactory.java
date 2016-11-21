package ca.ulaval.glo4002.flycheckin.boarding.services.luggage;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CarryOnLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CheckedLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CheckedLuggageFactory extends LuggageFactory {

  public final static String CHECKED_LUGGAGE = "checked";
  public final static String CARRY_ON_LUGGAGE = "carry-on";

  @Override
  public Luggage createLuggage(LuggageDto luggageDto) {
    Luggage luggage = null;
    switch (luggageDto.type) {
      case CHECKED_LUGGAGE:
        luggage = new CheckedLuggage(luggageDto.linear_dimension, luggageDto.linear_dimension_unit, luggageDto.weight,
            luggageDto.weight_unit);
        break;
      case CARRY_ON_LUGGAGE:
        luggage = new CarryOnLuggage(luggageDto.linear_dimension, luggageDto.linear_dimension_unit, luggageDto.weight,
            luggageDto.weight_unit);
        break;
    }
    return luggage;
  }
}
