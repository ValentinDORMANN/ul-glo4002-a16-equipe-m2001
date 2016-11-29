package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import ca.ulaval.glo4002.flycheckin.boarding.exception.LuggageTypeUndefinedException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class LuggageFactory {

  private static final String CHECKED_LUGGAGE = "checked";
  private static final String CARRY_ON_LUGGAGE = "carry-on";

  public Luggage createLuggage(LuggageDto luggageDto) throws LuggageTypeUndefinedException {
    Luggage luggage = null;
    switch (luggageDto.type) {
      case CHECKED_LUGGAGE:
        luggage = new CheckedLuggage(luggageDto.linear_dimension, luggageDto.weight);
        break;
      case CARRY_ON_LUGGAGE:
        luggage = new CarryOnLuggage(luggageDto.linear_dimension, luggageDto.weight);
        break;
      default:
        throw new LuggageTypeUndefinedException();
    }
    return luggage;
  }
}
